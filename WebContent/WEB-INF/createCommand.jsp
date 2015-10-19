<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'une Command</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div>
            <form method="post" action="<c:url value="/commandCreation"/>" enctype="multipart/form-data">
                <fieldset>
                    <legend>Informations client</legend>
                    <%-- Si et seulement si la Map des clients en session n'est pas vide, alors on propose un choix à l'utilisateur --%>
                    <c:if test="${ !empty sessionScope.clients }">
                        <label for="newClientChoice">Nouveau client ? <span class="requis">*</span></label>
                        <input type="radio" id="newClientChoice" name="newClientChoice" value="newClient" checked /> Oui
                        <input type="radio" id="newClientChoice" name="newClientChoice" value="oldClient" /> Non
                        <br/><br />
                    </c:if>
                    
                    <c:set var="client" value="${ command.client }" scope="request" />
                    <div id="newClient">
                        <c:import url="/inc/inc_client_form.jsp" />
                    </div>
                    
                    <%-- Si et seulement si la Map des clients en session n'est pas vide, alors on crée la liste déroulante --%>
                    <c:if test="${ !empty sessionScope.clients }">
                    <div id="oldClient">
                        <select name="clientsList" id="clientsList">
                            <option value="">Choisissez un client...</option>
                            <%-- Boucle sur la map des clients --%>
                            <c:forEach items="${ sessionScope.clients }" var="mapClients">
                            <%--  L'expression EL ${mapClients.value} permet de cibler l'objet Client stocké en tant que valeur dans la Map, 
                                  et on cible ensuite simplement ses propriétés nom et prenom comme on le ferait avec n'importe quel bean. --%>
                            <option value="${ mapClients.value.lastname }">${ mapClients.value.firstname } ${ mapClients.value.lastname }</option>
                            </c:forEach>
                        </select>
                    </div>
                    </c:if>
                </fieldset>
                <fieldset>
                    <legend>Informations Command</legend>
                    
                    <label for="dateCommand">Date <span class="requis">*</span></label>
                    <input type="text" id="v" name="dateCommand" value="<c:out value="${Command.date}"/>" size="30" maxlength="30" disabled />
                    <span class="erreur">${form.errors['dateCommand']}</span>
                    <br />
                    
                    <label for="amountCommand">Montant <span class="requis">*</span></label>
                    <input type="text" id="amountCommand" name="amountCommand" value="<c:out value="${Command.montant}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['amountCommand']}</span>
                    <br />
                    
                    <label for="paymentModeCommand">Mode de paiement <span class="requis">*</span></label>
                    <input type="text" id="paymentModeCommand" name="paymentModeCommand" value="<c:out value="${Command.modePaiement}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['paymentModeCommand']}</span>
                    <br />
                    
                    <label for="paymentStatusCommand">Statut du paiement</label>
                    <input type="text" id="paymentStatusCommand" name="paymentStatusCommand" value="<c:out value="${Command.statutPaiement}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['paymentStatusCommand']}</span>
                    <br />
                    
                    <label for="deliveryModeCommand">Mode de livraison <span class="requis">*</span></label>
                    <input type="text" id="deliveryModeCommand" name="deliveryModeCommand" value="<c:out value="${Command.modeLivraison}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['deliveryModeCommand']}</span>
                    <br />
                    
                    <label for="deliveryStatusCommand">Statut de la livraison</label>
                    <input type="text" id="deliveryStatusCommand" name="deliveryStatusCommand" value="<c:out value="${Command.statutLivraison}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['deliveryStatusCommand']}</span>
                    <br />
                    
                    <p class="info">${ form.result }</p>
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
        
        <%-- Inclusion de la bibliothèque jQuery. Vous trouverez des cours sur JavaScript et jQuery aux adresses suivantes :
               - http://www.siteduzero.com/tutoriel-3-309961-dynamisez-vos-sites-web-avec-javascript.html 
               - http://www.siteduzero.com/tutoriel-3-659477-un-site-web-dynamique-avec-jquery.html 
               
             Si vous ne souhaitez pas télécharger et ajouter jQuery à votre projet, vous pouvez utiliser la version fournie directement en ligne par Google :
             <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script> 
        --%>
        <script src="<c:url value="/inc/jquery.js"/>"></script>
        
        <%-- Petite fonction jQuery permettant le remplacement de la première partie du formulaire par la liste déroulante, au clic sur le bouton radio. --%>
        <script>
        	jQuery(document).ready(function(){
        		/* 1 - Au lancement de la page, on cache le bloc d'éléments du formulaire correspondant aux clients existants */
        		$("div#oldClient").hide();
        		/* 2 - Au clic sur un des deux boutons radio "newClientChoice", on affiche le bloc d'éléments correspondant (nouveau ou ancien client) */
                jQuery('input[name=newClientChoice]:radio').click(function(){
                	$("div#newClient").hide();
                	$("div#oldClient").hide();
                    var divId = jQuery(this).val();
                    $("div#"+divId).show();
                });
            });
        </script>
    </body>
</html>