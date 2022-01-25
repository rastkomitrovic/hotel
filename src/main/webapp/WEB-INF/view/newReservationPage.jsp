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
    <form:form modelAttribute="reservation" action="${pageContext.request.contextPath}/employee/saveReservation"
               id="reservationForm">

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
        <form:select path="user" id="user" items="${users}" itemLabel="valueForLabel" itemValue="userId"/>
        <br/>
        <form:errors path="user" cssStyle="color:red"/>

        <br/>
        <br/>


        <c:if test="${not empty roomTypes }">
            <label for="roomTypes">Tipovi soba</label>
            <select name="roomTypes" id="roomTypes">
                <c:forEach var="roomType" items="${roomTypes}">
                    <option value="${roomType.roomTypeId}"
                            price-per-day="${roomType.pricePerDay}">${roomType.valueForLabel}</option>
                </c:forEach>
            </select>
        </c:if>

        <button type="button" onclick="addRoom()">Dodaj sobu izabranog tipa</button>

        <div id="selectedRooms">
            <h3>Izabrane sobe</h3>
            <div id="infoSelectedRooms"></div>
            <div id="hidden-inputs-rooms">
                <c:if test="${not empty reservation.rooms}">
                    <c:forEach var="room" items="${reservation.rooms}">
                        <input checked hidden name="rooms" multiple type="checkbox"
                               value="${room.roomType.roomTypeId}"
                               value-for-label="${room.roomType.valueForLabel}"
                               price-per-day="${room.roomType.pricePerDay}">
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <form:errors path="rooms" cssStyle="color:red"/>


        <br/>
        <br/>

        <c:if test="${not empty services}">
            <label for="services">Usluge</label>
            <select name="services" id="services">
                <c:forEach var="service" items="${services}">
                    <option value="${service.serviceId}"
                            price-per-use="${service.pricePerUse}">${service.valueForLabel}</option>
                </c:forEach>
            </select>
        </c:if>

        <button type="button" onclick="addService()">Dodaj uslugu</button>

        <div id="selectedServices">
            <h3>Izabrane usluge</h3>
            <div id="infoSelectedServices"></div>
            <div id="hidden-inputs-services">
                <c:if test="${not empty reservation.reservationServices}">
                    <c:forEach var="service" items="${reservation.reservationServices}">
                        <input checked hidden name="reservationServices" multiple type="checkbox"
                               value="${service.reservationServiceEmbeddedIdDTO.service.serviceId}"
                               value-for-label="${service.reservationServiceEmbeddedIdDTO.service.valueForLabel}"
                               price-per-use="${service.reservationServiceEmbeddedIdDTO.service.pricePerUse}">
                    </c:forEach>
                </c:if>
            </div>
        </div>

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
