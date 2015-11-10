package com.ecom.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.dao.CustomerDaoLocal;
import com.ecom.entities.Customer;
import com.ecom.forms.CustomerCreationForm;

@WebServlet( name = "CustomerCreation", urlPatterns = { "/customerCreation" }, initParams = @WebInitParam( name = "path", value = "/home/alan/Documents/RICM5/eCOM-RICM5-2015/file/images/") )
@MultipartConfig( location = "/home/alan/Documents/RICM5/eCOM-RICM5-2015/file/images", maxFileSize = 2 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024, fileSizeThreshold = 1024 * 1024 )
public class CustomerCreation extends HttpServlet {
	public static final String PATH = "path";
	public static final String ATT_CLIENT = "customer";
	public static final String ATT_FORM = "form";
	public static final String CLIENTS_SESSION = "customers";

	public static final String VIEW_SUCCES = "/WEB-INF/displayCustomer.jsp";
	public static final String VIEW_FORM = "/WEB-INF/createCustomer.jsp";

	@EJB
	private CustomerDaoLocal customerDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* À la réception d'une requête GET, simple affichage du formulaire */
		this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Lecture du paramètre 'path' passé à la servlet via la déclaration
		 * dans le web.xml
		 */
		String path = this.getServletConfig().getInitParameter(PATH);

		/* Préparation de l'objet formulaire */
		CustomerCreationForm form = new CustomerCreationForm(customerDao);

		/* Traitement de la requête et récupération du bean en résultant */
		Customer customer = form.createCustomer(request, path);

		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_CLIENT, customer);
		request.setAttribute(ATT_FORM, form);

		/* Si aucune erreur */
		if (form.getErrors().isEmpty()) {
			/* Alors récupération de la map des customers dans la session */
			HttpSession session = request.getSession();
			Map<Long, Customer> customers = (HashMap<Long, Customer>) session.getAttribute(CLIENTS_SESSION);
			/*
			 * Si aucune map n'existe, alors initialisation d'une nouvelle map
			 */
			if (customers == null) {
				customers = new HashMap<Long, Customer>();
			}
			/* Puis ajout du customer courant dans la map */
			customers.put(customer.getId(), customer);
			/* Et enfin (ré)enregistrement de la map en session */
			session.setAttribute(CLIENTS_SESSION, customers);

			/* Affichage de la fiche récapitulative */
			this.getServletContext().getRequestDispatcher(VIEW_SUCCES).forward(request, response);
		} else {
			/* Sinon, ré-affichage du formulaire de création avec les erreurs */
			this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
		}
	}
}