<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hotel</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@include file="components/header.jsp"%>
<p>Main page</p>
<br/>
<a href="${pageContext.request.contextPath}/logout">
    <button type="submit">Izloguj se</button>
</a>
<%@include file="components/footer.jsp"%>
</body>
</html>
