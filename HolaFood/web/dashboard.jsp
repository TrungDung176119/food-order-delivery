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
                       
                        <div class="manage__order-container">
                            <div class="manage__order-option">
                                <div>
                                    <c:set var="totalRevenue" value="0" />
                                    <c:forEach var="pd" items="${listPD}">
                                        <c:set var="totalRevenue" value="${totalRevenue + pd.revenue}" />
                                    </c:forEach>
                                    <h2 style="margin-bottom: -5%" >Tổng Doanh thu : <span class="format-money">${totalRevenue}</span></h2>
                                </div>
                                <div class="manage__order-export flex">
                                    <form action="filterdbpro" method="get" class="manage__order-search flex">
                                        <span class="manage__order-export-title">Ngày :</span>
                                        <input title="Ngày bắt đầu cần tìm " type="date" pattern="\d{2}-\d{2}-\d{4}"
                                               class="manage__order-export-date" name="startDate" value="${startDate}">&nbsp;
                                        <i class="fa-solid fa-minus" style="color: #989898; font-size: 1.2rem"></i>&nbsp;
                                        <input type="date" class="manage__order-export-date" title="Ngày cuối cần tìm" name="endDate"
                                               value="${endDate}">
                                        <button class="btn__white manage__order-export-btn" type="submit" >Tìm kiếm</button>
                                    </form>
                                </div>
                            </div>
                            <form action="managerOrder" method="post" class="manage__order-title flex">
                                <div class="flex" style="width: 800px; justify-content: space-between;">
                                    <div class="manage__order-title-label">${requestScope.size} Sản phẩm</div>
                                    <c:if test="${ms != null}">
                                        <div class="home__header-mesage-del flex" style="min-width: unset">
                                            ${requestScope.ms}
                                        </div> 
                                    </c:if>

                                </div>
                            </form>
                            <div class="home__controll-main" style=" margin-bottom: 40px;">

                                <table style="max-width: 100%; width: 100%;">
                                    <tr class="table__product-title">
                                        <th>
                                            <div class="table__product-title-id"> Mã sản phẩm</div>
                                        </th>
                                        <th>
                                            <div class="table__product-title-name">Tên sản phẩm</div>
                                        </th>
                                        <th>
                                            <div class="table__product-title-category">Phân loại hàng</div>
                                        </th>
                                        <th>
                                            <div class="table__product-title-price">Doanh thu</div>
                                        </th>
                                        <th>
                                            <div class="table__product-title-sold">Đã bán </div>
                                        </th>
                                        <th>
                                            <div class="table__product-title-sold">Đánh giá tốt </div>
                                        </th>
                                        <th>
                                            <div class="table__product-title-category">Đánh giá xấu</div>
                                        </th>
                                    </tr>

                                    <c:forEach items="${listPro}" var="o">
                                        <tr class="table__product">
                                            <td>
                                                <div class="table__product-id">${o.pid}</div>
                                            </td>
                                            <td>
                                                <div class="table__product-name">
                                                    <img src="${o.image}" alt="">
                                                    <p class="text__overflow3"> ${o.title} </p>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="table__product-category">${o.category.name}</div>
                                            </td>
                                            <c:forEach items="${listPD}" var="pd">
                                                <c:if test="${pd.pid == o.pid}">
                                                    <td>
                                                        <div class="table__product-price format-money" style="color: var(--primary-color)">${pd.revenue}</div>
                                                    </td>
                                                    <td>
                                                        <div class="table__product-sold">${pd.quantity} </div>
                                                    </td>
                                                    <td>
                                                        <div class="table__product-sold">${pd.goodFB}</div>
                                                    </td>
                                                    <td>
                                                        <div class="table__product-category">${pd.badFB}</div>
                                                    </td>
                                                </c:if>
                                            </c:forEach>
                                        </tr>
                                    </c:forEach>
                                </table>
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