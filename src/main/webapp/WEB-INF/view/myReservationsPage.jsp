<%@include file="components/import.jsp" %>
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

<c:choose>
    <c:when test="${isEmpty}">
        <div>
            <p>Nemate rezervacija</p>
        </div>
    </c:when>
    <c:otherwise>
        <div>
            <p>Moje rezervacije</p>
            <br/>
            <p>Pronadjeno ${totalNumberOfFoundElements} rezervacija</p>
            <br/>
        </div>
        <div>
            <table>
                <tr>
                    <th>Kreator rezervacije</th>
                    <th>Datum pocetka rezervacije</th>
                    <th>Datum zavrsetka rezer>>vacije</th>
                    <th>Soba</th>
                    <th>Tip sobe</th>
                    <th>Usluge</th>
                    <th>Cena</th>
                    <th>Napomena</th>
                </tr>
                <c:forEach var="reservation" items="${reservations}">
                    <th><c:if test="${reservation.employee.firstName ne null}">${reservation.employee.firstName}</c:if>
                        <c:if test="${reservation.employee.lastName ne null}">${reservation.employee.lastName}</c:if></th>
                    <th><c:if test="${reservation.startDate ne null}"><fmt:formatDate value="${reservation.startDate}"
                                                                                      pattern="dd.MM.yyy"/></c:if></th>
                    <th><c:if test="${reservation.endDate ne null}"><fmt:formatDate value="${reservation.endDate}"
                                                                                    pattern="dd.MM.yyy"/></c:if></th>
                    <th><c:if
                            test="${reservation.room ne null}">Sprat:${reservation.room.floor}, Broj sobe:${reservation.room.roomNumber}</c:if></th>
                    <th><c:if
                            test="${reservation.room ne null && reservation.room.roomType ne null && reservation.room.roomType.roomTypeName ne null}">${reservation.room.roomType.roomTypeName}</c:if></th>
                    <th><c:choose>
                        <c:when test="${empty reservation.reservationServices}">Nema usluga</c:when>
                        <c:otherwise>
                            <c:forEach var="i" begin="0" end="${reservation.reservationServices.size()-2}">
                                ${reservation.reservationServices.get(i).reservationServiceEmbeddedIdDTO.service.serviceName} - ${reservation.reservationServices.get(i).reservationServiceEmbeddedIdDTO.service.pricePerUse},
                            </c:forEach>
                            ${reservation.reservationServices.get(reservation.reservationServices.size()-1).reservationServiceEmbeddedIdDTO.service.serviceName} - ${reservation.reservationServices.get(reservation.reservationServices.size()-1).reservationServiceEmbeddedIdDTO.service.pricePerUse}
                        </c:otherwise>
                    </c:choose></th>
                    <th>${reservation.totalSum}</th>
                    <th><c:if test="${reservation.note ne null}">${reservation.note}</c:if></th>
                </c:forEach>
            </table>
        </div>
        <div>
            <c:if test="${totalPages>1}">
                <form:form style="display: inline"
                           action="${pageContext.request.contextPath}/myReservationsPage/${currentPage-1}/${size}/${sort}"
                           method="get">
                    <button type="submit" class="results-prevnext-btn" <c:if test="${isFirstPage}">disabled</c:if>>
                        Prethodna
                    </button>
                </form:form>

                <c:forEach var="i" begin="1" end="${totalPages}">
                    <form:form style="display: inline"
                               action="${pageContext.request.contextPath}/myReservationsPage/${i-1}/${size}/${sort}"
                               method="get">
                        <button <c:if test="${i-1 eq currentPage}">disabled</c:if> class="<c:choose><c:when test="${i-1 eq currentPage}">results-nav-btn-bold</c:when><c:otherwise>results-nav-btn</c:otherwise></c:choose>">
                            ${i}
                        </button>
                    </form:form>
                </c:forEach>

                <form:form style="display: inline"
                           action="${pageContext.request.contextPath}/myReservationsPage/${currentPage+1}/${size}/${sort}"
                           method="get">
                    <button type="submit" class="results-prevnext-btn"
                            <c:if test="${isLastPage}">disabled</c:if> >Sledeca
                    </button>
                </form:form>
            </c:if>
        </div>
    </c:otherwise>
</c:choose>


<%@include file="components/footer.jsp" %>
</body>
</html>
