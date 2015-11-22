package com.ecom.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PaymentManagement", urlPatterns = { "/paymentManagement" })
public class PaymentManagement extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ATT_FORM = "form";
	public static final String PRODUCTS_SESSION = "products";

	public static final String VIEW_SUCCES = "/WEB-INF/recap.jsp";
	public static final String VIEW_FORM = "/WEB-INF/payment.jsp";
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* À la réception d'une requête GET, simple affichage du formulaire */
		this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO
		this.getServletContext().getRequestDispatcher(VIEW_SUCCES).forward(request, response);
	}
}
