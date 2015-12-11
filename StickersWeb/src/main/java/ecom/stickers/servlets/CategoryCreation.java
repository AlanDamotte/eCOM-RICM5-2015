package ecom.stickers.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecom.stickers.dao.CategoryDaoRemote;
import ecom.stickers.entities.Category;
import ecom.stickers.forms.CategoryCreationForm;

@WebServlet(name = "CategoryCreation", urlPatterns = { "/categoryCreation" })
public class CategoryCreation extends HttpServlet {
	public static final String PATH = "path";
	public static final String ATT_CATEGORY = "category";
	public static final String ATT_FORM = "form";

	public static final String VIEW_SUCCES = "/administration.jsp";
	public static final String VIEW_FORM = "/createCategory.jsp";

	@EJB
	private CategoryDaoRemote categoryDao;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* À la réception d'une requête GET, simple affichage du formulaire */
		this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Préparation de l'objet formulaire */
		CategoryCreationForm form = new CategoryCreationForm(categoryDao);

		/* Traitement de la requête et récupération du bean en résultant */
		Category category = form.createCategory(request);

		/* Si aucune erreur */
		if (form.getErrors().isEmpty()) {
			this.getServletContext().getRequestDispatcher(VIEW_SUCCES).forward(request, response);
		} else {
			/* Sinon, ré-affichage du formulaire de création avec les erreurs */
			this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
		}
	}
}
