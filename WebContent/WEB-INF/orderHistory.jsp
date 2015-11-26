<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Historique</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css"/>" />
</head>
<body>
	<c:import url="/inc/menu.jsp" />
	<div id="corps">
		<c:choose>
			<%-- Si aucun client n'existe en session, affichage d'un message par défaut. --%>
			<c:when test="${ empty sessionScope.orderHistory }">
				<p class="erreur">Aucune commande effectuée.</p>
			</c:when>
			<%-- Sinon, affichage du tableau. --%>
			<c:otherwise>
				<table>
					<tr>
						<th>Historique de commande</th>
					</tr>
					<%-- Parcours de la Map des clients en session, et utilisation de l'objet varStatus. --%>
					<c:forEach items="${ sessionScope.orderHistory }" var="listOrder" varStatus="boucle">
						<%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
						<tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
							<td>Date de commande : ${ listOrder.order.date } </td>
							<td>
								<table>
									<tr>
										<th>Produit</th>
										<th>Quantité</th>
									</tr>
									<c:forEach items="${ listOrder.order.cart }" var="mapProducts" varStatus="boucle">
										<td>
											<c:if test="${ !empty mapProducts.key.image }">
					                        <c:set var="image"><c:out value="${ mapProducts.key.image }"/></c:set>
						                    <img src="${pageContext.request.contextPath}/img/${ image }" height="200" width="200"class="img-responsive img-rounded"> 
					                        </c:if> 
                        				 </td>
										<%-- <td><c:out value="${ mapProducts.key.name }"/></td> --%>
										<td><c:out value="${ mapProducts.value }"/></td>
									</c:forEach>
								</table>
							
							</td>
							<%-- <c:forEach items="${ listOrder.order.cart }" var="mapProducts" varStatus="boucle">
							Affichage des propriétés du bean Client, qui est stocké en tant que valeur de l'entrée courante de la map
							 <td><c:out value="${ mapProducts.name }" /></td>
							</c:forEach> --%>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>