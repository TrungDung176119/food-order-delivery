<%-- 
    Document   : managerOrder
    Created on : Jan 7, 2024, 5:59:32 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HolaFood | Quản lý đơn hàng</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- boostrap -->
        <link href="./assests/boostrap/bootstrap.css" rel="stylesheet">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css.manager/manager.css">
        <link rel="stylesheet" href="./assests/css.manager/sidebar.css">
        <link rel="stylesheet" href="./assests/css/order.css">
        <link rel="stylesheet" href="./assests/css.manager/manageOrder.css">

        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.ico">

    </head>

    <body>
        <div class="app__manager">
            <jsp:include page="sidebar.jsp" />

            <div class="home__container">
                <div class="grid">
                    <div class="manage__order">
                        <div class="manage__order-header">
                            <section class="order-menu flex ">
                                <a class="order-menu__name ${tag == 0? "order-menu__name--active":""}"" href="/suga/manager/order?type=0">
                                    <span class="order-menu__link">Tất cả</span>
                                </a>
                                <a class="order-menu__name ${tag == 1? "order-menu__name--active":""}" href="/suga/manager/order?type=1">
                                    <span class="order-menu__link">Chờ xác nhận</span>
                                </a>
                                <a class="order-menu__name ${tag == 2? "order-menu__name--active":""}" href="/suga/manager/order?type=2">
                                    <span class="order-menu__link">Đang giao</span>
                                </a>
                                <a class="order-menu__name ${tag == 3? "order-menu__name--active":""}" href="/suga/manager/order?type=3">
                                    <span class="order-menu__link">Đã giao</span>
                                </a>
                                <a class="order-menu__name ${tag == 4? "order-menu__name--active":""}" href="/suga/manager/order?type=4">
                                    <span class="order-menu__link">Đã thanh toán </span>
                                </a>
                                <a class="order-menu__name ${tag == 5? "order-menu__name--active":""}" href="/suga/manager/order?type=5">
                                    <span class="order-menu__link">Đơn hủy</span>
                                </a>
                                <a class="order-menu__name ${tag == 6? "order-menu__name--active":""}" href="/suga/manager/order?type=6">
                                    <span class="order-menu__link">Trả hàng/Hoàn Tiền</span>
                                </a>
                                <a class="order-menu__name ${tag == 7? "order-menu__name--active":""}" href="/suga/manager/order?type=7">
                                    <span class="order-menu__link">Giao không thành công</span>
                                </a>
                            </section>
                        </div>
                        <div class="manage__order-container">
                            <div class="manage__order-option">
                                <div class="manage__order-export flex">
                                    <span class="manage__order-export-title">Ngày đặt hàng</span>
                                    <input title="Ngày bắt đầu cần tìm " type="date" pattern="\d{2}-\d{2}-\d{4}"
                                           class="manage__order-export-date">&nbsp;
                                    <i class="fa-solid fa-minus" style="color: #989898; font-size: 1.2rem"></i>&nbsp;
                                    <input type="date" class="manage__order-export-date" title="Ngày cuối cần tìm">
                                    <button class="btn__white manage__order-export-btn">Xuất</button>
                                </div>
                                <form action="/suga/manager/order" method="get" class="manage__order-search flex">
                                    <input type="hidden" name="type" value="${tag}" />
                                    <select required name="select" id="" class="manage__order-search-list" title="Chọn thể loại cần tìm">
                                        <option value="-1" disabled selected>Vui lòng chọn</option>
                                        <option value="1">Tên người mua</option>
                                        <option value="2">Sản phẩm</option>
                                        <option value="3">Mã đơn hàng</option>
                                    </select>
                                    <div class="manage__order-search-group">
                                        <input required name="text" type="text" class="manage__order-search-input" title="Nhập để tìm kiếm"
                                               placeholder="Nhập tên người mua">
                                        <i class="fa-solid fa-magnifying-glass manage__order-search-icon"></i>
                                    </div>
                                    <button type="submit" name="action" value="Tìm kiếm" class="btn__save manage__order-search-btn">Tìm kiếm</button>
                                    <button type="reset" class="btn__white manage__order-search-reset">Đặt lại</button>
                                </form>
                            </div>
                            <div class="manage__order-title flex">
                                <div class="flex" style="width: 800px; justify-content: space-between;">
                                    <div class="manage__order-title-label">${requestScope.size} Đơn hàng</div>
                                    <div class="home__header-mesage-del flex" style="min-width: unset">
                                        ${requestScope.ms}
                                    </div>
                                </div>
                                <button class="btn__save manage__order-title-btn"><i
                                        class="fa-solid fa-truck-fast manage__order-icon"></i>Giao hàng loạt
                            </div>
                            <div class="manage__order-list">
                                <div class="flex manage__order-list-header">
                                    <div class="order-item manage__order-item-id">Mã đơn hàng</div>
                                    <!-- <div class="order-item manage__order-item-product" >Sản phẩm</div> -->
                                    <div class="order-item manage__order-item-username">Khách hàng</div>
                                    <div class="order-item manage__order-item-date">Ngày đặt hàng</div>
                                    <div class="order-item manage__order-item-price">Tổng giá tiền</div>
                                    <div class="order-item manage__order-item-status">Trạng thái</div>
                                    <div class="order-item manage__order-item-purchase">Thanh toán</div>
                                    <div class="order-item manage__order-item-action">Thao tác</div>
                                </div>
                                <div class="manage__order-list-container">
                                    <c:forEach items="${listO}" var="o" > 
                                        <form action="/suga/manager/order" method="post" class="manage__order-list-item">
                                            <a href="" title="Click để xem chi tiết hóa đơn"
                                               class="order-item manage__order-item-id">#${o.orderId}</a>
                                            <!-- <div class="order-item manage__order-item-product" ></div> -->
                                            <div class="order-item manage__order-item-username">${o.username} </div>
                                            <div class="order-item manage__order-item-date"> ${o.orderDate}</div>
                                            <div class="order-item manage__order-item-price format-money " style="color: var(--primary-color);"> ${o.totalPrice}</div>

                                            <c:if test="${o.accepted == 1 && o.delivered == 2}" >
                                                <div class="order-item manage__order-item-status" style="color: rgb(28, 207, 18);"> Hoàn thành</div>
                                            </c:if>
                                            <c:if test="${o.accepted == -2}" >
                                                <div class="order-item manage__order-item-status" style="color: red;"> Đã hủy</div>
                                            </c:if>
                                            <c:if test="${o.accepted == 0}" >
                                                <div class="order-item manage__order-item-status" style="color: rgb(0, 98, 255);"> Chờ xác nhận</div>
                                            </c:if>
                                            <c:if test="${o.accepted == 1 && o.delivered == 0}" >
                                                <div class="order-item manage__order-item-status" style="color: rgb(240, 0, 236);"> Đang giao</div>
                                            </c:if>
                                            <c:if test="${o.delivered != 4}" >
                                                <div class="order-item manage__order-item-purchase" style="color: rgb(241, 176, 64);">Chưa thanh toán</div>
                                            </c:if>
                                            <c:if test="${o.delivered == 4}" >
                                                <div class="order-item manage__order-item-purchase" style="color: rgb(28, 207, 18);" >Đã thanh toán</div>
                                            </c:if>

                                            <input type="hidden" name="order_id" value="${o.orderId}" />
                                            <input type="hidden" name="username" value="${o.username}" />

                                            <div class="order-item manage__order-item-action">
                                                <input name="action" value="Giao hàng" type="submit" class="manage__order-btn suga-link ${o.accepted == -2 || o.delivered == 1 || o.delivered ==2? "manage__order-btn-cancel":""}"/>
                                                <input name="action" value="Hủy đơn" type="submit" class="manage__order-btn suga-link ${o.accepted == -2 || o.delivered == 1 || o.delivered ==2? "manage__order-btn-cancel":""} "/>
                                            </div>
                                        </form>
                                    </c:forEach> 

                                </div>
                            </div>
                        </div>

                        <div class="home__footer" style="height: 50px;">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            function formatAmountSold(amountSold) {
                return amountSold.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
            }

            document.addEventListener("DOMContentLoaded", function () {
                const amountSoldElements = document.querySelectorAll(".format-money");
                amountSoldElements.forEach(function (element) {
                    const amountSold = parseInt(element.textContent.replace("Đã bán ", ""));
                    element.textContent = "₫" + formatAmountSold(amountSold);
                });
            });
        </script>
    </body>

</html>