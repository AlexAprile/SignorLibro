<%@ page import="Storage.Entity.Utente" %><%--
  Created by IntelliJ IDEA.
  User: utente
  Date: 15/02/2024
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="./css/navbar.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
<ul class="navbar">
    <li class="left">
        <a class="active" href="/SignorLibro_war/AutenticazioneController/homeUtente">Home</a>
    </li>

    <li class="left">
        <div class="search">
            <form action="./risultati.jsp" method="GET">
                <input type="text" name="ricerca" placeholder="Inserisci nome">
                <button type="submit">Cerca</button>
            </form>
        </div>
    </li>

    <span id="icon">
      <li class="right"><a href="/SignorLibro_war/GestioneAcquistoController/showCartUtente"><span class="fas fa-shopping-cart cart-icon"></span></a></li>

        <% Utente utente = (Utente) request.getSession().getAttribute("account");
            if(utente != null) {
        %>
            <li class="second"><a href="direct-servlet">Bentornato</a></li>
            <% }else{ %>
            <li class="right"><a href="/SignorLibro_war/AutenticazioneController/signin"><div class="material-icons" id="person">person</div></a></li>
            <% } %>
      <li class="right"><a href="/SignorLibro_war/AutenticazioneController/logout">Disconnetti</a></li>
    </span>

</ul>
</div>
<br/><br/>
</body>
</html>