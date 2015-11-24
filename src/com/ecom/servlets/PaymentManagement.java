package com.ecom.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.entities.Category;

@WebServlet(name = "PaymentManagement", urlPatterns = { "/paymentManagement" })
public class PaymentManagement extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ATT_FORM = "form";
	public static final String PRODUCTS_SESSION = "products";

	public static final String VIEW_SUCCESS = "/WEB-INF/recap.jsp";
	public static final String VIEW_FORM = "/WEB-INF/payment.jsp";
	
	public static final String FIELD_CARD_NAME = "cardName";
	public static final String FIELD_CARD_NUMBER = "cardNumber";
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* À la réception d'une requête GET, simple affichage du formulaire */
		this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		String cardName = getFieldValue(request, FIELD_CARD_NAME);
		String cardNumber = getFieldValue(request, FIELD_CARD_NUMBER);
		
		session.setAttribute(FIELD_CARD_NAME, cardName);
		session.setAttribute(FIELD_CARD_NUMBER, cardNumber);
		
		this.getServletContext().getRequestDispatcher(VIEW_SUCCESS).forward(request, response);
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
