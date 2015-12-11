package ecom.stickers.forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.joda.time.DateTime;

import ecom.stickers.dao.DAOException;
import ecom.stickers.dao.ProductDaoRemote;
import ecom.stickers.entities.Product;
import eu.medsea.mimeutil.MimeUtil;

public class ProductCreationForm {

	public static final String FIELD_NAMEP = "name";
	public static final String FIELD_DESCRIPTION = "description";
	public static final String FIELD_PRICEP = "price";
	public static final String FIELD_QUANTITY = "quantity";
	public static final String FIELD_DISPONIBILITYP = "availability";
	public static final String FIELD_TAGS = "tags";
	private static final String FIELD_IMAGE = "image";
	
	private static final int BUFFER_SIZE = 10240; // 10ko
	
	private String result;

	private ProductDaoRemote productDao;
	
//	InitialContext ctx;
//	{
//		try {
//			ctx = new InitialContext();
//			productDao = (ProductDaoRemote) ctx.lookup("ProductDao");
//		} catch (NamingException ex) {
//			ex.printStackTrace();
//		}
//	}

	public ProductCreationForm(ProductDaoRemote productDao) {
		this.productDao = productDao;
	}

	private Map<String, String> errors = new HashMap<String, String>();

	public Map<String, String> getErrors() {
		return errors;
	}

	public String getResult() {
		return result;
	}

	public Product createProduct(HttpServletRequest request, String path) {
		
		String namep = getFieldValue(request, FIELD_NAMEP);
		String description = getFieldValue(request, FIELD_DESCRIPTION);
		String pricep = getFieldValue(request, FIELD_PRICEP);
		String quantity = getFieldValue(request, FIELD_QUANTITY);
		String tags = getFieldValue(request, FIELD_TAGS);
		String image = null;
		

		Product product = new Product();

		processNameP(namep, product);
		processDescription(description, product);
		processPriceP(pricep, product);
		processQuantity(quantity, product);
		processTags(tags, product);
		processImage(product, request, path);
		
		DateTime dt = new DateTime();
		product.setDate(dt);

		try {
			if (errors.isEmpty()) {
				productDao.create(product);
				result = "Succès de la création du produit.";
			} else {
				result = "Échec de la création du produit.";
			}
		} catch (DAOException e) {
			setError("imprévu", "Erreur imprévue lors de la création.");
			result = "Échec de la création du client : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}

		return product;
	}

	private void processTags(String tags, Product product) {
		List<String> listTags = getTags(tags);
		product.setTags(listTags);
	}
	
	public List<String> getTags(String tags) {
		return Arrays.asList(tags.split(";"));
	}
	
	private void processNameP(String nameP, Product product) {
		try {
			nameValidation(nameP);
		} catch (FormValidationException e) {
			setError(FIELD_NAMEP, e.getMessage());
		}
		product.setName(nameP);
	}

	private void processDescription(String description, Product product) {
		try {
			descriptionValidation(description);
		} catch (FormValidationException e) {
			setError(FIELD_DESCRIPTION, e.getMessage());
		}
		product.setDescription(description);
	}

	private void processPriceP(String priceP, Product product) {
		double price = -1;
		try {
			price = priceValidation(priceP);
		} catch (FormValidationException e) {
			setError(FIELD_PRICEP, e.getMessage());
		}
		product.setPrice(price);
	}

	private void processQuantity(String quantity, Product product) {
		int q = 0;
		try {
			q = quantityValidation(quantity);
		} catch (FormValidationException e) {
			setError(FIELD_QUANTITY, e.getMessage());
		}
		product.setQuantity(q);
	}


	private void nameValidation(String name) throws FormValidationException {
		if (name != null) {
			if (name.length() < 2) {
				throw new FormValidationException("Le nom du produit doit contenir au moins 2 caractères.");
			}
		} else {
			throw new FormValidationException("Merci d'entrer un nom de produit.");
		}
	}

	private void descriptionValidation(String description) throws FormValidationException {
		//
	}

	private int quantityValidation(String quantity) throws FormValidationException {
		int temp;
		if (quantity != null) {
			try {
				temp = Integer.parseInt(quantity);
				if (temp < 0) {
					throw new FormValidationException("La quantité doit être un nombre positif.");
				}
			} catch (NumberFormatException e) {
				temp = -1;
				throw new FormValidationException("La quantité doit être un nombre.");
			}
		} else {
			temp = -1;
			throw new FormValidationException("Merci d'entrer une quantité");
		}
		return temp;
	}

	private double priceValidation(String price) throws FormValidationException {
		double temp;
		if (price != null) {
			try {
				temp = Double.parseDouble(price);
				if (temp < 0) {
					throw new FormValidationException("Le prix doit être un nombre positif.");
				}
			} catch (NumberFormatException e) {
				temp = -1;
				throw new FormValidationException("Le prix doit être un nombre.");
			}
		} else {
			temp = -1;
			throw new FormValidationException("Merci d'entrer un prix.");
		}
		return temp;
	}
	
	private void processImage(Product product, HttpServletRequest request, String path) {
		String image = null;
		try {
			image = imageValidation(request, path);
		} catch (FormValidationException e) {
			setError(FIELD_IMAGE, e.getMessage());
		}
		product.setImage(image);
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
				 * mystique le path du fichier local à la machine du customer...
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
			 * FormValidationException retournée si la taille des données
			 * dépasse les limites définies dans la section <multipart-config>
			 * de la déclaration de notre servlet d'upload dans le fichier
			 * web.xml
			 */
			e.printStackTrace();
			throw new FormValidationException("Le fichier envoyé ne doit pas dépasser 1Mo.");
		} catch (IOException e) {
			/*
			 * FormValidationException retournée si une erreur au niveau des
			 * répertoires de stockage survient (répertoire inexistant, droits
			 * d'accès insuffisants, etc.)
			 */
			e.printStackTrace();
			throw new FormValidationException("Erreur de configuration du serveur.");
		} catch (ServletException e) {
			/*
			 * FormValidationException retournée si la requête n'est pas de type
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
