<%-- 
    Document   : forgotpassword
    Created on : Jan 15, 2024, 11:49:59 AM
    Author     : hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
        <link rel="stylesheet" href="assests/css/base.css">
        <link rel="stylesheet" href="assests/css.profile/forget_pass.css">
    </head>
    <body>
        <div class="container container__forgot">
            <h2 class="container__forgot-title">Quên Mật Khẩu</h2>
            <p class="container__forgot-label">Nhập địa chỉ email của bạn để đặt lại mật khẩu:</p>
            <form action="forget_password"  method="post">
                <label for="email" class="container__forgot-label">Email:</label>
                <input type="email" name="email" placeholder="Nhập email của bạn" class="form-forgot__input">
                <c:if test="${ms != null}" >
                    <span style="color: red">${ms}</span>
                </c:if>
                <br/>
                <button style="margin-bottom: 15px" type="submit" name="action" value="submitEmail">Lấy lại mật khẩu</button>      

                <label for="otp" class="container__forgot-label">Mã OTP: </label>
                <input type="number" name="otp" placeholder="Nhập mã OTP" class="form-forgot__input">
                <c:if test="${msOtp != null}" >
                    <span style="color: red">${msOtp}</span>
                </c:if>
                <br/>
                <div class="flex" style="justify-content: space-between; margin-top: 6px">
                    <button type="submit" name="action" value="submitOTP"> Xác nhận </button>
                    <a href="login.jsp" class="btn__white">Quay lại</a>
                </div>
            </form>
        </div>
    </body>
</html>