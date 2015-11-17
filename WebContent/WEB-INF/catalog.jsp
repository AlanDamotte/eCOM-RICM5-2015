<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Liste des produits existants</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div id="corps">
        <c:choose>
            <%-- Si aucun client n'existe en session, affichage d'un message par défaut. --%>
            <c:when test="${ empty sessionScope.products }">
                <p class="erreur">Aucun produit enregistré.</p>
            </c:when>
            <%-- Sinon, affichage du tableau. --%>
            <c:otherwise>
            <table>
                <tr>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Prix</th>
                    <th>Quantité</th>
                    <th>Disponibilité</th>
                    <th class="action">Action</th>                    
                </tr>
                <%-- Parcours de la Map des clients en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ sessionScope.products }" var="mapProducts" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <%-- Affichage des propriétés du bean Client, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                    <td><c:out value="${ mapProducts.value.name }"/></td>
                    <td><c:out value="${ mapProducts.value.description }"/></td>
                    <td><c:out value="${ mapProducts.value.price }"/></td>
                    <td><c:out value="${ mapProducts.value.quantity }"/></td>
                    <td><c:out value="${ mapProducts.value.availability }"/></td>
					<td>
						<form method="post" action="<c:url value="/addToCart"/>">
						<label for="quantityCart">Quantité<span class="requis">*</span></label>
                    	<input type="text" id="quantityCart" name="quantityCart" size="30" maxlength="30" 
                    			constraints="{min:1,max:${mapProducts.value.quantity},places:0}" 
                    			promptMessage= "Entrer une quantité entre 1 et ${mapProducts.value.quantity}"
                                required= "true"
                                invalidMessage= "Valeur Invalide."/>
                    	<input type="hidden" name="idProduct" value="${ mapProducts.key }">
						<input type="submit" value="Valider"/>
						</form>
                    </td>
                    <td>
		                <c:forEach items="${ sessionScope.categories }" var="mapCategory" varStatus="boucle">
		                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
		                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
		                    <%-- Affichage des propriétés du bean Client, qui est stocké en tant que valeur de l'entrée courante de la map --%>
		                    <td><c:out value="${ mapCategory.value.shape }"/></td>
		                    <td><c:out value="${ mapCategory.value.support }"/></td>
		                    <td><c:out value="${ mapCategory.value.dimension.height }"/></td>
		                    <td><c:out value="${ mapCategory.value.dimension.width }"/></td>
		                    </td>
		                </tr>
		                </c:forEach>
                    </td>
                </tr>
                </c:forEach>
            </table>
            </c:otherwise>
        </c:choose>
        </div>
    </body>
</html>