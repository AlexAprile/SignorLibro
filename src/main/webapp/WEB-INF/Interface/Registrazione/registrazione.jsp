<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="product" class="Storage.Entity.Prodotto" scope="request"/>

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

  /* Stile del form di registrazione */
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

  label {
    display: block;
    margin-bottom: 10px;
  }

  input[type="email"],
  input[type="password"],
  input[type="text"],
  input[type="date"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #cccccc;
    border-radius: 5px;
  }

  input[type="submit"] {
    background-color: #137231;
    color: #ffffff;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }

  input[type="submit"]:hover {
    background-color: #0d551c;
  }</style>
  <title>Registrazione</title>
  <link rel="stylesheet" href="./css/styles.css">
  <script src="script/FormValidationCliente.js"></script>

</head>
<body>

<div class="titolo">
  <img src="./icons/logoVerde.png" alt="SignorLibro logo" title="SignorLibro logo">
</div>

<h1 class="titolo">Inserimento Nuovo Utente:</h1>
<fieldset class="titolo">
  <legend>Form inserimento:</legend>

  <form action="/SignorLibro_war/RegistrazioneController" method="post">
    <c:if test="${not empty alert}">
      <%@ include file="/WEB-INF/Interface/errori/alert.jsp"%>
    </c:if>
    <label for="email">e-Mail</label>
    <input type="email" id="email" name="email"
           placeholder="nomecognome@signorlibro.it"><br><br>

    <label for="c-email">e-Mail</label>
    <input type="email" id="c-email" name="c-email"
           placeholder="nomecognome@signorlibro.it"><br><br>

    <label for="password">password</label>
    <input type="password" id="password" name="password"
           placeholder="Minimo 4 caratteri"><br><br>

    <label for="c-password">password</label>
    <input type="password" id="c-password" name="c-password"
           placeholder="Minimo 4 caratteri"><br><br>

    <label for="nome">Nome</label>
    <input type="text" id="nome" name="nome"><br><br>

    <label for="lastName">Cognome</label>
    <input type="text" id="lastName" name="cognome"><br><br>

    <label for="nascita">Data di Nascita</label>
    <input type="date" id="nascita" name="nascita"
           placeholder="Devi essere maggiorenne"><br><br>

    <input type="submit" value="Inserisci">

  </form>
</fieldset>
</body>
</html>
