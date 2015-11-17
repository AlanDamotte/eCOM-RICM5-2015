<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="menu">
    <p><a href="<c:url value="/customerCreation"/>">Créer un nouveau client</a></p>
    <p><a href="<c:url value="/orderCreation"/>">Créer une nouvelle commande</a></p>
    <p><a href="<c:url value="/productCreation"/>">Ajouter un produit</a></p>
    <p><a href="<c:url value="/categoryCreation"/>">Ajouter une catégorie</a></p>
    <p><a href="<c:url value="/customersList"/>">Voir les clients existants</a></p>
    <p><a href="<c:url value="/ordersList"/>">Voir les commandes existantes</a></p>
    <p><a href="<c:url value="/productsList"/>">Voir les produits existants</a></p>
</div>