<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="./css/navbar.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
<ul class="navbar">
    <li class="left">
      <a class="active" href="./index.jsp">Home</a>
    </li>

    <li class="left">
      <div class="search">
        <form action="./risultati.jsp" method="GET">
          <input type="text" name="ricerca" placeholder="Inserisci nome">
          <button type="submit">Cerca</button>
        </form>
      </div>
    </li>

    <span id="icon">
      <li class="right"><a href="cart"><span class="fas fa-shopping-cart cart-icon"></span></a></li>
      <li class="right"><a href="signin"><div class="material-icons" id="person">person</div></a></li>
    </span>

  </ul>
</div>
<br/><br/>
</body>
</html>