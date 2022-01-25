<%@include file="components/import.jsp" %>
<html>
<head>
    <title>Nova rezervacija</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/newReservationPage.js"></script>
</head>
<body>
<%@include file="components/header.jsp" %>
<%@include file="components/messages.jsp" %>

<div>
    <p>Nova rezervacija</p>
    <br/>
</div>
<div>
    <form:form modelAttribute="reservation" action="${pageContext.request.contextPath}/employee/saveReservation" id="reservationForm">

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
        <form:textarea path="note" id="note"/>
        <br/>
        <form:errors path="note" cssStyle="color:red"/>

        <br/>
        <br/>

        <label for="user">Klijent</label>
        <br/>
        <form:select path="user" id="user" items="${users}" itemLabel="valueForLabel" itemValue="userId" />
        <br/>
        <form:errors path="user" cssStyle="color:red"/>

        <br/>
        <br/>


        <c:if test="${not empty roomTypes }">
            <label for="roomTypes">Tipovi soba</label>
            <select name="roomTypes" id="roomTypes">
                <c:forEach var="roomType" items="${roomTypes}">
                    <option value="${roomType.roomTypeId}">${roomType.valueForLabel}</option>
                </c:forEach>
            </select>
        </c:if>

        <button type="button" onclick="addRoom(event)">Dodaj sobu izabranog tipa</button>

        <div id="selectedRooms">
            <h3>Izabrane sobe</h3>
            <div id="infoSelectedRooms"></div>
        <div id="hidden-inputs"></div>
        </div>



        <!--

        <span> ovako odradi jstl tag - sustina klikne tip sobe i dodaj u tabeli levo prikaze se kolko takvih tipova soba ima a za svaku doda jedan ovaj span sa checked i hidden
            <input id="rooms1" name="rooms" multiple="true" type="checkbox" value="1"><label for="rooms1">01 - Jednokrevetna</label>
        </span>

        <span>
            <input checked hidden id="rooms333" name="rooms" multiple="true" type="checkbox" value="1">
            <label for="rooms333">Sobaaa</label>
        </span>
        <span>
            <input checked hidden id="rooms3333" name="rooms" multiple="true" type="checkbox" value="2">
            <label for="rooms3333">Sobaaa</label>
        </span>

        -->
        <br/>
        <br/>

        <label for="reservationServices">Usluge</label>
        <form:select path="reservationServices" id="reservationServices" items="${services}" itemLabel="valueForLabel" itemValue="serviceId" multiple="true"/>
        <!--
            Slicna prica ovde samo sto u value mora ici id-kolicinaUpotrebe
            Znaci izaberes uslugu i u polju pored upises broj upotreba i on
            doda ovaj input samo sto mu value bude serviceId-numberOfUsages
        -->
        <br/>
        <br/>

        <label for="totalSum">Ukupna cena rezervacije</label>
        <br/>
        <form:input path="totalSum" disabled="true" id="totalSum"/>
        <br/>
        <form:errors path="totalSum" cssStyle="color:red"/>

        <button type="submit">Sacuvaj rezervaciju</button>
    </form:form>
</div>

<%@include file="components/footer.jsp" %>
</body>
</html>
