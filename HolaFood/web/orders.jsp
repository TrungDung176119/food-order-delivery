<%-- 
    Document   : orders
    Created on : Nov 26, 2023, 4:28:26 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Suga | Đơn hàng</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <!-- css -->
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

<body>
    <div class="app">
        <jsp:include page="menu_home.jsp"></jsp:include> 

            <!-- container profile -->
            <div class="container__profile ">
                <div class="grid">
                    <div class="grid__row row">
                    <jsp:include page="profile_menu.jsp"></jsp:include> 

                        <div class="grid__column-980 ">
                            <section class="order-menu flex profile__right " style="padding: 0;">
                                <a class="order-menu__name  ${tag == 0? "order-menu__name--active":""} " href="order?type=0">
                                <span class="order-menu__link">Tất cả</span>
                            </a>
                            <a class="order-menu__name  ${tag == 1? "order-menu__name--active":""}" href="order?type=1">
                                <span class="order-menu__link">Chờ xác nhận</span>
                            </a>
                            <a class="order-menu__name  ${tag == 2? "order-menu__name--active":""}" href="order?type=2">
                                <span class="order-menu__link">Chờ giao hàng</span>
                            </a>
                            <a class="order-menu__name  ${tag == 3? "order-menu__name--active":""}" href="order?type=3">
                                <span class="order-menu__link">Hoàn thành</span>
                            </a>
                            <a class="order-menu__name  ${tag == 4? "order-menu__name--active":""}" href="order?type=4">
                                <span class="order-menu__link">Chưa đánh giá</span>
                            </a>
                            <a class="order-menu__name  ${tag == 5? "order-menu__name--active":""}" href="order?type=5">
                                <span class="order-menu__link">Đã hủy</span>
                            </a>
                            <a class="order-menu__name  ${tag == 6? "order-menu__name--active":""}" href="order?type=6" >
                                <span class="order-menu__link" style="padding: 15px 0;">Trả hàng/Hoàn Tiền</span>
                            </a>
                        </section>
                        <c:if test="${ms != null}">
                            <div class="form__login-message">
                                ${requestScope.ms}
                            </div>
                        </c:if>
                        <c:forEach  items="${listOrder}" var="o" >   
                            <div action="order" method="post" style="margin-bottom: 10px ">

                                <div class="order profile__right ">
                                    <div class="order-product flex">
                                        <div class="flex">
                                            <img class="order-product-img" src="${o.pimage}" alt="">
                                            <div class="order-product-describe" style="min-width: 680px;">
                                                <span class="order-product-name text__overflow" >
                                                    <span class="order-product-label">${o.pstatus}</span>
                                                    ${o.ptitle}
                                                </span>
                                                <span class="order-product-category">Phân loại hàng: ${o.cname}. ${o.pcolor}</span>
                                                <span> x${o.quantity}</span>
                                            </div>
                                        </div>
                                        <div class="order-product-price flex ">
                                            <span class="order-product-old__price">${o.poldPrice}</span>
                                            <span class="order-product-current__price">${o.pcurrentPrice}</span>
                                        </div>
                                    </div>
                                    <div class="order-total flex">
                                        <span></span>
                                        <span style="font-size: 1.44rem;"><i style="color: var(--primary-color); font-size: 1.6rem; margin: 0 4px;" class="fa-solid fa-money-check-dollar"></i> Thành tiền: &nbsp; </span>
                                        <span class="order-total__price"> ${o.pTotlePrice}</span>
                                    </div>
                                    <div class="order-button flex">
                                        <div class="order-notify flex">
                                            <c:if test="${o.accepted == 1 && o.delivered == 2 && o.feedback == 0}" >
                                                <span class="order-notify-rating">Đánh giá sản phẩm trước&nbsp;</span>
                                                <u>${o.orderDate}</u>
                                                <span class="order-notify-coin">&nbsp;&nbsp;Đánh giá ngay và nhận 200 Xu </span>
                                            </c:if>
                                            <c:if test="${o.accepted == 0 }" >
                                                <span class="order-notify-rating">Đang đợi người bán xác nhận đơn&nbsp;</span>
                                            </c:if>
                                            <c:if test="${o.accepted == -1}" >
                                                <span class="order-notify-rating">Đơn hàng đã được hủy bởi bạn&nbsp;</span>
                                            </c:if>
                                            <c:if test="${o.accepted == -2}" >
                                                <span class="order-notify-rating">Đơn hàng đã được hủy bởi người bán&nbsp;</span>
                                            </c:if>
                                            <c:if test="${o.accepted == 1 && o.delivered == 0 && o.feedback == 0}" >
                                                <span class="order-notify-rating">Đơn hàng đang trên đường giao đến bạn&nbsp;</span>
                                            </c:if>

                                        </div>
                                        <div class="flex">
                                            <c:if test="${o.accepted == 1 && o.feedback == 0 && o.delivered == 2}" >
                                                <a href="order?type=1&orderid=${o.orderid}&pid=${o.pid}&color=${o.pcolor}#add-feedback"  class="btn__save order-btn">Đánh Giá</a>
                                                <a href="#" class="btn__white order-btn">Liên Hệ Người Bán</a>
                                                <a href="detail?pid=${o.pid}" class="btn__white order-btn">Mua Lại</a>
                                            </c:if>
                                            <c:if test="${o.accepted == 0 }" >
                                                <a href="order?type=${tag}&orderid=${o.orderid}&action=cancel" class="btn__save order-btn">Huỷ đơn</a>
                                                <a href="#" class="btn__white order-btn">Liên Hệ Người Bán</a>
                                            </c:if>
                                            <c:if test="${o.accepted == 1 && o.delivered == 0 && o.feedback == 0}" >
                                                <a href="order?type=${tag}&orderid=${o.orderid}&action=received" class="btn__save order-btn">Đã nhận được hàng</a>
                                                <a href="#" class="btn__white order-btn">Liên Hệ Người Bán</a>
                                                <a href="#" class="btn__white order-btn">Xem Đánh Giá Shop</a>
                                            </c:if>
                                            <c:if test="${o.accepted == 1 && o.delivered == 2 && o.feedback == 3}" >
                                                <a href="detail?pid=${o.pid}" class="btn__save order-btn">Mua Lại</a>
                                                <a href="#" class="btn__white order-btn">Liên Hệ Người Bán</a>
                                                <a href="#" class="btn__white order-btn">Xem Đánh Giá Shop</a>
                                            </c:if>
                                            <c:if test="${o.accepted == -1 || o.accepted ==  -2}" >
                                                <a href="detail?pid=${o.pid}" class="btn__save order-btn">Mua Lại</a>
                                                <a href="#" class="btn__white order-btn" style="min-width: 166px">Xem Chi Tiết Hủy Đơn</a>
                                                <a href="#" class="btn__white order-btn">Liên Hệ Người Bán</a>
                                            </c:if>
                                        </div>
                                    </div>  
                                </div>
                            </div>
                        </c:forEach>  
                    </div>
                </div>
            </div>
        </div>
        <div class="dialog overlay" id="add-feedback">
            <form action="order" method="post" class="dialog-body order-feedback">
                <input type="hidden" name="proid" value="${p.id}" />
                <input type="hidden" name="order_id" value="${order_id}" />
                <input type="hidden" name="pcolor" value="${color}" />

                <div class="flex" style="justify-content: space-between;">
                    <div class="dialog-feedback-title">Đánh Giá Sản Phẩm</div>
                </div>
                <div class="dialog-feedback">
                    <div class="dialog-feedback-product ">
                        <img src="${p.image}"
                             class="dialog-feedback-product__img" alt="">
                        <div class="dialog-feedback-product__describe">
                            <span class="dialog-feedback-product__name text__overflow">${p.title}</span>
                            <span class="dialog-feedback-product__category">Phân loại hàng: ${p.cate.cname}, ${color} </span>
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
                            <span class="dialog-feedback-content__title">Tính năng nổi bật : </span>
                            <textarea name="feature" class="dialog-feedback-content__textarea " rows="1"></textarea>
                        </div>
                        <div class="dialog-feedback-content__describe">
                            <span class="dialog-feedback-content__title">Chất lượng sản phẩm :</span>
                            <textarea name="standard" class="dialog-feedback-content__textarea" rows="1"></textarea>
                        </div>
                        <div class="dialog-feedback-border"></div>
                        <textarea name="content" class="dialog-feedback-content__textarea" rows="4" 
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
                        <input type="checkbox" name="display" value="1" class="dialog-feedback-checkbox__input" />
                        <span class="dialog-feedback-checkbox__title" > Hiện thị tên đăng nhập trên đánh giá này</span>
                    </div>
                </div>
                <div class="flex dialog-feedback-submit">
                    <a href="" class="btn__white dialog-feedback-submit__btn">Trở lại</a>
                    <button type="submit" class="btn__save dialog-feedback-submit__btn">Hoàn Thành</button>
                </div>
            </form>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
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
