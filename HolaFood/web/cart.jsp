<%-- 
    Document   : carte
    Created on : Oct 20, 2023, 11:51:12 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>HolaFood | Giỏ Hàng</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- boostrap -->
        <link href="./assests/boostrap/bootstrap.css" rel="stylesheet">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/main.css">
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css.cart/cart.css">
        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.ico">

    </head>

    <body>
        <div class="cart">
            <header class="header__cart">
                <div class="grid">
                    <nav class="header__navbar">
                        <ul class="header__navbar-list">
                            <li class="header__navbar-item">
                                <span class="header__navbar-title--no-pointer">Kết nối </span>
                                <a href="" class="header__navbar-icon-link">
                                    <i class="fab fa-facebook" style="color: var(--white-color);"></i>
                                </a>

                                <a href="" class="header__navbar-icon-link">
                                    <i class="fab fa-instagram" style="color: var(--white-color);"
                                       aria-hidden="false"></i>&nbsp
                                </a>
                            </li>
                            <li class="header__navbar-item">
                                Hotline:
                                <a class="header__navbar-support" style="text-decoration: none;" href="tel:19004869"
                                   title="1900 4869">1900 4869</a>
                            </li>
                            <li class="header__navbar-item">
                                Email:
                                <a class="header__navbar-support" style="text-decoration: none;"
                                   href="mailto:hoanpche170404@fpt.edu.vn">support@suga.vn</a>
                            </li>
                        </ul>
                        <ul class="header__navbar-list">
