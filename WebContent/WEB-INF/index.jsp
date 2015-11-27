<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    </head><body data-spy="scroll">
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
                            
                            
                            
                            
                            
                            <div class="col-sm-2 col-md-2"></div><div class="col-sm-2 col-md-4"></div><div class="col-md-3 col-sm-2 text-center">
                                <a class="btn btn-info btn-lg" href="./cartManagement">Panier</a>
                                <a class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="false" href="#formulaire">Connecte toi</a>
                            </div>
                        </div>
                    </div>
                </div>
                <form method="post" class="navbar-form navbar-right" action="<c:url value="/search"/>">
                    <div class="input-group">
                        <input type="text" id="search" name="search" style="width:150px" class="input-sm form-control" placeholder="Search">
                        <input type="submit" value="Rechercher" class="btn btn-primary btn-sm" />

                    </div>
                </form>
                <nav>
                    <ul class="nav nav-justified nav-tabs">
                        <li class="active">
                            <a href="./index">Accueil</a>
                        </li>
                        <li class="">
                            <a href="./create-your-stick">Imagine ton Sticker</a>
                        </li>
                        <li>
                            <a href="./catalog">&nbsp;Liste des stickers</a>
                        </li>
                        <li>
                            <a href="./contact-us">Nous Contacter</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class=" logging"></div>
            <!-- Jumbotron -->
            <div class="jumbotron">
                <div id="carousel-example" data-interval="4999" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="item active">
                            <img src="bootstrap/img/image-depart.jpg">
                            <div class="carousel-caption">
                                <h2 class="text-muted" contenteditable="false">CrÃ©e ton propre sticker</h2>
                                <p>it can be really fun to create really nice things</p>
                                <p class="text-info">
                                    <button data-toggle="modal" data-backdrop="false" href="#formulaire" class="btn btn-info">Rejoint notre communauté
                                        <p></p>
                                    </button>
                                </p>
                            </div>
                        </div>
                        <div class="item">
                            <img src="bootstrap/img/images-5.png">
                            <div class="carousel-caption">
                                <h2>Create your own stickers</h2>
                                <p>Have fun ;)</p>
                            </div>
                        </div>
                        <div class="item">
                            <img src="bootstrap/img/images-6.jpeg">
                            <div class="carousel-caption">
                                <h2>Create your sticker</h2>
                                <p>last creation of the month</p>
                            </div>
                        </div>
                        <div class="item">
                            <img src="bootstrap/img/images-5.png">
                            <div class="carousel-caption">
                                <h2>Create your own stickers</h2>
                                <p>last creation of the month</p>
                            </div>
                        </div>
                        <div class="item">
                            <img src="boostrap/img/images-5.png">
                            <div class="carousel-caption">
                                <h2>Create your own stickers</h2>
                                <p>last creation of the month</p>
                            </div>
                        </div>
                        <div class="item">
                            <img src="boostrap/img/images-5.png">
                            <div class="carousel-caption">
                                <h2>Create your own stickers</h2>
                                <p>last creation of the month</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div class="col-md-12">
                    <a href="#"><i class="fa fa-3x fa-mobile fa-fw"></i></a>
                </div>
            </div>
            <div class="container">
                <img src="bootstrap/img/images-5.png" width="200" class="img-thumbnail">
                <img src="bootstrap/img/img6.jpg" width="200" class="img-responsive img-thumbnail">
                <img src="bootstrap/img/img7.jpg" width="200" class="img-rounded">
                <img src="bootstrap/img/img4.jpg" width="200" class="img-thumbnail">
                <img src="bootstrap/img/img2.jpg" width="200" class="img-thumbnail">
                <img src="bootstrap/img/img7.jpg" width="200" class="img-thumbnail">
                <img src="bootstrap/img/img4.jpg" width="200" class="img-thumbnail">
                <img src="bootstrap/img/img8.jpg" width="200" class="img-thumbnail">
                <img src="bootstrap/img/img8.jpg" width="200" class="img-thumbnail">
                <img src="bootstrap/img/img8.jpg" width="200" class="img-thumbnail">
            </div>
            <div class="row">
                <div class="col-md-12">
                    <ul class="pager">
                        <li>
                            <a href="#">â  Prev</a>
                        </li>
                        <li>
                            <a href="#">Next  â</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
<!--         <script>
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
                    alert("Ã§a marche pas...");
                  });
                });
              });
        </script> -->
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script>
            $(function (){
          $('a').click(function () {
            with($(this)) {
              button('loading');
              setTimeout(function () {
                button('reset');
              }, 4000);
            }
          })
        });
        </script>
        <!-- Example row of columns -->
        <div class="col-lg-4"></div>
        <footer class="section section-primary">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="row">
                            <div class="col-md-12 hidden-lg hidden-md hidden-sm text-left">
                                <a href="#"><i class="fa fa-3x fa-fw fa-instagram text-inverse"></i></a>
                                <a href="#"><i class="fa fa-3x fa-fw fa-twitter text-inverse"></i></a>
                                <a href="#"><i class="fa fa-3x fa-fw fa-facebook text-inverse"></i></a>
                                <a href="#"><i class="fa fa-3x fa-fw fa-github text-inverse"></i></a>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 hidden-xs text-right">
                                <a href="#"><i class="fa fa-3x fa-fw fa-instagram text-inverse"></i></a>
                                <a href="#"><i class="fa fa-3x fa-fw fa-twitter text-inverse"></i></a>
                                <a href="#"><i class="fa fa-3x fa-fw fa-facebook text-inverse"></i></a>
                                <a href="#"><i class="fa fa-3x fa-fw fa-github text-inverse"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Site footer -->
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <hr>
                    </div>
                </div>
            </div>
        </div>
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <a href="#"><i class="fa fa-3x fa-mobile fa-fw"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <footer class="footer">
            <p>Â© ECOM Groupe 2 2015</p>
        </footer>
        <div class="section">
            <div class="container">
                <div class="row text-center">
                    <div class="col-xs-3 text-center">
                        <a><i class="fa fa-5x fa-fw fa-instagram"></i></a>
                    </div>
                    <div class="col-xs-3">
                        <a><i class="fa fa-5x fa-fw fa-twitter"></i></a>
                    </div>
                    <div class="col-xs-3">
                        <a><i class="fa fa-5x fa-fw fa-facebook"></i></a>
                    </div>
                    <div class="col-xs-3 text-center">
                        <a><i class="fa fa-5x fa-fw fa-github"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="section">
            <div class="container">
                <div class="row text-center">
                    <div class="col-xs-3 text-center">
                        <a><i class="fa fa-5x fa-fw fa-instagram"></i></a>
                    </div>
                    <div class="col-xs-3">
                        <a><i class="fa fa-5x fa-fw fa-twitter"></i></a>
                    </div>
                    <div class="col-xs-3">
                        <a><i class="fa fa-5x fa-fw fa-facebook"></i></a>
                    </div>
                    <div class="col-xs-3 text-center">
                        <a><i class="fa fa-5x fa-fw fa-github"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <!-- /container -->
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="bootstrap/js/ie10-viewport-bug-workaround.js"></script>
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
                                <label for="telephone">* TÃ©lÃ©phone</label>
                                <input type="adresse" class="form-control" name="TÃ©lÃ©phone" id="adresse" placeholder="+33609785992">
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
    

</body></html>