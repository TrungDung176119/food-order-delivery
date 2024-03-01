<%-- 
    Document   : registerSeller
    Created on : Jan 7, 2024, 11:25:55 PM
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
    <!-- boostrap -->
    <link href="./assests/boostrap/bootstrap.css" rel="stylesheet">
    <!-- css -->
    <link rel="stylesheet" href="./assests/css/base.css">
    <link rel="stylesheet" href="./assests/css/registerSeller.css">

    <!-- font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

    <!-- icon title -->
    <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.ico">


</head>

<body>
    <div class="resgister__seller">
        <div class="container__heading">
            <div class="header__manager">
                <div class="header__manager-left">
                    <a href="home" class="header__manager-item">
                        <i class="fa-brands fa-apple"></i>
                        <span class="header__manager-item__name">Suga</span>
                    </a>
                    <span class="header__manager-title">Xác Nhận Email</span>
                </div>

                <div class="header__manager-right">
                    
                    <ul class="header__manager-user-menu">
                        <li class="header__manager-user-item">
                            <a href="home.html" style="padding-top: 10px;">Trang chủ</a>
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

        <div class="register-seller-form">
            <div class="container">
                <form class="register-seller" action="checkemail" method="post">
                    <table class="register-seller-box" style="width: 100%; margin-bottom: 90px;">
                        
                       
                        <tr>
                            <td class="register-seller-item flex">
                                <div class="register-seller-title"> <span class="register-seller-icon">*</span>Mã OTP
                                </div>
                                <input required name="code" class="register-seller-input" type="text" style="max-width: 120px;">
                                <input type="submit" value="Xác Nhận" class="btn__save register-seller-btn">
                            </td>
                        </tr>
                      
                    </table>
                </form>
            </div>
        </div>

    </div>

</body>

</html>