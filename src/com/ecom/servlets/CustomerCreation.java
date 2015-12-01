package com.ecom.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecom.dao.CustomerDaoRemote;
import com.ecom.entities.Customer;
import com.ecom.forms.CustomerCreationForm;

@WebServlet( name = "CustomerCreation", urlPatterns = { "/customerCreation", "/inscription" })
public class CustomerCreation extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String PATH = "path";
	public static final String ATT_CLIENT = "customer";
	public static final String ATT_FORM = "form";
	public static final String CLIENTS_SESSION = "customers";

	public static final String VIEW_SUCCES = "/WEB-INF/displayCustomer.jsp";
	public static final String VIEW_FORM = "/WEB-INF/createCustomer.jsp";
	public static final String INSCRIPTION_SUCCESS = "/index";

	@EJB
	private CustomerDaoRemote customerDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* À la réception d'une requête GET, simple affichage du formulaire */
		this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Préparation de l'objet formulaire */
		CustomerCreationForm form = new CustomerCreationForm(customerDao);

		/* Traitement de la requête et récupération du bean en résultant */
		Customer customer = form.createCustomer(request);

		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_CLIENT, customer);
		request.setAttribute(ATT_FORM, form);
		

		
		/* Si aucune erreur */
		if (form.getErrors().isEmpty()) {
			if (request.getRequestURI().equals(request.getContextPath() + "/inscription")) {
				response.sendRedirect(request.getContextPath() + INSCRIPTION_SUCCESS);
			}else{
				/* Affichage de la fiche récapitulative */
				this.getServletContext().getRequestDispatcher(VIEW_SUCCES).forward(request, response);
			}
		} else {
			if (request.getRequestURI().equals(request.getContextPath() + "/inscription")) {
				//response.sendRedirect(request.getContextPath() + INSCRIPTION_SUCCESS);
			}else{
				/* Sinon, ré-affichage du formulaire de création avec les erreurs */
				this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
			}
		}
	}
}