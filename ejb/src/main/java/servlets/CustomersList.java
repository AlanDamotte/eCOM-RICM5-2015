package servlets;

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

import dao.CustomerDaoRemote;
import entities.Customer;

@WebServlet(name = "CustomersList", urlPatterns = { "/customersList" })
public class CustomersList extends HttpServlet {

	public static final String ATT_CUSTOMERS_SESSION = "customers";
	public static final String ATT_CLIENT = "customer";
	public static final String ATT_FORM = "form";

	public static final String VIEW = "/WEB-INF/customersList.jsp";

	@EJB
	private CustomerDaoRemote customerDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

		/*
		 * Récupération de la list des customers existants, et enregistrement en
		 * session
		 */
		List<Customer> listCustomers = customerDao.list();
		Map<Long, Customer> mapCustomers = new HashMap<Long, Customer>();
		for (Customer customer : listCustomers) {
			mapCustomers.put(customer.getId(), customer);
		}
		session.setAttribute(ATT_CUSTOMERS_SESSION, mapCustomers);

		/*
		 * À la réception d'une requête GET, affichage de la liste des customers
		 */
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}
}
