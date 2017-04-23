<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" fragment="true" %>
<%@ attribute name="head" fragment="true" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="../../css/foundation.css" />">
    <link rel="stylesheet" href="<c:url value="../../css/app.css" />">
    <jsp:invoke fragment="head"/>
    <title><jsp:invoke fragment="title"/></title>
</head>
<body>
<header>
    <div class="top-bar-container" data-sticky-container>
        <div class="sticky sticky-topbar" data-sticky
             data-options="anchor: page; marginTop: 0; stickyOn: small;">
            <div class="top-bar">
                <div class="top-bar-left">
                    <ul class="dropdown menu" data-dropdown-menu>
                        <li class="menu-text"><a href="<c:url value="/" />" style="color: inherit; padding: 0">Pokubuy</a></li>
                        <li class="has-submenu">
                            <a>Категории</a>
                            <c:if test="${not empty requestScope.categories}">
                                <ul class="submenu menu vertical" data-submenu>
                                    <c:forEach items="${requestScope.categories}" var="category">
                                        <li><a href="<c:url value="/categories/${category.id()}" />">
                                            ${category.name()}
                                        </a></li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </li>
                        <c:if test="${not empty requestScope.signedUser and
                        (requestScope.signedUser.role().toString() eq 'ADMIN' or requestScope.signedUser.role().toString() eq 'MANAGER')}">
                            <li><a href="<c:url value="/categories/add" />">
                                Добавить категорию
                            </a></li>
                        </c:if>
                    </ul>
                </div>
                <c:choose>
                    <c:when test="${not empty requestScope.signedUser}">
                        <div class="top-bar-right">
                            <ul class="menu">
                                <li><a>Ваш Аккаунт [${requestScope.signedUser.name()}]</a></li>
                                <li><a href="<c:url value="/signout"/>">Выйти</a></li>
                            </ul>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="top-bar-right">
                            <ul class="menu">
                                <li><a href="<c:url value="/signin"/>">Войти</a></li>
                                <li><a href="<c:url value="/signup"/>" class="button">Регистрация</a></li>
                            </ul>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="row" style="margin-bottom: 15px; margin-top: 15px">
        <div class="medium-12 columns">
            <img src="https://placehold.it/450x183&text=LOGO" alt="company logo" style="width: 100%; height: 175px">
        </div>
    </div>
</header>

<jsp:doBody/>

<script src="<c:url value="../../js/vendor/jquery.js" />"></script>
<script src="<c:url value="../../js/vendor/what-input.js" />"></script>
<script src="<c:url value="../../js/vendor/foundation.min.js" />"></script>
<script src="<c:url value="../../js/app.js" />"></script>
</body>
</html>