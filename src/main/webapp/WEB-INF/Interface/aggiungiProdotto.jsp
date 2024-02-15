<%--
  Created by IntelliJ IDEA.
  User: utente
  Date: 15/02/2024
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>





  <style>

    .f_product label{

      color: #1a1a1a;
      margin-bottom: 10px;
      font-weight: 500;
      font-style: italic;


    }


    #number1 {
      padding: 20px;
      display: flex;
      flex-flow: row;
      justify-content: space-between;

    }

    #number1 div{

      display: flex;
      flex-flow: column;


    }

    #number2{
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 20px;
      flex-flow: column;
    }


    #number3{
      padding: 20px;
      display: flex;
      flex-flow: row nowrap;
      justify-content: space-between;

    }

    #number3 div{

      display: flex;
      flex-flow: column;

    }



    #number4 {
      padding: 20px;
      display: flex;
      flex-flow: row nowrap;
      justify-content: space-between;
      align-items: center;


    }

    #number4 div{

      display: flex;
      flex-flow: column ;
    }

    #number5{
      display: flex;
      justify-content: center;
      align-items: center;
    }



  </style>



</head>
<body>


<!-- header-->


<!-- barra di navigazione  -->

<jsp:include page="/WEB-INF/Interface/navbarAmm.jsp"></jsp:include>





    <form class="f_product" action="/SignorLibro_war/GestioneProdottoController/creaProdotto"
          method="post"  enctype="multipart/form-data">


          <label for="nome" >Nome del prodotto</label>
          <input type="text" minlength="5" maxlength="50" id="nome" name="nome" required >

          <label for="autore" >Autore</label>
          <input type="text" minlength="5" maxlength="50" id="autore" name="autore" required >

          <label for="isbn" >ISBN</label>
          <input type="text" minlength="5" maxlength="50" id="isbn" name="isbn" required >

          <label for="quan" >Quantita</label>
          <input type="text"   id="quan" name="quan" required >

          <label for="prezzo" >Prezzo di base</label>
          <input type="text"   id="prezzo" name="prezzo" required >


        <label for="content" >Contenuto (max 5000 caratteri)</label>
        <textarea id="content" rows="20" maxlength="5000" cols="70" name="description" required></textarea>


          <label for="cover" >Immagine di copertina</label>
          <input type="file"
                 accept=".apng, .avif, .gif, .jpg, .jpeg, .jfif, .pjpeg, .pjp, .png, .svg, .webp"
                 id="cover" name="cover" required>

          <label for="date" >data uscita</label>
          <input type="date" name="date" id="date" required>



          <label for="categoria" >Categoria</label>
          <input type="text"   id="categoria" name="categoria" required >



        <button class="button" type="submit" >inserisci prodotto</button>

    </form>


<!--footer -->

<jsp:include page="/WEB-INF/Interface/footer.jsp"></jsp:include>


</body>
</html>
