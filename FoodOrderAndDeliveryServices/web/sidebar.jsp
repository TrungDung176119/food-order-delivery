<%-- 
    Document   : sidebar
    Created on : Dec 27, 2023, 11:00:25 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <link rel="stylesheet" href="./assests/css.manager/sidebar.css">
</head>

<div class="container__heading">
    <div class="container__heading">
        <div class="header__manager">
            <div class="header__manager-left">
                <a href="home" class="header__manager-item">
                    <i class="fa-solid fa-utensils"></i>
                    <span class="header__manager-item__name">HolaFood</span>
                </a>
                <a href="manager" class="header__manager-title">Trang Quản Lý</a>
            </div>

            <div class="header__manager-right">
                <div class="header__manager-user flex">
                    <img class="header__manager-user-img"
                         src="https://down-vn.img.susercontent.com/file/e87892e1b559685f3a891b90b8fd4ed4_tn" alt="">
                    <c:if test="${(AccDetail.nickname == null) || (AccDetail.nickname == '')}">
                        <div style="padding-left: 6px">&nbsp;${sessionScope.acc.username}</div>
                    </c:if>
                    <c:if test="${AccDetail.nickname != null}">
                        <div style="padding-left: 6px">&nbsp;${AccDetail.nickname}</div>
                    </c:if>
                </div>
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
</div>
<div class="sidebar">
    <ul class="sidebar-menu">
        <li class="sidebar-item">
            <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/3fa3bdb20eb201ae3f157ee8d11a39d5" alt="Quản Lý Sản Phẩm">
            <a href="manager" class="sidebar-item__title" >Quản Lý Sản Phẩm</a>
        </li>
        <c:if test="${sessionScope.acc.roleid == 3}" >
            <li class="sidebar-item">
                <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/6b1ffcde1ff12621088110f419a5283a" alt="Quản Lý Người Bán">
                <a href="managerSeller" class="sidebar-item__title">Quản Lý Người Bán</a>
            </li>
            <li class="sidebar-item">
                <i class="fa-regular fa-user sidebar-item__icon"></i>
                <a href="managerAcc" class="sidebar-item__title">Quản Lý Tài Khoản</a>
            </li>
            <li class="sidebar-item">
                <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/3fa3bdb20eb201ae3f157ee8d11a39d5" alt="Quản Lý Thể Loại Hàng">
                <a href="add.category" class="sidebar-item__title" >Quản Lý Thể Loại</a>
            </li>
        </c:if>
        <c:if test="${sessionScope.acc.roleid == 2}" >
            <li class="sidebar-item">
                <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/f82f8ccb649afcdf4f07f1dd9c41bcb0" alt="Quản Lý Menu">
                <a href="managerMenu" class="sidebar-item__title">Quản Lý Menu</a>
            </li>
            <li class="sidebar-item">
                <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/c15905d5a6284687c4a6ad00d0feb511"   alt="Quản Lý Đơn Hàng">
                <a href="managerOrder?type=0" class="sidebar-item__title">Quản Lý Đơn Hàng</a>
            </li>
        </c:if>
        <li class="sidebar-item">
            <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/09759afab8ae066ca5e1630bc19133a1" alt="Dữ Liệu">
            <a href="chart" class="sidebar-item__title">Dữ Liệu</a>
        </li>
        <li class="sidebar-item">
            <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/f9e8756bcf783fe1783bf59577fdb263" alt="Tài Chính">
            <a href="" class="sidebar-item__title">Tài Chính</a>
        </li>
        <li class="sidebar-item">
            <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/9f2ae273250a1a723d7d8892c9584c6d" alt="Chăm Sóc Khách Hàng">
            <a href="" class="sidebar-item__title">Chăm Sóc Khách Hàng</a>
        </li>
    </ul>
</div>