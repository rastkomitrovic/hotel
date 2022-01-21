<%@include file="components/import.jsp"%>
<html>
<head>
    <title>Nova rezervacija</title>
</head>
<body>
<%@include file="components/header.jsp" %>
<%@include file="components/messages.jsp" %>

<div>
    <p>Nova rezervacija</p>
    <br/>
</div>
<div>
    <form:form modelAttribute="reservation" action="${pageContext.request.contextPath}/employee/saveReservation">

    </form:form>
</div>

<%@include file="components/footer.jsp" %>
</body>
</html>
