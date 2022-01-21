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

<div>
    <p>About us text</p>
</div>

<%@include file="components/footer.jsp" %>
</body>
</html>
