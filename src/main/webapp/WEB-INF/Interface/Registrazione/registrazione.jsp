<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="product" class="Storage.Entity.Prodotto" scope="request"/>

<html>
<head>
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

  <form action="registrazione-controller" method="post">
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
