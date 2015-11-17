package com.ecom.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.entities.Customer;
import com.ecom.forms.ConnectionForm;

@WebServlet(name = "Connection", urlPatterns = { "/connection" })
public class Connection extends HttpServlet {
	public static final String ATT_CUSTOMER = "customer";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_CUSTOMER = "customerSession";
	public static final String VIEW = "/WEB-INF/connection.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		ConnectionForm form = new ConnectionForm();
		/* Traitement de la requête et récupération du bean en résultant */
		Customer customer = form.connectCustomer(request);
		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

		/*
		 * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
		 * Utilisateur à la session, sinon suppression du bean de la session.
		 */
		session.setAttribute(ATT_SESSION_CUSTOMER, customer);


		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_CUSTOMER, customer);

		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	/*
	 * Méthode utilitaire gérant la création d'un cookie et son ajout à la
	 * réponse HTTP.
	 */
	private static void setCookie(HttpServletResponse response, String nom, String valeur, int maxAge) {
		Cookie cookie = new Cookie(nom, valeur);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * Méthode utilitaire gérant la récupération de la valeur d'un cookie donné
	 * depuis la requête HTTP.
	 */
	private static String getCookieValue(HttpServletRequest request, String nom) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie != null && nom.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}