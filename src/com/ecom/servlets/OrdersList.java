package com.ecom.servlets;

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

import com.ecom.dao.OrderDaoRemote;
import com.ecom.entities.Order;

@WebServlet(name = "OrdersList", urlPatterns = { "/ordersList" })
public class OrdersList extends HttpServlet {
	public static final String ATT_ORDER = "order";
	public static final String ATT_FORM = "form";
	public static final String ATT_ORDERS_SESSION = "orders";

	public static final String VIEW = "/WEB-INF/ordersList.jsp";

	@EJB
	private OrderDaoRemote orderDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

		/*
		 * Récupération de la list des orders existantes, et enregistrement en
		 * session
		 */
		List<Order> listOrders = orderDao.list();
		Map<Long, Order> mapOrders = new HashMap<Long, Order>();
		for (Order order : listOrders) {
			mapOrders.put(order.getId(), order);
		}
		session.setAttribute(ATT_ORDERS_SESSION, mapOrders);

		/*
		 * À la réception d'une requête GET, affichage de la liste des orderes
		 */
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}
}