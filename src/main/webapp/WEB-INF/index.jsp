<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:index>
    <jsp:attribute name="title">
        Pokubuy
    </jsp:attribute>
    <jsp:body>
        <hr>

        <div class="row column">
            <h4 style="margin: 0;" class="text-center">ПОПУЛЯРНЫЕ КАТЕГОРИИ</h4>
        </div>

        <hr>

        <div class="row small-up-1 large-up-5">
            <c:forEach items="${requestScope.populars}" var="popular">
                <div class="column">
                    <h5 class="text-center">${popular.name()}</h5>
                    <a href="<c:url value="/categories/${popular.id()}" />" class="button expanded">
                        Подробнее
                    </a>
                </div>
            </c:forEach>
        </div>

        <hr>
    </jsp:body>
</t:index>