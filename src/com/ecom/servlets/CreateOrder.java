package com.ecom.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import com.ecom.bank.Bank;
import com.ecom.beans.MailSenderBean;
import com.ecom.beans.ShoppingCart;
import com.ecom.dao.OrderDaoRemote;
import com.ecom.dao.OrderHistoryDaoRemote;
import com.ecom.dao.ProductDaoRemote;
import com.ecom.entities.Customer;
import com.ecom.entities.Order;
import com.ecom.entities.OrderHistory;

@WebServlet(name = "CreateOrder", urlPatterns = { "/createOrder" })
public class CreateOrder extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String CART_PRODUCTS_SESSION = "cart_products";
	public static final String ATT_SESSION_CUSTOMER = "customerSession";
	public static final String ATT_PAYMENT_STATUS = "paymentStatus";

	public static final String VIEW = "/WEB-INF/catalog.jsp";
	public static final String VIEW_PAYMENT = "/WEB-INF/payment.jsp";

	public static final String FIELD_CARD_NUMBER = "cardNumber";
	public static final String FIELD_CARD_SECURITYCODE = "securityCode";
	
	@EJB
	private MailSenderBean mailSender;

	@EJB
	private OrderDaoRemote orderDao;

	@EJB
	private ProductDaoRemote productDao;

	@EJB
	private OrderHistoryDaoRemote orderHistory;

	Order order;

	Customer customer;
	
	Bank bank = new Bank();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		super.service(req, resp);
//
//		try {
//
//			String fromEmail = "alan.damotte@gmail.com";
//			String username = "alan.damotte";
//			String password = "xxx";
//
//			String toEmail = customer.getEmail();
//			String subject = "Confirmation de commande"; //+ order.getId().toString();
//			String message = "Test";
//
//			// Call to mail sender bean
//			mailSender.sendEmail(fromEmail, username, password, toEmail, subject, message);
//			// ----------------------
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
//		}
//	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		session.removeAttribute(ATT_PAYMENT_STATUS);

		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(CART_PRODUCTS_SESSION);
		customer = (Customer) session.getAttribute(ATT_SESSION_CUSTOMER);
		
		Long cardNumber = Long.parseLong((String) session.getAttribute(FIELD_CARD_NUMBER));
		int securityCode = Integer.parseInt((String) session.getAttribute(FIELD_CARD_SECURITYCODE));
		
		boolean validate = bank.paymentProcess(cardNumber, shoppingCart.getTotal(), securityCode);
		if(validate){
			/*
			 * Récupération et conversion de la date en String selon le format
			 * choisi.
			 */
			DateTime dt = new DateTime();

			order = new Order();
			order.setAmount(shoppingCart.getTotal());
			order.setCart(shoppingCart.getCart());
			order.setCustomer(customer);
			order.setDate(dt);
			// TODO
			order.setDeliveryStatus("");
			order.setPaymentStatus("");

			// orderDao.create(order);

			// Mise à jour des quantités de produits restantes
			productDao.updateProductQuantity(shoppingCart);

			shoppingCart.clear();
			request.getSession().setAttribute(CART_PRODUCTS_SESSION, shoppingCart);

			// TODO : Mise à jour de l'historique utilisateur

			OrderHistory orderH = new OrderHistory();
			orderH.setCustomer(customer);
			orderH.setOrder(order);
			orderHistory.create(orderH);

			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
			//response.getWriter().append("Served at: ").append(request.getContextPath());
		}else{
			//TODO : Gestion erreur à améliorer
			session.setAttribute(ATT_PAYMENT_STATUS, "Error");
			this.getServletContext().getRequestDispatcher(VIEW_PAYMENT).forward(request, response);
		}


	}

}
