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
        <c:import url="/inc/adminMenu.jsp" />
        <div id="corps">
        <c:choose>
            <%-- Si aucun client n'existe en session, affichage d'un message par défaut. --%>
            <c:when test="${ empty sessionScope.products }">
                <p class="erreur">Aucun produit enregistré.</p>
            </c:when>
            <%-- Sinon, affichage du tableau. --%>
            <c:otherwise>
            <table>
                <tr>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Prix</th>
                    <th>Quantité</th>
                    <th>Disponibilité</th>
                    <th class="action">Action</th>                    
                </tr>
                <%-- Parcours de la Map des clients en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ sessionScope.products }" var="mapProducts" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <%-- Affichage des propriétés du bean Client, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                    <td><c:out value="${ mapProducts.value.name }"/></td>
                    <td><c:out value="${ mapProducts.value.description }"/></td>
                    <td><c:out value="${ mapProducts.value.price }"/></td>
                    <td><c:out value="${ mapProducts.value.quantity }"/></td>
                    <td><c:out value="${ mapProducts.value.availability }"/></td>
                    <td class="action">
                        <a href="<c:url value="/productRemoval"><c:param name="idProduct" value="${ mapProducts.key }" /></c:url>">
                            <img src="<c:url value="/inc/supprimer.png"/>" alt="Supprimer" />
                        </a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            </c:otherwise>
        </c:choose>
        </div>
        <div class="section"><div class="container"><div class="row"><div class="col-md-12"></div><div class="row">
                            <div class="col-md-3">
                                <h1 class="text-muted">Stick-Gump</h1>
                            </div>
                            
                           
                            
                            <div class="col-md-6">
                            <a class="btn btn-info btn-lg" href="panier.jsp">Panier</a>
                             <a class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="false" href="#formulaire">Sign up </a>
                             </div>
                            </div></div></div>
        <div class="container">
            <!-- The justified navigation menu is meant for single line per list item.
            Multiple lines will require custom code not provided by Bootstrap. -->
            <div class="masthead">
                <form class="navbar-form navbar-right text-right" role="form">
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
                            <a href="index.jsp">Home</a>
                        </li>
                        <li>
                            <a href="create-your.jsp">Create Your Sticker</a>
                        </li>
                        <li class="active">
                            <a href="view-sticker.jsp">View Sticker List</a>
                        </li>
                        <li>
                            <a href="contact-us.jsp">Contact Us</a>
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
                    <a id="rt" href="affiche-image.html"><img src="<td><c:out value="${ mapProducts.value.image }"/></td>" class="img-responsive img-rounded"></a>
                     </div>
                   </c:forEach>
                <div class="col-md-3">
                    <a id="rt" href="affiche-image.html"><img src="cootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                    <a href="affiche-image.html"><img src="cootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                </div>
                <div class="col-md-3">
                    <a href="affiche-image.html" id="rt"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                </div>
                <div class="col-md-3">
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                </div>
                <div class="col-md-3">
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive"></a>
                </div>
                <div class="col-md-3">
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                </div>
                <div class="col-md-3">
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                </div>
                <div class="col-md-3">
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                </div>
                <div class="col-md-3">
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                </div>
                <div class="col-md-3">
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                </div>
                <div class="col-md-3">
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                    <a href="affiche-image.html" style="opacity: 0.5;"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                    <ul class="pager">
                        <li>
                            <a href="#">←  Prev</a>
                        </li>
                        <li>
                            <a href="view-sticker-page2.html">Next  →</a>
                        </li>
                    </ul>
                </div>
                <div class="col-md-3">
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                </div>
                <div class="col-md-3">
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
                    <a href="affiche-image.html"><img src="bootstrap/img/SensorsEVERYWHERE.jpg" class="img-responsive img-rounded"></a>
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
	                        <form action="test.php">
	                            <div class="form-group">
	                                <label for="nom">* Nom</label>
	                                <input type="text" class="form-control" name="nom" id="nom" placeholder="Votre nom">
	                            </div>
	                            <div class="form-group">
	                                <label for="email">* Email</label>
	                                <input type="email" class="form-control" name="email" id="email" placeholder="Votre Email">
	                            </div>
	                            <div class="form-group">
	                                <label for="adresse">* Adresse</label>
	                                <input type="adresse" class="form-control" name="adresse" id="adresse" placeholder="Rue des asticots">
	                            </div>
	                            <div class="form-group">
	                                <label for="code-postal">* Code-postal</label>
	                                <input type="adresse" class="form-control" name="adresse" id="adresse" placeholder="38400">
	                            </div>
	                            <div class="form-group">
	                                <label for="telephone">* Téléphone</label>
	                                <input type="adresse" class="form-control" name="Téléphone" id="adresse" placeholder="+33609785992">
	                            </div>
                            <button type="submit" class="btn btn-default">Envoyer</button>
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