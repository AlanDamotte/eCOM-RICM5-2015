<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<label for="lastnameCustomer">Nom <span class="requis">*</span></label>
<input class="form-control" placeholder="Votre Nom" type="text" id="lastnameCustomer" name="lastnameCustomer" value="<c:out value="${customer.lastname}"/>" size="30" maxlength="30" />
<span class="erreur">${form.errors['lastnameCustomer']}</span>
<br />

<label for="firstnameCustomer">Prénom </label>
<input class="form-control" placeholder="Votre Prenom" type="text" id="firstnameCustomer" name="firstnameCustomer" value="<c:out value="${customer.firstname}"/>" size="30" maxlength="30" />
<span class="erreur">${form.errors['firstnameCustomer']}</span>
<br />

<label for="password">Mot de passe <span class="requis">*</span></label>
<input class="form-control" placeholder="Mots passe" type="password" id="password" name="password" size="30" maxlength="60" />
<span class="erreur">${form.errors['password']}</span>
<br />

<label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
<input class="form-control" placeholder="Mots passe" type="password" id="confirmation" name="confirmation" size="30" maxlength="60" />
<span class="erreur">${form.errors['confirmation']}</span>
<br />

<label for="addressCustomer">Adresse de livraison <span class="requis">*</span></label>
<input class="form-control" placeholder="rue des assticots" type="text" id="addressCustomer" name="addressCustomer" value="<c:out value="${customer.address}"/>" size="30" maxlength="60" />
<span class="erreur">${form.errors['addressCustomer']}</span>
<br />

<label for=postCodeCustomer>Code postal <span class="requis">*</span></label>
<input class="form-control" placeholder="74100" type="text" id="postCodeCustomer" name="postCodeCustomer" value="<c:out value="${customer.postCode}"/>" size="30" maxlength="60" />
<span class="erreur">${form.errors['postCodeCustomer']}</span>
<br />

<label for="cityCustomer">Ville<span class="requis">*</span></label>
<input class="form-control" placeholder="Vetraz Morel" type="text" id="cityCustomer" name="cityCustomer" value="<c:out value="${customer.city}"/>" size="30" maxlength="60" />
<span class="erreur">${form.errors['cityCustomer']}</span>
<br />

<label for="phonenumberCustomer">Numéro de téléphone <span class="requis">*</span></label>
<input class="form-control" placeholder="0657290102" type="text" id="phonenumberCustomer" name="phonenumberCustomer" value="<c:out value="${customer.phonenumber}"/>" size="30" maxlength="30" />
<span class="erreur">${form.errors['phonenumberCustomer']}</span>
<br />

<label for="emailCustomer">Adresse email</label>
<input class="form-control" placeholder="alice@ca-glice.fr" type="email" id="emailCustomer" name="emailCustomer" value="<c:out value="${customer.email}"/>" size="30" maxlength="60" />
<span class="erreur">${form.errors['emailCustomer']}</span>
<br />