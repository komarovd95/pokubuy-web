<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:index>
    <jsp:attribute name="title">
        Pokubuy | Новый товар
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="medium-6 columns">
                <form method="post" action="/goods/add" data-abide novalidate>
                    <div class="row">
                        <div class="small-12 columns">
                            <label>Название товара
                                <input name="title" type="text" placeholder="Название" required>
                                <span class="form-error">
                                    От 1 до 20 букв или цифр
                                </span>
                            </label>
                        </div>
                        <div class="small-12 columns">
                            <label>Описание
                                <textarea name="description" placeholder="Описание товара" required></textarea>
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
                                        <option value="${category.id()}" ${not empty requestScope.categoryId and requestScope.categoryId eq category.id() ? 'selected' : ''}>
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
                            <button class="button" type="submit" value="Добавить">Добавить</button>
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
                        <c:if test="${not empty requestScope.success}">
                            <div class="medium-12 columns">
                                <div class="callout success">
                                    <p>Товар <c:out value="${requestScope.success}" /> успешно добавлен</p>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</t:index>
