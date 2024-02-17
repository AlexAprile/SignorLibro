<%--
  Created by IntelliJ IDEA.
  User: utente
  Date: 15/02/2024
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>





  <style>

    /*prodotto*/




    .prodotto{
      display: flex;
      flex-flow: column wrap;
      width: 100%;
      margin: 25px;


    }

    .prodottoTitolo{
      position:relative;
      display: flex;
      align-items: center;
      width: 100%;
      border-radius: 10px;
      box-shadow:0 0 8px 0 rgba(0,0,0,0.4);
      background-color:white;
      margin-bottom:20px;
      color: #8b8a8a;
    }

    .prodottoTitolo p{
      margin-left:10px;
      font-style: italic;
      font-weight: bold;
      font-size: 20px;


    }

    .prodotto img{

      width: 270px;
      border-radius: 10px;
      box-shadow:0 0 8px 0 rgba(0,0,0,0.7);
    }

    .prodottoimg{

      display: flex;
      flex-flow: row wrap;
      justify-content:space-between;
    }

    .latodetroCopertina{
      display: flex;
      flex-flow: column wrap;
      align-items: center;
      justify-content: center;
      justify-items: center;
      width:650px;
      background-color: white;
      border-radius: 10px;
      box-shadow:0 0 8px 0 rgba(0,0,0,0.4);


    }

    .latodetroCopertina #p1{
      font-style: italic;
      font-weight: bold;
      font-size: 30px;
      color: #8b8a8a;
      margin-bottom:5px;
    }

    .latodetroCopertina #p2{
      font-style: italic;
      font-weight: bold;
      font-size: 30px;
      color: #ee3124;
      margin-top:5px;
      margin-bottom:10px;
    }

    .latodetroCopertina #p2:hover {

      transform: scale(1.1);
    }



    .latodetroCopertina #p3{
      margin-top:30px;
      font-size: 18px;
      color: #8b8a8a;
      font-style: italic;
    }

    .descrizione{
      background-color:white;
      width: 100%;
      border-radius: 10px;
      box-shadow:0 0 8px 0 rgba(0,0,0,0.4);
      margin-top:20px;

    }
    .descrizione h3{
      font-size: 20px;
      text-align: center;
      font-weight: bold;
      font-style: italic;
      color: #8b8a8a;

    }

    .descrizione p{
      margin:10px;
      font-size: 18px;
      color: #8b8a8a;
      font-style: italic;



    }
  </style>


</head>
<body>



<!-- header -->

<jsp:include page="/WEB-INF/Interface/navbarUtente.jsp"></jsp:include>

<!-- barra di navigazione  -->






<aside class="containerBackground">

  <section class="containerCenter" id="ajax">

    <!--div prodotto  composto da 3 sotto div in colonna  -->

    <div class="prodotto">

      <!-- div titolo -->

      <div class="prodottoTitolo">

        <p>${prodotto.titolo}</p>

      </div>

      <!-- div con 2 sotto div uno copertina e l'altro prezzo e carrello -->

      <div class="prodottoimg">

        <!-- immagine -->

        <div class="latodetroCopertina">

          <p id="p1">DIGITALE</p>
          <p id="p2">${prodotto.prezzo}&euro;</p>

          <a href="/SignorLibro_war/GestioneAcquistoController/addCartUtente?isbn=${prodotto.isbn}"><button class="button">aggiungi al carrello</button></a>


          <p id="p3">
            Codice articolo:
            ${prodotto.id}
            <br>
            Genere:
            ${prodotto.categoria}
            <br>
            Rilascio:
            ${prodotto.dataPubblicazione}
          </p>

        </div>
      </div>

      <!-- div per la descrizione  -->

      <div class="descrizione">
        <h3>DESCRIZIONE</h3>
        <p>${prodotto.descrizione}</p>
      </div>

    </div>


  </section>
</aside>

<!-- footer -->

<div>
  <jsp:include page="/WEB-INF/Interface/footer.jsp"></jsp:include>
</div>


</body>
</html>
