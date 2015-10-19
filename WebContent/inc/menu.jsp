<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="menu">
    <p><a href="<c:url value="/clientCreation"/>">Créer un nouveau client</a></p>
    <p><a href="<c:url value="/commandCreation"/>">Créer une nouvelle commande</a></p>
    <p><a href="<c:url value="/clientsList"/>">Voir les clients existants</a></p>
    <p><a href="<c:url value="/commandsList"/>">Voir les commandes existantes</a></p>
</div>