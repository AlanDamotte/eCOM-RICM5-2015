package com.ecom.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.beans.Client;

public class ClientRemoval extends HttpServlet {

	public static final String PARAM_LASTNAME_CLIENT = "lastnameClient";
	public static final String CLIENTS_SESSION = "clients";

	public static final String VIEW = "/clientsList";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération du paramètre */
		String lastnameClient = getValueParameter(request, PARAM_LASTNAME_CLIENT);

		/* Récupération de la Map des clients enregistrés en session */
		HttpSession session = request.getSession();
		Map<String, Client> clients = (HashMap<String, Client>) session.getAttribute(CLIENTS_SESSION);

		/* Si le nom du client et la Map des clients ne sont pas vides */
		if (lastnameClient != null && clients != null) {
			/* Alors suppression du client de la Map */
			clients.remove(lastnameClient);
			/* Et remplacement de l'ancienne Map en session par la nouvelle */
			session.setAttribute(CLIENTS_SESSION, clients);
		}

		/* Redirection vers la fiche récapitulative */
		response.sendRedirect(request.getContextPath() + VIEW);
	}

	/*
	 * Méthode utilitaire qui retourne null si un paramètre est vide, et son
	 * contenu sinon.
	 */
	private static String getValueParameter(HttpServletRequest request, String fieldName) {
		String valeur = request.getParameter(fieldName);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}
}