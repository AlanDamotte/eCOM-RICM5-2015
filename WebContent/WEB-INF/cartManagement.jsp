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
            <c:when test="${ empty sessionScope.cart_products }">
                <p class="erreur">Panier vide.</p>
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
                <c:forEach items="${ sessionScope.cart_products.getMapProducts() }" var="mapCartProducts" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <%-- Affichage des propriétés du bean Client, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                    <td><c:out value="${ mapCartProducts.value.name }"/></td>
                    <td><c:out value="${ mapCartProducts.value.description }"/></td>
                    <td><c:out value="${ mapCartProducts.value.price }"/></td>
                    <td><c:out value="${ sessionScope.cart_products.getQuantity(mapCartProducts.key) }"/></td>
                    <td>
						<form method="post" action="<c:url value="/removeFromCart"/>">
						<label for="quantityCart">Quantité<span class="requis">*</span></label>
                    	<input type="text" id="quantityCart" name="quantityCart" size="30" maxlength="30"/>
                    	<input type="hidden" name="idProduct" value="${ mapCartProducts.key }">
						<input type="submit" value="Modifier la quantité"/>
						</form>
                    </td>
                    <td class="action">
                        <a href="<c:url value="/removeFromCart"><c:param name="idProduct" value="${ mapCartProducts.key }" /></c:url>">
                            <img src="<c:url value="/inc/supprimer.png"/>" alt="Supprimer" />
                        </a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            <p>Total : <c:out value="${ sessionScope.cart_products.getTotal() }"/> euros</p>
            </c:otherwise>
        </c:choose>
        </div>
    </body>
</html>