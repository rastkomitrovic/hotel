<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registracija</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <c:if test="${errorMessage ne null}">
        <p style="color: red">${errorMessage}</p>
    </c:if>
    <form:form action="${pageContext.request.contextPath}/register" modelAttribute="user" method="post">

        <label for="username">Korisnicko ime</label>
        <br/>
        <form:input path="username" id="username"/>
        <br/>
        <form:errors path="username" cssStyle="color: red"/>

        <br/>
        <br/>

        <label for="password">Sifra</label>
        <br/>
        <form:password path="password" id="password"/>
        <br/>
        <form:errors path="password" cssStyle="color: red"/>

        <br/>
        <br/>

        <label for="repeatPassword">Ponovite sifru</label>
        <br/>
        <form:password path="repeatPassword" id="repeatPassword"/>
        <br/>
        <form:errors path="repeatPassword" cssStyle="color: red"/>

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

        <form:button type="submit">Registruj se</form:button>

    </form:form>
</body>
</html>
