package ecom.stickers.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ecom.stickers.dao.DAOException;
import ecom.stickers.dao.OrderDaoRemote;
import ecom.stickers.entities.Order;

@WebServlet(urlPatterns = { "/orderRemoval" })
public class OrderRemoval extends HttpServlet {
	public static final String PARAM_ID_ORDER = "idOrder";
	public static final String ORDERS_SESSION = "orders";

	public static final String VIEW = "/ordersList";

	@EJB
	private OrderDaoRemote orderDao;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération du paramètre */
		String idOrder = getValeurParametre(request, PARAM_ID_ORDER);
		Long id = Long.parseLong(idOrder);

		/* Récupération de la Map des orders enregistrées en session */
		HttpSession session = request.getSession();
		Map<Long, Order> orders = (HashMap<Long, Order>) session.getAttribute(ORDERS_SESSION);

		/* Si l'id de la order et la Map des orders ne sont pas vides */
		if (id != null && orders != null) {
			try {
				/* Alors suppression de la order de la BDD */
				orderDao.remove(orders.get(id));
				/* Puis suppression de la order de la Map */
				orders.remove(id);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			/* Et remplacement de l'ancienne Map en session par la nouvelle */
			session.setAttribute(ORDERS_SESSION, orders);
		}

		/* Redirection vers la fiche récapitulative */
		response.sendRedirect(request.getContextPath() + VIEW);
	}

	/*
	 * Méthode utilitaire qui retourne null si un paramètre est vide, et son
	 * contenu sinon.
	 */
	private static String getValeurParametre(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}
}