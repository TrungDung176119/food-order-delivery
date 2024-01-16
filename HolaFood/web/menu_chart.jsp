<%-- 
    Document   : chart_menu
    Created on : Oct 29, 2023, 8:01:27 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Suga - Thống kê sản phẩm</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- boostrap -->
        <link href="./assests/boostrap/bootstrap.css" rel="stylesheet">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css/profile.css">
        <link rel="stylesheet" href="./assests/css.cart/cart.css">
        <link rel="stylesheet" href="./assests/css.cart/chart.css">

        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
        <!-- chart -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.ico">

    </head>

    <div class="cart">
        <header class="header__cart">
            <div class="grid">
                <nav class="header__navbar">
                    <ul class="header__navbar-list">
                        <li class="header__navbar-item">
                            <span class="header__navbar-title--no-pointer">Kết nối </span>
                            <a href="" class="header__navbar-icon-link">
                                <i class="fab fa-facebook" style="color: var(--white-color);"></i>
                            </a>

                            <a href="" class="header__navbar-icon-link">
                                <i class="fab fa-instagram" style="color: var(--white-color);"
                                   aria-hidden="false"></i>&nbsp
                            </a>
                        </li>

                        <li class="header__navbar-item">
                            Hotline:
                            <a class="header__navbar-support" style="text-decoration: none;" href="tel:19004869"
                               title="1900 4869">1900 4869</a>
                        </li>
                        <li class="header__navbar-item">
                            Email:
                            <a class="header__navbar-support" style="text-decoration: none;"
                               href="mailto:hoanpche170404@fpt.edu.vn">support@suga.vn</a>
                        </li>
                    </ul>

                    <ul class="header__navbar-list">
                        <li class="header__navbar-item">
                            <a href="manager" class="header__navbar-item-link" style="text-decoration: none;"><i
                                    class="fa-regular fa-bell"></i> &nbspThông báo</a>
                        </li>

                        <li class="header__navbar-item">
                            <a href="" class="header__navbar-item-link" style="text-decoration: none;"><i
                                    class="fa-regular fa-circle-question"></i> &nbspHỗ trợ </a>
                        </li>

                        <!-- <li class="header__navbar-item header__navbar-item--strong header__navbar-item--separate">
                        <a href="" class="header__navbar-item-link" style="text-decoration: none;">Đăng ký</a>
                    </li>
                    <li class="header__navbar-item header__navbar-item--strong">
                        <a href="" class="header__navbar-item-link" style="text-decoration: none;">Đăng nhập</a>
                    </li> -->

                        <li class="header__navbar-item header__navbar-user">
                        <c:if test="${sessionScope.acc == null}" >
                            <li class="header__navbar-item header__navbar-item--strong header__navbar-item--separate">
                                <a href="register.jsp" class="header__navbar-item-link" style="text-decoration: none;">Đăng ký</a>
                            </li>
                            <li class="header__navbar-item header__navbar-item--strong">
                                <a href="login.jsp" class="header__navbar-item-link" style="text-decoration: none;">Đăng nhập</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.acc != null}" >
                            <li class="header__navbar-item header__navbar-user"> 
                                <img class="header__navbar-user-img" 
                                     src="https://yt3.ggpht.com/yti/ADpuP3MEBtPK2w3PD74lGPnowgesuAR_VQYgWY4u0_NPcw=s88-c-k-c0x00ffffff-no-rj" alt="">

                            <c:if test="${(AccDetail.nickname == null) || (AccDetail.nickname == '')}">
                                <span style="padding-left: 6px">${sessionScope.acc.username}</span>
                            </c:if>
                            <c:if test="${AccDetail.nickname != null}">
                                <span style="padding-left: 6px">${AccDetail.nickname}</span>
                            </c:if>
                            <ul class="header__navbar-user-menu" >
                                <li class="header__navbar-user-item">
                                    <a href="profile" style="text-decoration: none;">Tài khoản của tôi</a>
                                </li>
                                <c:if test="${sessionScope.acc.isAdmin == 1}" >
                                    <li class="header__navbar-user-item">
                                        <a href="manager" style="text-decoration: none;">Quản lý sản phẩm</a>
                                    </li>
                                    <li class="header__navbar-user-item">
                                        <a href="add.account" style="text-decoration: none;">Quản lý tài khoản</a>
                                    </li>
                                    <li class="header__navbar-user-item">
                                        <a href="chart.jsp">Chart</a>
                                    </li>
                                </c:if>
                                <c:if test="${sessionScope.acc.isAdmin != 1}" >
                                    <li class="header__navbar-user-item">
                                        <a href="" style="text-decoration: none;">Đơn mua</a>
                                    </li>
                                </c:if>
                                <li class="header__navbar-user-item">
                                    <a href="logout" style="text-decoration: none;">Đăng xuất</a>
                                </li>
                            </ul>
                            </li>
                        </c:if>
                        </li>
                    </ul>
                </nav>
            </div>
        </header>

        <!-- cart with search -->
        <div class="cart-with-search">
            <div class="grid cart-search">
                <div class="cart__header-logo">
                    <a href="home" class="header-logo">
                        <i class="fa-brands fa-apple"></i>
                        <div class="header-logo__name">Suga </div>
                    </a>
                    <a href="chartrevenue.jsp" style="color: var(--primary-color)" class="cart__header-title">
                        Dashboard
                    </a>
                </div>
                <form action="search" method="post" class="cart__header-search">
                    <input class="cart__header-search-input" type="text" name="text"
                           placeholder="NGÀY 12/11 SIÊU SALE ĐẾN 50%">
                    <button type="submit" class="cart__header-search-btn">
                        <i class="fa-solid fa-magnifying-glass" style="color: white;"></i>
                    </button>
                </form>
            </div>
        </div>
        <!-- container chart  -->

        <div class="grid container-chart">
            <div class="profile__left grid__column-180" style="width: 16%">
                <h3>
                    Danh Mục
                </h3>
                <div class="profile__left-list">
                    <div class="profile__left-option">
                        <a href="chartline.jsp"><i class="fa-solid fa-chart-line"
                                                   style="color: rgb(243, 53, 36); font-size: 1.8rem;"></i>
                            &nbsp Thống kê đơn hàng </a>
                    </div>
                    <div class="profile__left-option">
                        <a href="chart.jsp"><i class="fa-solid fa-chart-column"
                                               style="color: rgb(5, 75, 196); font-size: 1.8rem;"></i>
                            &nbsp Sản phẩm tồn kho</a>
                    </div>
                    <div class="profile__left-option">
                        <a href="chartrevenue.jsp"><i class="fa-solid fa-chart-pie" style="color: #f960ec; font-size: 1.8rem;"></i>
                            &nbsp Doanh thu sản phẩm</a>
                    </div>
                    <div class="profile__left-option">
                        <a href=""><i class="fa-solid fa-chart-area"  style="color: #f9c860; font-size: 1.8rem;"></i>
                            &nbspĐơn mua</a>
                    </div>
                    <!-- <div>
                        <a href="">Suga Xu</a>
                    </div> -->
                </div>
            </div>


