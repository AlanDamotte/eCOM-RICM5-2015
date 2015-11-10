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

import com.ecom.dao.ProductDaoLocal;
import com.ecom.entities.Product;
import com.ecom.forms.ProductCreationForm;

@WebServlet(name = "ProductCreation", urlPatterns = { "/productCreation" })
public class ProductCreation extends HttpServlet {
	
	public static final String ATT_PRODUCT = "product";
	public static final String ATT_FORM = "form";
	public static final String PRODUCTS_SESSION = "products";

	public static final String VIEW_SUCCES = "/WEB-INF/displayProduct.jsp";
	public static final String VIEW_FORM = "/WEB-INF/addProduct.jsp";

	@EJB
	private ProductDaoLocal productDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* À la réception d'une requête GET, simple affichage du formulaire */
		this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		ProductCreationForm form = new ProductCreationForm(productDao);
		
		/* Traitement de la requête et récupération du bean en résultant */
		Product product = form.createProduct(request);

		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_PRODUCT, product);
		request.setAttribute(ATT_FORM, form);

		/* Si aucune erreur */
		if (form.getErrors().isEmpty()) {
			/* Alors récupération de la map des clients dans la session */
			HttpSession session = request.getSession();
			Map<Long, Product> products = (HashMap<Long, Product>) session.getAttribute(PRODUCTS_SESSION);
			/*
			 * Si aucune map n'existe, alors initialisation d'une nouvelle map
			 */
			if (products == null) {
				products = new HashMap<Long, Product>();
			}
			/* Puis ajout du client courant dans la map */
			products.put(product.getId(), product);
			/* Et enfin (ré)enregistrement de la map en session */
			session.setAttribute(PRODUCTS_SESSION, products);

			/* Affichage de la fiche récapitulative */
			this.getServletContext().getRequestDispatcher(VIEW_SUCCES).forward(request, response);
		} else {
			/* Sinon, ré-affichage du formulaire de création avec les erreurs */
			this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
		}
	}

}
