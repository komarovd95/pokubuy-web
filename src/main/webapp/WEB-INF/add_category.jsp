<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:index>
    <jsp:attribute name="title">
        Pokubuy | Новая категория
    </jsp:attribute>
    <jsp:body>
        <hr>
        <div class="row column">
            <h4 style="margin: 0;">Новая Категория</h4>
        </div>

        <form method="post" action="/categories/add" data-abide novalidate>
            <div class="row">
                <div class="medium-6 columns">
                    <label>Название Категории
                        <input name="category_name" type="text" placeholder="Новая категория"
                               required pattern="credential">
                        <small class="form-error">
                            От 3 до 20 букв или цифр
                        </small>
                    </label>
                </div>
                <div class="medium-12 columns">
                    <input type="submit" class="button" value="Добавить">
                </div>
            </div>
            <div class="row">
                <c:if test="${not empty requestScope.error}">
                    <div class="medium-6 columns">
                        <div class="callout alert">
                            <p>
                                <i class="fi-alert"></i>
                                <c:out value="${requestScope.error}" />
                            </p>
                        </div>
                    </div>
                </c:if>
                <c:if test="${not empty requestScope.success}">
                    <div class="medium-6 columns">
                        <div class="callout success">
                            <p>Категория <c:out value="${requestScope.success.name()}" /> успешно добавлена</p>
                        </div>
                    </div>
                </c:if>
            </div>

            <%--<div class="row">--%>
                <%--<div class="medium-12 columns">--%>
                    <%--<div class="input-group">--%>
                        <%--<span class="input-group-label">Название категории</span>--%>
                        <%--<input class="input-group-field" type="text" required>--%>
                        <%--<small class="form-error">--%>
                            <%--От 4 до 20 цифр, латинских символов и символов нижнего подчеркивания--%>
                        <%--</small>--%>
                        <%--<div class="input-group-button">--%>
                            <%--<input type="submit" class="button" value="Добавить">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<c:if test="${not empty requestScope.error}">--%>
                        <%--<div class="alert callout">--%>
                            <%--<p>--%>
                                <%--<i class="fi-alert"></i>--%>
                                <%--<c:out value="${requestScope.error}" />--%>
                            <%--</p>--%>
                        <%--</div>--%>
                    <%--</c:if>--%>
                <%--</div>--%>
            <%--</div>--%>
        </form>
    </jsp:body>
</t:index>
