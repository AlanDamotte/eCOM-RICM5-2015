package servlets;

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

import dao.ProductDaoRemote;
import entities.Product;

@WebServlet(name = "Index", urlPatterns = { "/index" })
public class Index extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String LAST_PRODUCTS = "lastProducts";

	public static final String VIEW = "/WEB-INF/index.jsp";

	@EJB
	private ProductDaoRemote productDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* Récupération de la Map des products enregistrés en session */
		HttpSession session = request.getSession();
		
		List<Product> listProducts = productDao.listLastProducts();
		Map<Long, Product> mapProducts = new HashMap<Long, Product>();
		for (Product product : listProducts) {
			mapProducts.put(product.getId(), product);
		}
		session.setAttribute(LAST_PRODUCTS, mapProducts);

		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

}
