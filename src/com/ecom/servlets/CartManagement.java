package com.ecom.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.beans.ShoppingCart;
import com.ecom.dao.DAOException;
import com.ecom.dao.ProductDao;
import com.ecom.entities.Product;
import com.ecom.forms.FormValidationException;

@WebServlet(name = "CartManagement", urlPatterns = { "/cartManagement", "/addToCart", "/removeFromCart" })
public class CartManagement extends HttpServlet {
	public static final String ATT_PRODUCT = "product";
	public static final String ATT_FORM = "form";
	public static final String VIEW = "/WEB-INF/cartManagement.jsp";

	public static final String PARAM_ID_PRODUCT = "idProduct";
	public static final String CART_PRODUCTS_SESSION = "cart_products";
	public static final String VIEWPRODUCT = "/productsList";

	public static final String VIEWCART = "/cartManagement";

	@EJB
	private ProductDao productDao;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute(CART_PRODUCTS_SESSION);
		if (shoppingCart == null) {
			try {
				InitialContext ctx = new InitialContext();
				shoppingCart = (ShoppingCart) ctx.lookup("ShoppingCartBean");

				// put EJB in HTTP session for future servlet calls
				request.getSession().setAttribute(CART_PRODUCTS_SESSION, shoppingCart);
			} catch (NamingException e) {
				throw new ServletException(e);
			}
		}
		if (request.getRequestURI().equals(request.getContextPath() + "/addToCart")) {

			String idProduct = getValueParameter(request, PARAM_ID_PRODUCT);
			Long id = Long.parseLong(idProduct);

			/* Récupération de la Map des products enregistrés en session */
			HttpSession session = request.getSession();
			
			String quantityCart = getValueParameter(request, "quantityCart");
			int quantity = Integer.parseInt(quantityCart);
			
			/* Si l'id du client et la Map des products ne sont pas vides */
			if (id != null) {
				try {
					shoppingCart.addProduct(id, quantity);
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}

			session.setAttribute(CART_PRODUCTS_SESSION, shoppingCart);
			/* Redirection vers la fiche récapitulative */
			response.sendRedirect(request.getContextPath() + VIEWPRODUCT);
		}else if (request.getRequestURI().equals(request.getContextPath() + "/removeFromCart")) {
			String idProduct = getValueParameter(request, PARAM_ID_PRODUCT);
			Long id = Long.parseLong(idProduct);
			
			String quantityCart = getValueParameter(request, "quantityCart");
			int quantity = Integer.parseInt(quantityCart);
			/* Récupération de la Map des products enregistrés en session */
			HttpSession session = request.getSession();

			/* Si l'id du client et la Map des products ne sont pas vides */
			if (id != null) {
				try {
					shoppingCart.updateQuantity(id, quantity);
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}

			session.setAttribute(CART_PRODUCTS_SESSION, shoppingCart);
			/* Redirection vers la fiche récapitulative */
			response.sendRedirect(request.getContextPath() + VIEWCART);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * À la réception d'une requête GET, affichage de la liste des clients
		 */
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute(CART_PRODUCTS_SESSION);
		if (shoppingCart == null) {
			// EJB is not present in the HTTP session
			// so let's fetch a new one from the container
			try {
				InitialContext ctx = new InitialContext();
				shoppingCart = (ShoppingCart) ctx.lookup("ShoppingCartBean");

				// put EJB in HTTP session for future servlet calls
				request.getSession().setAttribute(CART_PRODUCTS_SESSION, shoppingCart);
			} catch (NamingException e) {
				throw new ServletException(e);
			}
		}
		if (request.getRequestURI().equals(request.getContextPath() + "/cartManagement")) {

			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);

		}else if (request.getRequestURI().equals(request.getContextPath() + "/removeFromCart")) {
			String idProduct = getValueParameter(request, PARAM_ID_PRODUCT);
			Long id = Long.parseLong(idProduct);
			/* Récupération de la Map des products enregistrés en session */
			HttpSession session = request.getSession();
			/* Si l'id du client et la Map des products ne sont pas vides */
			if (id != null) {
				try {
					shoppingCart.removeProduct(id, shoppingCart.getQuantity(id));
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}
			session.setAttribute(CART_PRODUCTS_SESSION, shoppingCart);
			/* Redirection vers la fiche récapitulative */
			response.sendRedirect(request.getContextPath() + VIEWCART);
		}
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
	
	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getFieldValue(HttpServletRequest request, String fieldName) {
		String value = request.getParameter(fieldName);
		if (value == null || value.trim().length() == 0) {
			return null;
		} else {
			return value;
		}
	}
}
