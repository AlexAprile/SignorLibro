<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: giovanni
  Date: 16/02/2024
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>table.products {
        width: 100%;
        border-collapse: collapse;
    }

    table.products th,
    table.products td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    table.products th {
        background-color: #f2f2f2;
    }

    table.products tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    table.products tr:hover {
        background-color: #f5f5f5;
    }

    table.products form {
        display: inline;
        margin-right: 5px;
    }</style>
    <title>Title</title>
</head>
<body>
<jsp:include page="navbarAmm.jsp"></jsp:include>
<table class="products">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Prezzo</th>
        <th>Descrizione</th>
        <th>Categoria</th>
        <th>Foto</th>
        <th class="quantita">Quantità</th>
    </tr>
    <c:forEach items="${products}" var="product">
        <tr>
            <th>${product.getId()}</th>
            <th>${product.getTitolo()}</th>
            <th>${product.getPrezzo()}</th>
            <th>${product.getCategoria()}</th>
            <th class="quantita">${product.getQuantita()}</th>
            <th>
                <form action="update-product">
                    <input type="hidden" name="id" value="${product.getId()}">
                    <input type="submit" value="Modifica">
                </form>
                <form action="delete-product">
                    <input type="hidden" name="id" value="${product.getId()}">
                    <input type="submit" value="Cancella">
                </form>
            </th>
        </tr>
    </c:forEach>
</table>
</body>
</html>
