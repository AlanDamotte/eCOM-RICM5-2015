package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDaoRemote;
import dao.OrderDaoRemote;
import entities.Customer;
import entities.Order;
import forms.OrderCreationForm;

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
	private CustomerDaoRemote customerDao;
	@EJB
	private OrderDaoRemote orderDao;
	

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
		Order order = form.createOrder(request);

		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_ORDER, order);
		request.setAttribute(ATT_FORM, form);

		/* Si aucune erreur */
		if (form.getErrors().isEmpty()) {

			/* Affichage de la fiche récapitulative */
			this.getServletContext().getRequestDispatcher(VIEW_SUCCES).forward(request, response);
		} else {
			/* Sinon, ré-affichage du formulaire de création avec les erreurs */
			this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
		}
	}
}