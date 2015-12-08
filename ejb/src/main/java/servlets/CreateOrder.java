package servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import bank.Bank;
import beans.MailSenderBean;
import beans.ShoppingCart;
import dao.OrderDaoRemote;
import dao.OrderHistoryDaoRemote;
import dao.ProductDaoRemote;
import entities.Customer;
import entities.Order;
import entities.OrderHistory;
import service.*;

@WebServlet(name = "CreateOrder", urlPatterns = { "/createOrder" })
public class CreateOrder extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String VIEW = "/WEB-INF/catalog.jsp";
	public static final String VIEW_PAYMENT = "/WEB-INF/payment.jsp";

	public static final String CART_PRODUCTS_SESSION = "cart_products";
	public static final String ATT_SESSION_CUSTOMER = "customerSession";
	public static final String ATT_PAYMENT_STATUS = "paymentStatus";

	public static final String FIELD_CARD_NUMBER = "cardNumber";
	public static final String FIELD_CARD_SECURITYCODE = "securityCode";

	@EJB
	private OrderDaoRemote orderDao;

	@EJB
	private ProductDaoRemote productDao;

	@EJB
	private OrderHistoryDaoRemote orderHistory;
	
	@EJB
	private MailSenderBean mailSender;
	
	Customer customer;
	
	Order order;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		session.removeAttribute(ATT_PAYMENT_STATUS);
		
		/*
		 * Récupération et conversion de la date en String selon le
		 * format choisi.
		 */
		DateTime dt = new DateTime();

		order = new Order();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(CART_PRODUCTS_SESSION);
		customer = (Customer) session.getAttribute(ATT_SESSION_CUSTOMER);
		
		order.setAmount(shoppingCart.getTotal());
		order.setCart(shoppingCart.getProductsMap());
		order.setCustomer(customer);
		order.setDate(dt);
		// TODO
		order.setDeliveryStatus("");
		order.setPaymentStatus("");
		
		boolean availability = productDao.checkAvailability(order);
		System.out.println(availability);
		if(!availability){
			this.getServletContext().getRequestDispatcher(VIEW_PAYMENT).forward(request, response);
		}else{
			try {
				UserServiceImpl.proceedPayment(request, customer, order, shoppingCart, productDao, mailSender);
				long orderId = UserServiceImpl.persistOrder(request, productDao, orderHistory, customer, order, shoppingCart);
				UserServiceImpl.sendUserMail(customer,order,mailSender, orderId);
				shoppingCart.clear();
				this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
			} catch (Exception e) {
				session.setAttribute(ATT_PAYMENT_STATUS, "Error");
				this.getServletContext().getRequestDispatcher(VIEW_PAYMENT).forward(request, response);
			}
		}
	}

}
