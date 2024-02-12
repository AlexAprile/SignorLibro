<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accedi</title>
  <link rel="icon" type="image/x-icon" href="./icons/logoBankai.png">
  <link rel="stylesheet" type="text/css" href="./CSS/styles.css">
  <script src="script/FormValidationCliente.js"></script>
</head>

<% String errorMessage = (String) request.getAttribute("errorMessageLogIn"); %>
<% if (errorMessage != null && errorMessage.length() > 0) { %>
<script>
  var errorMessage = <%= new com.google.gson.Gson().toJson(errorMessage) %>;
  alert(errorMessage);
</script>
<% } %>

<body>
<div class="titolo">
  <img src="./icons/logoBankai.png" alt="Bankai Logo" title="Bankai Logo">
</div>

<h1 class="titolo">Accedi al tuo account:</h1>
<fieldset class="titolo">
  <legend>Bentornato!</legend>

  <form action="log-in" method="post">
    <label for="email">e-Mail</label>
    <input type="email" id="email" name="email"
           placeholder="nomecognome@bankai.it"
    onkeyup="emailValidation(this.form.email)"><br><br>

    <label for="password">password</label>
    <input type="password" id="password" name="password"
    onkeyup="passwordValidation(this.form.password)"><br><br>

    <input type="submit" value="Accedi">
  </form>
</fieldset>

</body>
</html>
