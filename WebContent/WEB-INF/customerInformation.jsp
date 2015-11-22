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
	<div>
	<c:if test="${sessionScope.cart_products.getTotal() != '0.0'}">
		<c:if test="${!empty sessionScope.customerSession}">
			<p>
				${sessionScope.customerSession.firstname}<br>
				${sessionScope.customerSession.lastname}<br>
				${sessionScope.customerSession.address}<br>
				${sessionScope.customerSession.postCode}<br>
				${sessionScope.customerSession.city}<br>
				${sessionScope.customerSession.phonenumber}<br>
			</p>
			<a href="<c:url value="/paymentManagement"/>">Valider</a>
		</c:if>
	</c:if>
	<c:if test="${empty sessionScope.customerSession}">
		<p>
			<a href="<c:url value="/connection"/>">Connexion</a>
		</p>
		<p>
			<a href="<c:url value="/customerCreation"/>">S'inscrire</a>
		</p>
	</c:if>
	</div>
</body>
</html>