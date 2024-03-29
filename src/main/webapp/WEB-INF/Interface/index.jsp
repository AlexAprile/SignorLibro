<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'  %>

<!DOCTYPE html>
<html>
<head>
    <style>
        .vetrina {
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
        }
    </style>
    <title>Signor Libro</title>
    <link rel="icon" type="image/x-icon" href="./icons/vettoreVerde.png">
<style>
    * {
        box-sizing: border-box;
    }

    body{
        margin: 0;
        overflow-y: scroll;
    }

    div.title {
        text-align:center;
        background-color: #137231;
        padding: 20px;
        margin: 0;
        font-family: "Comic Sans MS", sans-serif;
    }

    h1.title {
        color: #137231;
        text-align: center;
        font-family: "Comic Sans MS", sans-serif;
        font-weight: bold;
    }
</style>

</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>

<br/><br/><br/><br/>

<div class="vetrina" id="ajax">
    <c:forEach items="${vetrina}" var="prodotto">
        <div class="divVetrina">
            <img src="/icons/img.png">
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