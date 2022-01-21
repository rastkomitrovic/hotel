<%@include file="components/import.jsp"%>
<html>
<head>
    <title>Profil</title>
</head>
<body>
<%@include file="components/header.jsp" %>
<%@include file="components/messages.jsp" %>


<c:if test="${user ne null}">
    <form:form action="${pageContext.request.contextPath}/changePassword" modelAttribute="user" method="post">

        <label for="oldPassword">Stara sifra</label>
        <br/>
        <form:password path="oldPassword" id="oldPassword"/>
        <br/>
        <form:errors path="oldPassword" cssStyle="color: red"/>

        <br/>
        <br/>

        <label for="password">Nova Sifra</label>
        <br/>
        <form:password path="password" id="password"/>
        <br/>
        <form:errors path="password" cssStyle="color: red"/>

        <br/>
        <br/>

        <label for="repeatPassword">Ponovite novu sifru</label>
        <br/>
        <form:password path="repeatPassword" id="repeatPassword"/>
        <br/>
        <form:errors path="repeatPassword" cssStyle="color: red"/>

        <br/>
        <br/>

        <form:button type="submit">Promeni sifru</form:button>
    </form:form>
</c:if>


<%@include file="components/footer.jsp" %>
</body>
</html>
