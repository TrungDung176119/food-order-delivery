<%-- 
    Document   : detail
    Created on : Jan 7, 2024, 4:41:34 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>HolaFood </title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <link href="assests/boostrap/bootstrap.css" rel="stylesheet" type="text/css"/>
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css.manager/manager.css">
        <link rel="stylesheet" href="./assests/css.manager/add.css">

        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.png"/>


    </head>

    <body>
        <div class="app">
            <jsp:include page="menu_home.jsp"/>
            <div class="home__container">
                <div class="grid">
                    <div class="home__container-main">
                        <div class="home__main-header">
                            <div class="home__header-left">
                                <h4>Thông tin tố cáo</h4>
                                <c:if test="${ms != null}">
                                    <div class="home__header-mesage-del flex" style="min-width: unset">
                                        ${requestScope.ms}
                                    </div> 
                                </c:if>
                            </div>

                            <div class="home__header-right">
                                <c:if test="${not empty ReportedAccount}">
                                    <a href="details?pid=${ReportedAccount.accid}" class="btn btn__save"> Quay lại trang trước</a>></a>
                                </c:if>
                                <c:if test="${not empty ReportedProduct}">
                                    <a href="details?pid=${ReportedProduct.pid}" class="btn btn__save"> Quay lại trang trước</a>
                                </c:if>
                            </div>
                        </div>
                        <div class="home__controller container" style="padding-bottom: 30px;">
                            <form action="addReport" method="post" class="home__controll-main" style=" margin-bottom: 50px;">
                                <div class="home__controll-input">
                                    <div class="home__controll-input">
                                        <c:if test="${not empty ReportedAccount}">
                                            <span class="home__controll-input-name">Tài khoản bị tố cáo</span>
                                            <input name="account" value="${ReportedAccount.accid}" type="hidden">
                                            <input name="" type="text" class="home__controll-input-text" value="${ReportedAccount.username}" readonly placeholder="">
                                        </c:if>
                                        <c:if test="${not empty ReportedProduct}">
                                            <span class="home__controll-input-name">Sản phẩm bị tố cáo</span>
                                            <input name="product" value="${ReportedProduct.pid}" type="hidden">
                                            <input name="" type="text" class="home__controll-input-text" value="${ReportedProduct.title}" readonly placeholder="">
                                        </c:if>
                                    </div>
                                </div>
                                <div class="home__controll-input">
                                    <span class="home__controll-icon">*</span>
                                    <span class="home__controll-input-name">Chọn lý do</span>
                                    <select name="reason" class="home__controll-input-text">
                                        <c:forEach items="${listrt}" var="o">
                                            <option value="${o.report_id}">${o.report_name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="home__controll-input">
                                    <span class="home__controll-icon">*</span>
                                    <span class="home__controll-input-name">Mô tả tố cáo</span>
                                    <textarea required  name="report_description" class="home__controll-input-text text__describe" type="text" placeholder="Thêm mô tả sản phẩm"></textarea>
                                </div> 
                                <div class="home__controll-input">
                                    <span class="home__controll-input-name">Bằng chứng</span>
                                    <input name="proof" class="home__controll-input-text" type="text" placeholder="Thêm bằng chứng cho đơn tố cáo (nếu có)">
                                </div> 
                                <div class="home__controll-button">
                                    <div class="home__controll-button-left">
                                    </div>
                                    <div class="home__controll-button-right">
                                        <button class="btn btn__save btn__action-submit" type="submit">Xác nhận</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="footer.jsp" />
        </div>

    </body>

</html>