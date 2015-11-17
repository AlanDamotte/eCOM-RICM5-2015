<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage d'une order</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/adminMenu.jsp" />
        <div id="corps">
            <p class="info">${ form.result }</p>
            <p>Client</p>
            <p>Nom : <c:out value="${ order.client.lastname }"/></p>
            <p>Prénom : <c:out value="${ order.client.firstname }"/></p>
            <p>Adresse : <c:out value="${ order.client.address }"/></p>
            <p>Numéro de téléphone : <c:out value="${ order.client.phonenumber }"/></p>
            <p>Email : <c:out value="${ order.client.email }"/></p>
            <p>Image : <c:out value="${ order.client.image }"/></p>
            <p>Ordere</p>
            <p>Date  : <joda:format value="${ order.date }" pattern="dd/MM/yyyy HH:mm:ss"/></p> 
            <p>Montant  : <c:out value="${ order.amount }"/></p> 
            <p>Mode de paiement  : <c:out value="${ order.paymentMode }"/></p> 
            <p>Statut du paiement  : <c:out value="${ order.paymentStatus }"/></p> 
            <p>Mode de livraison  : <c:out value="${ order.deliveryMode }"/></p> 
            <p>Statut de la livraison  : <c:out value="${ order.deliveryStatus }"/></p> 
        </div>
    </body>
</html>