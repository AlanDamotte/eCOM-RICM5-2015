package com.ecom.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.dao.OrderHistoryDaoRemote;
import com.ecom.entities.Customer;
import com.ecom.entities.OrderHistory;

@WebServlet(name = "CustomerOrderHistory", urlPatterns = { "/orderHistory" })
public class CustomerOrderHistory extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private OrderHistoryDaoRemote orderHistoryDao;

	public static final String VIEW = "/WEB-INF/orderHistory.jsp";
	public static final String ATT_SESSION_CUSTOMER = "customerSession";
	public static final String ATT_CUSTOMER_HISTORY = "orderHistory";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		List<OrderHistory> orderHistoryList = orderHistoryDao.findHistoryByIdCustomer(((Customer) session.getAttribute(ATT_SESSION_CUSTOMER)).getId());

		session.setAttribute(ATT_CUSTOMER_HISTORY, orderHistoryList);

		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}
}
