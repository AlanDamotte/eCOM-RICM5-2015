package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import dao.CustomerDaoRemote;
import dao.DAOException;
import entities.Customer;

import eu.medsea.mimeutil.*;

public class CustomerCreationForm {

	private static final String ENCRYPTION = "SHA-256";

	public static final String FIELD_LASTNAME = "lastnameCustomer";
	public static final String FIELD_FIRSTNAME = "firstnameCustomer";
	public static final String FIELD_ADDRESS = "addressCustomer";
	public static final String FIELD_POSTCODE = "postCodeCustomer";
	public static final String FIELD_CITY = "cityCustomer";
	public static final String FIELD_PHONENUMBER = "phonenumberCustomer";
	public static final String FIELD_EMAIL = "emailCustomer";
	private static final String FIELD_PASS = "password";
	private static final String FIELD_CONF = "confirmation";

	private String result;

	private CustomerDaoRemote customerDao;

	public CustomerCreationForm(CustomerDaoRemote customerDao) {

		this.customerDao = customerDao;

	}

	private Map<String, String> errors = new HashMap<String, String>();

	public Map<String, String> getErrors() {
		return errors;
	}

	public String getResult() {
		return result;
	}

	public Customer createCustomer(HttpServletRequest request) {
		String lastname = getFieldValue(request, FIELD_LASTNAME);
		String firstname = getFieldValue(request, FIELD_FIRSTNAME);
		String address = getFieldValue(request, FIELD_ADDRESS);
		String postcode = getFieldValue(request, FIELD_POSTCODE);
		String city = getFieldValue(request, FIELD_CITY);
		String password = getFieldValue(request, FIELD_PASS);
		String confirmation = getFieldValue(request, FIELD_CONF);
		String phonenumber = getFieldValue(request, FIELD_PHONENUMBER);
		String email = getFieldValue(request, FIELD_EMAIL);

		Customer customer = new Customer();

		processLastname(lastname, customer);
		processFirstname(firstname, customer);
		processAddress(address, customer);
		processPostCode(postcode, customer);
		processCity(city, customer);
		processPhonenumber(phonenumber, customer);
		processPassword(password, confirmation, customer);
		processEmail(email, customer);

		try {
			if (errors.isEmpty()) {
				customerDao.create(customer);
				result = "Succès de la création du customer.";
			} else {
				result = "Échec de la création du customer.";
			}
		} catch (DAOException e) {
			setError("imprévu", "Erreur imprévue lors de la création.");
			result = "Échec de la création du customer : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}

		return customer;
	}

	private void processLastname(String lastname, Customer customer) {
		try {
			lastnameValidation(lastname);
		} catch (FormValidationException e) {
			setError(FIELD_LASTNAME, e.getMessage());
		}
		customer.setLastname(lastname);
	}

	private void processFirstname(String firstname, Customer customer) {
		try {
			firstnameValidation(firstname);
		} catch (FormValidationException e) {
			setError(FIELD_FIRSTNAME, e.getMessage());
		}
		customer.setFirstname(firstname);
	}

	private void processAddress(String address, Customer customer) {
		try {
			addressValidation(address);
		} catch (FormValidationException e) {
			setError(FIELD_ADDRESS, e.getMessage());
		}
		customer.setAddress(address);
	}

	private void processPostCode(String postcode, Customer customer) {
		try {
			postCodeValidation(postcode);
		} catch (FormValidationException e) {
			setError(FIELD_POSTCODE, e.getMessage());
		}
		customer.setPostCode(postcode);
	}

	private void processCity(String city, Customer customer) {
		try {
			cityValidation(city);
		} catch (FormValidationException e) {
			setError(FIELD_CITY, e.getMessage());
		}
		customer.setCity(city);
	}

	private void processPhonenumber(String phonenumber, Customer customer) {
		try {
			phonenumberValidation(phonenumber);
		} catch (FormValidationException e) {
			setError(FIELD_PHONENUMBER, e.getMessage());
		}
		customer.setPhonenumber(phonenumber);
	}

	/*
	 * Appel à la validation des mots de passe reçus, chiffrement du mot de
	 * passe et initialisation de la propriété motDePasse du bean
	 */
	private void processPassword(String password, String confirmation, Customer customer) {
		try {
			passwordValidation(password, confirmation);
		} catch (FormValidationException e) {
			setError(FIELD_PASS, e.getMessage());
			setError(FIELD_CONF, null);
		}

		/*
		 * Utilisation de la bibliothèque Jasypt pour chiffrer le mot de passe
		 * efficacement.
		 * 
		 * L'algorithme SHA-256 est ici utilisé, avec par défaut un salage
		 * aléatoire et un grand nombre d'itérations de la fonction de hashage.
		 * 
		 * La String retournée est de longueur 56 et contient le hash en Base64.
		 */
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm(ENCRYPTION);
		passwordEncryptor.setPlainDigest(false);
		String encryptedPassword = passwordEncryptor.encryptPassword(password);

		customer.setPassword(encryptedPassword);
	}

	private void processEmail(String email, Customer customer) {
		try {
			emailValidation(email);
		} catch (FormValidationException e) {
			setError(FIELD_EMAIL, e.getMessage());
		}
		customer.setEmail(email);
	}

	private void lastnameValidation(String lastname) throws FormValidationException {
		if (lastname != null) {
			if (lastname.length() < 2) {
				throw new FormValidationException("Le nom d'utilisateur doit contenir au moins 2 caractères.");
			}
		} else {
			throw new FormValidationException("Merci d'entrer un nom d'utilisateur.");
		}
	}

	private void firstnameValidation(String firstname) throws FormValidationException {
		if (firstname != null && firstname.length() < 2) {
			throw new FormValidationException("Le prénom d'utilisateur doit contenir au moins 2 caractères.");
		}
	}

	private void addressValidation(String address) throws FormValidationException {
		if (address == null) {
			throw new FormValidationException("Merci d'entrer une adresse de livraison.");
		}
	}

	private void postCodeValidation(String postcode) throws FormValidationException {
		if (postcode != null) {
			if (postcode.length() != 5) {
				throw new FormValidationException("Le code postal doit contenir 5 caractères");
			}
		} else {
			throw new FormValidationException("Merci d'entrer un code postal");
		}
	}

	private void cityValidation(String city) throws FormValidationException {
		if (city == null) {
			throw new FormValidationException("Merci d'entrer une ville");
		}
	}

	private void phonenumberValidation(String phonenumber) throws FormValidationException {
		if (phonenumber != null) {
			if (!phonenumber.matches("^\\d+$")) {
				throw new FormValidationException("Le numéro de téléphone doit uniquement contenir des chiffres.");
			} else if (phonenumber.length() < 4) {
				throw new FormValidationException("Le numéro de téléphone doit contenir au moins 4 chiffres.");
			}
		} else {
			throw new FormValidationException("Merci d'entrer un numéro de téléphone.");
		}
	}

	private void emailValidation(String email) throws FormValidationException {
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new FormValidationException("Merci de saisir une adresse mail valide.");
		}
		if (email == null) {
			throw new FormValidationException("Merci d'entrer un email");
		}
		if (customerDao.emailExists(email)) {
			throw new FormValidationException("Un compte client existe déjà avec cet email");
		}
	}

	/* Validation des mots de passe */
	private void passwordValidation(String password, String confirmation) throws FormValidationException {
		if (password != null && confirmation != null) {
			if (!password.equals(confirmation)) {
				throw new FormValidationException(
						"Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
			} else if (password.length() < 3) {
				throw new FormValidationException("Les mots de passe doivent contenir au moins 3 caractères.");
			}
		} else {
			throw new FormValidationException("Merci de saisir et confirmer votre mot de passe.");
		}
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
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
