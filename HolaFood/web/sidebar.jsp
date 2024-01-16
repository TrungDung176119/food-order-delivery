<%-- 
    Document   : sidebar
    Created on : Dec 27, 2023, 11:00:25 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HolaFood</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <!-- css -->
    <link rel="stylesheet" href="./assests/css/base.css">
    <link rel="stylesheet" href="./assests/css.manager/manager.css">
    <link rel="stylesheet" href="./assests/css.manager/sidebar.css">
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

<div class="container__heading">
    <div class="container__heading">
        <div class="header__manager">
            <div class="header__manager-left">
                <a href="home.jsp" class="header__manager-item">
                    <i class="fa-brands fa-apple"></i>
                    <span class="header__manager-item__name">Suga</span>
                </a>
                <span class="header__manager-title">Quản lý Admin</span>
            </div>

            <div class="header__manager-right">
                <div class="header__manager-user flex">
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
                        <a href="home" style="padding-top: 10px;">Trang chủ</a>
                    </li>
                    <li class="header__manager-user-item">
                        <a href="profile.jsp">Tài khoản của tôi</a>
                    </li>
                    <li class="header__manager-user-item">
                        <a href="logout.jsp">Đăng xuất</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="sidebar">
    <ul class="sidebar-menu">
        <li class="sidebar-item">
            <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/3fa3bdb20eb201ae3f157ee8d11a39d5" alt="Quản Lý Sản Phẩm">
            <a href="manager.jsp" class="sidebar-item__title" >Quản Lý Sản Phẩm</a>
        </li>
        <li class="sidebar-item">
            <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/f82f8ccb649afcdf4f07f1dd9c41bcb0" alt="Quản Lý Đơn Hàng">
            <a href="order.jsp" class="sidebar-item__title">Quản Lý Đơn Hàng</a>
        </li>
        <li class="sidebar-item">
            <i class="fa-regular fa-user sidebar-item__icon"></i>
            <a href="managerAcc.jsp" class="sidebar-item__title">Quản Lý Tài Khoản</a>
        </li>
        <li class="sidebar-item">
            <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/09759afab8ae066ca5e1630bc19133a1" alt="Quản Lý Đơn Hàng">
            <a href="chart.jsp" class="sidebar-item__title">Dữ Liệu</a>
        </li>
        <li class="sidebar-item">
            <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/f9e8756bcf783fe1783bf59577fdb263" alt="Quản Lý Đơn Hàng">
            <a href="" class="sidebar-item__title">Tài Chính</a>
        </li>
        <li class="sidebar-item">
            <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/9f2ae273250a1a723d7d8892c9584c6d" alt="Quản Lý Đơn Hàng">
            <a href="" class="sidebar-item__title">Chăm Sóc Khách Hàng</a>
        </li>
    </ul>
</div>