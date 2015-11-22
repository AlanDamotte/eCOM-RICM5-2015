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
                	<th>Image</th>                 
                </tr>
                <%-- Parcours de la Map des clients en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ sessionScope.products }" var="mapProducts" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <%-- Affichage des propriétés du bean Client, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                     <td>
                        <%-- On ne construit et affiche un lien vers l'image que si elle existe. --%>
                        <c:if test="${ !empty mapProducts.value.image }">
                            <c:set var="image"><c:out value="${ mapProducts.value.image }"/></c:set>
                            <a href="<c:url value="/productView"><c:param name="idProduct" value="${ mapProducts.key }" /></c:url>">
	                            <img src="${pageContext.request.contextPath}/images/${ image }" height="200" width="200"> 
	                        </a>
                        </c:if>
                    </td>
                </tr>
                </c:forEach>
            </table>
            </c:otherwise>
        </c:choose>
        </div>
    </body>
</html>