<%-- 
    Document   : searcg
    Created on : Jan 7, 2024, 6:36:54 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>HolaFood</title>
        <!-- boostrap -->
        <link href="./assests/boostrap/bootstrap.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css/main.css">
        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;600;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
        <link rel="stylesheet" href="assests/lib/jakarta.servlet.jsp.jstl-2.0.0.jar">

        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.png">

        <style>
            .home-filter__btn--active {
                background-color: var(--primary-color);
                color: var(--white-color);
            }

        </style>
    </head>
    <body>
        <div class="app">
            <jsp:include page="menu_home.jsp" />

            <div class="home__page">
                <div class="container">
                    <!-- <div>
                        <a>HolaFood > Tân Xã > Cơm Rang</a>
                    </div> -->
                    <div class="grid__row">
                        <div class="home-sidebar col-md-2 col-sm-2 col-lg-2 col-xs-2">
                            <nav class="category">
                                <h3 class="categoty__heading">
                                    <i class="fa-solid fa-list categoty__heading-icon" style="color: #000000;"></i>
                                    Danh mục
                                </h3>
                                <ul class="category-list">
                                    <jsp:useBean id="c" class="dal.ProductDAO">
                                        <c:forEach items="${c.allCategory}" var="cate">
                                            <li class="category-item ${tag == cate.id ?"category-item--active ":""} text__overflow" style="display: flex;"
                                                title="${cate.name}">
                                                <a href="search?cateid=${cate.id}" class="category-item__link" > ${cate.name}</a>
                                            </li>
                                        </c:forEach>
                                    </jsp:useBean>

                                </ul>
                            </nav>
                        </div>

                        <div class="home-product col-md-10 col-sm-10 col-lg-10 col-xs-10">
                            <!-- home filter sort -->
                            <div method="search" class="home-filter__sort">
                                <span class="home-filter__title">Sắp xếp theo</span>
                                <c:if test="${cateid == null}" >
                                    <a href="search?keyword=${keyword}&action=search&sortBy=sale" class="home-filter__btn btn ${sortBy == "sale"? "home-filter__btn--active":""}">Giảm Giá</a>
                                    <a href="search?keyword=${keyword}&action=search&sortBy=newest"  class="home-filter__btn btn ${sortBy == "newest"? "home-filter__btn--active":""}">Mới Nhất</a>
                                    <a href="search?keyword=${keyword}&action=search&sortBy=sellest"  class="home-filter__btn btn ${sortBy == "sellest"? "home-filter__btn--active":""}">Bán Chạy</a>
                                </c:if>
                                <c:if test="${cateid != null}" >
                                    <a href="search?cateid=${cateid}&sortBy=sale" class="home-filter__btn btn ${sortBy == "sale"? "home-filter__btn--active":""}">Giảm Giá</a>
                                    <a href="search?cateid=${cateid}&sortBy=newest"  class="home-filter__btn btn ${sortBy == "newest"? "home-filter__btn--active":""}">Mới Nhất</a>
                                    <a href="search?cateid=${cateid}&sortBy=sellest"  class="home-filter__btn btn ${sortBy == "sellest"? "home-filter__btn--active":""}">Bán Chạy</a>
                                </c:if>
                                <div class="select-input" style="padding: 0 8px">
                                    <span class="select-input__label">Giá</span>
                                    <i class="select-input__icon fas fa-angle-down"></i>
                                    <ul class="select-input__list">
                                        <li class="select-input__item">
                                            <c:if test="${cateid == null}" >
                                                <a href="search?keyword=${keyword}&action=search&sortBy=asc" class="select-input__link" style="text-decoration: none">Giá: Tăng dần</a>
                                            </c:if>
                                            <c:if test="${cateid != null}" >
                                                <a href="search?cateid=${cateid}&sortBy=asc" class="select-input__link" style="text-decoration: none">Giá: Tăng dần</a>
                                            </c:if>
                                        </li>
                                        <li class="select-input__item">
                                            <c:if test="${cateid == null}" >
                                                <a href="search?keyword=${keyword}&action=search&sortBy=desc" class="select-input__link" style="text-decoration: none">Giá: Giảm dần</a>
                                            </c:if>
                                            <c:if test="${cateid != null}" >
                                                <a href="search?cateid=${cateid}&sortBy=desc" class="select-input__link" style="text-decoration: none">Giá: Giảm dần</a>
                                            </c:if>
                                        </li>
                                    </ul>
                                </div>

                                <div class="home-filter__paging">
                                    <a href="" class="home-filter__paging-btn">
                                        <i class="page-icon fas fa-angle-left"> </i><span>Prev</span>
                                    </a>
                                    <div class="select-input" style="width: 80px;">
                                        <span class="select-input__label">1</span>
                                        <i class="select-input__icon fas fa-angle-down" style="font-size: 1.1rem;"></i>
                                        <ul class="select-input__list">
                                            <li class="select-input__item">
                                                <a class="select-input__link" href="page1.html">Trang 1</a>
                                            </li>
                                            <li class="select-input__item">
                                                <a class="select-input__link" href="page2.html">Trang 2</a>
                                            </li>
                                            <li class="select-input__item">
                                                <a class="select-input__link" href="page3.html">Trang 3</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <a href="" class="home-filter__paging-btn">
                                        <span>&nbsp; Next</span><i class="page-icon fas fa-angle-right"></i>
                                    </a>
                                </div>
                            </div>

                            <div class="product-best__seller">
                                <c:if test="${keyword != null}" >
                                    <h3 class="product-best__seller-title"> Sản Phẩm Chứa Từ Khóa "${keyword}".</h3>
                                </c:if> 

                                <div class="home-product-list">
                                    <c:if test="${listP != null}" >
                                        <div class="grid__row">
                                            <c:forEach items="${listP}" var="c">
                                                <div class="box-product-20">
                                                    <a href="detail?pid=${c.pid}" class="home-product-item">
                                                        <div class="home-product-item__img"
                                                             style="background-image: url(${c.image})">
                                                        </div>
                                                        <h4 class="home-product-item__name text__overflow-1">${c.title} </h4>
                                                        <div class="home-product-item__note text__overflow-1">${c.address_store}</div>

                                                        <div class="home-product-item__price">
                                                            <span
                                                                class="home-product-item__price-old text__overflow">${c.old_price}</span>
                                                            <span
                                                                class="home-product-item__price-current text__overflow">${c.current_price}</span>
                                                        </div>
                                                        <div class="home-product-item__action ">
                                                            <span class="home-product-item__like">
                                                                <i class="fas fa-heart"></i>
                                                            </span>
                                                            <div class="home-product-item__rating ">
                                                                <div>${c.rating}</div>
                                                                <i class="fas fa-star"></i>
                                                            </div>
                                                            <div class="home-product-item__sold"> Đã bán <span class="amount_sold"> ${c.amount_of_sold}</span></div>
                                                        </div>
                                                        <div class="home-product-item_favorite">
                                                            <i class="fas fa-check"></i>
                                                            <span>${c.status}</span>
                                                        </div>
                                                        <!-- <div class="home-product-item__origin">Thạch Thất </div> -->
                                                    </a>
                                                </div>   
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                    <c:if test="${listP.size() == 0}" >
                                        <div class="home-product-none__result"> 
                                            <img class="home-product-none__result-img" src="assests/img/search-no-result.png" alt="">
                                            <div class="home-product-none__result-title1">Không tìm thấy kết quả nào</div>
                                            <div class="home-product-none__result-title2">Hãy thử dùng từ khóa chung chung hơn</div>
                                        </div>
                                    </c:if>

                                </div>

                            </div>

                        </div>

                    </div>
                </div>
            </div>
            <jsp:include page="footer.jsp"/>
        </div>
        <script src="assests/js/Jquery.js"></script>
        <script src="assests/js/bootstrap.min.js"></script>
        <script src="assests/js/main.js"></script>
    </body>

</html>