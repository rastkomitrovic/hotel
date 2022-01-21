<%@include file="components/import.jsp" %>
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

        <label for="startDate">Pocetak rezervacije</label>
        <br/>
        <form:input path="startDate" type="date" id="startDate"/>
        <br/>
        <form:errors path="startDate" cssStyle="color:red"/>

        <br/>
        <br/>

        <label for="endDate">Kraj rezervacije</label>
        <br/>
        <form:input path="endDate" type="date" id="endDate"/>
        <br/>
        <form:errors path="endDate" cssStyle="color:red"/>

        <br/>
        <br/>

        <label for="note">Napomena</label>
        <br/>
        <form:textarea path="note" id="startDate"/>
        <br/>
        <form:errors path="startDate" cssStyle="color:red"/>

        <br/>
        <br/>

        <label for="user">Klijent</label>
        <br/>
        <form:select  path="user" id="user"/>
        <br/>
        <form:errors path="user" cssStyle="color:red"/>

        <br/>
        <br/>

        <label for="totalSum">Ukupna cena rezervacije</label>
        <br/>
        <form:input path="totalSum" disabled="true" id="totalSum"/>
        <br/>
        <form:errors path="totalSum" cssStyle="color:red"/>

    </form:form>
</div>

<%@include file="components/footer.jsp" %>
</body>
</html>
