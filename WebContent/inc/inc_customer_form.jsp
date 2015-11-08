<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<label for="lastnameCustomer">Nom <span class="requis">*</span></label>
<input type="text" id="lastnameCustomer" name="lastnameCustomer" value="<c:out value="${customer.lastname}"/>" size="30" maxlength="30" />
<span class="erreur">${form.errors['lastnameCustomer']}</span>
<br />

<label for="firstnameCustomer">Prénom </label>
<input type="text" id="firstnameCustomer" name="firstnameCustomer" value="<c:out value="${customer.firstname}"/>" size="30" maxlength="30" />
<span class="erreur">${form.errors['firstnameCustomer']}</span>
<br />

<label for="addressCustomer">Adresse de livraison <span class="requis">*</span></label>
<input type="text" id="addressCustomer" name="addressCustomer" value="<c:out value="${customer.address}"/>" size="30" maxlength="60" />
<span class="erreur">${form.errors['addressCustomer']}</span>
<br />

<label for="phonenumberCustomer">Numéro de téléphone <span class="requis">*</span></label>
<input type="text" id="phonenumberCustomer" name="phonenumberCustomer" value="<c:out value="${customer.phonenumber}"/>" size="30" maxlength="30" />
<span class="erreur">${form.errors['phonenumberCustomer']}</span>
<br />

<label for="emailCustomer">Adresse email</label>
<input type="email" id="emailCustomer" name="emailCustomer" value="<c:out value="${customer.email}"/>" size="30" maxlength="60" />
<span class="erreur">${form.errors['emailCustomer']}</span>
<br />

<label for="imageCustomer">Image</label>
<input type="file" id="imageCustomer" name="imageCustomer" />
<span class="erreur">${form.errors['imageCustomer']}</span>
<br />