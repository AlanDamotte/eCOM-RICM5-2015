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
import com.ecom.service.*;

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
//			String subject = "Confirmation de commande"; // +
//			order.getId().toString();
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

		try {
			UserServiceImpl.proceedPayment(request, orderDao, productDao, orderHistory, mailSender);

		} catch (Exception e) {
			session.setAttribute(ATT_PAYMENT_STATUS, "Error");
			this.getServletContext().getRequestDispatcher(VIEW_PAYMENT).forward(request, response);
		} finally {
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		}

	}

}
