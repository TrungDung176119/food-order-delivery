<%-- 
    Document   : detail
    Created on : Jan 7, 2024, 4:41:34 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>HolaFood </title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- boostrap -->
        <link href="assests/boostrap/bootstrap.css" rel="stylesheet" type="text/css"/>
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css/main.css">
        <link rel="stylesheet" href="./assests/css/details.css">
        <link rel="stylesheet" href="./assests/css.profile/feedback.css">

        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.png"/>

        <!-- Thư viện Moment.js -->
        <script src="https://momentjs.com/downloads/moment.min.js"></script>
        <!-- Thư viện tiếng Việt cho Moment.js -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/locale/vi.js"></script>

    </head>

    <body>
        <div class="app">
            <jsp:include page="menu_home.jsp"/>

            <div class="container__details">
                <div class="container">
                    <div class="app__details-header row">
                        <div class="app__details-header-img col-md-5 col-sm-5 col-lg-5 col-xs-5">
                            <div class="detail-product-item__img"
                                 style="background-image: url(${detail.image})">
                            </div>
                        </div>

                        <div class="app__details-header-describe col-md-7 col-sm-7 col-lg-7 ">
                            <form action="detail" method="post" class="header__describe">
                                <input type="hidden" name="pid" value="${detail.pid}" />
                                <div class="product-describe__cate flex">
                                    <div class="product-describe__cate-status"><i class="fa-solid fa-heart"></i> ${detail.status}
                                    </div>
                                    <div class="product-describe__cate-label"> ${detail.category.name}</div>
                                </div>
                                <h3 class="product-describe__title">${detail.title}</h3>
                                <div class="product-describe__address">${detail.address_store}</div>
                                <div class="product-describe__statistic flex">
                                    <div class="product-describe__rating">
                                        <span class="product-describe__rating-number">${detail.rating}</span>
                                        <i class="fas fa-star"></i>
                                    </div>
                                    <div class="product-describe__feedback">
                                        <span class="product-describe__feedback-number">82+</span>
                                        <span class="product-describe__feedback-title">Lượt đánh giá</span>
                                    </div>
                                    <div class="product-describe__sold">
                                        <span class="product-describe__sold-number">${detail.amount_of_sold}</span>
                                        <span class="product-describe__sold-title">Đã bán</span>
                                    </div>
                                </div>
                                <div class="flex product-describe__price">
                                    <div class="product-describe__price-old"> ${detail.old_price} </div>
                                    <div class="product-describe__price-current"> ${detail.current_price}</div>
                                </div>

                                <div class="product-describe__open"><i class="fa-solid fa-circle-dot"></i>&nbsp; Mở cửa:
                                    7:00 - 20:00</div>
                                <!-- <div><i class="fa-solid fa-circle-dot"></i> Đã đóng cửa</div> -->

                                <div class="product-describe__dotime"><i class="fa-regular fa-clock"></i> &nbsp;Ước tính
                                    thời gian làm: 12 phút</div>
                                <div class="product-describe-btn">
                                    <button type="submit" name="action" value="add-to-cart" class="btn__light product-describe-btn__cart">Thêm Vào Giỏ Hàng</button>
                                    <button type="submit" name="action" value="order-now" class="btn__save product-describe-btn__order">Đặt Hàng</button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>

            <div class="app_related-product">
                <div class="container">
                    <div>
                        <p class="detail-product-title">CÁC SẢN PHẨM KHÁC CỦA QUÁN</p>
                    </div>

                    <div class=" app__details-related">
                        <div class="detail__product-list">
                            <c:forEach items="${requestScope.listP}" var="c">
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
                                    <button type="submit" name="action" value="add-to-cart"  class="adding-food-cart">
                                        <div class="btn__adding">+</div>
                                    </button>
                                </form>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>

            <!--product shop page-->             
            <section class="detail-page__shop" >
                <div class="container">
                    <div class="page__shop-container flex">
                        <div class="page__shop-left flex">
                            <div class="page__shop-avatar">
                                <img src="https://down-vn.img.susercontent.com/file/06f4e963e03e598723b96d5ebe047d4d_tn" alt="" class="page__shop-avatar__img">
                            </div>
                            <div class="page__shop-infor">
                                <span class="page__shop-infor__name">${seller.store_name}</span>
                                <span class="page__shop-infor__status">Online 28 Phút Trước</span>
                                <div class="flex page__shop-infor__btn">
                                    <button class="btn__light-primary">
                                        <span class="page__shop-img" style="background-image: url(https://deo.shopeemobile.com/shopee/shopee-pcmall-live-sg/productdetailspage/7bf03ed38ca37787fe78.svg);"></span>
                                        <span>Chat Ngay</span>
                                    </button>
                                    <a href="shoppage?sid=${seller.seller_id}&cateid=${detail.category.id}" class="btn__white page__shop-btn-visit">
                                        <img class="page__shop-img" src="https://deo.shopeemobile.com/shopee/shopee-pcmall-live-sg/productdetailspage/192a8dfc1c23525d396b.svg"  alt="">
                                        <span>Xem Ngay</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="page__shop-right">
                            <div class="page__shop-descripte">
                                <label class="page__shop-descripte-title" >Đánh Giá</label>
                                <span class="page__shop-descripte-number">${seller.rating_store}</span>  
                            </div>
                            <div class="page__shop-descripte">
                                <label class="page__shop-descripte-title" >Sản Phẩm</label>
                                <span class="page__shop-descripte-number">${seller.number_of_foods}</span>  
                            </div>
                            <div class="page__shop-descripte">
                                <label class="page__shop-descripte-title" >Tham Gia</label>
                                <span class="page__shop-descripte-number" id="openTime">${seller.store_opentime}</span>  
                            </div>
                            <div class="page__shop-descripte">
                                <label class="page__shop-descripte-title" >Người Theo Dõi</label>
                                <span class="page__shop-descripte-number">${seller.follower}</span>  
                            </div>
                            <div class="page__shop-descripte">
                                <label class="page__shop-descripte-title" >Tỉ Lệ Phản Hồi</label>
                                <span class="page__shop-descripte-number">80%</span>  
                            </div>
                            <div class="page__shop-descripte">
                                <label class="page__shop-descripte-title" >Tỉ Lệ Hủy Đơn</label>
                                <span class="page__shop-descripte-number">1%</span>  
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <div class="app_related-product">
                <div class="container">
                    <div>
                        <p class="detail-product-title">CÁC SẢN PHẨM LIÊN QUAN</p>
                    </div>
                    <div class=" app__details-related">
                        <div class="detail__product-list">
                            <c:forEach begin="1" end="12" items="${related}" var="r">
                                <form action="detail" method="post"  class="box-product-16"  style="position: relative;">
                                    <input type="hidden" name="pid" value="${r.pid}" />
                                    <a href="detail?pid=${r.pid}" class="home-product-item">
                                        <div class="home-product-item__img"
                                             style="background-image: url(${r.image})">
                                        </div>
                                        <h4 class="home-product-item__name text__overflow-1">${r.title} </h4>
                                        <div class="home-product-item__note text__overflow-1">${r.address_store}</div>

                                        <div class="home-product-item__price">
                                            <span class="home-product-item__price-old text__overflow">${r.old_price}đ</span>
                                            <span class="home-product-item__price-current text__overflow">${r.current_price}đ</span>
                                        </div>
                                        <div class="home-product-item__action ">
                                            <span class="home-product-item__like">
                                                <i class="fas fa-heart"></i>
                                            </span>
                                            <div class="home-product-item__rating ">
                                                <div>${r.rating}</div>
                                                <i class="fas fa-star"></i>
                                            </div>

                                            <span class="home-product-item__sold">Đã bán <span class="amount_sold"> ${r.amount_of_sold}k</span> </span>
                                        </div>
                                        <div class="home-product-item_favorite">
                                            <i class="fas fa-check"></i>
                                            <span>${r.status}</span>
                                        </div>
                                        <!-- <div class="home-product-item__origin">Thạch Thất </div> -->
                                    </a>
                                    <button type="submit" name="action" value="add-to-cart"  class="adding-food-cart">
                                        <div class="btn__adding">+</div>
                                    </button>
                                </form>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>

            <div class="app__feedback">
                <div class="grid feedback">
                    <div class="feedback-title">ĐÁNH GIÁ SẢN PHẨM</div>
                    <div class="feedback-overiew flex">
                        <div class="feedback-rating">
                            <span class="feedback-rating__average">${avg_rate}</span>
                            <span class="feedback-rating__score">trên 5</span>
                            <div class="feedback-rating__star">
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                            </div>
                        </div>
                        <div class="feedback-filter flex">
                            <div class="feedback-filter__btn ">
                                <a href="detail?pid=${detail.pid}&rate=0" class="btn__light feedback-filter__btn-submit ${tag == 0? "feedback-filter__btn-submit--active":""}">Tất Cả</a>
                            </div>
                            <div class="feedback-filter__btn ">
                                <a href="detail?pid=${detail.pid}&rate=5" class="btn__light feedback-filter__btn-submit ${tag == 5? "feedback-filter__btn-submit--active":""}">5 Sao (${rate5})</a>
                            </div>
                            <div class="feedback-filter__btn ">
                                <a href="detail?pid=${detail.pid}&rate=4" class="btn__light feedback-filter__btn-submit ${tag == 4? "feedback-filter__btn-submit--active":""}">4 Sao (${rate4})</a>
                            </div>
                            <div class="feedback-filter__btn">
                                <a href="detail?pid=${detail.pid}&rate=3" class="btn__light feedback-filter__btn-submit  ${tag == 3? "feedback-filter__btn-submit--active":""}">3 Sao (${rate3})</a>
                            </div>
                            <div class="feedback-filter__btn ">
                                <a href="detail?pid=${detail.pid}&rate=2" class="btn__light feedback-filter__btn-submit ${tag == 2? "feedback-filter__btn-submit--active":""}">2 Sao (${rate2})</a>
                            </div>
                            <div class="feedback-filter__btn ">
                                <a href="detail?pid=${detail.pid}&rate=1" class="btn__light feedback-filter__btn-submit ${tag == 1? "feedback-filter__btn-submit--active":""}">1 Sao (${rate1})</a>
                            </div>
                            <div class="feedback-filter__btn">
                                <a href="detail?pid=${detail.pid}&rate=-1" class="btn__light feedback-filter__btn-submit  ${tag == -1? "feedback-filter__btn-submit--active":""}">Có Hình Ảnh / Video(0)</a>
                            </div>
                        </div>
                    </div>

                    <c:forEach items="${feedB}" var="f" > 
                        <div class="feedback-main">
                            <div class="suga-avatar feedback-avt">
                                <c:if test="${f.incognito == 1}" >
                                    <img class="suga-avatar__img"
                                         src="https://yt3.ggpht.com/yti/ADpuP3MEBtPK2w3PD74lGPnowgesuAR_VQYgWY4u0_NPcw=s88-c-k-c0x00ffffff-no-rj"
                                         alt="">
                                </c:if>
                                <c:if test="${f.incognito != 1}" >
                                    <img class="suga-avatar__img" 
                                         src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/User-avatar.svg/1200px-User-avatar.svg.png" 
                                         alt="" >
                                </c:if>
                            </div>
                            <div class="feedback-content">
                                <div class="flex">
                                    <div class="feedback-content__author">
                                        <c:if test="${f.incognito == 1}" >
                                            <span class="feedback-content__author-name">${f.username}</span>
                                        </c:if>
                                        <c:if test="${f.incognito == 0}" >
                                            <span class="feedback-content__author-name">${f.username.substring(0, 1)}*****${f.username.substring(f.username.length() - 1)}</span>
                                        </c:if>
                                        <div class="feedback-content__author-rating">
                                            <c:forEach begin="1" end="${f.rating}" >
                                                <i class="fas fa-star"></i>
                                            </c:forEach>  
                                            <c:forEach begin="1" end="${5 - f.rating}" >
                                                <i style="color: #d6d6d6" class="fas fa-star"></i>
                                            </c:forEach>  
                                        </div>
                                        <div class="feedback-content__author-time">
                                            <span>${f.feed_date}| Phân loại hàng: ${f.cate_name} </span>
                                        </div>
                                    </div>
                                    <c:if test="${sessionScope.acc.roleid == 3}">
                                        <form  class="flex" style="margin-left: 30px">
                                            <c:if test="${f.status == 0}">
                                                <a  href="detail?pid=${detail.pid}&rate=${tag}&feed_id=${f.feed_id}&action=show" >Hiện đánh giá</a>
                                            </c:if>
                                            <c:if test="${f.status == 1}">
                                                <a  href="detail?pid=${detail.pid}&rate=${tag}&feed_id=${f.feed_id}&action=hide" >Ẩn đánh giá</a>
                                            </c:if>
                                        </form>
                                    </c:if>
                                </div>
                                <div class="feedback-content__author-comment" style="${f.status == 0?' text-decoration: line-through':''}">
                                    <c:if  test="${f.describe != null && f.describe != ''}">
                                        <span class="feedback-content-label"> 
                                            Đúng với mô tả:&nbsp; 
                                            <span style="color: var(--text-color);"> ${f.describe}</span> 
                                        </span>
                                    </c:if>
                                    <c:if  test="${f.delivery != null&& f.delivery != ''}">
                                        <span class="feedback-content-label" style="padding: 4px 0;">
                                            Tính năng nổi bật: &nbsp;
                                            <span style="color: var(--text-color);"> ${f.delivery}</span>
                                        </span> 
                                    </c:if>
                                    <c:if  test="${f.standard != null && f.standard != ''}">
                                        <span class="feedback-content-label"> 
                                            Chất lượng sản phẩm: &nbsp;
                                            <span style="color: var(--text-color);">${f.standard}</span>
                                        </span>    
                                    </c:if>
                                </div>
                                <div class="feedback-content__author-main"  style="${f.status == 0?' text-decoration: line-through':''}"> ${f.content}</div>
                                <div class="flex">
                                    <div class="feedback-content-pic">
                                        <div class="feedback-content__image"
                                             style="background-image: url(https://down-bs-vn.img.susercontent.com/vn-11134103-7r98o-ln787jvdjpi0a6_tn.webp);">
                                        </div>
                                    </div>
                                    <div class="feedback-content-pic">
                                        <div class="feedback-content__image"
                                             style="background-image: url(https://down-bs-vn.img.susercontent.com/vn-11134103-7r98o-ln787jvdjpi0a6_tn.webp);">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="feedback-main">
                        <div class="suga-avatar feedback-avt">
                            <img class="suga-avatar__img"
                                 src="https://yt3.ggpht.com/yti/ADpuP3MEBtPK2w3PD74lGPnowgesuAR_VQYgWY4u0_NPcw=s88-c-k-c0x00ffffff-no-rj"
                                 alt="">
                        </div>
                        <div class="feedback-content">
                            <div class="feedback-content__author">
                                <span class="feedback-content__author-name">chaeyoung</span>
                                <div class="feedback-content__author-rating">
                                    <c:forEach  begin="0" end="4">
                                        <i class="fas fa-star"></i>
                                    </c:forEach>
                                </div>
                                <div class="feedback-content__author-time">
                                    <span>2023-11-25 20:06 | Phân loại hàng: Đen </span>
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
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </div>

    <script src="assests/js/main.js"></script>
    <script>
        function submitForm() {
            document.getElementById("feedbackForm").submit();
        }
        function reloadPageOnClick() {
            location.reload(); // Reload trang web
        }
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