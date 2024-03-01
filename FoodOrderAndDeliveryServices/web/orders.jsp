<%-- 
    Document   : orders
    Created on : Nov 26, 2023, 4:28:26 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <title>HolaFood | Đơn mua</title>
    <link href="assests/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="assests/css/order.css" rel="stylesheet" type="text/css"/>
    <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.png">

</head>

<body>
    <div class="app">
        <jsp:include page="menu_home.jsp"></jsp:include> 
            <!-- container profile -->
            <div class="container__profile ">
                <div class="grid">
                    <div class="grid__row row">
                    <jsp:include page="profile_menu.jsp"></jsp:include> 

                        <div class="grid__column-980 " style="min-height: 520px; margin-bottom: 30px">
                            <section class="order-menu flex profile__right " style="padding: 0;">
                                <a class="order-menu__name  ${tag == 0? "order-menu__name--active":""} " href="purchase?type=0">
                                <span class="order-menu__link">Tất cả</span>
                            </a>
                            <a class="order-menu__name  ${tag == 1? "order-menu__name--active":""}" href="purchase?type=1">
                                <span class="order-menu__link">Chờ xác nhận</span>
                            </a>
                            <a class="order-menu__name  ${tag == 2? "order-menu__name--active":""}" href="purchase?type=2">
                                <span class="order-menu__link">Chờ giao hàng</span>
                            </a>
                            <a class="order-menu__name  ${tag == 3? "order-menu__name--active":""}" href="purchase?type=3">
                                <span class="order-menu__link">Hoàn thành</span>
                            </a>
                            <a class="order-menu__name  ${tag == 4? "order-menu__name--active":""}" href="purchase?type=4">
                                <span class="order-menu__link">Chưa đánh giá</span>
                            </a>
                            <a class="order-menu__name  ${tag == 5? "order-menu__name--active":""}" href="purchase?type=5">
                                <span class="order-menu__link">Đã hủy</span>
                            </a>
                            <a class="order-menu__name  ${tag == 6? "order-menu__name--active":""}" href="purchase?type=6" >
                                <span class="order-menu__link" style="padding: 15px 0;">Trả hàng/Hoàn Tiền</span>
                            </a>
                        </section>

                        <c:if test="${tag == 0 || tag == null}">
                            <form action="purchase" method="get" class="order-menu__filter">
                                <button type="submit" name="action" value="filter" style="background: transparent; border: none" >
                                    <i class="fa-solid fa-magnifying-glass"></i>
                                </button>
                                <input type="text" name="keyword" value="${keyword}" class="order-menu__filter-input" placeholder="Bạn có thể tìm kiếm theo tên Shop, ID đơn hàng hoặc Tên Sản phẩm">
                            </form>
                        </c:if>

                        <!--model announced code--> 
                        <c:if test="${requestScope.ms_feedback != null}">
                            <div class="form__login-message" style="min-width: 260px">
                                ${requestScope.ms_feedback}
                            </div>
                        </c:if>
                        <!--model announced code--> 
                        <div class="dialog overlay" id="code-notification">
                            <div class="dialog-body dialog__order-code__notification">
                                <span class="dialog-body-reason__title"> <i class="fa-regular fa-bell" style="font-size: 1.9rem; margin-right: 4px"></i> Thông Báo Đơn Hàng </span>
                                <div class="dialog__order-content__notification">
                                    ${requestScope.ms}
                                </div>
                                <div class="flex" style="justify-content: flex-end;">
                                    <a href="#" class="btn__white dialog-detail__btn">Quay lại</a>
                                </div>
                            </div>
                        </div>
                        <c:if test="${listOrder.size() == 0}" >
                            <div class="order-no__box">
                                <img src="https://deo.shopeemobile.com/shopee/shopee-pcmall-live-sg/orderlist/5fafbb923393b712b964.png"
                                     class="holafood-no-order"></img>
                                <h2 class="order-no-title">Chưa có đơn hàng</h2>
                            </div>
                        </c:if>        
                        <c:forEach  items="${listOrder}" var="o" >   
                            <form action="order" method="post" style="margin-bottom: 10px ">
                                <div class="order profile__right ">
                                    <!-- Loop through order details for this order -->
                                    <%--<c:forEach var="od" items="${o.orderDetails}">--%>
                                    <div class="order-product flex">
                                        <div class="flex">
                                            <img class="order-product-img" src="${o.image}" alt="">
                                            <div class="order-product-describe" style="min-width: 680px;">
                                                <span class="order-product-name text__overflow" >
                                                    <span class="order-product-label">${o.status}</span>
                                                    ${o.title}
                                                </span>
                                                <span class="order-product-category">Phân loại hàng: ${o.cname}. ${o.store_name}</span>
                                                <span> x${o.quantity}</span>
                                            </div>
                                        </div>
                                        <div class="order-product-price flex ">
                                            <span class="order-product-old__price">${o.old_price}</span>
                                            <span class="order-product-current__price">${o.current_price}</span>
                                        </div>
                                    </div>
                                    <%--</c:forEach>--%>
                                    <div class="order-total flex">
                                        <span></span>
                                        <span style="font-size: 1.44rem;"><i style="color: var(--primary-color); font-size: 1.6rem; margin: 0 4px;" class="fa-solid fa-money-check-dollar"></i> Thành tiền: &nbsp; </span>
                                        <span class="order-total__price"> ${o.total_price}</span>
                                    </div>
                                    <div class="order-button flex">
                                        <div class="order-notify flex">
                                            <c:if test="${o.is_accepted == 1 && o.is_delivered == 3 && o.is_feedback == 0}" >
                                                <span class="order-notify-rating">Đánh giá sản phẩm trước&nbsp;</span>
                                                <script>
                                                    // Đoạn mã JavaScript tăng thêm 5 ngày
                                                    var orderDate = new Date("${o.order_date}");
                                                    orderDate.setDate(orderDate.getDate() + 5);
                                                    var formattedDate = ("0" + (orderDate.getDate())).slice(-2) + "-" + ("0" + (orderDate.getMonth() + 1)).slice(-2) + "-" + orderDate.getFullYear();
                                                    document.write("<u>" + formattedDate + "</u>");
                                                </script>
                                                <span class="order-notify-coin">&nbsp;&nbsp;Đánh giá ngay và nhận 200 Xu </span>
                                            </c:if>
                                            <c:if test="${o.is_accepted == 0 }" >
                                                <span class="order-notify-rating">Đang đợi người bán xác nhận đơn&nbsp;</span>
                                            </c:if>
                                            <c:if test="${o.is_accepted == -1}" >
                                                <span class="order-notify-rating">Đơn hàng đã được hủy bởi bạn&nbsp;</span>
                                            </c:if>
                                            <c:if test="${o.is_accepted == -2}" >
                                                <span class="order-notify-rating">Đơn hàng đã được hủy bởi người bán&nbsp;</span>
                                            </c:if>
                                            <c:if test="${o.is_accepted == 1 && o.is_delivered == 0 && o.is_feedback == 0}" >
                                                <span class="order-notify-rating">Đang chờ người bán giao hàng&nbsp;</span>
                                            </c:if>
                                            <c:if test="${o.is_accepted == 1 && o.is_delivered == 2 && o.is_feedback == 0}" >
                                                <span class="order-notify-rating">Đơn hàng đang trên đường giao đến bạn&nbsp;</span>
                                            </c:if>
                                            <c:if test="${o.is_accepted == 1 && o.is_delivered == 3 && o.is_feedback == 4 && o.is_purchased == 5}" >
                                                <span class="order-notify-rating">Đơn hàng đã giao hoàn thành&nbsp;</span>
                                            </c:if>
                                        </div>
                                        <div class="flex">
                                            <c:if test="${o.is_accepted == 1 && o.is_feedback == 0 && o.is_delivered == 3 && o.is_purchased == 5}" >
                                                <a href="purchase?type=${tag}&detailid=${o.detail_id}&pid=${o.pid}#add-feedback"  class="btn__save order-btn">Đánh Giá</a>
                                                <a href="#" class="btn__white order-btn">Liên Hệ Người Bán</a>
                                                <a href="detail?pid=${o.pid}" class="btn__white order-btn">Mua Lại</a>
                                            </c:if>
                                            <c:if test="${o.is_accepted == 0 }" >
                                                <a href="purchase?type=${tag}&order_id=${o.order_id}&action=cancel#code-notification" class="btn__save order-btn">Huỷ đơn</a>
                                                <a href="#" class="btn__white order-btn">Liên Hệ Người Bán</a>
                                                <a href="purchase?type=${tag}&order_id=${o.order_id}&action=reupdate#order-address" class="btn__white order-btn">Địa Chỉ Nhận Hàng</a>
                                            </c:if>
                                            <c:if test="${o.is_accepted == 1 && (o.is_delivered == 0 || o.is_delivered == 2) && o.is_feedback == 0 }" >
                                                <c:if test="${o.is_delivered == 0}">
                                                    <a href="purchase?type=${tag}&order_id=${o.order_id}&action=undelivered#code-notification" class="btn__save order-btn order-btn-no_drop">Đã nhận được hàng</a>
                                                </c:if>
                                                <c:if test="${o.is_delivered == 2}">
                                                    <a href="purchase?type=${tag}&order_id=${o.order_id}&action=received#code-notification" class="btn__save order-btn">Đã nhận được hàng</a>
                                                </c:if>
                                                <a href="#code-notification" class="btn__white order-btn">Liên Hệ Người Bán</a>
                                                <c:if test="${o.is_delivered == 2}">
                                                    <a href="#" class="btn__white order-btn">Trả hàng\Hoàn tiền</a>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${o.is_accepted == 1 && o.is_delivered == 3 && o.is_feedback == 4}" >
                                                <a href="detail?pid=${o.pid}" class="btn__save order-btn">Mua Lại</a>
                                                <a href="#" class="btn__white order-btn">Liên Hệ Người Bán</a>
                                                <a href="purchase?type=${tag}&order_id=${o.order_id}&action=review#feedback" class="btn__white order-btn">Xem Đánh Giá Shop</a>
                                            </c:if>
                                            <c:if test="${o.is_accepted == -1 || o.is_accepted ==  -2}" >
                                                <a href="detail?pid=${o.pid}" class="btn__save order-btn">Mua Lại</a>
                                                <a href="#" class="btn__white order-btn" style="min-width: 166px">Xem Chi Tiết Hủy Đơn</a>
                                                <a href="#" class="btn__white order-btn">Liên Hệ Người Bán</a>
                                            </c:if>
                                        </div>
                                    </div>  
                                </div>
                            </form>
                        </c:forEach>  
                    </div>
                </div>
            </div>

            <!-- The modal update address -->
            <div class="dialog overlay" id="order-address">
                <div class="dialog-body dialog__order-code__notification">
                    <span class="dialog-body-reason__title"><i class="fa-regular fa-bell" style="font-size: 1.9rem; margin-right: 4px"></i> Cập Nhật Địa Chỉ </span>
                    <form action="purchase" method="post">
                        <input type="hidden" name="oid_update" value="${order.order_id}" />
                        <div class="checkout-input-group">
                            <div class="checkout-input-field">
                                <input required class="input-box" type="text" name="logName" value="${order.username}" maxlength="31" title="Họ tên nhận hàng">
                                <label for="logName">Họ và tên: </label>
                                <!--                            </div>
                                                            <div class="checkout-input-field">
                                                                <input required class="input-box" type="email" placeholder="...@gmail.com" name="logEmail" value="${order.email}" title="Email nhận hàng">
                                                                <label for="logEmail">Email:</label>
                                                            </div>-->
                                <div class="checkout-input-field">
                                    <input required class="input-box" type="number" name="logPhone" value="${order.phone}" title="Số điện thoại nhận hàng">
                                    <label for="logPhone">Số điện thoại: </label>
                                </div>
                                <div class="checkout-input-field">
                                    <input required class="input-box" type="text" name="logAddress" maxlength="80" value="${order.address}" title="Địa chỉ nhận hàng">
                                    <label for="logAddress">Địa chỉ: </label>
                                </div>
                                <div class="checkout-input-field">
                                    <textarea required class="input-box textarea-note" type="text" name="logNote" maxlength="80" title="Lưu ý giao hàng cho người bán">${order.note}</textarea>
                                    <label for="logNote">Ghi chú: </label>
                                </div>
                            </div>
                            <div class="flex" style="justify-content: flex-end;">
                                <a href="#" class="btn__white dialog-detail__btn">Quay Lại</a> &nbsp;
                                <button name="action" type="submit" value="address" class="btn__primary ">Cập Nhật</button>
                            </div>
                    </form>
                </div>
            </div>
        </div>

        <!--model view feedbacked-->
        <div class="dialog overlay" id="feedback">
            <div class="dialog-body dialog__order-code__notification" style="width: 700px">
                <span class="dialog-body-reason__title" style="margin-left: 10px; color: black"> Đánh Giá Shop </span>     
                <div class="dialog-feedback-product " style="margin: 16px 0 10px 12px">
                    <img src="${p.image}"
                         class="dialog-feedback-product__img" alt="">
                    <div class="dialog-feedback-product__describe">
                        <span class="dialog-feedback-product__name text__overflow">${p.title}</span>
                        <span class="dialog-feedback-product__category">Phân loại hàng: ${p.category.name}, ${p.seller_name}</span>
                    </div>
                </div>
                <div class="feedback-main" style="border-bottom: none">
                    <div class="suga-avatar feedback-avt">
                        <img class="suga-avatar__img"
                             src="https://down-vn.img.susercontent.com/file/e87892e1b559685f3a891b90b8fd4ed4_tn"
                             alt="">
                    </div>
                    <div class="feedback-content">
                        <div class="feedback-content__author">
                            <span class="feedback-content__author-name">chaeyoung</span>
                            <div class="feedback-content__author-rating" style="color: var(--star-color)">
                                <c:forEach  begin="0" end="4">
                                    <i class="fas fa-star"></i>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="feedback-content__author-comment">
                            <span class="feedback-content-label" style="color: rgba(0, 0, 0, 0.4);"> Đúng với mô tả:
                                &nbsp; <span style="color: var(--text-color);"> Đúng</span> </span>
                            <span class="feedback-content-label" style="padding: 4px 0; color: rgba(0, 0, 0, 0.4)">Tính
                                năng nổi bật: &nbsp; <span style="color: var(--text-color);"> Nhiều tính
                                    năng</span></span>
                            <span class="feedback-content-label" style="color: rgba(0, 0, 0, 0.4); "> Chất lượng sản
                                phẩm: &nbsp;<span style="color: var(--text-color);">Tốt</span> </span>
                        </div>
                        <div class="feedback-content__author-main"> Cảm ơn Suga Shop rất nhiều vì đã trao tặng món quà
                            này</div>
                        <div class="flex">
                            <div class="feedback-content-pic">
                                <div class="feedback-content__image"
                                     style="background-image: url(https://down-bs-vn.img.susercontent.com/vn-11134103-7r98o-ln787jvdjpi0a6_tn.webp);">
                                </div>
                            </div>
                            <div class="feedback-content-pic">
                                <div class="feedback-content__image"
                                     style="background-image: url(https://down-bs-vn.img.susercontent.com/cn-11134210-7r98o-lptel3sloh112c.webp);">
                                </div>
                            </div>
                        </div>
                        <div class="feedback-content__author-time">
                            <span style="font-size: 1.1rem">2023-11-25 20:06</span>
                        </div>
                    </div>
                </div>
                <div class="flex" style="justify-content: flex-end;">
                    <a href="#" class="btn__white dialog-detail__btn">Quay Lại</a> &nbsp;
                </div>
            </div>
        </div>

        <!--model feedback--> 
        <div class="dialog overlay" id="add-feedback">
            <form action="purchase" method="post" class="dialog-body order-feedback">
                <input type="hidden" name="proid" value="${p.pid}" />
                <input type="hidden" name="order_id" value="${order_id}" />
                <input type="hidden" name="detailid" value="${detailid}" />
                <input type="hidden" name="cname" value="${p.category.name}" />

                <div class="flex" style="justify-content: space-between;">
                    <div class="dialog-feedback-title">Đánh Giá Sản Phẩm</div>
                </div>
                <div class="dialog-feedback">
                    <div class="dialog-feedback-product ">
                        <img src="${p.image}"
                             class="dialog-feedback-product__img" alt="">
                        <div class="dialog-feedback-product__describe">
                            <span class="dialog-feedback-product__name text__overflow">${p.title}</span>
                            <span class="dialog-feedback-product__category">Phân loại hàng: ${p.category.name}, ${p.seller_name}</span>
                        </div>
                    </div>
                    <div class="dialog-feedback-rating flex">
                        <span class="dialog-feedback-rating__title">Chất lượng sản phẩm </span>
                        <div class="dialog-feedback-rating__star">
                            <span class="star" data-rating="1">&#9733;</span>
                            <span class="star" data-rating="2">&#9733;</span>
                            <span class="star" data-rating="3">&#9733;</span>
                            <span class="star" data-rating="4">&#9733;</span>
                            <span class="star" data-rating="5">&#9733;</span>
                        </div>
                        <input type="hidden" id="selectedRating" name="selectedRating" value="">
                        <div class="dialog-feedback-rating__label" id="ratingDescription"></div>
                    </div>
                    <div class="dialog-feedback-content">
                        <div class="dialog-feedback-content__describe">
                            <span class="dialog-feedback-content__title">Đúng với mô tả : </span>
                            <textarea name="describe" class="dialog-feedback-content__textarea " rows="1" placeholder="để lại đánh giá.">${requestScope.describe}</textarea>
                        </div>
                        <div class="dialog-feedback-content__describe">
                            <span class="dialog-feedback-content__title">Giao hàng đúng giờ : </span>
                            <textarea name="delivery" class="dialog-feedback-content__textarea " rows="1"></textarea>
                        </div>
                        <div class="dialog-feedback-content__describe">
                            <span class="dialog-feedback-content__title">Chất lượng sản phẩm :</span>
                            <textarea name="standard" class="dialog-feedback-content__textarea" rows="1"></textarea>
                        </div>
                        <div class="dialog-feedback-border"></div>
                        <textarea name="content"  maxlength="220" class="dialog-feedback-content__textarea" rows="4" 
                                  placeholder="Hãy chia sẻ những điều bạn thích về sản phẩm này với những người mua khác nhé."></textarea>
                    </div>
                    <div class="flex dialog-feedback-file">
                        <input type="file" name="image" class="dialog-feedback-file__choose" placeholder="Thêm hình ảnh" />
                        <span style="color: var(--color-infor) ;">Thêm ít nhất 1 hình ảnh và 50 ký tự để nhận </span>
                        <span style="color: var(--primary-color);">&nbsp;200 xu</span>
                    </div>
                    <div class="dialog-feedback-image">
                        <div style="background-image: url(https://down-tx-vn.img.susercontent.com/vn-11134103-7r98o-lom422fiwycz74.webp);"></div>
                        <div style="background-image: url(https://down-tx-vn.img.susercontent.com/vn-11134103-7r98o-lom422fiwycz74.webp);"></div>
                    </div>
                    <div class="flex dialog-feedback-checkbox">
                        <input type="checkbox" checked name="incognito" value="1" class="dialog-feedback-checkbox__input" />
                        <span class="dialog-feedback-checkbox__title" > Hiện thị tên đăng nhập trên đánh giá này</span>
                    </div>
                </div>
                <div class="flex dialog-feedback-submit">
                    <a href="" class="btn__white dialog-feedback-submit__btn">Trở lại</a>
                    <button type="submit" name="action" value="feedback" class="btn__save dialog-feedback-submit__btn">Hoàn Thành</button>
                </div>
            </form>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </div>
</div>
<script src="assests/js/feedback.js"></script>
<script>
                                                    function highlightStars(rating) {
                                                        for (let i = 0; i < 5; i++) {
                                                            stars[i].classList.add("active");
                                                        }
                                                    }

</script>
</body>

</html>
