package com.ecom.forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.ecom.beans.Client;
import eu.medsea.mimeutil.MimeUtil;

public class ClientCreationForm {

	public static final String FIELD_LASTNAME = "lastnameClient";
	public static final String FIELD_FIRSTNAME = "firstnameClient";
	public static final String FIELD_ADDRESS = "addressClient";
	public static final String FIELD_PHONENUMBER = "phonenumberClient";
	public static final String FIELD_EMAIL = "emailClient";
	private static final String FIELD_IMAGE = "imageClient";

	private static final int BUFFER_SIZE = 10240; // 10ko

	private String result;
	private Map<String, String> errors = new HashMap<String, String>();

	public Map<String, String> getErrors() {
		return errors;
	}

	public String getResult() {
		return result;
	}

	public Client createClient(HttpServletRequest request, String path) {
		String lastname = getFieldValue(request, FIELD_LASTNAME);
		String firstname = getFieldValue(request, FIELD_FIRSTNAME);
		String address = getFieldValue(request, FIELD_ADDRESS);
		String phonenumber = getFieldValue(request, FIELD_PHONENUMBER);
		String email = getFieldValue(request, FIELD_EMAIL);
		String image = null;

		Client client = new Client();

		try {
			nameValidation(lastname);
		} catch (Exception e) {
			setError(FIELD_LASTNAME, e.getMessage());
		}
		client.setLastname(lastname);

		try {
			firstnameValidation(firstname);
		} catch (Exception e) {
			setError(FIELD_FIRSTNAME, e.getMessage());
		}
		client.setFirstname(firstname);

		try {
			addressValidation(address);
		} catch (Exception e) {
			setError(FIELD_ADDRESS, e.getMessage());
		}
		client.setAddress(address);

		try {
			phonenumberValidation(phonenumber);
		} catch (Exception e) {
			setError(FIELD_PHONENUMBER, e.getMessage());
		}
		client.setPhonenumber(phonenumber);

		try {
			emailValidation(email);
		} catch (Exception e) {
			setError(FIELD_EMAIL, e.getMessage());
		}
		client.setEmail(email);

		try {

			image = imageValidation(request, path);

		} catch (FormValidationException e) {

			setError(FIELD_IMAGE, e.getMessage());

		}

		client.setImage(image);

		if (errors.isEmpty()) {
			result = "Succès de la création du client.";
		} else {
			result = "Échec de la création du client.";
		}

		return client;
	}

	private void nameValidation(String lastname) throws Exception {
		if (lastname != null) {
			if (lastname.length() < 2) {
				throw new Exception("Le nom d'utilisateur doit contenir au moins 2 caractères.");
			}
		} else {
			throw new Exception("Merci d'entrer un nom d'utilisateur.");
		}
	}

	private void firstnameValidation(String firstname) throws Exception {
		if (firstname != null && firstname.length() < 2) {
			throw new Exception("Le prénom d'utilisateur doit contenir au moins 2 caractères.");
		}
	}

	private void addressValidation(String address) throws Exception {
		if (address != null) {
			if (address.length() < 10) {
				throw new Exception("L'adresse de livraison doit contenir au moins 10 caractères.");
			}
		} else {
			throw new Exception("Merci d'entrer une adresse de livraison.");
		}
	}

	private void phonenumberValidation(String phonenumber) throws Exception {
		if (phonenumber != null) {
			if (!phonenumber.matches("^\\d+$")) {
				throw new Exception("Le numéro de téléphone doit uniquement contenir des chiffres.");
			} else if (phonenumber.length() < 4) {
				throw new Exception("Le numéro de téléphone doit contenir au moins 4 chiffres.");
			}
		} else {
			throw new Exception("Merci d'entrer un numéro de téléphone.");
		}
	}

