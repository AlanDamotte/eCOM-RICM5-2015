package ecom.stickers.servlets;

import java.io.IOException;
import java.util.Arrays;
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

import ecom.stickers.dao.ProductDaoRemote;
import ecom.stickers.entities.Product;

@WebServlet(name = "Search", urlPatterns = { "/search" })
public class Search extends HttpServlet {

	public static final String PARAM_TAGS = "search";
	public static final String SEARCH_PRODUCTS_SESSION = "searchProducts";
	public static final String VIEWSEARCH = "/search";
	public static final String VIEW = "/search.jsp";

	@EJB
	private ProductDaoRemote productDao;
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tags = getValueParameter(request, PARAM_TAGS);
		List<String> tagsList = getTags(tags);

		/* Récupération de la Map des products enregistrés en session */
		HttpSession session = request.getSession();
		
		List<Product> listProducts = productDao.listWithTag(tagsList);
		Map<Long, Product> mapProducts = new HashMap<Long, Product>();
		for (Product product : listProducts) {
			mapProducts.put(product.getId(), product);
		}
		session.setAttribute(SEARCH_PRODUCTS_SESSION, mapProducts);
		
		response.sendRedirect(request.getContextPath() + VIEWSEARCH);

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* À la réception d'une requête GET, simple affichage du formulaire */
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	public List<String> getTags(String tags) {
		return Arrays.asList(tags.split(" "));
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
