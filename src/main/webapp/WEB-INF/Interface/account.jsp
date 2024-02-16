<%@ page import="model.carrello.Carrello" %>
<%@ page import="model.account.Account" %>
<%@ page import="model.carrello.CarrelloDao" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="product" class="model.prodotto.Prodotto" scope="request"/>
<html>
<html lang="it">
<head>
    <link rel="icon" href="images/logo(2).gif" type="image/gif">
  <meta charset="UTF-8">
  <title>Viking Nutrition</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script>
    //funzione per far apparire il menu
    $(document).ready(function(){
      $(".container").click(function(){
        $("#menu").slideToggle();
      });
    });
  </script>
  <link rel="stylesheet" type="text/css" href="./css/library.css">
  <link rel="stylesheet" type="text/css" href="./css/site.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
<!--logo-->

<div>
  <div id="head">
    <div class="img" align="center">
      <img id="logo" src="images/logo(2).gif">
    </div>
  </div>
</div>
<br/>
<h1>Aggiornamento Account</h1>

<c:if test="${not empty alert}">
  <%@ include file="/WEB-INF/Interface/errori/alert.jsp"%>
</c:if>

<form action="modifica-account" method="post">
  <input type="hidden" name="email" value="${account.getEmail()}">
  Email: ${account.getEmail()}<br>
  Username: <input type="text" name="username" value="${account.getUserName()}"><br/>
  Nome: <input type="text" name="nome" value="${account.getNome()}"><br/>
  Cognome: <input type="text" name="cognome" value="${account.getCognome()}"><br/>
  Password: <input type="password" name="psw"><br/>
  <input type="submit" value="Salva Modifica">
</form>
</body>
</html>
