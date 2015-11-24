package com.ecom.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecom.dao.ProductDaoRemote;

@WebServlet(name = "CreateYourStick", urlPatterns = { "/create-your-stick" })
public class CreateYourStick extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String VIEW = "/WEB-INF/create-your-stick.jsp";

	@EJB
	private ProductDaoRemote productDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

}