<!--                            <li class="header__navbar-item">
                                <a href="" class="header__navbar-item-link" style="text-decoration: none;"><i
                                        class="fa-regular fa-bell"></i> &nbspThông báo</a>
                            </li>-->
                            <li class="header__navbar-item header__navbar-notify">
                                <a href="notifications" class="header__navbar-item-link">
                                    <i class="fa-regular fa-bell" style="font-size: 1.6rem"></i> &nbsp;Thông báo
                                </a>
                                <c:if test="${sessionScope.sumNotify > 0}">
                                    <span class="header__notify-notice" style="top: 0px">${sessionScope.sumNotify}</span>
                                </c:if>
                                <div class="header__notify">
                                    <header class="header__notify-head">
                                        <h3>Thông báo mới nhận</h3>
                                    </header>
                                    <ul class="header__notify-list">
                                        <c:forEach begin="0" end="4" items="${sessionScope.listNotify}" var="n" >
                                            <li class="header__notify-item">
                                                <a href="purchase?type=0" class="header__notify-link" title="click để xem chi tiết thông báo">
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
                                <a href="" class="header__navbar-item-link" style="text-decoration: none;"><i
                                        class="fa-regular fa-circle-question"></i> &nbspHỗ trợ </a>
                            </li>
                            <li class="header__navbar-item header__navbar-user">
                                <c:if test="${sessionScope.acc == null}" >
                                <li class="header__navbar-item header__navbar-item--strong header__navbar-item--separate">
                                    <a href="register.jsp" class="header__navbar-item-link" style="text-decoration: none;">Đăng ký</a>
                                </li>
                                <li class="header__navbar-item header__navbar-item--strong">
                                    <a href="login.jsp" class="header__navbar-item-link" style="text-decoration: none;">Đăng nhập</a>
                                </li>
                            </c:if>
                            </li>
                            <c:if test="${sessionScope.acc != null}" >
                                <li class="header__navbar-item header__navbar-user"> 
                                    <img class="header__navbar-user-img" 
                                         src="https://yt3.ggpht.com/yti/ADpuP3MEBtPK2w3PD74lGPnowgesuAR_VQYgWY4u0_NPcw=s88-c-k-c0x00ffffff-no-rj" alt="">

                                    <c:if test="${(AccDetail.nickname == null) || (AccDetail.nickname == '')}">
                                        <span style="padding-left: 6px">${sessionScope.acc.username}</span>
                                    </c:if>
                                    <c:if test="${AccDetail.nickname != null}">
                                        <span style="padding-left: 6px">${AccDetail.nickname}</span>
                                    </c:if>
                                    <ul class="header__navbar-user-menu" >
                                        <li class="header__navbar-user-item">
                                            <a href="profile" style="text-decoration: none;">Tài khoản của tôi</a>
                                        </li>
                                        <c:if test="${sessionScope.acc.roleid == 2}" >
                                            <li class="header__navbar-user-item">
                                                <a href="manager" >Quản lý người bán</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${sessionScope.acc.roleid == 3}" >
                                            <li class="header__navbar-user-item">
                                                <a href="manager" style="text-decoration: none;">Admin</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${sessionScope.acc.roleid == 1}" >
                                            <li class="header__navbar-user-item">
                                                <a href="" style="text-decoration: none;">Đơn mua</a>
                                            </li>
                                        </c:if>
                                        <li class="header__navbar-user-item">
                                            <a href="logout" style="text-decoration: none;">Đăng xuất</a>
                                        </li>
                                    </ul>
                                </li>
                            </c:if>
                            </li>
                        </ul>
                    </nav>
                </div>
            </header>

            <!-- cart with search -->
            <div class="cart-with-search">
                <div class="grid cart-search">
                    <div class="cart__header-logo">
                        <a href="home" class="header-logo">
                            <i class="fa-solid fa-utensils"></i>
                            <div class="header-logo__name">HolaFood </div>
                        </a>
                        <a href="showcart" style="color: var(--primary-color)" class="cart__header-title">
                            Giỏ Hàng
                        </a>
                    </div>
                    <form action="search" method="get" class="cart__header-search">
                        <input class="cart__header-search-input" type="text" name="keyword" 
                               placeholder="NGÀY 20/01 SIÊU SALE ĐẾN 50%">
                        <button type="submit" name="action" value="search" class="cart__header-search-btn">
                            <i class="fa-solid fa-magnifying-glass" style="color: white;"></i>
                        </button>
                    </form>
                </div>
            </div>

            <!-- cart container -->
            <div class="container" style="padding: 0;min-height: 450px">
                <!-- main cart -->
                <div class="cart-box">
                    <div class="cart-menu-list">
                        <div class="cart-menu-item__product">Sản Phẩm</div>
                        <div class="cart-menu-item__price">Đơn Giá</div>
                        <div class="cart-menu-item__amount">Số Lượng</div>
                        <div class="cart-menu-item__total">Số Tiền</div>
                        <div class="cart-menu-item__action">Thao Tác</div>
                    </div>
                </div>

                <!-- cart product -->
                <c:set var="counter" value="1" />
                <c:forEach items="${cart.items}" var="i">
                    <div class="cart-box cart-product">
                        <section class="cart-product-item">
                            <div class="cart-product-order">${counter}</div>
                            <div class="cart-product-infor">
                                <div class="cart-product-infor__img1">
                                    <img src="${i.product.image}"
                                         alt="">
                                </div>
                                <div class="cart-product-infor__right" style="padding: 5px 20px 0 10px ;">
                                    <div class="cart-product-infor__describe text__overflow">${i.product.title}</div>
                                    <div class="cart-product-infor__address text__overflow-1">${i.product.address_store}</div>
                                    <img class="cart-product-infor__freeship"
                                         src="https://down-vn.img.susercontent.com/file/vn-50009109-abef578ba9ba32dc8c078d16f9f00943"
                                         alt="">
                                </div>
                                <div class="cart-product-cate">
                                    <span> Phân Loại Hàng: </span>
                                    <div>${i.product.category.name}</div>
                                </div>
                            </div>
                            <div class="cart-product-price">
                                <div class="cart-product-price__old">${i.product.old_price}</div>
                                <div class="cart-product-price__current">${i.product.current_price}</div>
                            </div>
                            <div class="cart-product-amount-box">
                                <div class="cart-product-amount">
                                    <button type="button" class="cart-product-amount__btn">
                                        <a href="showcart?number=-1&pid=${i.product.pid}" style="padding: 4px"><i class="fa-solid fa-minus"></i></a>
                                    </button>
                                    <input type="text" class="cart-product-amount__num" value="${i.quantity}" />
                                    <button type="button" class="cart-product-amount__btn">
                                        <a href="showcart?number=1&pid=${i.product.pid}"style="padding: 4px"><i class="fa-solid fa-plus"></i></a>
                                    </button>
                                </div>
                                <div class="cart-product-amount__left">còn ${i.product.number_in_stock} sản phẩm </div>
                            </div>

                            <div class="cart-product-total">${(i.product.current_price*i.quantity)}</div>
                            <form action="showcart" method="get" class="cart-product-action">
                                <input type="hidden" name="pid" value="${i.product.pid}" />
                                <input class="cart-product-action-del" type="submit" name="action" value="Xóa" />
                            </form>
                        </section>
                    </div>
                    <c:set var="counter" value="${counter + 1}" />
                </c:forEach>
            </div>

            <!-- cart check out -->
            <div class="cart-box cart-checkout">
                <div class="_hoan1">
                    <div class="_caoh1">
                        <span></span>
                        <input style="padding-right: 20px;" type="checkbox" name="">
                    </div>
                    <div class="_caoh2">
                        <span class="_caoh2-xu">
                            <img style="height: 24px; padding-right: 6px;"
                                 src="https://down-vn.img.susercontent.com/file/a0ef4bd8e16e481b4253bd0eb563f784" alt="">
                            Hola Xu
                        </span>
                        <span class="_caoh2-use_xu">Dùng ${sessionScope.acc.coin} Hola Xu</span>
                        <span class="_caoh2-numb_xu">-₫${sessionScope.acc.coin}</span>
                    </div>
                </div>
                <div class="_hoan2">
                    <span class="_naoh1">Tổng thanh toán (${cart.items.size()} Sản Phẩm): </span>
                    <div class="_naoh2">
                        <span class="_naoh3">${cart.totalMoney}</span>
                        <a href="showcheckout" class="btn__save btn-checkout" >Mua Hàng</a>
                    </div>
                </div>
            </div>

            <div class="container product-list-suggest">
                <h4 class="suga-title__suggestion">Có Thể Bạn Cũng Thích</h4>
                <c:if test="${listSusggest != null}" >
                    ${listSusggest.size()}
                </c:if>
                <div class="detail__product-list" style="flex-wrap: wrap; display: flex">
                    <c:forEach begin="1" end="18" items="${listSusggest}" var="c">
                        <form action="detail" method="post"  class="box-product-16" style="position: relative;" >
                            <input type="hidden" name="pid" value="${c.pid}" />
                            <a href="detail?pid=${c.pid}" class="home-product-item">
                                <div class="home-product-item__img"
                                     style="background-image: url(${c.image})">
                                </div>
                                <h4 class="home-product-item__name text__overflow-1">${c.title} </h4>
                                <div class="home-product-item__note text__overflow-1">${c.address_store}</div>

                                <div class="home-product-item__price">
                                    <span class="home-product-item__price-old text__overflow">${c.old_price}</span>
                                    <span class="home-product-item__price-current text__overflow">${c.current_price}</span>
                                </div>
                                <div class="home-product-item__action ">
                                    <span class="home-product-item__like">
                                        <i class="fas fa-heart"></i>
                                    </span>
                                    <div class="home-product-item__rating ">
                                        <div>${c.rating}</div>
                                        <i class="fas fa-star"></i>
                                    </div>
                                    <span class="home-product-item__sold"> Đã bán ${c.amount_of_sold} </span>
                                </div>
                                <div class="home-product-item_favorite">
                                    <i class="fas fa-check"></i>
                                    <span>${c.status}</span>
                                </div>
                            </a>
                            <!--                            <button type="submit" name="action" value="add-to-cart"  class="adding-food-cart">
                                                            <div class="btn__adding">+</div>
                                                        </button>-->
                        </form>
                    </c:forEach>
                </div>
            </div>         
        </div>
    </body>
    <script src="assests/js/main.js"></script>

</html>
