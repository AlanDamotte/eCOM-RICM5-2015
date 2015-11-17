package com.ecom.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductsList", urlPatterns = { "/productsList", "/catalog" })
public class ProductsList extends HttpServlet {
	public static final String ATT_PRODUCT = "product";
	public static final String ATT_FORM = "form";

	public static final String VIEW_ADMIN = "/WEB-INF/productsList.jsp";
	public static final String VIEW_CATALOG = "/WEB-INF/catalog.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * À la réception d'une requête GET, affichage de la liste des clients
		 */
		if (request.getRequestURI().equals(request.getContextPath() + "/productsList")) {
			this.getServletContext().getRequestDispatcher(VIEW_ADMIN).forward(request, response);
		} else if (request.getRequestURI().equals(request.getContextPath() + "/catalog")) {
			this.getServletContext().getRequestDispatcher(VIEW_CATALOG).forward(request, response);
		}
	}
}
