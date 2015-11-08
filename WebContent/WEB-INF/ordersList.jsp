<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Liste des orders existantes</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div id="corps">
        <c:choose>
            <%-- Si aucune ordere n'existe en session, affichage d'un message par défaut. --%>
            <c:when test="${ empty sessionScope.orders }">
                <p class="erreur">Aucune ordere enregistrée.</p>
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
                <%-- Parcours de la Map des orders en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ sessionScope.orders }" var="mapOrders" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <%-- Affichage des propriétés du bean Ordere, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                    <td><c:out value="${ mapOrders.value.client.firstname } ${ mapOrders.value.client.lastname }"/></td>
                    <td><joda:format value="${ mapOrders.value.date }" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                    <td><c:out value="${ mapOrders.value.amount }"/></td>
                    <td><c:out value="${ mapOrders.value.paymentMode }"/></td>
                    <td><c:out value="${ mapOrders.value.paymentStatus }"/></td>
                    <td><c:out value="${ mapOrders.value.deliveryMode }"/></td>
                    <td><c:out value="${ mapOrders.value.deliveryStatus }"/></td>
                    <%-- Lien vers la servlet de suppression, avec passage de la date de la ordere - c'est-à-dire la clé de la Map - en paramètre grâce à la balise <c:param/>. --%>
                    <td class="action">
                        <a href="<c:url value="/orderRemoval"><c:param name="idOrder" value="${ mapOrders.key }" /></c:url>">
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