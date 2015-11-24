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
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements
        and media queries -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head><body>
        <div class="container">
            <!-- The justified navigation menu is meant for single line per list item.
            Multiple lines will require custom code not provided by Bootstrap. -->
            <div class="masthead">
                <div class="section">
                    <div class="section"><div class="container"><div class="row"></div><div class="row"><div class="col-md-3"><div class="col-md-12">
                                <h1 class="text-muted">Stick-Gump</h1>
                            </div></div><div class="col-sm-2 col-md-3"></div><div class="col-sm-2 col-md-3"></div><div class="col-md-3 col-sm-2 text-center">
                                <a class="btn btn-info btn-lg" href="./cartManagement">Panier</a>
                                <a class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="false" href="#formulaire">Connecte toi</a>
                            </div></div></div></div>
                </div>
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
                            <a href="./create-your-stick">Imagine ton Sticker</a>
                        </li>
                        <li>
                            <a href="./catalog">Liste des Stickers</a>
                        </li>
                        <li class="active">
                            <a href="./contact-us">Nous Contacter</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <!-- Site footer -->
        </div>
        <!-- /container -->
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="bootstrap/js/ie10-viewport-bug-workaround.js"></script>
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12"></div>
                    <h1 class="text-center text-muted" contenteditable="true">The creators</h1>
                </div>
            </div>
        </div>
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <img src="file:///Users/quentintorck/Pictures/fleche-blanche-droite.png" class="center-block img-circle img-responsive">
                        <h3 class="text-center">Quentin Torck</h3>
                        <p class="text-center">Scrum Master</p>
                    </div>
                    <div class="col-md-4">
                        <img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" class="center-block img-circle img-responsive">
                        <h3 class="text-center">J�remy Hamerer</h3>
                        <p class="text-center">Developer</p>
                    </div>
                    <div class="col-md-4">
                        <img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" class="center-block img-circle img-responsive">
                        <h3 class="text-center">Rama Codadzy</h3>
                        <p class="text-center">Developer</p>
                    </div>
                    <div class="col-md-4">
                        <img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" class="center-block img-circle img-responsive">
                        <h3 class="text-center">Alan Damote</h3>
                        <p class="text-center">Chef de Projet</p>
                    </div>
                    <div class="col-md-4">
                        <img src="favicon.ico" class="center-block img-circle img-responsive" width="1000" height="1000">
                        <h3 class="text-center">John Doe</h3>
                        <p class="text-center">Developer</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="section">
            <div class="container">
                <footer class="footer">
                    <p>� ECOM 2015</p>
                </footer>
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
                                <label for="telephone">* T�l�phone</label>
                                <input type="adresse" class="form-control" name="T�l�phone" id="adresse" placeholder="+33609785992">
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
                                    alert("�a marche pas...");
                                  });
                                });
                              });
        </script>
    

</body></html>