package com.ecom.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.dao.ProductDao;
import com.ecom.dao.ProductDaoLocal;
import com.ecom.dao.DAOException;
import com.ecom.entities.Product;

@WebServlet( name = "ProductRemoval", urlPatterns = { "/productRemoval" } )
public class ProductRemoval extends HttpServlet {
	public static final String PARAM_ID_PRODUCT = "idProduct";
	public static final String PRODUCTS_SESSION = "products";

	public static final String VIEW = "/productsList";

	@EJB
	private ProductDaoLocal productDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURI() + request.getContextPath());
		/* Récupération du paramètre */
		String idProduct = getValueParameter(request, PARAM_ID_PRODUCT);
		Long id = Long.parseLong(idProduct);

		/* Récupération de la Map des products enregistrés en session */
		HttpSession session = request.getSession();
		Map<Long, Product> products = (HashMap<Long, Product>) session.getAttribute(PRODUCTS_SESSION);

		/* Si l'id du client et la Map des products ne sont pas vides */
		if (id != null && products != null) {
			try {
				/* Alors suppression du client de la BDD */
				productDao.remove(products.get(id));
				/* Puis suppression du client de la Map */
				products.remove(id);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			/* Et remplacement de l'ancienne Map en session par la nouvelle */
			session.setAttribute(PRODUCTS_SESSION, products);
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