<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <style>/* Stile del body */
  body {
    margin: 0;
    padding: 0;
    font-family: Arial, sans-serif;
  }

  /* Stile del titolo */
  .titolo {
    text-align: center;
  }

  .titolo img {
    width: 100px;
    height: 100px;
  }

  /* Stile del form di accesso */
  fieldset {
    margin: 0 auto;
    max-width: 400px;
    padding: 20px;
    border: 1px solid #cccccc;
    border-radius: 5px;
  }

  .legend {
    font-weight: bold;
    font-size: 20px;
  }

  .label {
    display: block;
    margin-bottom: 10px;
  }

  input[type="email"],
  input[type="password"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #cccccc;
    border-radius: 5px;
  }

  button {
    background-color: #137231;
    color: #ffffff;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }

  button:hover {
    background-color: #0d551c;
  }</style>
    <title>Accedi</title>
  <link rel="icon" type="image/x-icon" href="./icons/logoBankai.png">
  <link rel="stylesheet" type="text/css" href="./CSS/styles.css">
  <script src="script/FormValidationCliente.js"></script>
</head>


<body>

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
  <form action="/SignorLibro_war/registrazione-controller">
    <button class="button"  type="submit" >Registrati</button>
  </form>
</fieldset>

</body>
</html>
