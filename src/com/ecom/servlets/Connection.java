package com.ecom.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.beans.ShoppingCart;
import com.ecom.dao.CustomerDaoRemote;
import com.ecom.entities.Customer;
import com.ecom.forms.ConnectionForm;

@WebServlet(name = "Connection", urlPatterns = { "/connection", "/disconnection" })
public class Connection extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ATT_CUSTOMER = "customer";
	public static final String ATT_FORM = "form";
	public static final String ATT_MERGE_CART = "merge_cart";
	public static final String CART_PRODUCTS_SESSION = "cart_products";
	public static final String ATT_SESSION_CUSTOMER = "customerSession";
	public static final String VIEW = "/WEB-INF/connection.jsp";
	public static final String VIEW_CATALOG = "/catalog";

	@EJB
	private CustomerDaoRemote customerDao;

	private ShoppingCart shoppingCart;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getRequestURI().equals(request.getContextPath() + "/connection")) {
			/* Affichage de la page de connexion */
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		} else if (request.getRequestURI().equals(request.getContextPath() + "/disconnection")) {
			shoppingCart = (ShoppingCart) request.getSession().getAttribute(CART_PRODUCTS_SESSION);
			shoppingCart.saveCart();
			shoppingCart.setId(null);
			HttpSession session = request.getSession();
			session.removeAttribute(ATT_SESSION_CUSTOMER);
			response.sendRedirect(request.getContextPath() + VIEW_CATALOG);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Préparation de l'objet formulaire */
		ConnectionForm form = new ConnectionForm(customerDao);
		/* Traitement de la requête et récupération du bean en résultant */
		Customer customer = form.connectCustomer(request);
		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

		/*
		 * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
		 * Utilisateur à la session, sinon suppression du bean de la session.
		 */
		if (customer != null) {
			session.setAttribute(ATT_SESSION_CUSTOMER, customer);
			ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute(CART_PRODUCTS_SESSION);
			if (shoppingCart == null) {
				try {
					InitialContext ctx = new InitialContext();
					// shoppingCart = (ShoppingCart)
					// ctx.lookup("java:comp/env/ShoppingCartBean");
					shoppingCart = (ShoppingCart) ctx.lookup("ShoppingCartBean");
				} catch (NamingException e) {
					throw new ServletException(e);
				} finally {
					shoppingCart.setId(customer.getId());
					shoppingCart.initializeClientCart();
					/* Stockage du formulaire et du bean dans l'objet request */
					request.setAttribute(ATT_CUSTOMER, customer);
					request.getSession().setAttribute(CART_PRODUCTS_SESSION, shoppingCart);
					/* Redirection vers la le catalogue */
					response.sendRedirect(request.getContextPath() + VIEW_CATALOG);
				}
			} else {
				shoppingCart.setId(customer.getId());
				if (session.getAttribute(ATT_MERGE_CART).equals("false")) {
					shoppingCart.mergeClientCart();
					session.setAttribute(ATT_MERGE_CART, "true");
				}

				request.getSession().setAttribute(CART_PRODUCTS_SESSION, shoppingCart);
				response.sendRedirect(request.getContextPath() + VIEW_CATALOG);
			}
		} else {
			request.setAttribute(ATT_FORM, form);
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		}

	}

	/*
	 * Méthode utilitaire gérant la création d'un cookie et son ajout à la
	 * réponse HTTP.
	 */
	private static void setCookie(HttpServletResponse response, String nom, String valeur, int maxAge) {
		Cookie cookie = new Cookie(nom, valeur);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * Méthode utilitaire gérant la récupération de la valeur d'un cookie donné
	 * depuis la requête HTTP.
	 */
	private static String getCookieValue(HttpServletRequest request, String nom) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie != null && nom.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}