<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="menu">
	<%--     <p><a href="<c:url value="/customerCreation"/>">Créer un nouveau client</a></p> --%>
	<%--     <p><a href="<c:url value="/orderCreation"/>">Créer une nouvelle commande</a></p> --%>
	<%--     <p><a href="<c:url value="/productCreation"/>">Ajouter un produit</a></p> --%>
	<%--     <p><a href="<c:url value="/customersList"/>">Voir les clients existants</a></p> --%>
	<%--     <p><a href="<c:url value="/ordersList"/>">Voir les commandes existantes</a></p> --%>
	<c:if test="${!empty sessionScope.customerSession}">
		<p class="succes">Bienvenue
			${sessionScope.customerSession.firstname}
			${sessionScope.customerSession.lastname}</p>
		<p>
			<br>
		<p>
			<a href="<c:url value="/orderHistory"/>">Historique de commande</a>
			<a href="<c:url value="/disconnection"/>">Déconnexion</a>
		</p>
	</c:if>
	<c:if test="${empty sessionScope.customerSession}">
		<p>
			<a href="<c:url value="/connection"/>">Connexion</a>

		</p>
		<p>
			<a href="<c:url value="/customerCreation"/>">S'inscrire</a>
		</p>
	</c:if>

	<a href="<c:url value="/catalog"/>">Voir les produits existants</a>
	</p>
	<p>
		<a href="<c:url value="/cartManagement"/>">Voir le panier</a>
	</p>
	<p>
		<a href="<c:url value="/administration"/>">Administration</a>
	</p>
	<form method="post" action="<c:url value="/search"/>">
		<label for="search">Recherche<span class="requis">*</span></label> <input
			type="text" id="search" name="search" size="30" maxlength="30">
		<input type="submit" value="Valider" />
	</form>

</div>