<%-- 
    Document   : home_menu.jsp
    Created on : Jan 7, 2024, 5:06:35 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HolaFood</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <!-- boostrap -->
    <link href="assests/boostrap/bootstrap.css" rel="stylesheet" type="text/css"/>
    <!-- css -->
    <link rel="stylesheet" href="./assests/css/base.css">
    <link rel="stylesheet" href="./assests/css/main.css">
    <link rel="stylesheet" href="./assests/css/details.css">
    <!-- font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

    <!-- icon title -->
    <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.png">
</head>

<header class="header">
    <div class="container">
        <nav class="header__navbar">
            <ul class="header__navbar-list">
                <li class="header__navbar-item">
                    <a href="registerSeller.jsp" class="header__navbar-item-link header__navbar-item--separate">Kênh người bán</a>
                </li>
                <li class="header__navbar-item select">
                    <span class="header__navbar-item-link"><i class="fa-solid fa-earth-americas"></i>
                        &nbsp;Tiếng Việt <i class="fa-solid fa-angle-down navbar-item__icon"></i></span>
                    <ul style="display: none;">
                        <li class="select-item">Tiếng Việt</li>
                        <li class="select-item">EngLish</li>
                    </ul>
                </li>
            </ul>
            <ul class="header__navbar-list">
                <li class="header__navbar-item header__navbar-notify">
                    <a href="notifications" class="header__navbar-item-link">
                        <i class="fa-regular fa-bell" style="font-size: 1.6rem"></i> &nbsp;Thông báo
                    </a>
                    <c:if test="${sessionScope.sumNotify > 0}">
                        <span class="header__notify-notice">${sessionScope.sumNotify}</span>
                    </c:if>
                    <div class="header__notify">
                        <header class="header__notify-head">
                            <h3>Thông báo mới nhận</h3>
                        </header>
                        <ul class="header__notify-list">
                            <c:forEach begin="0" end="4" items="${sessionScope.listNotify}" var="n" >
                                <li class="header__notify-item">
                                    <a href="${n.url}" class="header__notify-link" title="click để xem chi tiết thông báo">
                                        <div>
                                            <img src="${n.no_img}" alt="" class="header__notify-img">
                                        </div>
                                        <div class="header__notify-info">
                                            <span class="header__notify-name">${n.no_title}</span>
                                            <span class="header__notify-describe">${n.no_message}</span>
                                        </div>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                        <footer class="header__notify-footer">
                            <a href="notifications" class="header__notify-footer-btn">Xem tất cả</a>
                        </footer>
                    </div>
                </li>
                <li class="header__navbar-item">
                    <a href="#" class="header__navbar-item-link"><i class="fa-regular fa-circle-question"></i>
                        &nbspHỗ trợ </a>
                </li>

                <c:if test="${sessionScope.acc == null}" >
                    <li class="header__navbar-item header__navbar-item--separate">
                        <a href="register.jsp" class="header__navbar-item-link">Đăng ký</a>
                    </li>
                    <li class="header__navbar-item ">
                        <a href="login" class="header__navbar-item-link">Đăng nhập</a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.acc != null}" >
                    <li class="header__navbar-item header__navbar-user ">
                        <img class="header__navbar-user-img"
                             src="https://down-vn.img.susercontent.com/file/e87892e1b559685f3a891b90b8fd4ed4_tn"
                             alt="">    
                        <!--<div class="header__navbar-user-name "> &nbsp;${acc.username}</div>-->
                        <c:if test="${(AccDetail.nickname == null) || (AccDetail.nickname == '')}">
                            <div class="header__navbar-user-name ">&nbsp;${sessionScope.acc.username}</div>
                        </c:if>
                        <c:if test="${AccDetail.nickname != null}">
                            <div class="header__navbar-user-name ">&nbsp;${AccDetail.nickname}</div>
                        </c:if>
                        <ul class="header__navbar-user-menu">
                            <li class="header__navbar-user-item">
                                <a href="profile">Tài khoản của tôi</a>
                            </li>
                            <c:if test="${sessionScope.acc.roleid == 3}" >
                                <li class="header__navbar-user-item">
                                    <a href="managerAcc">Admin</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.acc.roleid == 2}" >
                                <li class="header__navbar-user-item">
                                    <a href="manager" >Quản Lý Người Bán</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.acc.roleid == 1}" >
                                <li class="header__navbar-user-item">
                                    <a href="purchase?type=0">Đơn mua</a>
                                </li>
                            </c:if>
                            <li class="header__navbar-user-item">
                                <a href="logout">Đăng xuất</a>
                            </li>
                        </ul>
                    </li> 
                </c:if>
            </ul>
        </nav>

        <div class="header-with-search">
            <a href="home" class="header__logo flex">
                <i class="fa-solid fa-utensils"></i>
                <div class="header__logo-name">HolaFood</div>
            </a>

            <div class="header-address__list">
                <select class="header-address__select" id="categorySelect" onchange="redirectToServlet()">
                    <jsp:useBean id="c" class="dal.ProductDAO">
                        <c:forEach items="${c.allCategory}" var="cate">
                            <option value="${cate.id}">${cate.name}</option>
                        </c:forEach>
                    </jsp:useBean>
                </select>
            </div>

            <form action="search" method="get" class="header__search">
                <input type="text" name="keyword" value="${keyword}" class="header__search-input" placeholder="Nhập để tìm kiếm sản phẩm"
                       title="search">

                <div class="header__search-select">
                    <span class="header__search-select-label">Lựa chọn &nbsp;</span>
                    <i class="fa-solid fa-sort-down"></i>

                    <ul class="header__search-option">
                        <li class="header__search-option-item ">
                            <a href="">Trong trường</a>
                        </li>
                        <li class="header__search-option-item">
                            <a href="">Ngoài trường</a>
                        </li>
                    </ul>
                </div>
                <button type="submit" name="action" value="search" class="header__search-button">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
            </form>


            <!--header cart list-->
            <div class="header__cart">
                <a href="showcart">
                    <i class="header__cart-icon fa-solid fa-cart-shopping"></i>
                </a>
                <span class="header__cart-notice">${amount}</span>

                <!-- cart is empty -->
                <div class="header__cart-list">
                    <c:if test="${amount == 0}">
                        <img src="assests/img/no-cart.png" alt="Chưa có sản phẩm" class="header__cart-list-none-img">
                        <span class="header__cart-list-none-msg">Chưa có sản phẩm</span> 
                    </c:if>
                    <c:if test="${amount != 0}">
                        <h4 class="header__cart-heading">Sẳn phẩm mới thêm</h4>
                        <ul class="header__cart-list-item" ">
                            <c:forEach items="${cart.items}" begin="0" end="7" var="i">
                                <li class=" header__cart-item">
                                    <img class="header__cart-img"
                                         src="${i.product.image}"
                                         alt="">
                                    <a href="detail?pid=${i.product.pid}" class="header__cart-item-info">
                                        <div class="header__cart-item-head">
                                            <h5 class="header__cart-item-name text__overflow-1">${i.product.title}</h5>
                                            <span class="header__cart-item-price">${i.product.current_price}</span>
                                        </div>
                                        <div class="header__cart-item-body">
                                            <span class="header__cart-item-address text__overflow-1">Địa chỉ: ${i.product.address_store}</span>
                                            <span class="header__cart-item-remove"> x${i.quantity} </span>
                                        </div>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                        <div class="header__cart-footer">
                            <p>${amount} thêm hàng vào giỏ</p>
                            <a href="showcart" class="btn header__cart-btn"> Xem giỏ hàng </a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
