<%--
  Created by IntelliJ IDEA.
  User: utente
  Date: 13/02/2024
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'  %>
<html>
<head>



    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>


<!-- header -->




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
                    <a href="/SignorLibro_war/GestioneAcquistoController/rimuoviCarrello?isbn=${prodotto.prodotto.isbn}">rimuovi</a>
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

            </div>


    </section>
</aside>


<!-- footer -->


</body>
</html>