<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Détails</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css"/>" />
</head>
<body>
	<c:import url="/inc/menu.jsp" />
	<div>


		<p>
			<c:out value="${ productView.name }" />
		</p>
		<p>
			<c:out value="${ productView.description }" />
		</p>
		<p>
			<c:out value="${ productView.price }" />
		</p>
		<p>
			<c:out value="${ productView.quantity }" />
		</p>
		<p>
		<form method="post" action="<c:url value="/addToCart"/>">
			<label for="quantityCart">Quantité<span class="requis">*</span></label>
			<input type="text" id="quantityCart" name="quantityCart" size="30"
				maxlength="30"
				constraints="{min:1,max:${productView.quantity},places:0}"
				promptMessage="Entrer une quantité entre 1 et ${productView.quantity}"
				required="true" invalidMessage="Valeur Invalide." /> <input
				type="hidden" name="idProduct" value="${ productView.id }">
			<input type="submit" value="Valider" />
		</form>
		</p>
		<p>
			<c:forEach items="${ sessionScope.categories }" var="mapCategory"
				varStatus="boucle">
				<%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
				<tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
					<%-- Affichage des propriétés du bean Client, qui est stocké en tant que valeur de l'entrée courante de la map --%>
					<td><c:out value="${ mapCategory.value.shape }" /></td>
					<td><c:out value="${ mapCategory.value.support }" /></td>
					<td><c:out value="${ mapCategory.value.dimension.height }" /></td>
					<td><c:out value="${ mapCategory.value.dimension.width }" /></td>
					</td>
				</tr>
			</c:forEach>
		</p>
	</div>
</body>
</html>