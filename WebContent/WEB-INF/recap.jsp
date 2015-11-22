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
			<p>
				${sessionScope.customerSession.firstname}<br>
				${sessionScope.customerSession.lastname}<br>
				${sessionScope.customerSession.address}<br>
				${sessionScope.customerSession.postCode}<br>
				${sessionScope.customerSession.city}<br>
				${sessionScope.customerSession.phonenumber}<br>
			</p>
			<a href="<c:url value="/createOrder"/>">Payer</a>
	</div>
</body>
</html>