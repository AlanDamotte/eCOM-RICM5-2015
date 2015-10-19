package com.ecom.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.beans.Command;

public class CommandRemoval extends HttpServlet {

	public static final String PARAM_DATE_COMMAND = "dateCommand";
	public static final String COMMANDS_SESSION = "commands";

	public static final String VIEW = "/commandsList";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération du paramètre */
		String dateCommand = getValueParameter(request, PARAM_DATE_COMMAND);

		/* Récupération de la Map des commands enregistrées en session */
		HttpSession session = request.getSession();
		Map<String, Command> commands = (HashMap<String, Command>) session.getAttribute(COMMANDS_SESSION);

		/* Si la date de la Command et la Map des commands ne sont pas vides */
		if (dateCommand != null && commands != null) {
			/* Alors suppression de la Command de la Map */
			commands.remove(dateCommand);
			/* Et remplacement de l'ancienne Map en session par la nouvelle */
			session.setAttribute(COMMANDS_SESSION, commands);
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