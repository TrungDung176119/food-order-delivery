F<%-- 
    Document   : registerSeller
    Created on : Jan 7, 2024, 11:25:55 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>HolaFood - Register to Seller</title>
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
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.png">

    </head>

    <body>
        <div class="resgister__seller">
            <div class="container__heading">
                <div class="header__manager">
                    <div class="header__manager-left">
                        <a href="home" class="header__manager-item">
                            <i class="fa-solid fa-utensils"></i>
                            <span class="header__manager-item__name">HolaFood</span>
                        </a>
                        <span class="header__manager-title">Đăng Ký Để Trở Thành Người Bán</span>
                    </div>

                    <div class="header__manager-right">

                        <ul class="header__manager-user-menu">
                            <li class="header__manager-user-item">
                                <a href="home" style="padding-top: 10px;">Trang chủ</a>
                            </li>
                            <li class="header__manager-user-item">
                                <a href="profile">Tài khoản của tôi</a>
                            </li>
                            <li class="header__manager-user-item">
                                <a href="logout">Đăng xuất</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="register-seller-form">
                <div class="container">
                    <form class="register-seller" action="registerseller" method="post" style="padding-top: 40px">
                        <table class="register-seller-box" style="width: 100%; margin-bottom: 90px;">
                            <tr>
                                <td class="register-seller-item flex">
                                    <div  type="text" class="register-seller-title"> <span class="register-seller-icon">*</span>Tên Shop
                                    </div>
                                    <div class="flex_coloumn">
                                        <input required name="store_name" maxlength="30" value="${sessionScope.store_name}" class="register-seller-input" type="text">
                                        <span class="text-notify__wrong">${msName}</span>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="register-seller-item flex">
                                    <div  type="text" class="register-seller-title"> <span class="register-seller-icon">*</span>Địa Chỉ
                                    </div>
                                    <div class="flex_coloumn">
                                        <input required name="address_store" maxlength="80" value="${sessionScope.address_store}" class="register-seller-input" type="text">
                                        <span class="text-notify__wrong">${msAddress}</span>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="register-seller-item flex">
                                    <div type="text"  class="register-seller-title"> <span class="register-seller-icon">*</span>Email
                                    </div>
                                    <div class="flex_coloumn">
                                        <input required name="email_store" value="${sessionScope.email_store}" class="register-seller-input input-box" type="text" placeholder="@gmail.com">
                                        <span class="text-notify__wrong">${msEmail}</span>
                                    </div>
                                </td>
                                </td>
                            </tr>
                            <tr>
                                <td class="register-seller-item flex">
                                    <div name="phone_store" type="text" class="register-seller-title"> <span class="register-seller-icon">*</span>Số Điện
                                        Thoại</div>
                                    <div class="flex_coloumn">
                                        <input required name="phone_store" value="${sessionScope.phone_store}" class="register-seller-input" type="number" min="0">
                                        <span class="text-notify__wrong">${msPhone}</span>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="register-seller-item flex">
                                    <div class="register-seller-title">
                                        <input required name="rule" value="1" style="height: 18px;width: 23px;" type="checkbox" id="ruleCheckbox">
                                    </div>
                                    <span>Vui lòng tuân thủ các quy định đăng bài</span>
                                </td>
                            </tr>
                            <tr>
                                <td class="register-seller-item flex">
                                    <span class="register-seller-title"></span>
                                    <div>
                                        <input type="submit" value="Đăng Kí" name="action" class="btn__save register-seller-btn" style="margin: 0" id="registerBtn" disabled>
                                        <span class="home__header-mesage-del" style="margin-left: 30px">${ms}</span>
                                    </div>
                                </td>
                            </tr>
                            <c:if test="${sessionScope.otpRegisterSeller != null}"  >
                                <tr>
                                    <td class="register-seller-item flex" style="margin-top: 18px">
                                        <div class="register-seller-title"> <span class="register-seller-icon">*</span>Mã OTP</div>
                                        <input name="otpCode" class="register-seller-input" type="text" style="max-width: 160px;">
                                        <input type="submit" value="Xác Nhận" name="action" class="btn__save register-seller-btn">
                                    </td>
                                </tr> 
                            </c:if>
                        </table>
                    </form>
                </div>
            </div>

        </div>

    </body>
    <script>
        const ruleCheckbox = document.getElementById('ruleCheckbox');
        const registerBtn = document.getElementById('registerBtn');

        ruleCheckbox.addEventListener('change', function () {
            if (!ruleCheckbox.checked) {
                registerBtn.disabled = true;
                registerBtn.style.color = "#6a6a6a";
                registerBtn.style.backgroundColor = "rgba(0, 0, 0, .26)";
            } else {
                registerBtn.disabled = false;
                registerBtn.style.color = "#fff"; // Đặt màu chữ về mặc định
                registerBtn.style.backgroundColor = "#ee4d2d"; // Đặt màu nền về mặc định
            }
        });

        // Hàm debouncing tránh spam đăng kí
        function debounce(func, delay) {
            let timer;
            return function () {
                const context = this;
                const args = arguments;
                clearTimeout(timer);
                timer = setTimeout(() => {
                    func.apply(context, args);
                }, delay);
            };
        }

        // Xử lý hành động khi nút được click
        function handleClick() {
            // Thực hiện hành động khi nút được click, ví dụ: gửi yêu cầu đăng ký
            console.log("Đăng kí đã được gửi!");
        }

        const debouncedClick = debounce(handleClick, 10000); // Giới hạn 1 lần click mỗi 1 giây

        registerBtn.addEventListener('click', debouncedClick);

    </script>
</html>