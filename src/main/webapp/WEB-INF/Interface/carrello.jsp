<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: utente
  Date: 15/02/2024
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <style>/* Stile del body */
    body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
        background-color: #ffffff;
    }

    /* Stile del contenitore principale */
    .containerBackground {
        background-color: #f2f2f2;
        padding: 20px;
    }

    /* Stile del contenitore centrale */
    .containerCenter {
        margin: 0 auto;
        max-width: 800px;
    }

    /* Stile del titolo del carrello */
    .CrtTitolo {
        background-color: #137231;
        color: #ffffff;
        padding: 10px;
    }

    .CrtTitolo p {
        margin: 0;
        font-size: 20px;
    }

    /* Stile delle righe del carrello */
    .hedercart {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px;
        border-bottom: 1px solid #cccccc;
    }

    #a2 {
        flex-grow: 1;
    }

    #a3 {
        flex-shrink: 0;
        text-align: right;
    }

    .hedercart p {
        margin: 0;
    }

    .hedercart a {
        text-decoration: none;
        color: #137231;
    }

    /* Stile del footer del carrello */
    .footerCart {
        background-color: #f9f9f9;
        padding: 10px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-top: 1px solid #cccccc;
    }

    #a4 {
        flex-grow: 1;
    }

    #a7 {
        flex-shrink: 0;
    }

    /* Stile del pulsante Procedi all'acquisto */
    .button {
        background-color: #137231;
        color: #ffffff;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        text-decoration: none;
    }

    .button:hover {
        background-color: #0d551c;
    }</style>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>


<!-- header -->


<jsp:include page="navbar.jsp"></jsp:include>

<!-- barra di navigazione  -->




<aside class="containerBackground">

    <section class="containerCenter" id="ajax">

        <div class="generalCart">
            <div class="CrtTitolo">

                <p >Carrello</p>

            </div>

            <!--lista prodotti nel carrello -->

            <c:forEach items="${carrello.carrello}" var="prodotto">

            <div class="hedercart">

                <!--inserire qua tag immagine-->
                <div id="a2">
                    <p>
                        <b>Nome libro:</b> ${prodotto.prodotto.titolo} <br>
                        <b>Categoria:</b> ${prodotto.prodotto.categoria} <br>
                        <b>Data uscita:</b> ${prodotto.prodotto.dataPubblicazione}<br>
                        <b>Quantita:</b> ${prodotto.prodotto.quantita}<br>

                    </p>
                </div>

                <div id="a3">
                    <a href="/SignorLibro_war/GestioneAcquistoController/rimuoviCarrelloUtente?isbn=${prodotto.prodotto.isbn}">rimuovi</a>
                    <p>prezzo: ${prodotto.prodotto.prezzo}&euro;</p>
                </div>

            </div>
            </c:forEach>
            <div class="footerCart">


                <div id="a4">
                    <p>
                        SubTotale : ${totale} &euro; <br>
                        Spedizione: Gratis  <br>
                        Totale    : ${totale} &euro; <br>

                    </p>

                </div>

                <!-- continua a navigare -->
                <div id="a7">
                    <a href="/SignorLibro_war/GestioneAcquistoController/createOrder"><button class="button">Procedi all'acquisto</button></a>
                </div>

            </div>


    </section>
</aside>


<!-- footer -->


</body>
</html>