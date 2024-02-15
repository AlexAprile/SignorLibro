<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accedi</title>
  <link rel="icon" type="image/x-icon" href="./icons/logoBankai.png">
  <link rel="stylesheet" type="text/css" href="./CSS/styles.css">
  <script src="script/FormValidationCliente.js"></script>
</head>


<body>
<div class="titolo">
  <img src="./icons/logoBankai.png" alt="Bankai Logo" title="Bankai Logo">
</div>

<h1 class="titolo">Accedi al tuo account:</h1>
<fieldset class="titolo">
  <legend>Bentornato!</legend>

  <form action="/SignorLibro_war/AutenticazioneController/login" method="post">
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
