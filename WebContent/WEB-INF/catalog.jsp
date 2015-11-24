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
        <div class="section"><div class="container"><div class="row"></div><div class="row"><div class="col-md-4"><div class="col-md-12">
                                <h1 class="text-muted">Stick-Gump</h1>
                            </div></div><div class="col-sm-2 col-md-3"></div><div class="col-sm-2 col-md-2"></div><div class="col-md-3 col-sm-2 text-center">
                                <a class="btn btn-info btn-lg" href="panier.html">&nbsp; Panier &nbsp;&nbsp;</a>
                                <c:if test="${empty sessionScope.customerSession}">
                                <a class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="false" href="#formulaire">Connecte toi</a>
                                </c:if>
                            </div></div></div></div><div class="container">
            <!-- The justified navigation menu is meant for single line per list item.
            Multiple lines will require custom code not provided by Bootstrap. -->
            <div class="masthead">
                
                <form class="navbar-form navbar-right" role="form">
                    <div class="input-group">
                        <input type="text" style="width:150px" class="input-sm form-control" placeholder="Search">
                        <span class="input-group-btn">
                            <button type="submit" class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-eye-open"></span>Search</button>
                        </span>
                    </div>
                </form>
                <nav>
                    <ul class="nav nav-justified">
                        <li>
                            <a href="./index">Accueil</a>
                        </li>
                        <li>
                            <a href="./create-your">Imagine ton Sticker</a>
                        </li>
                        <li class="active">
                            <a href="./catalog">View Sticker List</a>
                        </li>
                        <li>
                            <a href="./contact-us">Nous Contacter</a>
                        </li>
                    </ul>
                </nav>
            </div>
            
            <!-- Site footer -->
        </div>
        <!-- /container -->
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                    
                        <h1 class="text-center text-muted" contenteditable="true">Sticker Gallery</h1>
                    </div>
                    
                </div>
                 <c:forEach items="${ sessionScope.products }" var="mapProducts" varStatus="boucle">
                 <div class="col-md-3">
                 		<c:if test="${ !empty mapProducts.value.image }">
                            <c:set var="image"><c:out value="${ mapProducts.value.image }"/></c:set>
                            <a href="<c:url value="/productView"><c:param name="idProduct" value="${ mapProducts.key }" /></c:url>">
	                            <img src="${pageContext.request.contextPath}/img/${ image }" height="200" width="200"class="img-responsive img-rounded"> 
	                        </a>
                        </c:if>                     </div>
                   </c:forEach>
                 <div class="container">
                    <ul class="pager">
                        <li>
                            <a href="#">←  Prev</a>
                        </li>
                        <li>
                            <a href="view-sticker-page2.html">Next  →</a>
                        </li>
                    </ul>
                </div>
            </div>
             </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <hr>
            </div>
        </div>
        <div id="formulaire" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">x</button>
                        <h4 class="modal-title">Vos infos :</h4>
                    </div>
                     <div class="modal-body">
	                     <form method="post" action="<c:url value="/connection" />">
	                            <div class="form-group">
	                                <label for="name">Adresse email <span class="requis">*</span></label>
					                <input type="email" class="form-control" id="email" name="email" value="<c:out value="${customer.email}"/>" size="20" maxlength="60" placeholder="Adresse"/>
					                <span class="erreur">${form.errors['email']}</span>
	                            </div>
	                            <div class="form-group">
	                                <label for="password">Mot de passe <span class="requis">*</span></label>
					                <input type="password" class="form-control" id="password" name="password" value="" size="20" maxlength="20" placeholder="Mot de passe"/>
					                <span class="erreur">${form.errors['motdepasse']}</span>
	                            </div>
                            <input type="submit" class="btn btn-default">Connexion</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-info" data-dismiss="modal">Annuler</button>
                    </div>
                </div>
            </div>
        </div>
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script>
            $(function(){
                                $("form").submit(function(e) {
                                  e.preventDefault();
                                  var $form = $(this);
                                  $.post($form.attr("action"), $form.serialize())
                                  .done(function(data) {
                                    $("#html").html(data);
                                    $("#formulaire").modal("hide"); 
                                  })
                                  .fail(function() {
                                    alert("ça marche pas...");
                                  });
                                });
                              });
        </script>
    

    </body>
</html>