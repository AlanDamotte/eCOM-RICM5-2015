package com.ecom.forms;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import com.ecom.dao.CategoryDaoRemote;
import com.ecom.dao.CustomerDaoRemote;
import com.ecom.entities.Customer;

public final class ConnectionForm {

	private static final String FIELD_EMAIL = "email";
	private static final String FIELD_PASS = "password";

	CustomerDaoRemote customerDao;
	
	InitialContext ctx;
	{
		try {
			ctx = new InitialContext();
			customerDao = (CustomerDaoRemote) ctx.lookup("CustomerDao");
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}

	private String result;
	private Map<String, String> errors = new HashMap<String, String>();

	public String getResult() {
		return result;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public Customer connectCustomer(HttpServletRequest request) {
		/* Récupération des champs du formulaire */
		String email = getFieldValue(request, FIELD_EMAIL);
		String password = getFieldValue(request, FIELD_PASS);

		Customer customer = new Customer();

		/* Validation du champ email. */
		try {
			emailValidation(email);
		} catch (Exception e) {
			setError(FIELD_EMAIL, e.getMessage());
		}

		/* Validation du champ mot de passe. */
		try {
			passwordValidation(email, password);
		} catch (Exception e) {
			setError(FIELD_PASS, e.getMessage());
		}

		/* Initialisation du résultat global de la validation. */
		if (errors.isEmpty()) {
			result = "Succès de la connexion.";
		} else {
			result = "Échec de la connexion.";
		}
		try{
	        customer = customerDao.checkPassword(email,password);	
		}catch(Exception e){
			//TODO
		}


		return customer;
	}

	/**
	 * Valide l'adresse email saisie.
	 */
	private void emailValidation(String email) throws Exception {
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Merci de saisir une adresse mail valide.");
		}
	}

	/**
     * Valide le mot de passe saisi.
     */
    private void passwordValidation( String email, String password ) throws Exception {
        if ( password != null ) {
            if ( password.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
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