package com.ecom.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.dao.CustomerDao;
import com.ecom.dao.CustomerDaoRemote;
import com.ecom.dao.DAOException;
import com.ecom.entities.Customer;

@WebServlet( name = "CustomerRemoval", urlPatterns = { "/customerRemoval" } )
public class CustomerRemoval extends HttpServlet {
	public static final String PARAM_ID_CLIENT = "idCustomer";
	public static final String CLIENTS_SESSION = "customers";

	public static final String VIEW = "/customersList";

	@EJB
	private CustomerDaoRemote customerDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération du paramètre */
		String idCustomer = getValueParameter(request, PARAM_ID_CLIENT);
		Long id = Long.parseLong(idCustomer);

		/* Récupération de la Map des customers enregistrés en session */
		HttpSession session = request.getSession();
		Map<Long, Customer> customers = (HashMap<Long, Customer>) session.getAttribute(CLIENTS_SESSION);

		/* Si l'id du customer et la Map des customers ne sont pas vides */
		if (id != null && customers != null) {
			try {
				/* Alors suppression du customer de la BDD */
				customerDao.remove(customers.get(id));
				/* Puis suppression du customer de la Map */
				customers.remove(id);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			/* Et remplacement de l'ancienne Map en session par la nouvelle */
			session.setAttribute(CLIENTS_SESSION, customers);
		}

		/* Redirection vers la fiche récapitulative */
		response.sendRedirect(request.getContextPath() + VIEW);
	}

	/*
	 * Méthode utilitaire qui retourne null si un paramètre est vide, et son
	 * contenu sinon.
	 */
	private static String getValueParameter(HttpServletRequest request, String fieldName) {
		String value = request.getParameter(fieldName);
		if (value == null || value.trim().length() == 0) {
			return null;
		} else {
			return value;
		}
	}
}