package forms;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import dao.CustomerDaoRemote;
import dao.OrderDaoRemote;
import entities.Customer;
import entities.Order;
import dao.DAOException;

public final class OrderCreationForm {
	private static final String FIELD_CLIENT_CHOICE = "newCustomerChoice";
	private static final String FIELD_CLIENT_LIST = "customersList";
	public static final String FIELD_DATE = "dateOrder";
	public static final String FIELD_AMOUNT = "amountOrder";
	public static final String FIELD_PAYMENT_MODE = "paymentModeOrder";
	public static final String FIELD_PAYMENT_STATUS = "paymentStatusOrder";
	public static final String FIELD_DELIVERY_MODE = "deliveryModeOrder";
	public static final String FIELD_DELIVERY_STATUS = "deliveryStatusOrder";

	private static final String OLD_CLIENT = "oldCustomer";
	private static final String CLIENTS_SESSION = "customers";

	private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

	private String result;
	private Map<String, String> errors = new HashMap<String, String>();

	private CustomerDaoRemote customerDao;

	private OrderDaoRemote orderDao;
	
//	InitialContext ctx;
//	{
//		try {
//			ctx = new InitialContext();
//			customerDao = (CustomerDaoRemote) ctx.lookup("CustomerDao");
//			orderDao = (OrderDaoRemote) ctx.lookup("OrderDao");
//		} catch (NamingException ex) {
//			ex.printStackTrace();
//		}
//	}

	public OrderCreationForm( CustomerDaoRemote customerDao, OrderDaoRemote orderDao ) {
        this.customerDao = customerDao;
        this.orderDao = orderDao;
    }

	public Map<String, String> getErrors() {
		return errors;
	}

	public String getResult() {
		return result;
	}

	public Order createOrder(HttpServletRequest request) {
		
		Customer customer;
		/*
		 * Si l'utilisateur choisit un customer déjà existant, pas de validation à
		 * effectuer
		 */
		String newCustomerChoice = getFieldValue(request, FIELD_CLIENT_CHOICE);
		if (OLD_CLIENT.equals(newCustomerChoice)) {
			/* Récupération du nom du customer choisi */
			String customerOldName = getFieldValue(request, FIELD_CLIENT_LIST);
			/* Récupération de l'objet customer correspondant dans la session */
			HttpSession session = request.getSession();
			customer = ((Map<Long, Customer>) session.getAttribute(CLIENTS_SESSION)).get(customerOldName);
		} else {
			/*
			 * Sinon on garde l'ancien mode, pour la validation des champs.
			 * 
			 * L'objet métier pour valider la création d'un customer existe déjà,
			 * il est donc déconseillé de dupliquer ici son contenu ! À la
			 * place, il suffit de passer la requête courante à l'objet métier
			 * existant et de récupérer l'objet Customer créé.
			 */
			CustomerCreationForm customerForm = new CustomerCreationForm(customerDao);
			customer = customerForm.createCustomer(request);

			/*
			 * Et très important, il ne faut pas oublier de récupérer le contenu
			 * de la map d'erreur créée par l'objet métier CustomerCreationForm
			 * dans la map d'Errors courante, actuellement vide.
			 */
			errors = customerForm.getErrors();
		}

		/*
		 * Ensuite, il suffit de procéder normalement avec le reste des champs
		 * spécifiques à une order.
		 */

		/*
		 * Récupération et conversion de la date en String selon le format
		 * choisi.
		 */
		DateTime dt = new DateTime();

		String amount = getFieldValue(request, FIELD_AMOUNT);
		String paymentStatus = getFieldValue(request, FIELD_PAYMENT_STATUS);
		String deliveryStatus = getFieldValue(request, FIELD_DELIVERY_STATUS);
		
		Order order = new Order();

		 try {
	            processCustomer( customer, order );

	            order.setDate( dt );

	            processAmount( amount, order );
	            processPaymentStatus( paymentStatus, order );
	            processDeliveryStatus( deliveryStatus, order );

	            if ( errors.isEmpty() ) {
	                orderDao.create( order );
	                result = "Succès de la création de la commande.";
	            } else {
	                result = "Échec de la création de la commande.";
	            }
	        } catch ( DAOException e ) {
	            setError( "imprévu", "Erreur imprévue lors de la création." );
	            result = "Échec de la création de la commande : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	            e.printStackTrace();
	        }

	        return order;
	    }

	    private void processCustomer( Customer customer, Order order ) {
	        if ( customer == null ) {
	            setError( FIELD_CLIENT_CHOICE, "Customer inconnu, merci d'utiliser le formulaire prévu à cet effet." );
	        }
	        order.setCustomer( customer );
	    }

	    private void processAmount( String amount, Order order ) {
	        double valueAmount = -1;
	        try {
	            valueAmount = amountValidation( amount );
	        } catch ( FormValidationException e ) {
	            setError( FIELD_AMOUNT, e.getMessage() );
	        }
	        order.setAmount( valueAmount );
	    }

	    private void processPaymentStatus( String paymentStatus, Order order ) {
	        try {
	            paymentStatusValidation( paymentStatus );
	        } catch ( FormValidationException e ) {
	            setError( FIELD_PAYMENT_STATUS, e.getMessage() );
	        }
	        order.setPaymentStatus( paymentStatus );
	    }

	    private void processDeliveryStatus( String deliveryStatus, Order order ) {
	        try {
	            deliveryStatusValidation( deliveryStatus );
	        } catch ( FormValidationException e ) {
	            setError( FIELD_DELIVERY_STATUS, e.getMessage() );
	        }
	        order.setDeliveryStatus( deliveryStatus );
	    }

	private double amountValidation(String amount) throws FormValidationException {
		double temp;
		if (amount != null) {
			try {
				temp = Double.parseDouble(amount);
				if (temp < 0) {
					throw new FormValidationException("Le amount doit être un nombre positif.");
				}
			} catch (NumberFormatException e) {
				temp = -1;
				throw new FormValidationException("Le amount doit être un nombre.");
			}
		} else {
			temp = -1;
			throw new FormValidationException("Merci d'entrer un amount.");
		}
		return temp;
	}

	private void paymentModeValidation(String paymentMode) throws FormValidationException {
		if (paymentMode != null) {
			if (paymentMode.length() < 2) {
				throw new FormValidationException("Le mode de paiement doit contenir au moins 2 caractères.");
			}
		} else {
			throw new FormValidationException("Merci d'entrer un mode de paiement.");
		}
	}

	private void paymentStatusValidation(String paymentStatus) throws FormValidationException {
		if (paymentStatus != null && paymentStatus.length() < 2) {
			throw new FormValidationException("Le statut de paiement doit contenir au moins 2 caractères.");
		}
	}

	private void deliveryModeValidation(String deliveryMode) throws FormValidationException {
		if (deliveryMode != null) {
			if (deliveryMode.length() < 2) {
				throw new FormValidationException("Le mode de livraison doit contenir au moins 2 caractères.");
			}
		} else {
			throw new FormValidationException("Merci d'entrer un mode de livraison.");
		}
	}

	private void deliveryStatusValidation(String deliveryStatus) throws FormValidationException {
		if (deliveryStatus != null && deliveryStatus.length() < 2) {
			throw new FormValidationException("Le statut de livraison doit contenir au moins 2 caractères.");
		}
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des Errors.
	 */
	private void setError(String champ, String message) {
		errors.put(champ, message);
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getFieldValue(HttpServletRequest request, String fieldName) {
		String value = request.getParameter(fieldName);
		if (value == null || value.trim().length() == 0) {
			return null;
		} else {
			return value;
		}
	}
}