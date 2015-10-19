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
import com.ecom.beans.Command;
import com.ecom.forms.CommandCreationForm;

public class CommandCreation extends HttpServlet {

	public static final String PATH = "path";
	public static final String ATT_COMMAND = "command";
	public static final String ATT_FORM = "form";
	public static final String CLIENTS_SESSION = "clients";
	public static final String COMMANDS_SESSION = "commands";

	public static final String VIEW_SUCCES = "/WEB-INF/displayCommand.jsp";
	public static final String VIEW_FORM = "/WEB-INF/createCommand.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* À la réception d'une requête GET, simple affichage du formulaire */
		this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 * Lecture du paramètre 'chemin' passé à la servlet via la déclaration
		 * dans le web.xml
		 */
		String path = this.getServletConfig().getInitParameter(PATH);

		/* Préparation de l'objet formulaire */
		CommandCreationForm form = new CommandCreationForm();

		/* Traitement de la requête et récupération du bean en résultant */
		Command command = form.createCommand(request, path);

		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_COMMAND, command);
		request.setAttribute(ATT_FORM, form);

		/* Si aucune erreur */
		if (form.getErrors().isEmpty()) {
			/* Alors récupération de la map des clients dans la session */
			HttpSession session = request.getSession();
			Map<String, Client> clients = (HashMap<String, Client>) session.getAttribute(CLIENTS_SESSION);
			/*
			 * Si aucune map n'existe, alors initialisation d'une nouvelle map
			 */
			if (clients == null) {
				clients = new HashMap<String, Client>();
			}
			/* Puis ajout du client de la command courante dans la map */
			clients.put(command.getClient().getLastname(), command.getClient());
			/* Et enfin (ré)enregistrement de la map en session */
			session.setAttribute(CLIENTS_SESSION, clients);

			/* Ensuite récupération de la map des commands dans la session */
			Map<String, Command> commands = (HashMap<String, Command>) session.getAttribute(COMMANDS_SESSION);
			/*
			 * Si aucune map n'existe, alors initialisation d'une nouvelle map
			 */
			if (commands == null) {
				commands = new HashMap<String, Command>();
			}
			/* Puis ajout de la command courante dans la map */
			commands.put(command.getDate(), command);
			/* Et enfin (ré)enregistrement de la map en session */
			session.setAttribute(COMMANDS_SESSION, commands);

			/* Affichage de la fiche récapitulative */
			this.getServletContext().getRequestDispatcher(VIEW_SUCCES).forward(request, response);
		} else {
			/* Sinon, ré-affichage du formulaire de création avec les erreurs */
			this.getServletContext().getRequestDispatcher(VIEW_FORM).forward(request, response);
		}
	}
}