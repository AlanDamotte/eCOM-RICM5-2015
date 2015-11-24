<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Informations de payment</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div>
        	<c:if test="${!empty sessionScope.paymentStatus}">
                    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
                	<p >Une erreur est survenue durant le paiement, veuillez vérifier vos coordonnées bancaire ou contacter votre banque.</p>
            </c:if>
            <form method="post" action="<c:url value="/paymentManagement"/>">
                <fieldset>
                    <legend>Informations de payment</legend>
                    
                    <label for="cardName">Nom du titulaire de la carte <span class="requis">*</span></label>
                    <input type="text" id="cardName" name="cardName" size="30" maxlength="30"/>
                    <span class="erreur">${form.errors['support']}</span>
                    <br />
                    
                    <label for="cardNumber">Numéro de carte bancaire <span class="requis">*</span></label>
                    <input type="text" id="cardNumber" name="cardNumber" size="30" maxlength="30"/>
                    <span class="erreur">${form.errors['support']}</span>
                    <br />
                    
                    <label for="securityCode">Code de sécurité <span class="requis">*</span></label>
                    <input type="text" id="securityCode" name="securityCode" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['shape']}</span>
                    <br />
                                        
                    <p class="info">${ form.result }</p>
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
    </body>
</html>