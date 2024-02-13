<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="${alert.getType()}">
    <c:forEach var="msg" items="${alert.getMessages()}">
      ${msg}<br>
    </c:forEach>
</div>