<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Ajout d'un produit</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
    	<c:import url="/inc/adminMenu.jsp" />
        <div>
            <form method="post" action="<c:url value="/productCreation"/>"  enctype="multipart/form-data">
                <fieldset>
                    <legend>Informations Produit</legend>
                    
                    <label for="name">Nom <span class="requis">*</span></label>
                    <input type="text" id="name" name="name" value="<c:out value="${product.name}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['namep']}</span>
                    <br />
                    
                    <label for="price">Prix <span class="requis">*</span></label>
                    <input type="text" id="price" name="price" value="<c:out value="${product.price}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['pricep']}</span>
                    <br />
                    
                    <label for="quantity">Quantite <span class="requis">*</span></label>
                    <input type="text" id="quantity" name="quantity" value="<c:out value="${product.quantity}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['quantity']}</span>
                    <br />
                    
                    <label for="description">Description</label>
                    <input type="text" id="description" name="description" value="<c:out value="${product.description}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['description']}</span>
                    <br />
                    
                    <label for="tags">Tags<span class="requis">*</span></label>
                    <input type="text" id="tags" name="tags" value="<c:out value="${product.tags}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['tags']}</span>
                    <br />
                    
                    <label for="image">Image<span class="requis">*</span></label>
					<input type="file" id="image" name="image" />
					<span class="erreur">${form.errors['image']}</span>
					<br />
                    
                    <p class="info">${ form.result }</p>
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
    </body>
</html>