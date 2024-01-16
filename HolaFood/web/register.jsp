<%-- 
    Document   : register
    Created on : Sep 24, 2023, 9:55:13 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng ký ngay</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- css -->
        <link href="assests/css/base.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="./assests/css/login.css">
        <link rel="stylesheet" href="./assests/css/register.css">
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
            <div class="container__login">
                <div class="header__login-title">
                    <div class="header__login-left">
                        <i class="fa-brands fa-apple"></i>
                        <a href="home" class="header__login-left__name" style="color: var(--primary-color); text-decoration: none;">Suga&nbsp&nbsp</a>
                        <div class="header__login-left__name" style="font-size: 2.18rem; margin-top: 2px;">Đăng ký</div>
                    </div>
                    <div class="header__login-help">
                        <a href="" style="text-decoration: none;">Bạn cần giúp đỡ?</a>
                    </div>
                </div>

                <div style="background-color: var(--primary-color);">
                    <div class="center__login">
                        <div class="center__login-title">
                            <i class="fa-brands fa-apple"></i>
                            <h2>Suga Shop</h2>
                            <h3>Nền tảng bán điện thoại trực tuyến </h3>
                            <h3>yêu thích ở Đông Nam Á & Việt Nam </h3>
                        </div>
                        <div class="center__login-form " style="padding: 0 32px;">
                            <h3 class="form__heading">Đăng ký </h3>
                            <form class="form__login" action="signup" method="post">
                                <div class="form__login-${ms}" >
                                    ${message}
                                </div>
                                <div class="form__login-group">
                                    <input required name="user" type="text" class="form__login-group__input" placeholder="Email/Số điện thoại/Tên đăng nhập">
                                </div>
                                <div class="form__login-group">
                                    <input required name="pass" type="password" class="form__login-group__input" placeholder="Mật khẩu">
                                </div>
                                <div class="form__login-group">
                                    <input required name="repass" type="password" class="form__login-group__input" placeholder="Nhập lại mật khẩu">
                                </div>
                                <div class="form__login-controls">
                                    <button type="submit" class="form__login-controls__btn">Đăng ký </button>
                                </div>
                                <div class="form__login-hoac">
                                    <div class="hoac"></div>
                                    <span class="hoac__name" style="color: #dbdbdb;;">HOẶC</span>
                                    <div class="hoac"></div>
                                </div>
                                <div class="form__login-social">
                                    <div class="form__login__btn"><i class="fa-brands fa-facebook" style="color: #0563ce; font-size: 2.2rem;"></i> &nbspFacebook </div>
                                    <div class="form__login__btn "><i class="fa-brands fa-google" style="color: #f6720e; font-size: 2.2rem;"></i>&nbspGoogle </div>
                                </div>
                                <div class="form__login-footer">
                                    <p>Bạn đã có tài khoản?&nbsp</p>
                                    <a href="login.jsp" style="text-decoration: none; color: var(--primary-color);">Đăng nhập</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
            <!--app footer-->   
            <jsp:include page="footer.jsp"></jsp:include>
        </div>

        <script src="assests/js/Jquery.js"></script>
        <script src="assests/js/bootstrap.min.js"></script>
    </body>

</html>