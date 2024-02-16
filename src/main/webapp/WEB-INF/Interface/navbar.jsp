<%@ page import="Storage.Entity.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>body {
        margin:0;
    }
    li div.search input[type="text"],
    li div.search button[type="submit"] {
        vertical-align: middle;
    }
    ul.navbar {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: #333;
        position: fixed;
        top: 0;
        width: 100%;
    }

    li.left {
        float: left;
    }

    li.right {
        float: right;
    }

    li a {
        display: block;
        color: white;
        text-align: center;
        padding: 16px 18px;
        text-decoration: none;
    }


    li a:hover:not(.active) {
        background-color: #000;
    }

    .active {
        background-color: #137231;
    }

    li div.search{
        width: 400px;
    }

    li div.search input[type="text"]{
        width: 200px;
        margin-top: 10px;
    }

    li div.search button[type="submit"]{
        background-color: #137231;
        color: #FFF;
        font-family: "Bookman Old Style", serif;
        border: solid #FFF;
        padding: 12px 24px;
        cursor: pointer;
        border-radius: 6px;
    }

    li div.search input[type="text"]{
        width: 200px;
        height: 30px;
        margin-left: 100px;
        border-radius: 6px;
        border: solid #137231;
    }

    li div.search button[type="submit"]{
        margin-left: 5px;
        background-color: #137231;
        color: #FFF;
        font-family: "Bookman Old Style", serif;
        border: solid #FFF;
        padding: 12px 24px;
        cursor: pointer;
        border-radius: 6px;
    }
    @media screen and (max-width: 820px) {
        ul.navbar{
            position: static;
        }

        li.left,
        li.right {
            float: none;
            display: block;
            width: 100%;
            text-align: center;
        }

        li div.search {
            width: 100%;
            text-align: center;
        }

        li div.search input[type="text"] {
            width: 100%;
            margin-top: 10px;
        }
    }

    /* Media query per schermi con larghezza massima di 650px */
    @media screen and (max-width: 650px) {
        ul.navbar{
            position: static;
        }

        li a {
            padding: 10px 18px; /* Riduci il padding del link */
        }

        li div.search {
            width: 100%;
            text-align: center;
        }

        li div.search input[type="text"] {
            width: 80%; /* Riduci la larghezza dell'input di ricerca */
        }
    }

    /* Media query per schermi con larghezza massima di 480px */
    @media screen and (max-width: 480px) {
        ul.navbar{
            position: static;
        }

        li a {
            font-size: 14px; /* Riduci la dimensione del testo del link */
        }

        li div.search {
            width: 100%;
            text-align: center;
        }

        li div.search input[type="text"] {
            width: 70%; /* Riduci ulteriormente la larghezza dell'input di ricerca */
            max-height: 30px;
        }
    }</style>
    <style>
        body {
            margin:0;
        }

        ul.navbar {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
            position: fixed;
            top: 0;
            width: 100%;
        }

        li.left {
            float: left;
        }

        li.right {
            float: right;
        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 16px 18px;
            text-decoration: none;
        }


        li a:hover:not(.active) {
            background-color: #000;
        }

        .active {
            background-color: #137231;
        }

        li div.search{
            width: 400px;
        }

        li div.search input[type="text"]{
            width: 200px;
            margin-top: 10px;
        }

        li div.search button[type="submit"]{
            background-color: #137231;
            color: #FFF;
            font-family: "Bookman Old Style", serif;
            border: solid #FFF;
            padding: 12px 24px;
            cursor: pointer;
            border-radius: 6px;
        }

        li div.search input[type="text"]{
            width: 200px;
            height: 30px;
            margin-left: 100px;
            border-radius: 6px;
            border: solid #137231;
        }

        li div.search button[type="submit"]{
            margin-left: 5px;
            background-color: #137231;
            color: #FFF;
            font-family: "Bookman Old Style", serif;
            border: solid #FFF;
            padding: 12px 24px;
            cursor: pointer;
            border-radius: 6px;
        }
        @media screen and (max-width: 820px) {
            ul.navbar{
                position: static;
            }

            li.left,
            li.right {
                float: none;
                display: block;
                width: 100%;
                text-align: center;
            }

            li div.search {
                width: 100%;
                text-align: center;
            }

            li div.search input[type="text"] {
                width: 100%;
                margin-top: 10px;
            }
        }

        /* Media query per schermi con larghezza massima di 650px */
        @media screen and (max-width: 650px) {
            ul.navbar{
                position: static;
            }

            li a {
                padding: 10px 18px; /* Riduci il padding del link */
            }

            li div.search {
                width: 100%;
                text-align: center;
            }

            li div.search input[type="text"] {
                width: 80%; /* Riduci la larghezza dell'input di ricerca */
            }
        }

        /* Media query per schermi con larghezza massima di 480px */
        @media screen and (max-width: 480px) {
            ul.navbar{
                position: static;
            }

            li a {
                font-size: 14px; /* Riduci la dimensione del testo del link */
            }

            li div.search {
                width: 100%;
                text-align: center;
            }

            li div.search input[type="text"] {
                width: 70%; /* Riduci ulteriormente la larghezza dell'input di ricerca */
                max-height: 30px;
            }
        }

    </style>
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
<ul class="navbar">
    <li class="left">
      <a class="active" href="/SignorLibro_war/AutenticazioneController/home">Home</a>
    </li>

    <li class="left">
      <div class="search">
        <form action="/SignorLibro_war/GestioneProdottoController/risultati" method="GET">
          <input type="text" name="ricerca" placeholder="Inserisci nome">
          <button type="submit">Cerca</button>
        </form>
      </div>
    </li>

    <span id="icon">
      <li class="right"><a href="/SignorLibro_war/GestioneAcquistoController/showCartGuest"><span class="fas fa-shopping-cart cart-icon"></span></a></li>

        <% Utente utente = (Utente) request.getSession().getAttribute("account");
            if(utente != null) {
        %>
            <li class="second"><a href="/SignorLibro_war/AutenticazioneController/profile">Bentornato</a></li>
            <% }else{ %>
            <li class="right"><a href="/SignorLibro_war/AutenticazioneController/signin"><div class="material-icons" id="person">person</div></a></li>
            <% } %>
    </span>

  </ul>
</div>
<br/><br/>
</body>
</html>