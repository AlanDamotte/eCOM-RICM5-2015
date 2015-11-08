package com.ecom.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ecom.dao.DAOException;
import com.ecom.dao.ProductDao;
import com.ecom.entities.Product;

public class ProductCreationForm {

	public static final String FIELD_NAMEP = "namep";
	public static final String FIELD_DESCRIPTION = "description";
	public static final String FIELD_PRICEP = "pricep";
	public static final String FIELD_QUANTITY = "quantity";
	public static final String FIELD_DISPONIBILITYP = "disponibilityp";

	private String result;
	private ProductDao productDao;

	public ProductCreationForm(ProductDao productDao) {
		this.productDao = productDao;
	}

	private Map<String, String> errors = new HashMap<String, String>();

	public Map<String, String> getErrors() {
		return errors;
	}

	public String getResult() {
		return result;
	}

	public Product createProduct(HttpServletRequest request) {
		
		String namep = getFieldValue(request, FIELD_NAMEP);
		String description = getFieldValue(request, FIELD_DESCRIPTION);
		String pricep = getFieldValue(request, FIELD_PRICEP);
		String quantity = getFieldValue(request, FIELD_QUANTITY);
		String disponibility = getFieldValue(request, FIELD_DISPONIBILITYP);
		

		Product product = new Product();

		processNameP(namep, product);
		processDescription(description, product);
		processPriceP(pricep, product);
		processQuantity(quantity, product);
		processDisponibility(disponibility, product);

		try {
			if (errors.isEmpty()) {
				productDao.create(product);
				result = "Succès de la création du client.";
			} else {
				result = "Échec de la création du client.";
			}
		} catch (DAOException e) {
			setError("imprévu", "Erreur imprévue lors de la création.");
			result = "Échec de la création du client : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}

		return product;
	}

	private void processNameP(String nameP, Product product) {
		try {
			nameValidation(nameP);
		} catch (FormValidationException e) {
			setError(FIELD_NAMEP, e.getMessage());
		}
		product.setName(nameP);
	}

	private void processDescription(String description, Product product) {
		try {
			descriptionValidation(description);
		} catch (FormValidationException e) {
			setError(FIELD_DESCRIPTION, e.getMessage());
		}
		product.setDescription(description);
	}

	private void processPriceP(String priceP, Product product) {
		double price = -1;
		try {
			price = priceValidation(priceP);
		} catch (FormValidationException e) {
			setError(FIELD_PRICEP, e.getMessage());
		}
		product.setPrice(price);
	}

	private void processQuantity(String quantity, Product product) {
		int q = 0;
		try {
			q = quantityValidation(quantity);
		} catch (FormValidationException e) {
			setError(FIELD_QUANTITY, e.getMessage());
		}
		product.setQuantity(q);
	}

	private void processDisponibility(String disponibility, Product product) {
		boolean dispo = false;
		try {
			dispo = dispoValidation(disponibility);
		} catch (FormValidationException e) {
			setError(FIELD_DISPONIBILITYP, e.getMessage());
		}
		product.setAvailability(dispo);
	}

	private void nameValidation(String name) throws FormValidationException {
		if (name != null) {
			if (name.length() < 2) {
				throw new FormValidationException("Le nom du produit doit contenir au moins 2 caractères.");
			}
		} else {
			throw new FormValidationException("Merci d'entrer un nom de produit.");
		}
	}

	private boolean dispoValidation(String dispo) throws FormValidationException {
		boolean disponibility = false;
		if (dispo != null && dispo == "Oui") {
			disponibility = true;
		}
		return disponibility;
	}

	private void descriptionValidation(String description) throws FormValidationException {
		//
	}

	private int quantityValidation(String quantity) throws FormValidationException {
		int temp;
		if (quantity != null) {
			try {
				temp = Integer.parseInt(quantity);
				if (temp < 0) {
					throw new FormValidationException("La quantité doit être un nombre positif.");
				}
			} catch (NumberFormatException e) {
				temp = -1;
				throw new FormValidationException("La quantité doit être un nombre.");
			}
		} else {
			temp = -1;
			throw new FormValidationException("Merci d'entrer une quantité");
		}
		return temp;
	}

	private double priceValidation(String price) throws FormValidationException {
		double temp;
		if (price != null) {
			try {
				temp = Double.parseDouble(price);
				if (temp < 0) {
					throw new FormValidationException("Le prix doit être un nombre positif.");
				}
			} catch (NumberFormatException e) {
				temp = -1;
				throw new FormValidationException("Le prix doit être un nombre.");
			}
		} else {
			temp = -1;
			throw new FormValidationException("Merci d'entrer un prix.");
		}
		return temp;
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
