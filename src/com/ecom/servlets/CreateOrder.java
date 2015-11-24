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

import com.ecom.beans.ShoppingCart;
import com.ecom.dao.OrderDaoRemote;
import com.ecom.dao.OrderHistoryDaoRemote;
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
	
	public static final String VIEW = "/WEB-INF/catalog.jsp";
	
	@EJB
	private OrderDaoRemote orderDao;
	
	@EJB
	private OrderHistoryDaoRemote orderHistory;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(CART_PRODUCTS_SESSION);
		Customer customer = (Customer) session.getAttribute(ATT_SESSION_CUSTOMER);
		
		/*
		 * Récupération et conversion de la date en String selon le format
		 * choisi.
		 */
		DateTime dt = new DateTime();
		
		Order order = new Order();
		order.setAmount(shoppingCart.getTotal());
		order.setCart(shoppingCart.getCart());
		order.setCustomer(customer);
		order.setDate(dt);
		//TODO
		order.setDeliveryStatus("");
		order.setPaymentStatus("");
		
		orderDao.create(order);
		shoppingCart.clear();
		request.getSession().setAttribute(CART_PRODUCTS_SESSION, shoppingCart);
		
		//TODO : Mise à jour de l'historique utilisateur
		
		OrderHistory orderH = new OrderHistory();
		orderH.setCustomer(customer);
		orderH.setOrder(order);
		orderHistory.create(orderH);

		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

}
