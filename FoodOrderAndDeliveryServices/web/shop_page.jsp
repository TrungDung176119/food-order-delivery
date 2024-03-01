<%-- 
    Document   : shop_page
    Created on : Jan 31, 2024, 11:05:47 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ShopPage | HolaFood</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- boostrap -->
        <link href="./assests/boostrap/bootstrap.css" rel="stylesheet">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css/main.css">
        <link rel="stylesheet" href="./assests/css/details.css">
        <link rel="stylesheet" href="./assests/css/shop_page.css">

        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.png">

        <!-- Thư viện Moment.js -->
        <script src="https://momentjs.com/downloads/moment.min.js"></script>
        <!-- Thư viện tiếng Việt cho Moment.js -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/locale/vi.js"></script>

    </head>

    <body>
        <div class="shop">
            <jsp:include page="menu_home.jsp" />

            <div class="page__shop">
                <div class="page__shop-header">
                    <div class="container page__shop-header__infor">
                        <div class="page__shop-header__left">
                            <div class="flex">
                                <a href=shop_page.jsp class="page__shop-header-avatar">
                                    <img src="https://down-vn.img.susercontent.com/file/06f4e963e03e598723b96d5ebe047d4d_tn"
                                         alt="" class="suga-avatar__img">
                                </a>
                                <div class="page__shop-infor">
                                    <span class="page__shop-header-infor__name">${seller.store_name}</span>
                                    <span class="page__shop-header-infor__status">Online 16 phút trước</span>
                                </div>
                            </div>
                            <button class="page__shop-btn-follow">+ THEO DÕI</button>
                        </div>
                        <div class="page__shop-header__right">
                            <div class="page__shop-header__right-descript">
                                <label class="page__shop-descripte-title">Sản Phẩm: </label>
                                <span class="page__shop-descripte-number">&nbsp; ${seller.number_of_foods}</span>
                            </div>
                            <div class="page__shop-header__right-descript">
                                <label class="page__shop-descripte-title">Người Theo dõi: </label>
                                <span class="page__shop-descripte-number">&nbsp; ${seller.follower}</span>
                            </div>
                            <div class="page__shop-header__right-descript">
                                <label class="page__shop-descripte-title">Số Điện thoại: </label>
                                <span class="page__shop-descripte-number">&nbsp; ${seller.phone_store}</span>
                            </div>
                            <div class="page__shop-header__right-descript">
                                <label class="page__shop-descripte-title">Đánh Giá: </label>
                                <span class="page__shop-descripte-number">&nbsp; ${seller.rating_store}</span>
                            </div>
                            <div class="page__shop-header__right-descript">
                                <label class="page__shop-descripte-title">Tỉ lệ Phản hồi: </label>
                                <span class="page__shop-descripte-number">&nbsp; 52%</span>
                            </div>
                            <div class="page__shop-header__right-descript">
                                <label class="page__shop-descripte-title">Tham Gia: &nbsp;</label>
                                <span class="page__shop-descripte-number" id="openTime"> ${seller.store_opentime}</span>
                            </div>
                            <div class="page__shop-header__right-descript">
                                <label class="page__shop-descripte-title">Tỉ lệ shop hủy đơn: </label>
                                <span class="page__shop-descripte-number">&nbsp; 1%</span>
                            </div>
                            <div class="page__shop-header__right-descript">
                                <label class="page__shop-descripte-title">Địa chỉ quán: </label>
                                <span class="page__shop-descripte-number" style="white-space: normal; text-align: start;">&nbsp;  ${seller.address_store}</span>
                            </div>
                        </div>
                    </div>
                    <div class="page__shop-menu">
                        <section class="container page__shop-menu-list">
                            <a href="" class="page__shop-menu-item page__shop-menu-item--active">
                                <span class="shop__page-menu-item__link">DẠO</span>
                            </a>
                            <a href="#menuToday" class="page__shop-menu-item">
                                <span class="shop__page-menu-item__link">Thực Đơn Hôm Nay</span>
                            </a>
                            <a href="" class="page__shop-menu-item">
                                <span class="shop__page-menu-item__link">Cơm</span>
                            </a>
                            <a href="" class="page__shop-menu-item">
                                <span class="shop__page-menu-item__link">DẠO</span>
                            </a>
                            <a href="" class="page__shop-menu-item">
                                <span class="shop__page-menu-item__link">DẠO</span>
                            </a>
                            <a href="" class="page__shop-menu-item">
                                <span class="shop__page-menu-item__link">DẠO</span>
                            </a>

                        </section>
                    </div>
                </div>

                <div class="container ">
                    <h3 class="page__shop-title-suggest">Gợi Ý Cho Bạn</h3>
                    <div class="detail__product-list">
                        <c:forEach items="${suggestionofseller}" var="o" begin="1" end="6">
                            <div class="box-product-16">
                                <a href="detail?pid=${o.pid}" class="home-product-item">
                                    <div class="home-product-item__img"
                                         style="background-image: url(${o.image})">
                                    </div>
                                    <h4 class="home-product-item__name text__overflow-1">${o.title} </h4>
                                    <div class="home-product-item__note text__overflow-1">${o.address_store}</div>
                                    <div class="home-product-item__price">
                                        <span class="home-product-item__price-old text__overflow">${o.old_price}</span>
                                        <span class="home-product-item__price-current text__overflow">${o.current_price}</span>
                                    </div>
                                    <div class="home-product-item__action ">
                                        <span class="home-product-item__like">
                                            <i class="fas fa-heart"></i>
                                        </span>
                                        <div class="home-product-item__rating ">
                                            <div>${o.rating}</div>
                                            <i class="fas fa-star"></i>
                                        </div>
                                        <span class="home-product-item__sold"> Đã bán <span class="amount_sold">${o.amount_of_sold}</span></span>
                                    </div>
                                    <div class="home-product-item_favorite">
                                        <i class="fas fa-check"></i>
                                        <span>${o.status}</span>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="page__shop-view">
                        <div id="menuToday" class="page__shop-view-left">
                            <h3  class="page__shop-title-suggest">Thực Đơn Hôm nay</h3>
                            <div class="home-product-list">
                                <div class="grid__row">
                                    <c:forEach items="${listmenuofseller}" var="o">
                                        <div class="box-product-20">
                                            <a href="detail?pid=${o.pid}" class="home-product-item">
                                                <div class="home-product-item__img"
                                                     style="background-image: url(${o.image})">
                                                </div>
                                                <h4 class="home-product-item__name text__overflow-1">${o.title} </h4>
                                                <div class="home-product-item__note text__overflow-1">${o.address_store}</div>
                                                <div class="home-product-item__price">
                                                    <span class="home-product-item__price-old text__overflow">${o.old_price}</span>
                                                    <span class="home-product-item__price-current text__overflow">${o.current_price}</span>
                                                </div>
                                                <div class="home-product-item__action ">
                                                    <span class="home-product-item__like">
                                                        <i class="fas fa-heart"></i>
                                                    </span>
                                                    <div class="home-product-item__rating ">
                                                        <div>${o.rating}</div>
                                                        <i class="fas fa-star"></i>
                                                    </div>
                                                    <span class="home-product-item__sold"> Đã bán <span class="amount_sold">${o.amount_of_sold}</span></span>
                                                </div>
                                                <div class="home-product-item_favorite">
                                                    <i class="fas fa-check"></i>
                                                    <span>${o.status}</span>
                                                </div>
                                            </a>
                                        </div>  
                                    </c:forEach>
                                </div>
                            </div>
                        </div>

                        <div class="page__shop-view-right">
                            <section class="page__shop-view-special">
                                <h4 class="page__shop-view-special__name">Top Sản Phẩm Nổi Bật</h4>
                                <c:forEach items="${outstadingproduct}" begin="1" end="4" var="o">
                                    <a href="detail?pid=${o.pid}" class="page__shop-view-special__link">
                                        <div class="page__shop-product-img">
                                            <div class="page__shop-product-img__url"
                                                 style="background-image: url(${o.image})">
                                            </div>
                                        </div>
                                        <div class="page__shop-product-describe">
                                            <div class="page__shop-product-title text__overflow-2">${o.title}</div>
                                            <div class="page__shop-product-current__price">${o.current_price}</div>
                                        </div>
                                    </a>
                                </c:forEach>
                            </section>
                        </div>
                    </div>


                </div>

            </div>
            <jsp:include page="footer.jsp"/>
        </div>
        <script src="assests/js/Jquery.js"></script>
        <script src="assests/js/bootstrap.min.js"></script>
        <script src="assests/js/main.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var linkMenuToday = document.querySelector('.page__shop-menu-item a[href="#menuToday"]');
                if (linkMenuToday) {
                    linkMenuToday.addEventListener("click", function (event) {
                        event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ <a>

                        var menuTodayElement = document.getElementById("menuToday");
                        if (menuTodayElement) {
                            menuTodayElement.scrollIntoView({behavior: "smooth-scroll"}); // Cuộn đến phần tử mong muốn một cách mượt mà
                        }
                    });
                }
            });

            // Lấy thẻ span bằng ID
            var openTimeElement = document.getElementById("openTime");
            // Lấy giá trị thời gian từ thẻ span
            var openTimeValue = openTimeElement.textContent;
            // Sử dụng Moment.js để định dạng lại thời gian với ngôn ngữ tiếng Việt
            moment.locale('vi'); // Đặt ngôn ngữ là tiếng Việt
            // Lấy số ngày giữa thời điểm hiện tại và thời gian cần định dạng
            var daysAgo = moment().diff(moment(openTimeValue), 'days');
            // Định dạng lại văn bản
            var formattedOpenTime;

            if (daysAgo < 30) {
                formattedOpenTime = daysAgo + ' ngày trước';
            } else if (daysAgo < 365) {
                var monthsAgo = Math.floor(daysAgo / 30);
                formattedOpenTime = monthsAgo + ' tháng trước';
            } else {
                var yearsAgo = Math.floor(daysAgo / 365);
                formattedOpenTime = yearsAgo + ' năm trước';
            }

            // Cập nhật nội dung của thẻ span
            openTimeElement.textContent = formattedOpenTime;
        </script>
    </body>

</html>