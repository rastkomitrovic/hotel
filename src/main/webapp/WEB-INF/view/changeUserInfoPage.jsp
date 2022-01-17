<%@include file="components/import.jsp"%>
<html>
<head>
    <title>Profil</title>
</head>
<body>
<%@include file="components/header.jsp" %>

<label>
    <c:if test="${errorMessage ne null}">
        <p style="color: red">${errorMessage}</p>
    </c:if>

    <c:if test="${infoMessage ne null}">
        <p style="color: blue">${infoMessage}</p>
    </c:if>
</label>


<c:if test="${user ne null}">
    <form:form action="${pageContext.request.contextPath}/saveAccountInfo" modelAttribute="user" method="post">

        <br/>
        <br/>

        <label for="password">Sifra</label>
        <br/>
        <form:password path="password" id="password"/>
        <br/>
        <form:errors path="password" cssStyle="color: red"/>

        <br/>
        <br/>

        <label for="firstName">Ime</label>
        <br/>
        <form:input path="firstName" id="firstName"/>
        <br/>
        <form:errors path="firstName" cssStyle="color: red"/>

        <br/>
        <br/>

        <label for="lastName">Prezime</label>
        <br/>
        <form:input path="lastName" id="lastName"/>
        <br/>
        <form:errors path="lastName" cssStyle="color: red"/>

        <br/>
        <br/>

        <label for="dateOfBirth">Datum rodjenja</label>
        <br/>
        <form:input type="date" path="dateOfBirth" id="dateOfBirth"/>
        <br/>
        <form:errors path="dateOfBirth" cssStyle="color: red"/>

        <br/>
        <br/>

        <label for="address">Adresa</label>
        <br/>
        <form:input path="address" id="address"/>
        <br/>
        <form:errors path="address" cssStyle="color: red"/>

        <br/>
        <br/>

        <label for="passportNumber">Broj pasosa</label>
        <br/>
        <form:input path="passportNumber" id="passportNumber"/>
        <br/>
        <form:errors path="passportNumber" cssStyle="color: red"/>

        <br/>
        <br/>

        <label for="email">Email</label>
        <br/>
        <form:input path="email" id="email"/>
        <br/>
        <form:errors path="email" cssStyle="color: red"/>

        <br/>
        <br/>

        <label for="phoneNumber">Broj Telefona</label>
        <br/>
        <form:input path="phoneNumber" id="phoneNumber"/>
        <br/>
        <form:errors path="phoneNumber" cssStyle="color: red"/>

        <br/>
        <br/>

        <form:button type="submit">Sacuvaj izmene</form:button>
    </form:form>
</c:if>


<%@include file="components/footer.jsp" %>
</body>
</html>
