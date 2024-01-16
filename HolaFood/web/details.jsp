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
        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.png"/>
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
                                    <button class="btn__save product-describe-btn__order">Đặt Hàng</button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>

            <div class="app_related-product">
                <div class="container">
                    <div>
                        <p class="detail-product-title">CÁC SẢN PHẨM LIÊN QUAN</p>
                    </div>
                    <div class=" app__details-related">
                        <div class="detail__product-list">
                            <c:forEach begin="1" end="6">
                                <div class="box-product-16">
                                    <a href="" class="home-product-item">
                                        <div class="home-product-item__img"
                                             style="background-image: url(https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSoCdynac-lsDYILxc2mr5HIkPeoeFdJQBFY5sn7jsWSg&s)">
                                        </div>
                                        <h4 class="home-product-item__name text__overflow-1">Cơm rang dưa bò thạch rau câu </h4>
                                        <div class="home-product-item__note text__overflow-1">Gần cây xăng 39-Thạch
                                            Hòa-Thạch Thất</div>

                                        <div class="home-product-item__price">
                                            <span class="home-product-item__price-old text__overflow">330.990đ</span>
                                            <span class="home-product-item__price-current text__overflow">260.890đ</span>
                                        </div>
                                        <div class="home-product-item__action ">
                                            <span class="home-product-item__like">
                                                <i class="fas fa-heart"></i>
                                            </span>
                                            <div class="home-product-item__rating ">
                                                <div>4.7</div>
                                                <i class="fas fa-star"></i>
                                            </div>
                                            
                                            <span class="home-product-item__sold">Đã bán <span class="amount_sold">88 k</span> </span>
                                        </div>
                                        <div class="home-product-item_favorite">
                                            <i class="fas fa-check"></i>
                                            <span>Yêu thích +</span>
                                        </div>
                                        <!-- <div class="home-product-item__origin">Thạch Thất </div> -->
                                    </a>
                                </div>
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
                            <span class="feedback-rating__average">4.9</span>
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
                            <div class="feedback-filter__btn">
                                <button type="submit" class="btn__light feedback-filter__btn-submit">Tất Cả</button>
                            </div>
                            <div class="feedback-filter__btn">
                                <button type="submit" class="btn__light feedback-filter__btn-submit">5 Sao (3,3k)</button>
                            </div>
                            <div class="feedback-filter__btn">
                                <button type="submit" class="btn__light feedback-filter__btn-submit">4 Sao (377)</button>
                            </div>
                            <div class="feedback-filter__btn">
                                <button type="submit" class="btn__light feedback-filter__btn-submit">3 Sao (118)</button>
                            </div>
                            <div class="feedback-filter__btn">
                                <button type="submit" class="btn__light feedback-filter__btn-submit">2 Sao (46)</button>
                            </div>
                            <div class="feedback-filter__btn">
                                <button type="submit" class="btn__light feedback-filter__btn-submit">1 Sao (89)</button>
                            </div>
                            <div class="feedback-filter__btn">
                                <button type="submit" class="btn__light feedback-filter__btn-submit">Có Hình Ảnh /
                                    Video(1,2k)</button>
                            </div>
                        </div>
                    </div>

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
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
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
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
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
                                         style="background-image: url(https://down-bs-vn.img.susercontent.com/vn-11134103-7r98o-ln787jvdjpi0a6_tn.webp);">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <jsp:include page="footer.jsp" />
        </div>

        <script src="assests/js/Jquery.js"></script>
        <script src="assests/js/bootstrap.min.js"></script>
        <script src="assests/js/main.js"></script>


    </body>

</html>