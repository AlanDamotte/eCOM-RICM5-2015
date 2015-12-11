package ecom.stickers.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ecom.stickers.dao.ProductDaoRemote;
import ecom.stickers.entities.Product;

@WebServlet(name = "Index", urlPatterns = { "/index" })
public class Index extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Properties env;
	private InitialContext ctx;

	public static final String LAST_PRODUCTS = "lastProducts";

	public static final String VIEW = "/index.jsp";

	@EJB
	private ProductDaoRemote productDao;

	// public Index(){
	// env = new Properties();
	// try {
	// ctx = new InitialContext();
	// } catch (NamingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Récupération de la Map des products enregistrés en session */
		HttpSession session = request.getSession();

		// productDao = (ProductDaoRemote)ctx.lookup("ProductDao");
		List<Product> listProducts = productDao.listLastProducts();

		Map<Long, Product> mapProducts = new HashMap<Long, Product>();
		for (Product product : listProducts) {
			mapProducts.put(product.getId(), product);
		}
		session.setAttribute(LAST_PRODUCTS, mapProducts);

		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);

	}

}
