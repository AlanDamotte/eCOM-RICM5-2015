<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Liste des customers existants</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/adminMenu.jsp" />
        <div id="corps">
        <c:choose>
            <%-- Si aucun customer n'existe en session, affichage d'un message par défaut. --%>
            <c:when test="${ empty sessionScope.customers }">
                <p class="erreur">Aucun customer enregistré.</p>
            </c:when>
            <%-- Sinon, affichage du tableau. --%>
            <c:otherwise>
            <table>
                <tr>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Adresse</th>
                    <th>Téléphone</th>
                    <th>Email</th>
                    <th>Image</th>
                    <th class="action">Action</th>                    
                </tr>
                <%-- Parcours de la Map des customers en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ sessionScope.customers }" var="mapCustomers" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <%-- Affichage des propriétés du bean Customer, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                    <td><c:out value="${ mapCustomers.value.lastname }"/></td>
                    <td><c:out value="${ mapCustomers.value.firstname }"/></td>
                    <td><c:out value="${ mapCustomers.value.address }"/></td>
                    <td><c:out value="${ mapCustomers.value.phonenumber }"/></td>
                    <td><c:out value="${ mapCustomers.value.email }"/></td>
                    <td>
                        <%-- On ne construit et affiche un lien vers l'image que si elle existe. --%>
                        <c:if test="${ !empty mapCustomers.value.image }">
                            <c:set var="image"><c:out value="${ mapCustomers.value.image }"/></c:set>
                            <a href="<c:url value="/images/${ image }"/>">Voir</a>
                        </c:if>
                    </td>
                    <%-- Lien vers la servlet de suppression, avec passage du nom du customer - c'est-à-dire la clé de la Map - en paramètre grâce à la balise <c:param/>. --%>
                    <td class="action">
                        <a href="<c:url value="/customerRemoval"><c:param name="idCustomer" value="${ mapCustomers.key }" /></c:url>">
                            <img src="<c:url value="/inc/supprimer.png"/>" alt="Supprimer" />
                        </a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            </c:otherwise>
        </c:choose>
        </div>
    </body>
</html>