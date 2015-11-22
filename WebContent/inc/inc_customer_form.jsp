<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<label for="lastnameCustomer">Nom <span class="requis">*</span></label>
<input type="text" id="lastnameCustomer" name="lastnameCustomer" size="30" maxlength="30" />
<span class="erreur">${form.errors['lastnameCustomer']}</span>
<br />

<label for="firstnameCustomer">Prénom </label>
<input type="text" id="firstnameCustomer" name="firstnameCustomer" size="30" maxlength="30" />
<span class="erreur">${form.errors['firstnameCustomer']}</span>
<br />

<label for="password">Mot de passe <span class="requis">*</span></label>
<input type="password" id="password" name="password" size="30" maxlength="60" />
<span class="erreur">${form.errors['password']}</span>
<br />

<label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
<input type="password" id="confirmation" name="confirmation" size="30" maxlength="60" />
<span class="erreur">${form.errors['confirmation']}</span>
<br />

<label for="addressCustomer">Adresse de livraison <span class="requis">*</span></label>
<input type="text" id="addressCustomer" name="addressCustomer" size="30" maxlength="60" />
<span class="erreur">${form.errors['addressCustomer']}</span>
<br />

<label for=postCodeCustomer>Code postal <span class="requis">*</span></label>
<input type="text" id="postCodeCustomer" name="postCodeCustomer" size="30" maxlength="60" />
<span class="erreur">${form.errors['postCodeCustomer']}</span>
<br />

<label for="cityCustomer">Ville<span class="requis">*</span></label>
<input type="text" id="cityCustomer" name="cityCustomer" size="30" maxlength="60" />
<span class="erreur">${form.errors['cityCustomer']}</span>
<br />

<label for="phonenumberCustomer">Numéro de téléphone <span class="requis">*</span></label>
<input type="text" id="phonenumberCustomer" name="phonenumberCustomer" size="30" maxlength="30" />
<span class="erreur">${form.errors['phonenumberCustomer']}</span>
<br />

<label for="emailCustomer">Adresse email</label>
<input type="email" id="emailCustomer" name="emailCustomer" size="30" maxlength="60" />
<span class="erreur">${form.errors['emailCustomer']}</span>
<br />