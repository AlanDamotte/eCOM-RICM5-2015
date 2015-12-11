<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage d'un customer</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/adminMenu.jsp" />
        <div id="corps">
            <p class="info">${ form.result }</p>
            <p>Nom : <c:out value="${ customer.lastname }"/></p>
            <p>Prénom : <c:out value="${ customer.firstname }"/></p>
            <p>Adresse : <c:out value="${ customer.address }"/></p>
            <p>Numéro de téléphone : <c:out value="${ customer.phonenumber }"/></p>
            <p>Email : <c:out value="${ customer.email }"/></p>
        </div>
    </body>
</html>