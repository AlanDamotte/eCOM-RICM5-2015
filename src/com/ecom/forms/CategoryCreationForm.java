package com.ecom.forms;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import com.ecom.dao.CategoryDaoRemote;
import com.ecom.dao.DAOException;
import com.ecom.entities.Category;

public class CategoryCreationForm {
	public static final String FIELD_SUPPORT = "support";
	public static final String FIELD_SHAPE = "shape";
	public static final String FIELD_HEIGHT = "height";
	public static final String FIELD_WIDTH = "width";

	private static final int BUFFER_SIZE = 10240; // 10ko

	private String result;
	private CategoryDaoRemote categoryDao;
	InitialContext ctx;
	{
		try {
			ctx = new InitialContext();
			categoryDao = (CategoryDaoRemote) ctx.lookup("CategoryDao");
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}

	public CategoryCreationForm(CategoryDaoRemote categoryDao) {

		this.categoryDao = categoryDao;

	}

	private Map<String, String> errors = new HashMap<String, String>();

	public Map<String, String> getErrors() {
		return errors;
	}

	public String getResult() {
		return result;
	}

	public Category createCategory(HttpServletRequest request) {
		String support = getFieldValue(request, FIELD_SUPPORT);
		String shape = getFieldValue(request, FIELD_SHAPE);
		String height = getFieldValue(request, FIELD_HEIGHT);
		String width = getFieldValue(request, FIELD_WIDTH);

		Category category = new Category();

		processSupport(support, category);
		processShape(shape, category);
		processDimension(height, width, category);

		try {
			if (errors.isEmpty()) {
				categoryDao.create(category);
				result = "Succès de la création du customer.";
			} else {
				result = "Échec de la création du customer.";
			}
		} catch (DAOException e) {
			setError("imprévu", "Erreur imprévue lors de la création.");
			result = "Échec de la création du customer : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}

		return category;
	}
	
	private void processSupport(String support, Category category) {
		try {
			supportValidation(support);
		} catch (FormValidationException e) {
			setError(FIELD_SUPPORT, e.getMessage());
		}
		category.setSupport(support);
	}
	
	private void supportValidation(String support) throws FormValidationException {
		if (support == null) {
			throw new FormValidationException("Merci d'entrer un support");
		}
	}
	
	private void processShape(String shape, Category category) {
		try {
			shapeValidation(shape);
		} catch (FormValidationException e) {
			setError(FIELD_SHAPE, e.getMessage());
		}
		category.setShape(shape);
	}
	
	private void shapeValidation(String shape) throws FormValidationException {
		if (shape == null) {
			throw new FormValidationException("Merci d'entrer une forme");
		}
	}
	
	private void processDimension(String height, String width, Category category) {
		try {
			heightValidation(height);
		} catch (FormValidationException e) {
			setError(FIELD_HEIGHT, e.getMessage());
		}
		try {
			widthValidation(width);
		} catch (FormValidationException e) {
			setError(FIELD_WIDTH, e.getMessage());
		}
		double h =  Double.parseDouble(height);
		double w =  Double.parseDouble(width);
		Dimension dimension = new Dimension();
		dimension.setSize(w, h);
		category.setDimension(dimension);
	}
	
	private void heightValidation(String height) throws FormValidationException {
		if (height == null) {
			throw new FormValidationException("Merci d'entrer une hauteur");
		}
	}
	
	private void widthValidation(String width) throws FormValidationException {
		if (width == null) {
			throw new FormValidationException("Merci d'entrer une largeur");
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
