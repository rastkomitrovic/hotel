<label>
    <c:if test="${errorMessage ne null}">
        <p style="color: red">${errorMessage}</p>
    </c:if>

    <c:if test="${infoMessage ne null}">
        <p style="color: blue">${infoMessage}</p>
    </c:if>
</label>
