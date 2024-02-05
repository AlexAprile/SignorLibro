<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="./css/navbar.css">
</head>
<body>
<div>
  <ul class="navbar">
    <li class="left">
      <a class="active" href="./index.jsp">Home</a></li>

    <li class="left">
      <div class="search">
        <form action="./risultati.jsp" method="GET">
          <input type="text" name="ricerca" placeholder="Inserisci nome">
          <button type="submit">Cerca</button>
        </form>
      </div>
    </li>

    <li class="right"><a href="carrello-servlet">Carrello</a></li>
    <li class="right"><a href="direct-servlet">Profilo</a></li>

  </ul>
</div>
<br/><br/>
</body>
</html>