</header>
<!-- Include jQuery -->
<!--<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>-->
<script>
                    function redirectToServlet() {
                        var selectedValue = document.getElementById("categorySelect").value;
                        window.location.href = "search?cateid=" + selectedValue;
                    }

//                    function updateNotificationsList() {
//                        // Gửi yêu cầu AJAX để lấy dữ liệu mới từ server
//                        $.ajax({
//                            url: '/home', // Đặt URL của API của bạn ở đây
//                            method: 'GET',
//                            dataType: 'json',
//                            success: function (data) {
//                                // Cập nhật số lượng thông báo
//                                updateNotificationCount(data.length);
//
//                                // Cập nhật danh sách thông báo trên trang
//                                updateNotificationList(data);
//                            },
//                            error: function (xhr, status, error) {
//                                console.error('Error fetching notifications:', error);
//                                console.log('Status:', status);
//                                console.log('Response Text:', xhr.responseText);
//                            }
//                        });
//                    }
//                    
//                    function updateNotificationCount(count) {
//                        // Lấy phần tử ul trong DOM để thêm các thông báo vào
//                        var noticeElement = document.querySelector('.header__notify-notice');
//
//                        if (noticeElement) { // Kiểm tra xem phần tử có tồn tại không
//                            if (count > 0) {
//                                noticeElement.textContent = count;
//                                noticeElement.style.display = 'inline-block'; // Hiển thị số lượng thông báo
//                            } else {
//                                noticeElement.style.display = 'none'; // Ẩn nếu không có thông báo
//                            }
//                        } else {
//                            console.error('Phần tử .header__notify-notice không tìm thấy trong DOM.');
//                        }
//                    }
//
//                    function updateNotificationList(data) {
//                        // Lấy phần tử ul trong DOM để thêm các thông báo vào
//                        var notificationList = document.querySelector('.header__notify-list');
//
//                        // Xóa tất cả các phần tử hiện tại trong danh sách
//                        notificationList.innerHTML = '';
//
//                        // Lặp qua mảng data để tạo và thêm các phần tử li mới vào danh sách
//                        for (var i = 0; i < Math.min(data.length, 4); i++) {
//                            var notification = data[i];
//
//                            // Tạo phần tử li
//                            var listItem = document.createElement('li');
//                            listItem.innerHTML = `
//            <a href="${notification.url}" class="header__notify-link" title="click để xem chi tiết thông báo">
//                <div>
//                    <img src="${notification.no_img}" alt="" class="header__notify-img">
//                </div>
//                <div class="header__notify-info">
//                    <span class="header__notify-name">${notification.no_title}</span>
//                    <span class="header__notify-describe">${notification.no_message}</span>
//                    <span class="header__notify-timestamp">${notification.no_timestamp}</span>
//                </div>
//            </a>
//        `;
//
//                            // Thêm li vào danh sách
//                            notificationList.appendChild(listItem);
//                        }
//                    }
//
//
//// Gọi hàm updateNotificationsList sau một khoảng thời gian nhất định (ví dụ: 1 phút)
//                    setInterval(updateNotificationsList, 60000);

//                    setInterval(function () {
//                        // Lấy giá trị từ session và truyền vào hàm updateNotificationCount
//                        updateNotificationCount(${sessionScope.sumNotify});
//                    }, 600);
</script>
