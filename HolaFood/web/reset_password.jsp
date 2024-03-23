<%-- 
    Document   : reset_password
    Created on : Jan 22, 2024, 4:24:46 PM
    Author     : admin
--%>
<%@ page import="model.profile.AccountDetail" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="assests/css/base.css">
        <link rel="stylesheet" href="assests/css.profile/forget_pass.css">
    </head>
    <body>
        <div class="container container__forgot">
            <h2 class="container__forgot-title">Đặt Lại Mật Khẩu</h2>
            <div style="color: red; font-size: 13px">Lưu ý: Mật khẩu mới phải dài 8-16 ký tự, chứa ít nhất một ký tự viết hoa và 1 ký tự số và 1 ký tự đặc biệt</div>
            <form action="reset_password"  method="post">
                <label for="pass" class="container__forgot-label">Mật khẩu mới: </label>
                <input required type="text" name="pass" placeholder="Nhập mật khẩu mới" class="form-forgot__input">
                <c:if test="${ms != null}" >
                    <p style="color: red">${ms}</p>
                </c:if>
                <label for="repass" class="container__forgot-label" style="margin-top: 12px">Nhập lại mật khẩu:  </label>
                <input required type="text" name="repass" placeholder="Nhập lại mật khẩu" class="form-forgot__input">
                <c:if test="${msRe != null}" >
                    <p style="color: red">${msRe}</p>
                </c:if>
                <button type="submit" style=" display: flex;margin-left: auto !important"> Xác nhận </button>
            </form>
            <%
   // Lấy đối tượng từ session
   AccountDetail accd = (AccountDetail) session.getAttribute("accDetail1");
            %>
            <%
                // Kiểm tra xem đối tượng có tồn tại hay không
                if (accd != null) {
            %>
            <p>Đối tượng đã còn tồn tại trong session</p>
            <!-- Thực hiện các thao tác cần thiết với đối tượng -->
            <%
                } else {
            %>
            <p>Đối tượng không tồn tại trong session. Vui lòng lấy lại OTP code</p>
            <%
                }
            %>
        </div>
    </body>
</html>
