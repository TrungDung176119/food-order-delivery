<%-- 
    Document   : checkout
    Created on : Oct 21, 2023, 1:38:22 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh Toán</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <!-- boostrap -->
    <link href="./assests/boostrap/bootstrap.css" rel="stylesheet">
    <!-- css -->
    <link rel="stylesheet" href="./assests/css/base.css">
    <link rel="stylesheet" href="./assests/css/main.css">
    <link rel="stylesheet" href="./assests/css.cart/cart.css">
    <link rel="stylesheet" href="./assests/css.cart/checkout.css">
    <!-- font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

    <!-- icon title -->
    <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.png">
</head>

<body>
    <div class="checkout">
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
                               href="mailto:hoanpche170404@fpt.edu.vn">support@holafood.vn</a>
                        </li>
                    </ul>

                    <ul class="header__navbar-list">
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
                                        <a href="profile">Tài khoản của tôi</a>
                                    </li>
                                    <c:if test="${sessionScope.acc.roleid == 2}" >
                                        <li class="header__navbar-user-item">
                                            <a href="manager" >Quản lý người bán</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${sessionScope.acc.roleid == 3}" >
                                        <li class="header__navbar-user-item">
                                            <a href="manager" >Quản lý sản phẩm</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${sessionScope.acc.roleid == 1}" >
                                        <li class="header__navbar-user-item">
                                            <a href="" >Đơn mua</a>
                                        </li>
                                    </c:if>
                                    <li class="header__navbar-user-item">
                                        <a href="logout">Đăng xuất</a>
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
                    <a href="showcheckout" style="color: var(--primary-color)" class="cart__header-title">
                        Thanh Toán
                    </a>
                </div>
                <form action="search" method="get" class="cart__header-search">
                    <input class="cart__header-search-input" type="text" name="keyword" name="actio"
                           placeholder="NGÀY 30/01 SIÊU SALE ĐẾN 50%">
                    <button type="submit" class="cart__header-search-btn">
                        <i class="fa-solid fa-magnifying-glass" style="color: white;"></i>
                    </button>
                </form>
            </div>
        </div>

        <!-- container checkout -->
        <div  class="container-checkout container">
            <!-- checkout form address -->
            <c:choose>
                <c:when test="${empty listA}">
                    <div class="box-checkout checkout-form" style="width: 32.334%;">
                        <div class="checkout-form-title">
                            <i class="fa-solid fa-location-dot"></i>&nbsp;
                            Địa Chỉ Của Bạn
                        </div>
                        <form action="showcheckout" method="post" class="checkout-input-group">
                            <div class="checkout-input-field" style="margin-top: 0;">
                                <input required  class="input-box" type="text" name="logName" value="${accd.nickname}">
                                <label for="logName">Họ và tên: </label>
                            </div>
                            <div class="checkout-input-field">
                                <input required class="input-box" value="${accd.email}" type="email" placeholder="...@gmail.com" name="logEmail">
                                <label for="logEmail">Email:</laDebel>
                            </div>
                            <div class="checkout-input-field">
                                <input required class="input-box" value="${accd.phone}" type="number" name="logPhone" min="10">
                                <label for="logPhone">Số điện thoại: </label>
                            </div>
                            <div class="checkout-input-field">
                                <input required class="input-box" value="${accAddress.address}" type="text" name="logAddress">
                                <label for="logAddress">Địa chỉ: </label>
                            </div>
                            <div class="checkout-input-field" style="">
                                <input required class="input-box" style="color: blue" value="Bạn không cần nhập thông tin vào ô này" type="text" name="logDetailAddr">
                                <label for="logDetailAddr">Địa chỉ cụ thể: </label>
                            </div>
                            <div class="checkout-input-field">
                                <textarea required class="input-box textarea-note" type="text" name="logNote">${accAddress.note}</textarea>
                                <label for="logNote">Ghi chú: </label>
                            </div>
                            <div class="checkout-flex ">
                                <span></span>
                                <button type="submit" name="action" value="update_address"class="btn__save btn__checkout">Cập Nhật</button>
                            </div>
                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="box-checkout address-list" style="width: 32.334%;">
                        <div class="checkout-form-title">
                            <i class="fa-solid fa-location-dot"></i>&nbsp;
                            Danh sách Địa Chỉ
                        </div>
                        <div class="address-list">
                            <c:forEach var="o" items="${listA}">
                                <div class="address-card" onclick="selectAddress('<c:out value="${o.address_id}" />')">
                                    <p><strong>Tên người nhận:</strong> ${o.nickname}</p>
                                    <p><strong>Số điện thoại:</strong> ${o.phone_address}</p>
                                    <p><strong>Địa chỉ:</strong> ${o.address}</p>
                                    <p><strong>Địa chỉ cụ thể:</strong> ${o.note}</p>
                                    <p><strong>Loại địa chỉ:</strong> ${o.status}</p>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>

            <div style="display: flex">
                <!-- checkout delivery  -->
                <form action="showcheckout" method="get" id="checkoutForm"   class="box-checkout checkout-delivery" style="width: 50%">
                    <div>
                        <div class="checkout-form-title" >
                            <span> <i class="fa-solid fa-truck"></i> Vận chuyển  </span>
                        </div>
                        <div class="checkout-delivery-box">
                            <input required id="input3" type="radio" name="delivery" value="ship" onchange="submitForm()" ${delivery == "ship" ? 'checked': ''} class="checkout-input__radio">
                            <div class="checkout-flex">
                                <span class="checkout-delivery-name">Giao hàng tận nơi</span>
                                <span class="checkout-delivery-price">₫14.000</span>
                            </div>
                        </div>
                        <div class="checkout-delivery-box">
                            <input required id="input4" type="radio" name="delivery" value="noship" onchange="submitForm()" ${delivery == "noship" ? 'checked': ''} class="checkout-input__radio">
                            <div class="checkout-flex">
                                <span class="checkout-delivery-name">Đến quán nhận hàng </span>
                                <span class="checkout-delivery-price">₫0</span>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="checkout-form-title" style="padding-top: 8px;">
                            <i class="fa-solid fa-money-check-dollar"></i>
                            Phương thức thanh toán
                        </div>
                        <div class="checkout-delivery-">
                            <div class="checkout-delivery-box">
                                <input required id="input1" type="radio" ${type == 0 ? 'checked': ''} onchange="submitForm()" class="checkout-input__radio" name="type_checkout" value="0">
                                <span for="input1" class="checkout-delivery-name">Thanh toán khi nhận hàng</span>
                            </div>
                            <div class="checkout-delivery-box">
                                <input disabled required id="input2" type="radio" id="input2"  ${type == 1 ? 'checked': ''} onchange="submitForm()" class="checkout-input__radio" name="type_checkout" value="1">
                                <div class="checkout-flex">
                                    <span for="input2" class="checkout-delivery-name">Thanh toán qua ví SugaPay</span>
                                    <span class="checkout-delivery-price">Giảm thêm ₫15k</span>
                                </div>
                            </div>
                        </div>
                        <div class="checkout-message">
                            <span>${ms}</span>
                        </div>
                    </div>
                </form>

                <!-- checkout product list-->
                <form action="showcheckout" method="post" class="box-checkout checkout-product" style="padding-right: 0; width: 50%;">
                    <span class="checkout-form-title">
                        <i class="fa-brands fa-product-hunt"></i>
                        Đơn hàng (${amount} sản phẩm)
                    </span>

                    <div class="checkout-product-list">
                        <!-- checkout product item -->
                        <c:forEach items="${cart.items}" var="i">
                            <div class="checkout-product-item">
                                <img style="height: 50px; min-width: 88px"
                                     src="${i.product.image}" alt="">
                                <div class="checkout-flex" style="padding-top: 3px">
                                    <div class="">
                                        <span class="checkout-product-title text__overflow"> ${i.product.title} </span >
                                        <span class="checkout-product-address text__overflow-1">${i.product.address_store}</span>
                                    </div>
                                    <div>
                                        <span class="checkout-delivery-price checkouttotal">${i.product.current_price}</span>
                                        <span class="checkout-product-amount">x${i.quantity}</span>
                                    </div>
                                </div>
                            </div>
                        </c:forEach> 
                    </div>

                    <!-- checkout prouduct voucher -->
                    <div class="checkout-product-voucher">
                        <div class="checkout-input-field">
                            <input  type="text" name="voucher" class="input-box" style="width: 250px;">
                            <label for="logVoucher" >Nhập mã voucher </label>
                        </div>
                        <div>   
                            <button type="button" disabled class="btn__save btn__checkout">Áp dụng</button>
                        </div>
                    </div>

                    <div class="checkout-product-total">
                        <div class="checkout-flex checkout-money">
                            <span>Tổng tiền hàng</span>
                            <span class="checkouttotal">${(cart.totalMoney)}</span>
                        </div>
                        <div class="checkout-flex checkout-money">
                            <c:if test="${number == 1 || number == null}"  >
                                <span>Phí vận chuyển</span>
                            </c:if>
                            <c:if test="${number > 1}" >
                                <span>Phí vận chuyển (cho ${number} quán)</span>
                            </c:if>
                            <span class="checkouttotal">${delivery == 'ship' ? ship: "0"}</span>
                        </div>

                        <!--this for voucher-->
                        <c:if test="${voucher == 1}">
                            <div class="checkout-flex checkout-money">
                                <span></span>
                                <span>₫${delivery == ship ? "14.000": "0"}</span>
                            </div>
                        </c:if>
                        <div class="checkout-flex checkout-money1">
                            <span>Tổng thanh toán</span>
                            <span class="checkout-total-money">${checkMoney}</span>
                        </div>
                    </div>
                    <div class="checkout-flex checkout-money">
                        <a href="showcart">
                            <i class="fa-solid fa-chevron-left"></i>
                            <span>Quay về giỏ hàng</span>
                        </a>
                        <input type="hidden" name="type_delivery" value="${delivery}" />
                        <button type="submit" name="action" value="checkout_now" onclick="checkAndSubmit(event)" class="btn__save btn__order">Đặt hàng</button>
                    </div>
            </div>
            </form>
        </div>
    </div>
</div>
</body>
<script src="assests/js/main.js"></script>
<script>
                            function submitForm() {
                                document.getElementById('checkoutForm').submit();
                            }
                            function checkAndSubmit(event) {
                                var input1 = document.querySelector('#input1');
                                var input2 = document.querySelector('#input2');

                                if ((input1.checked || input2.checked) && (input3.checked || input4.checked)) {
                                    document.getElementById('checkoutForm').submit();
                                } else {
                                    event.preventDefault();
                                    alert("Vui lòng chọn phương thức thanh toán và vận chuyển.");
                                }
                            }
                            function selectAddress(addressId) {
                                // Update the hidden input field value with the selected address ID
                                document.getElementById("selectedAddressId").value = addressId;

                                // Optionally, you can submit the form automatically after selecting an address
                                document.querySelector('.checkout-input-group').submit();
                            }
</script>

</html>