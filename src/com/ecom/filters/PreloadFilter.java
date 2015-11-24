package com.ecom.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ecom.beans.ShoppingCart;
import com.ecom.dao.CategoryDaoRemote;
import com.ecom.dao.CustomerDaoRemote;
import com.ecom.dao.OrderDaoRemote;
import com.ecom.dao.ProductDaoRemote;
import com.ecom.entities.Category;
import com.ecom.entities.Customer;
import com.ecom.entities.Order;
import com.ecom.entities.Product;

@WebFilter(urlPatterns = { "/*" })
public class PreloadFilter implements Filter {
	public static final String ATT_CUSTOMERS_SESSION = "customers";
	public static final String ATT_CATEGORIES_SESSION = "categories";
	public static final String ATT_ORDERS_SESSION = "orders";
	public static final String ATT_PRODUCTS_SESSION = "products";
	public static final String CART_PRODUCTS_SESSION = "cart_products";
	public static final String ATT_MERGE_CART = "merge_cart";

	@EJB
	private CustomerDaoRemote customerDao;
	@EJB
	private OrderDaoRemote orderDao;
	@EJB
	private ProductDaoRemote productDao;
	@EJB
	private CategoryDaoRemote categoryDao;

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		/* Cast de l'objet request */
		HttpServletRequest request = (HttpServletRequest) req;

		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

//		InitialContext ctx;
//		try {
//			ctx = new InitialContext();
//			customerDao = (CustomerDaoRemote) ctx.lookup("CustomerDao");
//			categoryDao = (CategoryDaoRemote) ctx.lookup("CategoryDao");
//			productDao = (ProductDaoRemote) ctx.lookup("ProductDao");
//			orderDao = (OrderDaoRemote) ctx.lookup("OrderDao");
//		} catch (NamingException ex) {
//			ex.printStackTrace();
//		}
		
		//Attribut permettant de merge les paniers sessions et utilisateur qu'une seule fois par session.
		if(session.getAttribute(ATT_MERGE_CART)==null){
			session.setAttribute(ATT_MERGE_CART, "false");
		}		

		/*
		 * Si la map des customers n'existe pas en session, alors l'utilisateur
		 * se connecte pour la première fois et nous devons précharger en
		 * session les infos contenues dans la BDD.
		 */
		
		
		if (session.getAttribute(ATT_CUSTOMERS_SESSION) == null) {

			/*
			 * Récupération de la list des customers existants, et
			 * enregistrement en session
			 */
			List<Customer> listCustomers = customerDao.list();
			Map<Long, Customer> mapCustomers = new HashMap<Long, Customer>();
			for (Customer customer : listCustomers) {
				mapCustomers.put(customer.getId(), customer);
			}
			session.setAttribute(ATT_CUSTOMERS_SESSION, mapCustomers);

		}

		/*
		 * De même pour la map des orders
		 */
		if (session.getAttribute(ATT_ORDERS_SESSION) == null) {

			/*
			 * Récupération de la list des orders existantes, et enregistrement
			 * en session
			 */
			List<Order> listOrders = orderDao.list();
			Map<Long, Order> mapOrders = new HashMap<Long, Order>();
			for (Order order : listOrders) {
				mapOrders.put(order.getId(), order);
			}
			session.setAttribute(ATT_ORDERS_SESSION, mapOrders);

		}

		if (session.getAttribute(ATT_PRODUCTS_SESSION) == null) {
			/*
			 * Récupération de la list des orders existantes, et enregistrement
			 * en session
			 */
			List<Product> listProducts = productDao.list();
			Map<Long, Product> mapProducts = new HashMap<Long, Product>();
			for (Product product : listProducts) {
				mapProducts.put(product.getId(), product);
			}
			session.setAttribute(ATT_PRODUCTS_SESSION, mapProducts);

		}

		if (session.getAttribute(ATT_CATEGORIES_SESSION) == null) {

			/*
			 * Récupération de la list des catégories existantes, et
			 * enregistrement en session
			 */
			List<Category> listCategory = categoryDao.list();
			Map<Long, Category> mapCategory = new HashMap<Long, Category>();
			for (Category category : listCategory) {
				mapCategory.put(category.getId(), category);
			}
			session.setAttribute(ATT_CATEGORIES_SESSION, mapCategory);

		}

		/* Pour terminer, poursuite de la requête en cours */
		chain.doFilter(request, res);
	}

	public void destroy() {
	}
}