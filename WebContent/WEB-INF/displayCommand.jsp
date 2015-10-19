<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage d'une commande</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div id="corps">
            <p class="info">${ form.result }</p>
            <p>Client</p>
            <p>Nom : <c:out value="${ command.client.lastname }"/></p>
            <p>Prénom : <c:out value="${ command.client.firstname }"/></p>
            <p>Adresse : <c:out value="${ command.client.address }"/></p>
            <p>Numéro de téléphone : <c:out value="${ command.client.phonenumber }"/></p>
            <p>Email : <c:out value="${ command.client.email }"/></p>
            <p>Image : <c:out value="${ commande.client.image }"/></p>
            <p>Commande</p>
            <p>Date  : <c:out value="${ command.date }"/></p> 
            <p>Montant  : <c:out value="${ command.amount }"/></p> 
            <p>Mode de paiement  : <c:out value="${ command.paymentMode }"/></p> 
            <p>Statut du paiement  : <c:out value="${ command.paymentStatus }"/></p> 
            <p>Mode de livraison  : <c:out value="${ command.deliveryMode }"/></p> 
            <p>Statut de la livraison  : <c:out value="${ command.deliveryStatus }"/></p> 
        </div>
    </body>
</html>