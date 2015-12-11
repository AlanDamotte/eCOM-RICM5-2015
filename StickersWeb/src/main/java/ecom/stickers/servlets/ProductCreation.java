package ecom.stickers.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ecom.stickers.dao.ProductDaoRemote;
import ecom.stickers.entities.Product;
import ecom.stickers.forms.ProductCreationForm;

@WebServlet(name = "ProductCreation", urlPatterns = { "/productCreation" }, initParams = @WebInitParam( name = "path", value = "/home/alan/Documents/RICM5/eCOM-RICM5-2015/file/img") )
@MultipartConfig( location = "/home/alan/Documents/RICM5/eCOM-RICM5-2015/file/img", maxFileSize = 2 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024, fileSizeThreshold = 1024 * 1024 )
public class ProductCreation extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String PATH = "path";
	public static final String ATT_PRODUCT = "product";
	public static final String ATT_FORM = "form";
	public static final String PRODUCTS_SESSION = "products";

	public static final String VIEW_SUCCES = "/displayProduct.jsp";
	public static final String VIEW_FORM = "/addProduct.jsp";

	@EJB
	private ProductDaoRemote productDao;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* À la réception d'une requête GET, simple affichage du formulaire */
		this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Lecture du paramètre 'path' passé à la servlet via la déclaration
		 * dans le web.xml
		 */
		String path = this.getServletConfig().getInitParameter(PATH);
		
		/* Préparation de l'objet formulaire */
		ProductCreationForm form = new ProductCreationForm(productDao);
		
		/* Traitement de la requête et récupération du bean en résultant */
		Product product = form.createProduct(request,path);

		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_PRODUCT, product);
		request.setAttribute(ATT_FORM, form);

		/* Si aucune erreur */
		if (form.getErrors().isEmpty()) {
			/* Alors récupération de la map des clients dans la session */
			HttpSession session = request.getSession();

			/* Affichage de la fiche récapitulative */
			this.getServletContext().getRequestDispatcher(VIEW_SUCCES).forward(request, response);
		} else {
			/* Sinon, ré-affichage du formulaire de création avec les erreurs */
			this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
		}
	}

}