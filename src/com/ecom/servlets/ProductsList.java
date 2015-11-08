package com.ecom.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( name = "ProductsList", urlPatterns = { "/productsList" } )
public class ProductsList extends HttpServlet {
	public static final String ATT_PRODUCT = "product";
	public static final String ATT_FORM = "form";

	public static final String VIEW = "/WEB-INF/productsList.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* À la réception d'une requête GET, affichage de la liste des clients */
        this.getServletContext().getRequestDispatcher( VIEW ).forward( request, response );
    }
}