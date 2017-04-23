<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:index>
    <jsp:attribute name="title">
        Pokubuy | ${requestScope.category.name()}
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="medium-8 columns">
                <h3><c:out value="${requestScope.category.name()}"/></h3>
            </div>
            <c:if test="${not empty requestScope.signedUser}">
                <div class="medium-2 columns">
                    <a href="<c:url value="/categories/${requestScope.category.id()}/delete" />"
                       class="button alert expanded">Удалить категорию</a>
                </div>
            </c:if>
            <div class="medium-2 columns">
                <a href="<c:url value="/goods/add?categoryId=${requestScope.category.id()}" />"
                   class="button expanded">Добавить товар</a>
            </div>
        </div>

        <div class="row column">
            <hr>
        </div>

        <div class="row small-up-1 medium-up-2 large-up-3">
            <c:if test="${empty requestScope.goods}">
                <p>В данной категории нет товаров</p>
            </c:if>
            <c:forEach items="${requestScope.goods}" var="good">
                <div class="column">
                    <div class="callout">
                        <p>${good.title()}</p>
                        <p class="subheader">${good.description()}</p>
                        <c:if test="${not empty requestScope.signedUser}">
                            <a class="button" href="<c:url value="/goods/${good.id()}/edit" />">
                                Редактировать
                            </a>
                            <a class="button alert" href="<c:url value="/goods/${good.id()}/delete?redirect=/categories/${requestScope.category.id()}" />" style="margin-bottom: 1rem">
                                Удалить
                            </a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </jsp:body>
</t:index>
