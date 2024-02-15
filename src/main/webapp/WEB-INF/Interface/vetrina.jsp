<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: utente
  Date: 15/02/2024
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
