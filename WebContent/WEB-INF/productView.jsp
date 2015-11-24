<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html><head>
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
  </head><body>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="section">
            <div class="container">
              <div class="row"></div>
              <div class="row">
                <div class="col-md-3">
                  <div class="col-md-12">
                    <h1 class="text-muted">Stick-Gump</h1>
                  </div>
                </div>
                <div class="col-sm-2 col-md-3"></div>
                <div class="col-sm-2 col-md-3"></div>
                <div class="col-md-3 col-sm-2 text-right">
                  <a class="btn btn-info btn-lg" href="panier.html">Panier</a>
                  <a class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="false" href="#formulaire">Connecte toi</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <!-- The justified navigation menu is meant for single line per list item.
      Multiple lines will require custom code not provided by Bootstrap. -->
      <div class="masthead">
        <form class="navbar-form navbar-right" role="form">
          <div class="input-group">
            <input type="text" style="width:150px" class="input-sm form-control" placeholder="Search">
            <span class="input-group-btn">
              <button type="submit" class="btn btn-primary btn-sm">
                <span class="glyphicon glyphicon-eye-open"></span>Search
                <i class="fa fa-star fa-fw"></i>
              </button>
            </span>
          </div>
        </form>
        <nav>
          <ul class="nav nav-justified">
            <li>
              <a href="/addToCart">Home</a>
            </li>
            <li class="disabled">
              <a href="create-your.jsp">Create Your Sticker</a>
            </li>
            <li>
              <a href="catalog.jsp">View Sticker List</a>
            </li>
            <li>
              <a href="contact-us.jsp">Contact Us</a>
            </li>
          </ul>
        </nav>
      </div>
      <!-- Site footer -->
    </div>
    <div class="section">
      <div class="container">
        <div class="row" style="display: block;">
          <div class="col-md-6" style="display: block;">
            <h1>Name:</h1>
            <p>
			<c:out value="${ productView.name }" />

			</p>
            <h1>Quantity</h1>
            <p>

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
			
		
		</p>
            <h1>
              </select>Description:</h1>
            <p>
			<c:out value="${ productView.description }" />
		</p>
            <h1>
              <select class="form-control">
                <option>Rond</option>
                <option>Carré</option>
                <option>Rectangle</option>
                <option>Triangulaire</option>
              </select>
            </h1>
            <h1>Price:</h1>
           <p>
			<c:out value="${ productView.price }" />
		</p>
          </div>
          <div class="col-md-6">
            <img src="${pageContext.request.contextPath}/img/${ productView.image }" class="img-responsive img-thumbnail" id="rt" height="400" width="400" >
          </div>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <hr>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6 text-center">
          <input type="submit" value="Valider" class="btn btn-info btn-lg"/>
            
            </form>
          </div>
          <div class="col-md-6 text-center">
            <a  href="index.html" class="btn btn-info btn-lg" >&nbsp; Retour &nbsp;&nbsp;</a>
          </div>
        </div>
      </div>
    </div>
    <p>© ECOM Groupe 2 2015</p>
    <div class="row">
      <div class="col-md-12">
        <hr>
      </div>
    </div>
    <br>
    <div class="modal fade" id="formulaire">
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
  

</body></html>