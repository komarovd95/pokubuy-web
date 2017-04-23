<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:index>
    <jsp:attribute name="title">
        Pokubuy | Редактирование товара
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="medium-6 columns">
                <form method="post" action="/goods/${requestScope.good.id()}/edit" data-abide novalidate>
                    <div class="row">
                        <div class="small-12 columns">
                            <label>Название товара
                                <input name="title" type="text" placeholder="Название"
                                       value="${requestScope.good.title()}" required>
                                <span class="form-error">
                                    От 1 до 20 букв или цифр
                                </span>
                            </label>
                        </div>
                        <div class="small-12 columns">
                            <label>Описание
                                <textarea name="description" placeholder="Описание товара" required><c:out value="${requestScope.good.description()}" /></textarea>
                                <span class="form-error">
                                    От 1 до 255 букв или цифр
                                </span>
                            </label>
                        </div>
                        <div class="small-12 columns">
                            <label>Категория
                                <select id="category" name="category" required>
                                    <option value="">Выберите категорию</option>
                                    <c:forEach items="${requestScope.categories}" var="category">
                                        <option value="${category.id()}" ${requestScope.good.category().id() eq category.id() ? 'selected' : ''}>
                                            <c:out value="${category.name()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <span class="form-error">
                                    Необходимо выбрать категорию
                                </span>
                            </label>
                        </div>
                    </div>
                    <div class="row">
                        <fieldset class="large-6 columns">
                            <button class="button" type="submit" value="Обновить">Обновить</button>
                        </fieldset>
                    </div>
                    <div class="row">
                        <c:if test="${not empty requestScope.error}">
                            <div class="medium-12 columns">
                                <div class="callout alert">
                                    <p>
                                        <i class="fi-alert"></i>
                                        <c:out value="${requestScope.error}" />
                                    </p>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</t:index>
