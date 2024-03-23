<%-- 
    Document   : profile
    Created on : Oct 6, 2023, 8:56:51 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HolaFood</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <!-- css -->
    <link rel="stylesheet" href="./assests/css.profile/profile.css">
    <link href="assests/css/order.css" rel="stylesheet" type="text/css"/>

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
<div class="profile__left grid__column-180">
    <div class="profile__left-head">
        <img id="ngoaiFormImage" src="https://down-vn.img.susercontent.com/file/e87892e1b559685f3a891b90b8fd4ed4_tn"
             alt="">
        <div class="profile__left-head-name">
            <c:if test="${(sessionScope.AccDetail.nickname == null) || (sessionScope.AccDetail.nickname == '')}">
                <span style="padding-left: 6px">${sessionScope.acc.username}</span>
            </c:if>
            <c:if test="${sessionScope.AccDetail.nickname != null}">
                <span style="padding-left: 6px">${sessionScope.AccDetail.nickname}</span>
            </c:if>
            <a href="profile">
                <i class="fa-solid fa-pen" style="color: rgb(155, 155, 155); font-size: 1.3rem"></i>
                Sửa hồ sơ
            </a>
        </div>
    </div>
    <div class="profile__left-list">
        <div class="profile__left-option">
            <a href="profile"><i class="fa-regular fa-user"  style="color: rgb(15, 117, 200); font-size: 1.8rem;"></i>
                &nbsp Thông tin tài khoản </a>
        </div>
        <div class="profile__left-option">
            <a href="password"><i class="fa-solid fa-key" style="color: #ff4444; font-size: 1.8rem;"></i>
                &nbsp Đổi mật khẩu</a>
        </div>
        <div class="profile__left-option">
            <a href="address"><i class="fa-regular fa-address-book" style="color: rgb(26, 230, 60); font-size: 2rem;"></i>
                &nbsp Địa chỉ</a>
        </div>
        <div class="profile__left-option">
            <a href="purchase?type=0"><img style="height: 24px;" src="https://down-vn.img.susercontent.com/file/f0049e9df4e536bc3e7f140d071e9078"alt="">
                &nbspĐơn mua</a>
        </div>
        <div class="profile__left-option ">
            <a href="notifications">&nbsp;<i class="fa-regular fa-bell" style="color: #ff4444; font-size: 1.8rem;"></i>
                &nbsp;&nbsp;Thông báo</a>
        </div>
        <div class="profile__left-option">
            <a href=""> <img style="height: 24px;" src="https://down-vn.img.susercontent.com/file/a0ef4bd8e16e481b4253bd0eb563f784"alt="">
                &nbspSuga Xu</a>
        </div> 

    </div>
</div>

