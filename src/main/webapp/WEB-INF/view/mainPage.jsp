<%@include file="components/import.jsp"%>
<html>
<head>
    <title>Hotel</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@include file="components/header.jsp"%>
<%@include file="components/messages.jsp" %>


<p>Main page</p>
<br/>

<a href="${pageContext.request.contextPath}/logout">
    <button type="submit">Izloguj se</button>
</a>

<%@include file="components/footer.jsp"%>
</body>
</html>
