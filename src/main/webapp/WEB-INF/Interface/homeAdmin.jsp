<%--
  Created by IntelliJ IDEA.
  User: utente
  Date: 15/02/2024
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>


 <!-- head -->

  <style>

    /*contenuto centrale vetrina*/
    .divsCenter{
      position: relative;
      width: 50%;
      padding: 20px;
      max-height: 50%;
      font-family: OpenSans;
      font-style: italic;
      font-weight: bold;
    }

    .divsCenter h3{
      font-weight: bold;

    }

    .divsCenter p {
      display: flex;
      justify-content: center;
      justify-items: center;
      align-content: center;
      border-radius: 10px;
      background: #ff002b;
      color:#efe9ea;
      padding: 10px 10px 10px 10px;

    }

    .divsCenter p:hover{
      position: relative;
      top:5px;
      background: #1466e1;

    }


    #para1 {

      font-size: 18px;
      color: white;
      font-style: italic;
      font-weight:bold;


    }


  </style>


  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>



<!-- header-->


<!-- barra di navigazione  -->
<jsp:include page="navbarAmm.jsp"></jsp:include>



<aside class="containerBackground">

  <section class="containerCenter" id="ajax">


    <div class="divsCenter">
      <h3>Numero prodotti</h3>
      <p>${n_products}</p>
    </div>

    <div class="divsCenter">
      <h3>Numero clienti registrati</h3>
      <p>${n_client}</p>
    </div>

    <div class="divsCenter">
      <h3>Numero ordini mensili</h3>
      <p>${n_ordini}</p>
    </div>


  </section>
</aside>

<!--footer -->
<footer>
  <jsp:include page="/WEB-INF/Interface/footer.jsp" flush="true"/>
</footer>


</body>
</html>

