<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
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
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
	rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
	rel="stylesheet" type="text/css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media
    queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<meta charset="utf-8" />
<title>Liste des produits existants</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css"/>" />
</head>

<body>
	<div class="container">
		<!-- The justified navigation menu is meant for single line per list item.
      Multiple lines will require custom code not provided by Bootstrap. -->
		<div class="masthead">
			<div class="section">
				<div class="container">
					<div class="row">
						<div class="col-md-3">
							<h1 class="text-muted">Stick-Gump</h1>
						</div>
						<div class="col-sm-2 col-md-2"></div>
						<div class="col-sm-2 col-md-5"></div>
						<div class="col-md-2 col-sm-2 text-center">
							<c:if test="${empty sessionScope.customerSession}">
								<a class="btn btn-info btn-lg" data-toggle="modal"
									data-backdrop="false" href="#formulaire">Connecte toi</a>
							</c:if>
							<c:if test="${not empty sessionScope.customerSession}">
								<a class="btn btn-info btn-lg" data-toggle="modal"
									data-backdrop="false" href=./disconnection>Déconnexion</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<form method="post" class="navbar-form navbar-right"
				action="<c:url value="/search"/>">
				<div class="input-group">
					<input type="text" id="search" name="search" style="width: 150px"
						class="input-sm form-control" placeholder="Search"> <input
						type="submit" value="Rechercher" class="btn btn-primary btn-sm" />

				</div>
			</form>
			<nav>
				<ul class="nav nav-justified nav-tabs">
					<li class="disabled"><a href="./index">Acueil</a></li>
					<li class=""><a href="./create-your-stick">Imagine ton
							Sticker</a></li>
					<li><a href="./catalog">&nbsp;Liste des stickers</a></li>
					<li><a href="./contact-us">Nous Contacter</a></li>
				</ul>
			</nav>
		</div>
		<div class=" logging"></div>

		<!-- Site footer -->
	</div>
	<!-- /container -->
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
	<div class="section">
		<div class="container">

			<div class="row">
				<h1 class="text-center text-muted">Echec de l'inscription, veuillez réessayer.</h1>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<form method="post" action="<c:url value="/customerCreation"/>">
               <fieldset>
                    <legend>Informations client</legend>
                    <c:import url="/inc/inc_customer_form.jsp" />
                </fieldset>
                <p class="info">${ form.result }</p>
                <input class="btn btn-info" type="submit" value="Valider"  />
                <input class="btn btn-info" type="reset" value="Remettre à zéro" /> <br />
            </form>
				</div>
			</div>
		</div>
</body>
</html>