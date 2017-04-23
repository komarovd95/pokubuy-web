<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Войти</title>
    <link rel="stylesheet" href="<c:url value="../css/foundation.css" />">
    <link rel="stylesheet" href="<c:url value="../css/app.css" />">
    <style>
        #content {
            height: 100%;
        }

        .columns {
            position: relative;
            top: 50%;
            transform: translateY(-50%);
        }

        #form-content {
            border: 1px solid #cacaca;
            padding: 1rem;
            border-radius: 3px;
        }
    </style>
</head>
<body>
<div id="content" class="row">
    <div class="medium-6 medium-centered large-4 large-centered columns">
        <form data-abide action="signup" method="post" novalidate>
            <div id="form-content" class="row column">
                <h4 class="text-center">
                    Регистрация
                </h4>
                <label>Ваше имя
                    <input type="text" name="username" placeholder="Имя" required pattern="credential">
                    <small class="form-error">
                        От 4 до 20 цифр, латинских символов и символов нижнего подчеркивания
                    </small>
                </label>
                <label>Ваш пароль
                    <input type="password" name="password" id="password" placeholder="Пароль" required pattern="credential">
                    <small class="form-error">
                        От 4 до 20 цифр, латинских символов и символов нижнего подчеркивания
                    </small>
                </label>
                <label>Повторите пароль
                    <input type="password" placeholder="Пароль" required pattern="credential" data-equalto="password">
                    <small class="form-error">
                        Введенные пароли не совпадают
                    </small>
                </label>
                <c:if test="${not empty requestScope.error}">
                    <div class="alert callout">
                        <p>
                            <i class="fi-alert"></i>
                            <c:out value="${requestScope.error}" />
                        </p>
                    </div>
                </c:if>
                <p><input type="submit" class="button expanded success" value="Зарегистрироваться" /></p>
                <p class="text-center"><a href="<c:url value="/signin" />">Есть аккаунт? Войдите</a></p>
            </div>
        </form>
    </div>
</div>

<script src="<c:url value="../js/vendor/jquery.js" />"></script>
<script src="<c:url value="../js/vendor/what-input.js" />"></script>
<script src="<c:url value="../js/vendor/foundation.min.js" />"></script>
<script src="<c:url value="../js/app.js" />"></script>
</body>
</html>
