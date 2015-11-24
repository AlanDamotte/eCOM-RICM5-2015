package com.ecom.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.dao.DAOException;
import com.ecom.dao.ProductDaoRemote;
import com.ecom.entities.Product;

@WebServlet(name = "ProductView", urlPatterns = { "/productView" })
public class ProductView extends HttpServlet {

	public static final String PARAM_ID_PRODUCT = "idProduct";
	public static final String PARAM_PRODUCT_VIEW = "productView";
	public static final String PRODUCTS_SESSION = "products";

	public static final String VIEW_PRODUCT = "/WEB-INF/productView.jsp";

	@EJB
	private ProductDaoRemote productDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Récupération du paramètre */
		String idProduct = getValueParameter(request, PARAM_ID_PRODUCT);
		Long id = Long.parseLong(idProduct);

		/* Récupération de la Map des products enregistrés en session */
		HttpSession session = request.getSession();

		Product product = null;

		/* Si l'id du client et la Map des products ne sont pas vides */
		if (id != null) {
			try {
				product = productDao.find(id);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			session.setAttribute(PARAM_PRODUCT_VIEW, product);
		}
		this.getServletContext().getRequestDispatcher(VIEW_PRODUCT).forward(request, response);
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