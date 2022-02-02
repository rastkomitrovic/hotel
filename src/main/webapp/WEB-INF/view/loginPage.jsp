<%@include file="components/import.jsp"%>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="login-body">
<%@include file="components/messages.jsp" %>
    <form:form action="${pageContext.request.contextPath}/performLogin" modelAttribute="user"
               method="post"> <!-- onsubmit maybe -->

        <label for="username" >Korisnicko ime</label>
        <form:input path="username" id="username"/>

        <label for="password">Sifra</label>
        <form:password path="password" id="password"/>

        <button type="submit">Uloguj se</button>
    </form:form>

    <a href="${pageContext.request.contextPath}/newUser">Registruj se</a>
</body>
</html>