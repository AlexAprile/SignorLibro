<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'  %>

<!DOCTYPE html>
<html>
<head>
    <title>Signor Libro</title>
    <link rel="icon" type="image/x-icon" href="./icons/vettoreVerde.png">

    <link rel="stylesheet" type="text/css" href="./css/styles.css">
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<h1 class="title">Signor Libro</h1>
<div>
    <img src="./icons/logoVerde.png" alt="SignorLibro Logo" title="SignorLibro Logo">
</div>
<br/>

<div class="vetrina" id="ajax">
    <c:forEach items="${vetrina}" var="prodotto">
        <div class="divVetrina">

            <a href="/SignorLibro_war/GestioneProdottoController/showProduct?isbn=${prodotto.isbn}">
                <p>${prodotto.titolo}</p>
            </a>
            <p class="divPrezzo">${prodotto.prezzo}&euro;</p>
        </div>
    </c:forEach>

</div>

<footer>
    <jsp:include page="/WEB-INF/Interface/footer.jsp" flush="true"/>
</footer>
</body>
</html>