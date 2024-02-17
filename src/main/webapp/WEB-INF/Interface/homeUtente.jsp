<%@ page import="Storage.Entity.Utente" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: utente
  Date: 15/02/2024
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Signor Libro</title>
    <link rel="icon" type="image/x-icon" href="./icons/vettoreVerde.png">
    <style>.vetrina {
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
    }

    /* Stile del singolo elemento della vetrina */
    .divVetrina {
        width: 200px;
        padding: 10px;
        border: 1px solid #cccccc;
        text-align: center;
        margin-bottom: 20px;
    }

    .divVetrina a {
        text-decoration: none;
        color: #137231;
    }

    .divVetrina p {
        margin: 0;
    }

    /* Stile del prezzo */
    .divPrezzo {
        font-weight: bold;
    }</style>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
</head>
<body>

<%
        Utente utente= (Utente) request.getAttribute("account");
        if(utente==null){%>
<jsp:include page="navbar.jsp"></jsp:include>
<%}else{%><jsp:include page="navbarUtente.jsp"></jsp:include><%}%>

<h1 class="title">Signor Libro</h1>
<div>
    <img src="./icons/logoVerde.png" alt="SignorLibro Logo" title="SignorLibro Logo">
</div>
<br/>

<div class="vetrina" id="ajax">
    <c:forEach items="${vetrina}" var="prodotto">
        <div class="divVetrina">

            <a href="/SignorLibro_war/GestioneProdottoController/showProductUtente?isbn=${prodotto.isbn}">
                <p>${prodotto.titolo}</p>
            </a>
            <p class="divPrezzo">${prodotto.prezzo}&euro;</p>
        </div>
    </c:forEach>

</div>

</div>


<footer>
    <jsp:include page="/WEB-INF/Interface/footer.jsp" flush="true"/>
</footer>
</body>
</html>
