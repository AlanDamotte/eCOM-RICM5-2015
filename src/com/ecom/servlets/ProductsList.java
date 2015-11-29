package com.ecom.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.dao.CategoryDaoRemote;
import com.ecom.dao.ProductDaoRemote;
import com.ecom.entities.Category;
import com.ecom.entities.Product;

@WebServlet(name = "ProductsList", urlPatterns = { "/productsList", "/catalog" })
public class ProductsList extends HttpServlet {
	public static final String ATT_PRODUCT = "product";
	public static final String ATT_PRODUCTS_SESSION = "products";
	public static final String ATT_FORM = "form";

	public static final String VIEW_ADMIN = "/WEB-INF/productsList.jsp";
	public static final String VIEW_CATALOG = "/WEB-INF/catalog.jsp";

	@EJB
	private ProductDaoRemote productDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

		/*
		 * Récupération de la list des orders existantes, et enregistrement en
		 * session
		 */
		List<Product> listProducts = productDao.list();
		Map<Long, Product> mapProducts = new HashMap<Long, Product>();
		for (Product product : listProducts) {
			mapProducts.put(product.getId(), product);
		}
		session.setAttribute(ATT_PRODUCTS_SESSION, mapProducts);

		/*
		 * À la réception d'une requête GET, affichage de la liste des clients
		 */
		if (request.getRequestURI().equals(request.getContextPath() + "/productsList")) {
			this.getServletContext().getRequestDispatcher(VIEW_ADMIN).forward(request, response);
		} else if (request.getRequestURI().equals(request.getContextPath() + "/catalog")) {
			this.getServletContext().getRequestDispatcher(VIEW_CATALOG).forward(request, response);
		}
	}
}
