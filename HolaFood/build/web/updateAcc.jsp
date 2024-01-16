<%-- 
    Document   : updateAcc
    Created on : Oct 14, 2023, 11:35:45 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Suga</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css/manager.css">
        <link rel="stylesheet" href="./assests/css.manager/updateAcc.css">

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
        <div class="app__manager">
            <div class="container__heading">
                <div class="header__manager">
                    <div class="header__manager-left">
                        <a href="" class="header__manager-item">
                            <i class="fa-brands fa-apple"></i>
                            <span class="header__manager-item__name">Suga</span>
                        </a>
                        <span class="header__manager-title">Câp nhật tài khoản</span>
                    </div>

                    <div class="header__manager-right">
                        <div class="header__manager-user">
                            <img class="header__manager-user-img"
                                 src="https://yt3.ggpht.com/yti/ADpuP3MEBtPK2w3PD74lGPnowgesuAR_VQYgWY4u0_NPcw=s88-c-k-c0x00ffffff-no-rj" alt="">
                            <c:if test="${(sessionScope.AccDetail.nickname == null) || (sessionScope.AccDetail.nickname == '')}">
                                <span class="header__manager-user-name"> ${sessionScope.acc.username}</span>
                            </c:if>
                            <c:if test="${sessionScope.AccDetail.nickname != null}">
                                <span class="header__manager-user-name">${sessionScope.AccDetail.nickname}</span>
                            </c:if>
                        </div>
                        <ul class="header__manager-user-menu">
                            <li class="header__manager-user-item">
                                <a href="" style="padding-top: 10px;">Trang chủ</a>
                            </li>
                            <li class="header__manager-user-item">
                                <a href="">Tài khoản của tôi</a>
                            </li>
                            <li class="header__manager-user-item">
                                <a href="">Đăng xuất</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="home__container">
                <div class="grid">
                    <div class="home__container-main">
                        <div class="home__main-header">
                            <div class="home__header-left">
                                <h4>Thông tin cơ bản</h4>
                            </div>
                            <div class="home__header-mesage-del">
                                <span>${message__del}</span>
                            </div>
                            <div class="home__header-right">
                                <a href="add.account" class="btn btn__new"> Quay lại trang trước</a>
                            </div>

                        </div>
                        <div class="home__controller container" style="padding-bottom: 30px;">
                            <div class="home__controll-main" style=" margin-bottom: 50px;">
                                <form action="update.account" method="post"  style="margin-left: 30px;">
                                    <input type="hidden" name="acc_id" value="${acca.accID}" />
                                    <input type="hidden" name="username" value="${acca.username}"/>
                                    <div class="update__account">
                                        <label class="account-icon">*</label>
                                        <span>Tên tài khoản: </span>
                                        <span style="color: black">${acca.username}</span>
                                    </div>
                                    <div class="update__account">
                                        <label class="account-icon">*</label>
                                        <span>Mật khẩu: </span>
                                        <input required class="input__light" value="${acca.password}" type="text" name="password" placeholder="Nhập vào">
                                    </div>
                                    <div class="update__account">
                                        <label class="account-icon">*</label>
                                        <span>isAdmin: </span>
                                        <input required class="input__radio" ${acca.isAdmin == 1 ? 'checked': ''} type="radio" name="isAdmin" value="1">Admin &nbsp;&nbsp;&nbsp;&nbsp;
                                        <input class="input__radio" ${acca.isAdmin == 0 ? 'checked': ''} type="radio" name="isAdmin" value="0"> None
                                    </div>
                                    <div class="update__account">
                                        <label class="account-icon">*</label>
                                        <span>isBlock: </span>
                                        <input required class="input__radio" ${acca.isBlock == 1 ? 'checked': ''} type="radio" name="isBlock" value="1">Yes &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <input class="input__radio" ${acca.isBlock == 0 ? 'checked': ''} type="radio" name="isBlock" value="0"> No
                                    </div>
                                    <div class="update__account" style="margin-top: 20px;">
                                        <span></span>
                                        <input style="margin-left: 10px;" type="reset" value="Reset" class="btn__white">
                                        <input style="margin-left: 10px;" type="submit" value="Lưu & Hiển thị" class="btn btn__save">
                                    </div>
                                </form>
                            </div> 
                        </div>

                    </div>
                    <div class="home__footer" style="height: 50px;">

                    </div>
                </div>
            </div>
        </div>
    </body>

</html>