package ecom.stickers.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecom.stickers.dao.ProductDaoRemote;

@WebServlet(name = "Contact-us", urlPatterns = { "/contact-us" })
public class ContactUs extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String VIEW = "/contact-us.jsp";

	@EJB
	private ProductDaoRemote productDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

}
