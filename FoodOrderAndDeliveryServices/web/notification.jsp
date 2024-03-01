<%-- 
    Document   : notification
    Created on : Feb 7, 2024, 4:34:00 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Holafood | Thông báo</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css.profile/notification.css">
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

    <body>
        <div class="app">
            <jsp:include page="menu_home.jsp" />

            <!-- container notifications -->
            <div class="container__profile ">
                <div class="grid">
                    <div class="grid__row row">
                        <jsp:include page="profile_menu.jsp" />

                        <div class="grid__column-980 notify-right ">
                            <form action="notifications" method="post" class="notify-header-title">
                                <button type="submit"class="notify-header-btn">Đánh dấu Đã đọc tất cả</button>
                            </form>
                            <div class="notify-list">
                                <c:forEach begin="0" end="14" items="${sessionScope.listNotify}" var="n" >
                                    <a href="${n.url}" class="notify-item">
                                        <img src="${n.no_img}"
                                             class="notify-item-image" alt="">
                                        <div class="notify-item-infor">
                                            <div class="notify-item-infor__title">${n.no_title}</div>
                                            <div class="notify-item-infor__describe">${n.no_message}</div>
                                            <div class="notify-item-infor__time">${n.no_timestamp}</div>
                                        </div>
                                        <div class="notify-item-btn">
                                            <button class="btn__white notify-item-btn__button">Xem Chi Tiết</button>
                                        </div>
                                    </a>
                                </c:forEach>
                                <a href="orders.html" class="notify-item">
                                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSoCdynac-lsDYILxc2mr5HIkPeoeFdJQBFY5sn7jsWSg&s"
                                         class="notify-item-image" alt="">
                                    <div class="notify-item-infor">
                                        <div class="notify-item-infor__title">Giao kiện hàng thành công</div>
                                        <div class="notify-item-infor__describe"> Đơn hàng đã giao thành công đến bạn Đơn
                                            hàng đã giao thành công đến bạn</div>
                                        <div class="notify-item-infor__time">13:48 07-02-2024</div>
                                    </div>
                                    <div class="notify-item-btn">
                                        <button class="btn__white notify-item-btn__button">Xem Chi Tiết</button>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <jsp:include page="footer.jsp" />
        </div>

        <script src="assests/js/Jquery.js"></script>
        <script src="assests/js/bootstrap.min.js"></script>
    </body>

</html>