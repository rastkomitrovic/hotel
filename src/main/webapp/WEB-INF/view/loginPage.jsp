<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="login-body">
    <label>
        <c:if test="${infoMessage ne null}">
            <p style="color: red">${infoMessage}</p>
        </c:if>
    </label>
    <form:form action="${pageContext.request.contextPath}/performLogin" modelAttribute="user"
               method="post"> <!-- onsubmit maybe -->

        <label for="username">Korisnicko ime</label>
        <form:input path="username" id="username"/>

        <label for="password">Sifra</label>
        <form:password path="password" id="password"/>

        <button type="submit">Uloguj se</button>
    </form:form>

    <label>
        <c:if test="${errorMessage ne null}">
            <p>${errorMessage}</p>
        </c:if>
    </label>

    <a href="${pageContext.request.contextPath}/newUser">Registruj se</a>
</body>
</html>