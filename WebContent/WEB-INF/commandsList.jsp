<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Liste des commands existantes</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div id="corps">
        <c:choose>
            <%-- Si aucune commande n'existe en session, affichage d'un message par défaut. --%>
            <c:when test="${ empty sessionScope.commands }">
                <p class="erreur">Aucune commande enregistrée.</p>
            </c:when>
            <%-- Sinon, affichage du tableau. --%>
            <c:otherwise>
            <table>
                <tr>
                    <th>Client</th>
                    <th>Date</th>
                    <th>Montant</th>
                    <th>Mode de paiement</th>
                    <th>Statut de paiement</th>
                    <th>Mode de livraison</th>
                    <th>Statut de livraison</th>
                    <th class="action">Action</th>                    
                </tr>
                <%-- Parcours de la Map des commands en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ sessionScope.commands }" var="mapcommands" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <%-- Affichage des propriétés du bean Commande, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                    <td><c:out value="${ mapcommands.value.client.firstname } ${ mapcommands.value.client.lastname }"/></td>
                    <td><c:out value="${ mapcommands.value.date }"/></td>
                    <td><c:out value="${ mapcommands.value.amount }"/></td>
                    <td><c:out value="${ mapcommands.value.paymentMode }"/></td>
                    <td><c:out value="${ mapcommands.value.paymentStatus }"/></td>
                    <td><c:out value="${ mapcommands.value.deliveryMode }"/></td>
                    <td><c:out value="${ mapcommands.value.deliveryStatus }"/></td>
                    <%-- Lien vers la servlet de suppression, avec passage de la date de la commande - c'est-à-dire la clé de la Map - en paramètre grâce à la balise <c:param/>. --%>
                    <td class="action">
                        <a href="<c:url value="/commandRemoval"><c:param name="dateCommand" value="${ mapcommands.key }" /></c:url>">
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