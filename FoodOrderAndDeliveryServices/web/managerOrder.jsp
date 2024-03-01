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
        <link rel="stylesheet" href="./assests/css/common.css">
        <link rel="stylesheet" href="./assests/css.manager/manager.css">
        <link rel="stylesheet" href="./assests/css/order.css">
        <link rel="stylesheet" href="./assests/css.manager/manageOrder.css">

        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.png">
        <!-- Ví dụ về cookie với SameSite=None -->
        <script>
            document.cookie = "myCookie=myValue; SameSite=None; Secure";
        </script>

    </head>

    <body>
        <div class="app__manager">
            <jsp:include page="sidebar.jsp" />

            <div class="home__container">
                <div class="grid">
                    <div class="manage__order">
                        <div class="manage__order-header">
                            <section class="order-menu flex ">
                                <a class="order-menu__name ${tag == 0? "order-menu__name--active":""}" href="managerOrder?type=0">
                                    <span class="order-menu__link">Tất cả</span>
                                </a>
                                <a class="order-menu__name ${tag == 1? "order-menu__name--active":""}" href="managerOrder?type=1">
                                    <span class="order-menu__link">Chờ xác nhận</span>
                                </a>
                                <a class="order-menu__name ${tag == 2? "order-menu__name--active":""}" href="managerOrder?type=2">
                                    <span class="order-menu__link">Vận chuyển</span>
                                </a>
                                <a class="order-menu__name ${tag == 3? "order-menu__name--active":""}" href="managerOrder?type=3">
                                    <span class="order-menu__link">Đã giao</span>
                                </a>
                                <a class="order-menu__name ${tag == 4? "order-menu__name--active":""}" href="managerOrder?type=4">
                                    <span class="order-menu__link">Đã thanh toán </span>
                                </a>
                                <a class="order-menu__name ${tag == 5? "order-menu__name--active":""}" href="managerOrder?type=5">
                                    <span class="order-menu__link">Đơn hủy</span>
                                </a>
                                <a class="order-menu__name ${tag == 6? "order-menu__name--active":""}" href="managerOrder?type=6">
                                    <span class="order-menu__link">Trả hàng/Hoàn Tiền</span>
                                </a>
                                <a class="order-menu__name ${tag == 7? "order-menu__name--active":""}" href="managerOrder?type=7">
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
                                <form action="managerOrder" method="get" class="manage__order-search flex">
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
                            <form action="managerOrder" method="post" class="manage__order-title flex">
                                <div class="flex" style="width: 800px; justify-content: space-between;">
                                    <div class="manage__order-title-label">${requestScope.size} Đơn hàng</div>
                                    <c:if test="${ms != null}">
                                        <div class="home__header-mesage-del flex" style="min-width: unset">
                                            ${requestScope.ms}
                                        </div> 
                                    </c:if>

                                </div>
                                <button type="submit" name="action" value="accept-all" class="btn__save manage__order-title-btn"><i
                                        class="fa-solid fa-truck-fast manage__order-icon"></i>Giao hàng loạt </button>
                            </form>
                            <div class="manage__order-list">
                                <div class="flex manage__order-list-header">
                                    <div class="order-item manage__order-item-id">Mã đơn hàng</div>
                                    <div class="order-item manage__order-item-username">Khách hàng</div>
                                    <div class="order-item manage__order-item-date">Ngày đặt hàng</div>
                                    <div class="order-item manage__order-item-price">Tổng giá tiền</div>
                                    <div class="order-item manage__order-item-status">Trạng thái</div>
                                    <div class="order-item manage__order-item-purchase">Thanh toán</div>
                                    <div class="order-item manage__order-item-action" style="color: var(--color-gray);">Thao tác</div>
                                </div>
                                <div class="manage__order-list-container">
                                    <c:forEach items="${listO}" var="o" > 
                                        <form action="managerOrder" method="post" class="manage__order-list-item">
                                            <a href="managerOrder?type=${tag}&orderid=${o.order_id}#view_orderDetail" title="Click để xem chi tiết hóa đơn"
                                               class="order-item manage__order-item-id">#${o.order_id}</a>
                                            <div class="order-item manage__order-item-username">${o.username} </div>
                                            <div class="order-item manage__order-item-date"> ${o.order_date}</div>
                                            <div class="order-item manage__order-item-price format-money " style="color: var(--primary-color);"> ${o.total_price}</div>

                                            <c:if test="${o.is_accepted == 1 && o.is_delivered == 3}" >
                                                <div class="order-item manage__order-item-status" style="color: rgb(28, 207, 18);"> Hoàn thành</div>
                                            </c:if>
                                            <c:if test="${o.is_accepted == -2 || o.is_accepted == -1}" >
                                                <div class="order-item manage__order-item-status" style="color: red;"> Đã hủy</div>
                                            </c:if>
                                            <c:if test="${o.is_accepted == 0}" >
                                                <div class="order-item manage__order-item-status" style="color: rgb(0, 98, 255);"> Chờ xác nhận</div>
                                            </c:if>
                                            <c:if test="${o.is_accepted == 1 && o.is_delivered == 0  }" >
                                                <div class="order-item manage__order-item-status" style="color: rgb(240, 0, 236);"> Chờ giao</div>
                                            </c:if>
                                            <c:if test="${o.is_accepted == 1 && o.is_delivered == 2}" >
                                                <div class="order-item manage__order-item-status" style="color: rgb(240, 0, 236);"> Đang giao</div>
                                            </c:if>
                                            <c:if test="${o.is_purchased == 0}" >
                                                <div class="order-item manage__order-item-purchase" style="color: rgb(241, 176, 64);">Chưa thanh toán</div>
                                            </c:if>
                                            <c:if test="${o.is_purchased == 5}" >
                                                <div class="order-item manage__order-item-purchase" style="color: rgb(28, 207, 18);" >Đã thanh toán</div>
                                            </c:if>

                                            <input type="hidden" name="accid" value="${o.acc_id}" />
                                            <input type="hidden" name="oid" value="${o.order_id}" />

                                            <div class="order-item manage__order-item-action">
                                                <c:if test="${o.is_accepted != 1}" >
                                                    <input type="submit"  name="action" value="Nhận Đơn" class="manage__order-btn ${o.is_accepted == -2 || o.is_accepted == 1 ||  o.is_accepted == -1 || o.is_delivered == 2 || o.is_delivered ==3? "manage__order-btn-cancel":""}"/>
                                                </c:if>
                                                <c:if test="${o.is_accepted == 1}" >
                                                    <input type="submit"  name="action" value="Giao Hàng" class="manage__order-btn ${o.is_accepted == -2 ||  o.is_accepted == -1 || o.is_delivered == 2 || o.is_delivered == 3 ? "manage__order-btn-cancel":""}"/>
                                                </c:if>
                                                <a href="#reason-cancel-${o.order_id}" class="manage__order-btn ${o.is_accepted == -2 ||o.is_accepted == -1 || o.is_delivered == 2 || o.is_delivered == 3? "manage__order-btn-cancel":""}">Hủy đơn</a>
                                            </div>
                                            <!-- The modal reason cancel order -->
                                            <div class="dialog overlay" id="reason-cancel-${o.order_id}">
                                                <div class="dialog-body dialog__order-reason-container">
                                                    <span class="dialog-body-reason__title">Lý Do Hủy Đơn: #${o.order_id}</span>
                                                    <div>
                                                        <textarea  name="reason-cancel" maxlength="200" class="dialog__order-reason__input" placeholder="Điền tại đây.."></textarea>
                                                    </div>
                                                    <div class="flex" style="justify-content: flex-end;">
                                                        <input type="submit" name="action" value="Gửi" class="btn__primary dialog__order-reason__btn"   />
                                                        <a href="#" class="btn__white dialog-detail__btn">Quay lại</a>
                                                    </div>
                                                </div>
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

        <!-- The modal view detail order -->
        <div class="dialog overlay" id="view_orderDetail">
            <a href="#" class="overlay-close"></a>
            <div class="dialog-body order-detail-view">
                <span class="dialog-body__title">Chi tiết đơn hàng:
                    <span class="dialog-body__title-code">#${oid}</span>
                </span>
                <div class="dialog-order-container">
                    <section style="padding: 6px 0 18px 0">
                        <c:forEach items="${listView}" var="v" >
                            <div class="dialog-view-product flex">
                                <div class="dialog-detail-product flex">
                                    <div class="dialog-detail-product__img"
                                         style="background-image: url(${v.image})">
                                    </div>
                                    <div class="dialog-detail-product__describe ">
                                        <span class="dialog-detail-product__name text__overflow-1"><span
                                                class="suga-product-status">${v.status}</span>${v.title}</span>
                                        <span class="dialog-detail-product__category">Phân loại hàng: ${v.cname}</span>
                                        <div class="dialog-order-title">
                                            <span class="dialog-detail-product__quantity">x${v.quantity}</span>
                                            <span class="dialog-order__code">Mã chi tiết: #${v.detail_id}</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="dialog-detail-price flex">
                                    <div class="format-money" style="color: var(--color-gray);">${v.old_price}</div>
                                    <div class="format-money" style="color: var(--primary-color);">${v.current_price}</div>
                                    <div class="dialog-detail-rating__box">
                                        <span>Đánh giá Shop</span>
                                        <div class="dialog-detail-rating">
                                            <c:forEach begin="0" end="4" >
                                                <i class="fas fa-star"></i>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </section>
                    <c:forEach var="v" items="${listView}" varStatus="loopStatus">
                        <c:if test="${loopStatus.index == 0}">
                            <div class="dialog-view-detail-content">
                                <div class="dialog-view-address">
                                    <div class="dialog-view-address__title">Địa chỉ nhận hàng</div>
                                    <div class="dialog-view-address__name">${v.nickname}</div>
                                    <span class="dialog-view-address__detail">${v.phone}</span>
                                    <span class="dialog-view-address__detail">${v.email}</span>
                                    <span class="dialog-view-address__detail">${v.address}</span>
                                    <span class="dialog-view-address__detail">
                                        <span style="color: var(--primary-color);">Lưu ý:</span>
                                        ${v.note}
                                    </span>
                                </div>
                                <div class="dialog-view-order">
                                    <div class="flex dialog-detail-order">
                                        <span class="dialog-detail__title ">Tổng tiền hàng</span>
                                        <span class="dialog-detail__price format-money">${totalprice}</span>
                                    </div>
                                    <div class="flex dialog-detail-order">
                                        <span class="dialog-detail__title">Phí vận chuyển</span>
                                        <c:if test="${v.is_shipped !=0 }" >
                                            <span class="dialog-detail__price format-money">${v.is_shipped}</span>
                                        </c:if>
                                        <c:if test="${v.is_shipped == 0 || v.is_shipped == null}" >
                                            <span class="dialog-detail__price "><span style="color: var(--color-gray); font-size: 1.2rem">(Đến nhận hàng)</span>&nbsp; ₫0 </span>
                                        </c:if>
                                    </div>
                                    <div class="flex dialog-detail-order">
                                        <span class="dialog-detail__title">Phiếu giảm giá</span>
                                        <span class="dialog-detail__price">₫0</span>
                                    </div>
                                    <div class="flex dialog-detail-order">
                                        <span class="dialog-detail__title">Thành tiền</span>
                                        <c:if test="${v.is_shipped != 0}" >
                                            <span class="dialog-detail__total format-money">${v.total_price}</span>
                                        </c:if>
                                        <c:if test="${v.is_shipped == 0}" >
                                            <span class="dialog-detail__total format-money">${totalprice}</span>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
                <div class="flex" style="justify-content: flex-end;">
                    <a href="#" class="btn__white dialog-detail__btn">Quay lại</a>
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
                    if (element.textContent.trim() !== "") {
                        element.textContent = "₫" + formatAmountSold(amountSold);
                    } else {
                        element.textContent = "0₫";
                    }
                });
            });

        </script>
    </body>

</html>