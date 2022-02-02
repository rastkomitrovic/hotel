
<nav>
    <div>
        <a href="${pageContext.request.contextPath}/aboutUsPage">O Nama</a>
        <a href="${pageContext.request.contextPath}/changeAccountInfoPage">Profil</a>
        <a href="${pageContext.request.contextPath}/changePasswordPage">Promeni sifru</a>
        <a href="${pageContext.request.contextPath}/myReservationsPage/0/5/startDate">Moje rezervacije</a>
        <sec:authorize access="hasAnyAuthority('ADMIN','EMPLOYEE')">
            <a href="${pageContext.request.contextPath}/employee/allReservations/0/5/startDate">Sve rezervacije</a>
        </sec:authorize>
        <sec:authorize access="hasAnyAuthority('ADMIN','EMPLOYEE')">
            <a href="${pageContext.request.contextPath}/employee/newReservationPage">Nova rezervacija</a>
        </sec:authorize>
        <sec:authorize access="hasAnyAuthority('ADMIN','EMPLOYEE')">
            <a href="${pageContext.request.contextPath}/employee/newUserPage">Novi korisnik</a>
        </sec:authorize>
        <sec:authorize access="hasAnyAuthority('ADMIN')">
            <a href="${pageContext.request.contextPath}/admin/usersPage">Pregled korisnika</a>
        </sec:authorize>
        <sec:authorize access="hasAnyAuthority('ADMIN','EMPLOYEE')">
            <a href="${pageContext.request.contextPath}/employee/roomsPage">Pregled soba</a>
        </sec:authorize>
        <sec:authorize access="hasAnyAuthority('ADMIN','EMPLOYEE')">
            <a href="${pageContext.request.contextPath}/employee/servicesPage">Pregled usluga</a>
        </sec:authorize>
    </div>
</nav>
