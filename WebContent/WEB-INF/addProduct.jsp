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
    	<c:import url="/inc/menu.jsp" />
        <div>
            <form method="post" action="<c:url value="/productCreation"/>">
                <fieldset>
                    <legend>Informations Produit</legend>
                    
                    <label for="namep">Nom <span class="requis">*</span></label>
                    <input type="text" id="namep" name="namep" value="<c:out value="${product.nameP}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['namep']}</span>
                    <br />
                    
                    <label for="pricep">Prix <span class="requis">*</span></label>
                    <input type="text" id="pricep" name="pricep" value="<c:out value="${product.priceP}"/>" size="30" maxlength="30" />
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
                    
                    <label for="disponibilityp">Disponibilité<span class="requis">*</span></label>
                    <input type="text" id="disponibilityp" name="disponibilityp" value="<c:out value="${product.disponibilityP}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['disponibility']}</span>
                    <br />
                    
                    <p class="info">${ form.result }</p>
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
    </body>
</html>