package ecom.stickers.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ecom.stickers.beans.ShoppingCart;
import ecom.stickers.dao.DAOException;
import ecom.stickers.dao.ProductDaoRemote;

@WebServlet(name = "CartManagement", urlPatterns = { "/cartManagement", "/addToCart", "/removeFromCart", "/addToCartFromSearch" })
public class CartManagement extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ATT_PRODUCT = "product";
	public static final String ATT_FORM = "form";
	public static final String VIEW = "/cartManagement.jsp";

	public static final String PARAM_ID_PRODUCT = "idProduct";
	public static final String CART_PRODUCTS_SESSION = "cart_products";
	public static final String VIEWPRODUCT = "/catalog";
	public static final String VIEWSEARCH = "/search";

	public static final String VIEWCART = "/cartManagement";

	@EJB
	private ProductDaoRemote productDao;
	
	private ShoppingCart shoppingCart = null;

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		shoppingCart = (ShoppingCart) request.getSession().getAttribute(CART_PRODUCTS_SESSION);
		if (shoppingCart == null) {
			try {
				InitialContext ctx = new InitialContext();
				//shoppingCart = (ShoppingCart) ctx.lookup("java:comp/env/ShoppingCartBean");
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
		}else if (request.getRequestURI().equals(request.getContextPath() + "/addToCartFromSearch")) {

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
			response.sendRedirect(request.getContextPath() + VIEWSEARCH);
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
		shoppingCart = (ShoppingCart) request.getSession().getAttribute(CART_PRODUCTS_SESSION);
		if (shoppingCart == null) {
			try {
				InitialContext ctx = new InitialContext();
				shoppingCart = (ShoppingCart) ctx.lookup("ShoppingCartBean");
				//shoppingCart = (ShoppingCart) ctx.lookup("java:comp/env/ShoppingCartBean");

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
