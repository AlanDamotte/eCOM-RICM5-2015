<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<label for="lastnameClient">Nom <span class="requis">*</span></label>
<input type="text" id="lastnameClient" name="lastnameClient" value="<c:out value="${client.lastname}"/>" size="30" maxlength="30" />
<span class="erreur">${form.errors['lastnameClient']}</span>
<br />

<label for="firstnameClient">Prénom </label>
<input type="text" id="firstnameClient" name="firstnameClient" value="<c:out value="${client.firstname}"/>" size="30" maxlength="30" />
<span class="erreur">${form.errors['firstnameClient']}</span>
<br />

<label for="addressClient">Adresse de livraison <span class="requis">*</span></label>
<input type="text" id="addressClient" name="addressClient" value="<c:out value="${client.address}"/>" size="30" maxlength="60" />
<span class="erreur">${form.errors['addressClient']}</span>
<br />

<label for="phonenumberClient">Numéro de téléphone <span class="requis">*</span></label>
<input type="text" id="phonenumberClient" name="phonenumberClient" value="<c:out value="${client.phonenumber}"/>" size="30" maxlength="30" />
<span class="erreur">${form.errors['phonenumberClient']}</span>
<br />

<label for="emailClient">Adresse email</label>
<input type="email" id="emailClient" name="emailClient" value="<c:out value="${client.email}"/>" size="30" maxlength="60" />
<span class="erreur">${form.errors['emailClient']}</span>
<br />

<label for="imageClient">Image</label>
<input type="file" id="imageClient" name="imageClient" />
<span class="erreur">${form.errors['imageClient']}</span>
<br />