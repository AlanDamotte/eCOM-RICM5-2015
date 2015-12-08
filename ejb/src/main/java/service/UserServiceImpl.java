package service;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJBContext;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bank.Account;
import bank.Bank;
import beans.MailSenderBean;
import beans.ShoppingCart;
import dao.OrderHistoryDaoRemote;
import dao.ProductDaoRemote;
import entities.Customer;
import entities.Order;
import entities.OrderHistory;

@Stateless
public class UserServiceImpl {

	public static final String CART_PRODUCTS_SESSION = "cart_products";
	public static final String ATT_SESSION_CUSTOMER = "customerSession";
	public static final String ATT_PAYMENT_STATUS = "paymentStatus";

	public static final String FIELD_CARD_NUMBER = "cardNumber";
	public static final String FIELD_CARD_SECURITYCODE = "securityCode";


	@Resource
	static SessionContext context;
	
	@Asynchronous
	public static void sendUserMail(Customer customer, Order order, MailSenderBean mailSender, long orderId) throws ServletException, IOException{
		try{
			System.out.println("Envoi de mail");
			String fromEmail = "ecom.franois@gmail.com";
			String username = "ecom.franois";
			String password = "ecom1990";

			String toEmail = customer.getEmail();
			System.out.println(toEmail);
			// TODO : modifier le sujet et le corps de message
			String subject = "Confirmation de commande numéro " + orderId + " du " + order.getDate().toString();
			String message = "Bonjour M/Mme " + customer.getLastname() + " " + customer.getFirstname() + ":\n"
					+ "Nous vous confirmation votre commande numéro " + orderId + " du " + order.getDate().toString() + " d'un montant total de " + order.getAmount().toString() + " euros";

			// Call to mail sender bean
			mailSender.sendEmail(fromEmail, username, password, toEmail, subject, message);

			System.out.println("Mail envoyé");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static long persistOrder(HttpServletRequest request, ProductDaoRemote productDao,
			OrderHistoryDaoRemote orderHistory, Customer customer, Order order, ShoppingCart shoppingCart)
					throws Exception {
			// Mise à jour des quantités de produits restantes
			productDao.updateProductQuantity(shoppingCart);

			shoppingCart.clear();
			request.getSession().setAttribute(CART_PRODUCTS_SESSION, shoppingCart);

			OrderHistory orderH = new OrderHistory();
			orderH.setCustomer(customer);
			orderH.setOrder(order);
			long orderId = orderHistory.create(orderH);
			System.out.println("Payment finished");
			return orderId;		
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public synchronized static void proceedPayment(HttpServletRequest request, Customer customer, Order order,
			ShoppingCart shoppingCart, ProductDaoRemote productDao, MailSenderBean mailSender) throws Exception {
		HttpSession session = request.getSession();

		Bank bank = new Bank();
		//Map<Long, Account> listAccount = bank.getAccount();
		//System.out.println(listAccount.get(123456).getBalance());
		try {
			System.out.println("Preparing payment");

			Long cardNumber = Long.parseLong((String) session.getAttribute(FIELD_CARD_NUMBER));
			int securityCode = Integer.parseInt((String) session.getAttribute(FIELD_CARD_SECURITYCODE));
			
			boolean validatePayment = bank.paymentProcess(cardNumber, shoppingCart.getTotal(), securityCode);
			if (!validatePayment) {
				throw new Exception();
			}else{
				// Mise à jour des quantités de produits restantes
				productDao.updateProductQuantity(shoppingCart);
			}
		} catch (Exception up) {
			//System.out.println(listAccount.get(123456).getBalance());
			System.out.println("Exception catched : " + up.getMessage());
			((EJBContext) context).setRollbackOnly();
			throw up;
		}
	}
}
