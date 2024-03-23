<%-- 
    Document   : home.jsp
    Created on : Jan 7, 2024, 4:00:50 PM
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
        <link rel="stylesheet" href="./assests/css/footer.css">

        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;600;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.png">

    </head>
    <body>
        <div class="app">
            <jsp:include page="menu_home.jsp"/>

            <div class="home__page">
                <div class="container">
                    <div class="grid__row">
                        <div class="product-best__seller">
                            <h3 class="product-best__seller-title">Món Được Mua Nhiều Nhất</h3>
                            <div class="home-product-list" style="width: 1200px">
                                <div class="grid__row">
                                    <c:forEach items="${listProMostP}" var="o" >
                                        <div class="box-product-20">
                                            <a href="detail?pid=${o.pid}" class="home-product-item">
                                                <div class="home-product-item__img"
                                                     style="background-image: url(${o.image})">
                                                </div>
                                                <h4 class="home-product-item__name text__overflow-1">${o.title}</h4>
                                                <div class="home-product-item__note text__overflow-1">${o.address_store}</div>

                                                <div class="home-product-item__price">
                                                    <span class="home-product-item__price-old text__overflow">${o.old_price}đ</span>
                                                    <span class="home-product-item__price-current text__overflow">${o.current_price}đ</span>
                                                </div>
                                                <div class="home-product-item__action ">
                                                    <span class="home-product-item__like">
                                                        <i class="fas fa-heart"></i>
                                                    </span>
                                                    <div class="home-product-item__rating ">
                                                        <div>${o.rating}</div>
                                                        <i class="fas fa-star"></i>                                                                      
                                                        <i class="fas fa-star"></i>
                                                        <i class="fas fa-star"></i>
                                                        <i class="fas fa-star"></i>
                                                        <i class="fas fa-star"></i>
                                                    </div>
                                                    <span class="home-product-item__sold"> Đã bán ${o.amount_of_sold}</span>
                                                </div>
                                                <div class="home-product-item_favorite">
                                                    <i class="fas fa-check"></i>
                                                    <span>${o.status}</span>
                                                </div>
                                                <!-- <div class="home-product-item__origin">Thạch Thất </div> -->
                                            </a>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="product-best__seller">
                            <h3 class="product-best__seller-title"> Được Đánh Giá Cao</h3>

                            <div class="home-product-list" style="width: 1200px">
                                <div class="grid__row">
                                    <c:forEach items="${listProHigh}" var="o" >
                                        <div class="box-product-20">
                                            <a href="detail?pid=${o.pid}" class="home-product-item">
                                                <div class="home-product-item__img"
                                                     style="background-image: url(${o.image})">
                                                </div>
                                                <h4 class="home-product-item__name text__overflow-1">${o.title}</h4>
                                                <div class="home-product-item__note text__overflow-1">${o.address_store}</div>

                                                <div class="home-product-item__price">
                                                    <span class="home-product-item__price-old text__overflow">${o.old_price}đ</span>
                                                    <span
                                                        class="home-product-item__price-current text__overflow">${o.current_price}đ</span>
                                                </div>
                                                <div class="home-product-item__action ">
                                                    <span class="home-product-item__like">
                                                        <i class="fas fa-heart"></i>
                                                    </span>
                                                    <div class="home-product-item__rating ">
                                                        <div>${o.rating}</div>
                                                        <i class="fas fa-star"></i>                                                                      
                                                        <i class="fas fa-star"></i>
                                                        <i class="fas fa-star"></i>
                                                        <i class="fas fa-star"></i>
                                                        <i class="fas fa-star"></i>
                                                    </div>
                                                    <div class="home-product-item__sold"> Đã bán <span class="amount_sold"> ${o.amount_of_sold}</span></div>
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

                        <div class="product-best__seller">
                            <h3 class="product-best__seller-title flex"> 
                                <span>Gợi Ý Hôm Nay</span>
                                <!--home filter pagination--> 
                                <div class="home-filter__paging">
                                    <c:if test="${tag > 1}">
                                        <a href="home?index=${tag-1}" class="home-filter__paging-btn">
                                            <i class="page-icon fas fa-angle-left"> </i>
                                            <span>Prev</span>
                                        </a>
                                    </c:if>
                                    <div class="select-input" style="width: 77px;">
                                        <span class="select-input__label">${tag}</span>
                                        <i class="select-input__icon fas fa-angle-down" style="font-size: 1.1rem;"></i>
                                        <ul class="select-input__list">
                                            <c:forEach begin="1" end="${endP}" var="i">
                                                <li class="select-input__item">
                                                    <a class="select-input__link ${tag == i? "link__active":""}"  href="home?index=${i}">Page ${i}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <c:if test="${tag < endP}">
                                        <a href="home?index=${tag+1}" class="home-filter__paging-btn" style="justify-content: right">
                                            <span>Next</span>
                                            <i class="page-icon fas fa-angle-right"></i>
                                        </a>
                                    </c:if>

                                </div><!-- comment -->
                            </h3>
                            <div class="home-product-list" style="width: 1200px">
                                <div class="grid__row">
                                    <c:forEach items="${listPToday}" var="o" >
                                        <div class="box-product-20">
                                            <a href="detail?pid=${o.pid}" class="home-product-item">
                                                <div class="home-product-item__img"
                                                     style="background-image: url(${o.image})">
                                                </div>
                                                <h4 class="home-product-item__name text__overflow-1">${o.title}</h4>
                                                <div class="home-product-item__note text__overflow-1">${o.address_store}</div>

                                                <div class="home-product-item__price">
                                                    <span class="home-product-item__price-old text__overflow">${o.old_price}đ</span>
                                                    <span
                                                        class="home-product-item__price-current text__overflow">${o.current_price}đ</span>
                                                </div>
                                                <div class="home-product-item__action ">
                                                    <span class="home-product-item__like">
                                                        <i class="fas fa-heart"></i>
                                                    </span>
                                                    <div class="home-product-item__rating ">
                                                        <div>${o.rating}</div>
                                                        <i class="fas fa-star"></i>                                                                      
                                                        <i class="fas fa-star"></i>
                                                        <i class="fas fa-star"></i>
                                                        <i class="fas fa-star"></i>
                                                        <i class="fas fa-star"></i>

                                                    </div>
                                                    <span class="home-product-item__sold"> Đã bán ${o.amount_of_sold}</span>
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
                            <!--home filter pagination--> 
                            <div class="home-filter__paging" style="justify-content: end">
                                <c:if test="${tag > 1}">
                                    <a href="home?index=${tag-1}" class="home-filter__paging-btn">
                                        <i class="page-icon fas fa-angle-left"> </i>
                                        <span>Prev</span>
                                    </a>
                                </c:if>
                                <div class="select-input" style="width: 77px;">
                                    <span class="select-input__label">${tag}</span>
                                    <i class="select-input__icon fas fa-angle-down" style="font-size: 1.1rem;"></i>
                                    <ul class="select-input__list">
                                        <c:forEach begin="1" end="${endP}" var="i">
                                            <li class="select-input__item">
                                                <a class="select-input__link ${tag == i? "link__active":""}"  href="home?index=${i}">Page ${i}</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <c:if test="${tag < endP}">
                                    <a href="home?index=${tag+1}" class="home-filter__paging-btn" style="justify-content: right">
                                        <span>Next</span>
                                        <i class="page-icon fas fa-angle-right"></i>
                                    </a>
                                </c:if>

                            </div><!-- comment -->     
                        </div>

                    </div>
                </div>
            </div>
            <jsp:include page="footer.jsp"/>
        </div>
        <script src="assests/js/Jquery.js"></script>
        <script src="assests/js/bootstrap.min.js"></script>
        <script src="assests/js/main.js"></script>
        

            <!-- Your existing content here -->
            <!-- Chat Box Container -->
            
    </body>

</html>