	private void emailValidation(String email) throws Exception {
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Merci de saisir une adresse mail valide.");
		}
	}

	private String imageValidation(HttpServletRequest request, String path) throws FormValidationException {
		/*
		 * Récupération du contenu du champ image du formulaire. Il faut ici
		 * utiliser la méthode getPart().
		 */
		String fileName = null;
		InputStream fileContent = null;
		try {
			Part part = request.getPart(FIELD_IMAGE);
			fileName = getFileName(part);

			/*
			 * Si la méthode getNomFichier() a renvoyé quelque chose, il s'agit
			 * donc d'un champ de type fichier (input type="file").
			 */
			if (fileName != null && !fileName.isEmpty()) {
				/*
				 * Antibug pour Internet Explorer, qui transmet pour une raison
				 * mystique le path du fichier local à la machine du client...
				 * 
				 * Ex : C:/dossier/sous-dossier/fichier.ext
				 * 
				 * On doit donc faire en sorte de ne sélectionner que le nom et
				 * l'extension du fichier, et de se débarrasser du superflu.
				 */
				fileName = fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);

				/* Récupération du contenu du fichier */
				fileContent = part.getInputStream();

				/* Extraction du type MIME du fichier depuis l'InputStream */
				MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
				Collection<?> mimeTypes = MimeUtil.getMimeTypes(fileContent);

				/*
				 * Si le fichier est bien une image, alors son en-tête MIME
				 * commence par la chaîne "image"
				 */
				if (mimeTypes.toString().startsWith("image")) {
					/* Ecriture du fichier sur le disque */
					writeFile(fileContent, fileName, path);
				} else {
					throw new FormValidationException("Le fichier envoyé doit être une image.");
				}
			}
		} catch (IllegalStateException e) {
			/*
			 * Exception retournée si la taille des données dépasse les limites
			 * définies dans la section <multipart-config> de la déclaration de
			 * notre servlet d'upload dans le fichier web.xml
			 */
			e.printStackTrace();
			throw new FormValidationException("Le fichier envoyé ne doit pas dépasser 1Mo.");
		} catch (IOException e) {
			/*
			 * Exception retournée si une erreur au niveau des répertoires de
			 * stockage survient (répertoire inexistant, droits d'accès
			 * insuffisants, etc.)
			 */
			e.printStackTrace();
			throw new FormValidationException("Erreur de configuration du serveur.");
		} catch (ServletException e) {
			/*
			 * Exception retournée si la requête n'est pas de type
			 * multipart/form-data.
			 */
			e.printStackTrace();
			throw new FormValidationException(
					"Ce type de requête n'est pas supporté, merci d'utiliser le formulaire prévu pour envoyer votre fichier.");
		}

		return fileName;
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setError(String champ, String message) {
		errors.put(champ, message);
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getFieldValue(HttpServletRequest request, String fieldName) {
		String value = request.getParameter(fieldName);
		if (value == null || value.trim().length() == 0) {
			return null;
		} else {
			return value;
		}
	}

	/*
	 * Méthode utilitaire qui a pour unique but d'analyser l'en-tête
	 * "content-disposition", et de vérifier si le paramètre "filename" y est
	 * présent. Si oui, alors le champ traité est de type File et la méthode
	 * retourne son nom, sinon il s'agit d'un champ de formulaire classique et
	 * la méthode retourne null.
	 */
	private static String getFileName(Part part) {
		/*
		 * Boucle sur chacun des paramètres de l'en-tête "content-disposition".
		 */
		for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
			/* Recherche de l'éventuelle présence du paramètre "filename". */
			if (contentDisposition.trim().startsWith("filename")) {
				/*
				 * Si "filename" est présent, alors renvoi de sa valeur,
				 * c'est-à-dire du nom de fichier sans guillemets.
				 */
				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		/* Et pour terminer, si rien n'a été trouvé... */
		return null;
	}

	/*
	 * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre
	 * sur le disque, dans le répertoire donné et avec le nom donné.
	 */
	private void writeFile(InputStream fileContent, String fileName, String path) throws FormValidationException {
		/* Prépare les flux. */
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			/* Ouvre les flux. */
			entree = new BufferedInputStream(fileContent, BUFFER_SIZE);
			sortie = new BufferedOutputStream(new FileOutputStream(new File(path + fileName)), BUFFER_SIZE);

			/*
			 * Lit le fichier reçu et écrit son contenu dans un fichier sur le
			 * disque.
			 */
			byte[] buffer = new byte[BUFFER_SIZE];
			int length = 0;
			while ((length = entree.read(buffer)) > 0) {
				sortie.write(buffer, 0, length);
			}
		} catch (Exception e) {
			throw new FormValidationException("Erreur lors de l'écriture du fichier sur le disque.");
		} finally {
			try {
				sortie.close();
			} catch (IOException ignore) {
			}
			try {
				entree.close();
			} catch (IOException ignore) {
			}
		}
	}
}
