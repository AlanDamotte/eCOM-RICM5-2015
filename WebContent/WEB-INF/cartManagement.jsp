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
    <!-- Fonts -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media
    queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
        <meta charset="utf-8" />
        <title>Liste des produits existants</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
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

                                <a class="btn btn-info btn-lg" href="./cartManagement">&nbsp;  Panier   &nbsp;</a>
                               
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
      <!-- Jumbotron -->
      <h1 class="text-center text-muted">Contenu du Panier
        <br>
        <br>
      </h1>
      <div class="row">
        <div class="col-md-12">
          <hr>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <a href="#"><i class="fa fa-3x fa-mobile fa-fw"></i></a>
        </div>
      </div>
    <c:forEach items="${ sessionScope.cart_products.getMapProducts() }" var="mapCartProducts" varStatus="boucle">
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-3">
             <img src="${pageContext.request.contextPath}/img/${ mapCartProducts.value.image }" class="img-responsive img-thumbnail" id="rt" height="400" width="400" >
          </div>
          <div class="col-md-6">
            <h2><c:out value="${ mapCartProducts.value.name }"/></h2>
            <p>Description :<c:out value="${ mapCartProducts.value.description }"/></p>
            <p>Prix :<c:out value="${ mapCartProducts.value.price }"/><p>		
            
						<form method="post" action="<c:url value="/removeFromCart"/>">
						<label for="quantityCart">Quantité :<c:out value="${ sessionScope.cart_products.getQuantity(mapCartProducts.key) }"/>		
                     	<span class="requis"></span></label>
                    	<input type="text" id="quantityCart" name="quantityCart" size="30" maxlength="30"/>
                    	<input type="hidden" name="idProduct" value="${ mapCartProducts.key }">
						<input type="submit" class="btn btn-info btn-sm" value="Modifier" />
						 </form>
                    
            <a href="<c:url value="/removeFromCart"><c:param name="idProduct" value="${ mapCartProducts.key }" /></c:url>" class="btn btn-info btn-lg">Supprimer</a>
          </div>
         
          <div class="col-md-3"> <p>Total : <c:out value="${ sessionScope.cart_products.getTotal() }"/> euros</p> </div>
          
        </div>
      </div>
    </div>
</c:forEach>
    
    
    
       
        <div class="row">
          <div class="col-md-12">
            <div class="container">
              <div class="row">
                <div class="col-md-12">
                  <hr>
                </div>
              </div>
              <div class="row">
                <div class="col-md-5 text-left">
                 <p>Total : <c:out value="${ sessionScope.cart_products.getTotal() }"/> euros</p>
            <a href="<c:url value="/customerInformationManagement"></c:url>">
            </a>  <c:if test="${empty sessionScope.customerSession}">
                                <a class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="false" href="#formulaire">Payer</a>
                                </c:if>
                                <c:if test="${not empty sessionScope.customerSession}">
                                <a class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="false" href="./paymentManagement">Payer</a>
                                 </c:if>
                 
                </div>
                <div class="col-md-6 text-right">
                  <a class="btn btn-lg btn-warning" href="./index">&nbsp; &nbsp;Annuler &nbsp;&nbsp;</a>
                </div>
              </div>
            </div>
          </div>
        </div>
        
        
        
          <div id="formulaireConnexion" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">x</button>
                        <h4 class="modal-title">Vos infos :</h4>
                    </div>
                     <form method="post" action="<c:url value="/connection" />">
            
				<div class="modal-body">
                <label for="email">  Email<span class="requis">*</span></label>
                <input type="email" class="form-control" name="email" id="email" placeholder="Votre Email"  value="<c:out value="${customer.email}"/>">
                <span class="erreur">${form.errors['email']}</span>
                <br />

                <label for="password">  Mot de passe <span class="requis">*</span></label>
                <input type="password" class="form-control" id="password" name="password"placeholder="Votre Mots de passe" value="" size="20" maxlength="20" />
                <span class="erreur">${form.errors['motdepasse']}</span>
                <br />
            
                <label for="memory">  Se souvenir de moi</label>
                <input type="checkbox" id="memory" name="memory" />
                <br />

                <button type="submit" class="btn btn-default">Envoyer</button>
                <br />
                
                <p class="${empty form.errors ? 'succes' : 'erreur'}">${form.result}</p>
                
                <%-- Vérification de la présence d'un objet utilisateur en session --%>
                <c:if test="${!empty sessionScope.customerSession}">
                    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
                	<p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.customerSession.email}</p>
                </c:if>
                </div>
           
        </form>
                    <div class="modal-footer">
                        <button class="btn btn-info" data-dismiss="modal">Annuler</button>
                    </div>
                </div>
            </div>
        </div>
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <div class="section">
            <div class="container">
                <footer class="footer">
                    <p>© ECOM 2015</p>
                </footer>
            </div>
        </div>
                    
          
     <div id="formulaire" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">x</button>
            <h4 class="modal-title">Connections</h4>
          </div>
           <div class="modal-body">
            
              <a class="btn btn-info btn-lg" class="close" data-toggle="modal"data-dismiss="modal" data-backdrop="false" href="#formulaireInscription">Inscription</a>
           	 <a class="btn btn-info btn-lg" class="close" data-toggle="modal" data-dismiss="modal"data-backdrop="false" href="#formulaireConnexion">Connexion</a>
          </div>
          
        </div>
      </div>
    </div>
          
    
    
    
    
    
    <div id="formulaireInscription" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">x</button>
            <h4 class="modal-title">Informations client</h4>
          </div>
          
          <div class="modal-body">
            <form method="post" action="<c:url value="/inscription"/>">
              <div class="form-group">
              <c:import url="/inc/inc_customer_form.jsp" />
               <p class="info">${ form.result }</p>
              </div>
              <button type="submit" class="btn btn-default">Envoyer</button>
            </form>
          </div>
          <div class="modal-footer">
            <button type="reset" class="btn btn-info" data-dismiss="modal">Annuler</button>
          </div>
        </div>
      </div>
    </div>
  

</body></html>