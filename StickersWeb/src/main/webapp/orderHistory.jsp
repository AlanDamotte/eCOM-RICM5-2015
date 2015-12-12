<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head
        content must come *after* these tags -->
        <meta name="description" content="test">
        <meta name="author" content="Groupe 2 ECOM">
        <link rel="icon" href="favicon.ico">
        <title>Stick Your Sticker</title>
        <!-- Bootstrap core CSS -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="bootstrap/css/justified-nav.css" rel="stylesheet">
        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]>
            <script src="../../assets/js/ie8-responsive-file-warning.js"></script>
        <![endif]-->
        <script src="bootstrap/js/ie-emulation-modes-warning.js"></script>
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements
        and media queries -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <title>Liste des produits existants</title>
    </head>
<body data-spy="scroll">
        <div class="container">
            <!-- The justified navigation menu is meant for single line per list item.
            Multiple lines will require custom code not provided by Bootstrap. -->
            <div class="masthead">
                <div class="section">
                    <div class="container">
                        <div class="row">
                                    <c:if test="${empty sessionScope.customerSession}">
                        		<div class="col-md-3">
                                <h1 class="text-muted">Stick-Gump</h1>
                            </div>
                            
                            
                            
                            
                            
                            <div class="col-sm-2 col-md-2"></div><div class="col-sm-2 col-md-4"></div><div class="col-md-3 col-sm-2 text-center">
                                <a class="btn btn-info btn-lg" href="./cartManagement">Panier</a>
                                
                                <a class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="false" href="#formulaire">Connecte toi</a>
                            </div>
                        		</c:if>
                        
                        <c:if test="${not empty sessionScope.customerSession}">
                                    <div class="col-md-3">
                                <h1 class="text-muted">Stick-Gump</h1>
                            </div>
                            
                            
                            
                            
                            
                             <div class="col-sm-2 col-md-2"></div>
              <div class="col-md-2 col-sm-2 text-right"></div>
              <div class="col-md-5 col-sm-2 text-center">
                                <a class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="false" href=./orderHistory>&nbsp;Historique &nbsp;</a>

                                <a class="btn btn-info btn-lg" href="./cartManagement">&nbsp; &nbsp; &nbsp;  Panier &nbsp; &nbsp;  &nbsp;</a>
                               
                                <a class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="false" href=./disconnection>Déconnexion</a>
                                
                                 
                            </div>
                            </c:if>
                        </div>
                    </div>
                </div>
                <form method="post" class="navbar-form navbar-right" action="<c:url value="/search"/>">
                    <div class="input-group">
                        <input type="text" id="search" name="search" style="width:150px" class="input-sm form-control" placeholder="Search">
                        <input type="submit" value="Rechercher" class="btn btn-sm btn-info" />

                    </div>
                </form>
                <nav>
                    <ul class="nav nav-justified">
                        <li>
                            <a href="./index">Accueil</a>
                        </li>
                        <li>
                            <a href="./create-your-stick">Imagine ton Sticker</a>
                        </li>
                        <li class="active">
                            <a href="./catalog">Liste de Stickers</a>
                        </li>
                        <li>
                            <a href="./contact-us">Nous Contacter</a>
                        </li>
                    </ul>
                </nav>
            </div>
            
            <!-- Site footer -->
        </div>
        
        
        
        
        <h1 class="text-center text-muted">Historique des derniers Achats</h1>
        
        <c:choose>
			<%-- Si aucun client n'existe en session, affichage d'un message par défaut. --%>
			<c:when test="${ empty sessionScope.orderHistory }">
				<p  class="text-center text-muted" class="erreur">Aucune commande effectuée.</p>
			</c:when>
			<%-- Sinon, affichage du tableau. --%>
			<c:otherwise>
			<form method="post" action="<c:url value="/connection" />">
			<c:forEach items="${ sessionScope.orderHistory }" var="listOrder" varStatus="boucle">
			<fieldset>
						<%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
						<p>    </p>
						<p>    </p>
						<p>    </p>
						<tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}"></tr>
						<h3>  Date de commande : ${ listOrder.order.date } </h3>
						<h4 class="text-center text-muted"> Prix : ${ listOrder.order.amount } </h4>
							
							
							
		<c:forEach items="${ listOrder.order.cart }" var="mapProducts" varStatus="boucle">
    		<div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-3">
             <c:if test="${ !empty mapProducts.key.image }">
					                        <c:set var="image"><c:out value="${ mapProducts.key.image }"/></c:set>
						                    <img src="${pageContext.request.contextPath}/img/${ image }" height="200" width="200"class="img-responsive img-rounded"> 
					                        </c:if> 
          </div>
          <div class="col-md-6">
            <h2>Quantité commande<c:out value="${ mapProducts.value }"/></h2>		
            
          </div>
      </div>
    </div></div>
    
</c:forEach></fieldset></form></c:forEach>
			</c:otherwise>
		</c:choose>
        
        
 <div class="section">
            <div class="container">
                <footer class="footer">
                    <p>© ECOM 2015</p>
                </footer>
            </div>
        </div>
	
</body>
</html>