<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'une order</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div>
            <form method="post" action="<c:url value="/orderCreation"/>" enctype="multipart/form-data">
                <fieldset>
                    <legend>Informations client</legend>
                    <%-- Si et seulement si la Map des clients en session n'est pas vide, alors on propose un choix à l'utilisateur --%>
                    <c:if test="${ !empty sessionScope.clients }">
                        <label for="newClientChoice">Nouveau client ? <span class="requis">*</span></label>
                        <input type="radio" id="newClientChoice" name="newClientChoice" value="newClient" checked /> Oui
                        <input type="radio" id="newClientChoice" name="newClientChoice" value="oldClient" /> Non
                        <br/><br />
                    </c:if>
                    
                    <c:set var="client" value="${ order.client }" scope="request" />
                    <div id="newClient">
                        <c:import url="/inc/inc_customer_form.jsp" />
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
                    <legend>Informations order</legend>
                    
                    <label for="dateOrder">Date <span class="requis">*</span></label>
                    <input type="text" id="dateOrder" name="dateOrder" value="<c:out value="${order.date}"/>" size="30" maxlength="30" disabled />
                    <span class="erreur">${form.errors['dateOrder']}</span>
                    <br />
                    
                    <label for="amountOrder">Montant <span class="requis">*</span></label>
                    <input type="text" id="amountOrder" name="amountOrder" value="<c:out value="${order.amount}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['amountOrder']}</span>
                    <br />
                    
                    <label for="paymentModeOrder">Mode de paiement <span class="requis">*</span></label>
                    <input type="text" id="paymentModeOrder" name="paymentModeOrder" value="<c:out value="${order.paymentMode}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['paymentModeOrder']}</span>
                    <br />
                    
                    <label for="paymentStatusOrder">Statut du paiement</label>
                    <input type="text" id="paymentStatusOrder" name="paymentStatusOrder" value="<c:out value="${order.paymentStatus}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['paymentStatusOrder']}</span>
                    <br />
                    
                    <label for="deliveryModeOrder">Mode de livraison <span class="requis">*</span></label>
                    <input type="text" id="deliveryModeOrder" name="deliveryModeOrder" value="<c:out value="${order.deliveryMode}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['deliveryModeOrder']}</span>
                    <br />
                    
                    <label for="deliveryStatusOrder">Statut de la livraison</label>
                    <input type="text" id="deliveryStatusOrder" name="deliveryStatusOrder" value="<c:out value="${order.deliveryStatus}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['deliveryStatusOrder']}</span>
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