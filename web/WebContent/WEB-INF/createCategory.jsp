<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'une catégorie</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/adminMenu.jsp" />
        <div>
            <form method="post" action="<c:url value="/categoryCreation"/>">
                <fieldset>
                    <legend>Catégorie</legend>
                    
                    <label for="support">Support <span class="requis">*</span></label>
                    <input type="text" id="support" name="support" value="<c:out value="${category.support}"/>" size="30" maxlength="30"/>
                    <span class="erreur">${form.errors['support']}</span>
                    <br />
                    
                    <label for="shape">Forme <span class="requis">*</span></label>
                    <input type="text" id="shape" name="shape" value="<c:out value="${category.shape}"/>" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['shape']}</span>
                    <br />
                    
                    <label for="height">Hauteur <span class="requis">*</span></label>
                    <input type="text" id="height" name="height" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['height']}</span>
                    <br />
                    
                    <label for="width">Largeur</label>
                    <input type="text" id="width" name="width" size="30" maxlength="30" />
                    <span class="erreur">${form.errors['width']}</span>
                    <br />
                                        
                    <p class="info">${ form.result }</p>
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
    </body>
</html>