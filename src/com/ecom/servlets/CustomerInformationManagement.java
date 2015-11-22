package com.ecom.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.beans.ShoppingCart;

@WebServlet(name = "CustomerInformationManagement", urlPatterns = { "/customerInformationManagement" })
public class CustomerInformationManagement extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ATT_SESSION_CUSTOMER = "customerSession";
	public static final String VIEW = "/WEB-INF/connection.jsp";
	public static final String VIEW_RECAP = "/WEB-INF/customerInformation.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher(VIEW_RECAP).forward(request, response);

	}
}
