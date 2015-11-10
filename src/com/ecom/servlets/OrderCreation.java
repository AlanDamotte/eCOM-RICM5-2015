package com.ecom.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.dao.CustomerDaoLocal;
import com.ecom.dao.OrderDaoLocal;
import com.ecom.entities.Customer;
import com.ecom.entities.Order;
import com.ecom.forms.OrderCreationForm;

@WebServlet( name = "OrderCreation", urlPatterns = { "/orderCreation" }, initParams = @WebInitParam( name = "path", value = "/home/alan/Documents/RICM5/eCOM-RICM5-2015/file/images/" ) )
@MultipartConfig( location = "/home/alan/Documents/RICM5/eCOM-RICM5-2015/file/images", maxFileSize = 2 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024, fileSizeThreshold = 1024 * 1024 )
public class OrderCreation extends HttpServlet {
	public static final String PATH = "path";
	public static final String ATT_ORDER = "order";
	public static final String ATT_FORM = "form";
	public static final String CLIENTS_SESSION = "customers";
	public static final String APPLICATION_CLIENTS = "initCustomers";
	public static final String ORDERS_SESSION = "orders";
	public static final String APPLICATION_ORDERS = "initOrders";

	public static final String VIEW_SUCCES = "/WEB-INF/displayOrder.jsp";
	public static final String VIEW_FORM = "/WEB-INF/createOrder.jsp";

	@EJB
	private CustomerDaoLocal customerDao;
	@EJB
	private OrderDaoLocal orderDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* À la réception d'une requête GET, simple affichage du formulaire */
		this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Lecture du paramètre 'path' passé à la servlet via la déclaration
		 * dans le web.xml
		 */
		String path = this.getServletConfig().getInitParameter(PATH);

		/* Préparation de l'objet formulaire */
		OrderCreationForm form = new OrderCreationForm(customerDao, orderDao);

		/* Traitement de la requête et récupération du bean en résultant */
		Order order = form.createOrder(request, path);

		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_ORDER, order);
		request.setAttribute(ATT_FORM, form);

		/* Si aucune erreur */
		if (form.getErrors().isEmpty()) {
			/* Alors récupération de la map des customers dans la session */
			HttpSession session = request.getSession();
			Map<Long, Customer> customers = (HashMap<Long, Customer>) session.getAttribute(CLIENTS_SESSION);
			/*
			 * Si aucune map n'existe, alors initialisation d'une nouvelle map
			 */
			if (customers == null) {
				customers = new HashMap<Long, Customer>();
			}
			/* Puis ajout du customer de la order courante dans la map */
			customers.put(order.getCustomer().getId(), order.getCustomer());
			/* Et enfin (ré)enregistrement de la map en session */
			session.setAttribute(CLIENTS_SESSION, customers);

			/* Ensuite récupération de la map des orders dans la session */
			Map<Long, Order> orders = (HashMap<Long, Order>) session.getAttribute(ORDERS_SESSION);
			/*
			 * Si aucune map n'existe, alors initialisation d'une nouvelle map
			 */
			if (orders == null) {
				orders = new HashMap<Long, Order>();
			}
			/* Puis ajout de la order courante dans la map */
			orders.put(order.getId(), order);
			/* Et enfin (ré)enregistrement de la map en session */
			session.setAttribute(ORDERS_SESSION, orders);

			/* Affichage de la fiche récapitulative */
			this.getServletContext().getRequestDispatcher(VIEW_SUCCES).forward(request, response);
		} else {
			/* Sinon, ré-affichage du formulaire de création avec les erreurs */
			this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
		}
	}
}