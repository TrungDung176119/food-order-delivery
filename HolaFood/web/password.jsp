<%-- 
    Document   : profile
    Created on : Oct 6, 2023, 8:56:51 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Suga</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <!-- css -->
    <link rel="stylesheet" href="./assests/css.profile/password.css">

    <!-- font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

    <!-- icon title -->
    <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.ico">
    <!-- boostrap -->
    <link href="./assests/boostrap/bootstrap.css" rel="stylesheet">

</head>

<body>
    <div class="app">
        <jsp:include page="menu_home.jsp"></jsp:include> 

            <!-- container profile -->
            <div class="container__profile ">
                <div class="grid">
                    <div class="grid__row row">
                    <jsp:include page="profile_menu.jsp"></jsp:include> 

                        <div class="profile__right grid__column-980">
                            <div class="profile__right-head">
                                <div>
                                    <h2>Đổi mật khẩu</h2>
                                    <span>Để bảo mật tài khoản, vui lòng không chia sẻ mật khẩu cho người khác</span>
                                </div>
                                <div class="profile__right-message ">
                                    ${msSuccess}
                                </div>
                            </div>
                            <div class="profile__right-content" style="margin-top: 25px;">
                                <div class="profile__right-form">
                                    <form action="password" method="post" style="width: 100%;">
                                        <div class="profile__right-password">
                                            <span class="password__title">Mật khẩu cũ</span>
                                            <input name="old_pass" style="border-color: ${red}" value="${oldPass}"  class="input__light" type="text">
                                        </div>
                                        <div class="profile__right-password" style="display: ${requestScope.syntaxOld}">
                                            <span class="password__title" ></span>
                                            <p class="password__message">${msOldpass}</p>
                                        </div>
                                        <div class="profile__right-password">
                                            <span class="password__title">Mật khẩu mới</span>
                                            <input name="new_pass" style="border-color: ${red}" value="${newPass}"  class="input__light" type="password">
                                        </div>
                                        <div class="profile__right-password" style="display: ${requestScope.syntaxNew}">
                                            <span class="password__title" ></span>
                                            <p class="password__message">${msNewpass} </p>
                                        </div>
                                        <div class="profile__right-password">
                                            <span class="password__title">Xác nhận mật khẩu</span>
                                            <input name="new_repass" style="border-color: ${red}" value="${newRepass}"  class="input__light" type="password">
                                        </div>
                                        <div class="profile__right-password" style="display: ${requestScope.syntax}">
                                            <span class="password__title" ></span>
                                            <p class="password__message">${msNewrepass} </p>
                                        </div>
                                        <div class="profile__right-button">
                                            <span class="password__title"></span>
                                            <button type="submit" class="btn__save">Xác nhận </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

        <jsp:include page="footer.jsp"></jsp:include>

    </div>
</body>

</html>
