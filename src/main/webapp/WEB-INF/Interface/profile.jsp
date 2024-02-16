<%@ page import="Storage.Entity.Utente" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <style>/* Stile del body */
    body {
        background-color: #137231;
    }

    /* Stile del profilo */
    #profile {
        margin: 20px;
        padding: 20px;
        background-color: #ffffff;
        border-radius: 5px;
    }

    /* Stile del modulo dell'account */
    form.account {
        margin-bottom: 20px;
    }

    /* Stile dei campi del profilo */
    span {
        display: block;
        margin-bottom: 10px;
        color: #333333;
    }

    /* Stile del pulsante di modifica */
    input[type="submit"] {
        background-color: #137231;
        color: #ffffff;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    /* Stile del pulsante di logout */
    form[action="/SignorLibro_war/AutenticazioneController/logout"] input[type="submit"] {
        background-color: #137231;
        color: #ffffff;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    /* Stile del pulsante di cancellazione dell'account */
    form#myForm input[type="submit"] {
        background-color: #137231;
        color: #ffffff;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }</style>
</head>
<body>
<!--logo-->
<jsp:include page="navbarUtente.jsp"></jsp:include>
<br>
<br>
<br>
<%Utente account = (Utente) request.getSession().getAttribute("account");%>
<div id="profile">
    <form class="account" action="account">
        <span>Email: <%=account.getMail()%></span><br>
        <span>Cognome: <%=account.getCognome()%></span><br>
        <span>Nome: <%=account.getNome()%><br/></span><br>
        <span>Data di nascita: <%=account.getNascita()%><br/></span><br>
        <input type="submit" value="Modifica">
    </form>
    <div>
    <form action="/SignorLibro_war/AutenticazioneController/logout">
        <input type="submit" value="Logout">
    </form>
    <form id="myForm" action="delete-account">
        <input type="hidden" name="email" value="<%=account.getMail()%>">
        <input type="submit" value="Cancella account" onclick="confermaInvioForm(); return false;">
    </form>
    </div>
</div>
</body>
</html>
