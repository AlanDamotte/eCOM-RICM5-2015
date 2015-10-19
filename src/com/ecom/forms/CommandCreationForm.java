package com.ecom.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.ecom.beans.Client;
import com.ecom.beans.Command;

public final class CommandCreationForm {
	private static final String FIELD_CLIENT_CHOICE = "newClientChoice";
	private static final String FIELD_CLIENT_LIST = "clientsList";
	public static final String FIELD_DATE = "dateCommand";
	public static final String FIELD_AMOUNT = "amountCommand";
	public static final String FIELD_PAYMENT_MODE = "paymentModeCommand";
	public static final String FIELD_PAYMENT_STATUS = "paymentStatusCommand";
	public static final String FIELD_DELIVERY_MODE = "deliveryModeCommand";
	public static final String FIELD_DELIVERY_STATUS = "deliveryStatusCommand";

	private static final String OLD_CLIENT = "oldClient";
	private static final String CLIENTS_SESSION = "clients";

	private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

	private String result;
	private Map<String, String> errors = new HashMap<String, String>();

	public Map<String, String> getErrors() {
		return errors;
	}

	public String getResult() {
		return result;
	}

	public Command createCommand(HttpServletRequest request, String path) {
		Client client;
		/*
		 * Si l'utilisateur choisit un client déjà existant, pas de validation à
		 * effectuer
		 */
		String newClientChoice = getFieldValue(request, FIELD_CLIENT_CHOICE);
		if (OLD_CLIENT.equals(newClientChoice)) {
			/* Récupération du nom du client choisi */
			String clientOldName = getFieldValue(request, FIELD_CLIENT_LIST);
			/* Récupération de l'objet client correspondant dans la session */
			HttpSession session = request.getSession();
			client = ((Map<String, Client>) session.getAttribute(CLIENTS_SESSION)).get(clientOldName);
		} else {
			/*
			 * Sinon on garde l'ancien mode, pour la validation des champs.
			 * 
			 * L'objet métier pour valider la création d'un client existe déjà,
			 * il est donc déconseillé de dupliquer ici son contenu ! À la
			 * place, il suffit de passer la requête courante à l'objet métier
			 * existant et de récupérer l'objet Client créé.
			 */
			ClientCreationForm clientForm = new ClientCreationForm();
			client = clientForm.createClient(request, path);

			/*
			 * Et très important, il ne faut pas oublier de récupérer le contenu
			 * de la map d'erreur créée par l'objet métier ClientCreationForm
			 * dans la map d'Errors courante, actuellement vide.
			 */
			errors = clientForm.getErrors();
		}

		/*
		 * Ensuite, il suffit de procéder normalement avec le reste des champs
		 * spécifiques à une command.
		 */

		/*
		 * Récupération et conversion de la date en String selon le format
		 * choisi.
		 */
		DateTime dt = new DateTime();
		DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);
		String date = dt.toString(formatter);

		String amount = getFieldValue(request, FIELD_AMOUNT);
		String paymentMode = getFieldValue(request, FIELD_PAYMENT_MODE);
		String paymentStatus = getFieldValue(request, FIELD_PAYMENT_STATUS);
		String deliveryMode = getFieldValue(request, FIELD_DELIVERY_MODE);
		String deliveryStatus = getFieldValue(request, FIELD_DELIVERY_STATUS);

		Command command = new Command();

		command.setClient(client);

		double valeurMontant = -1;
		try {
			valeurMontant = amountValidation(amount);
		} catch (Exception e) {
			setError(FIELD_AMOUNT, e.getMessage());
		}
		command.setAmount(valeurMontant);

		command.setDate(date);

		try {
			paymentModeValidation(paymentMode);
		} catch (Exception e) {
			setError(FIELD_PAYMENT_MODE, e.getMessage());
		}
		command.setPaymentMode(paymentMode);

		try {
			paymentStatusValidation(paymentStatus);
		} catch (Exception e) {
			setError(FIELD_PAYMENT_STATUS, e.getMessage());
		}
		command.setPaymentStatus(paymentStatus);

		try {
			deliveryModeValidation(deliveryMode);
		} catch (Exception e) {
			setError(FIELD_DELIVERY_MODE, e.getMessage());
		}
		command.setDeliveryMode(deliveryMode);

		try {
			deliveryStatusValidation(deliveryStatus);
		} catch (Exception e) {
			setError(FIELD_DELIVERY_STATUS, e.getMessage());
		}
		command.setDeliveryStatus(deliveryStatus);

		if (errors.isEmpty()) {
			result = "Succès de la création de la command.";
		} else {
			result = "Échec de la création de la command.";
		}
		return command;
	}

	private double amountValidation(String amount) throws Exception {
		double temp;
		if (amount != null) {
			try {
				temp = Double.parseDouble(amount);
				if (temp < 0) {
					throw new Exception("Le montant doit être un nombre positif.");
				}
			} catch (NumberFormatException e) {
				temp = -1;
				throw new Exception("Le montant doit être un nombre.");
			}
		} else {
			temp = -1;
			throw new Exception("Merci d'entrer un montant.");
		}
		return temp;
	}

	private void paymentModeValidation(String paymentMode) throws Exception {
		if (paymentMode != null) {
			if (paymentMode.length() < 2) {
				throw new Exception("Le mode de paiement doit contenir au moins 2 caractères.");
			}
		} else {
			throw new Exception("Merci d'entrer un mode de paiement.");
		}
	}

	private void paymentStatusValidation(String paymentStatus) throws Exception {
		if (paymentStatus != null && paymentStatus.length() < 2) {
			throw new Exception("Le statut de paiement doit contenir au moins 2 caractères.");
		}
	}

	private void deliveryModeValidation(String deliveryMode) throws Exception {
		if (deliveryMode != null) {
			if (deliveryMode.length() < 2) {
				throw new Exception("Le mode de livraison doit contenir au moins 2 caractères.");
			}
		} else {
			throw new Exception("Merci d'entrer un mode de livraison.");
		}
	}

	private void deliveryStatusValidation(String deliveryStatus) throws Exception {
		if (deliveryStatus != null && deliveryStatus.length() < 2) {
			throw new Exception("Le statut de livraison doit contenir au moins 2 caractères.");
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