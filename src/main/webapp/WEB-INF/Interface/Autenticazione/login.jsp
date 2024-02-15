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
    <label class="label" for="email">email</label>
    <input   type="email" name="email" id="email"  placeholder="email" required>
    <br>

    <label class="label" for="password">password </label>
    <input type="password" name="password" id="password"  placeholder="password" required>
    <br>
    <p></p>
    <button class="button"  type="submit" >login</button>
  </form>
</fieldset>

</body>
</html>
