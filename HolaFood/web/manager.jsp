<%-- 
    Document   : manager
    Created on : Sep 30, 2023, 10:43:32 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Suga | Quản lý sản phẩm</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- boostrap -->
        <link href="./assests/boostrap/bootstrap.css" rel="stylesheet">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css/main.css">
        <link rel="stylesheet" href="./assests/css.manager/manager.css">
        <link rel="stylesheet" href="./assests/css.manager/sidebar.css">

        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.ico">

    </head>
    <body>
        <div class="app__manager">
            <jsp:include page="./sidebar.jsp" />
            <div class="home__container">
                <div class="grid">
                    <div class="home__container-main">
                        <div class="home__main-header">
                            <div class="home__header-left">
                                <h4>Tổng ${sum} sản phẩm </h4>
                            </div>
                            <div class="home__header-mesage-del">
                                <span>${message__del}</span>
                            </div>
                            <div class="home__header-right">
                                <a href="/suga/add" class="btn btn__new"> + Thêm 1 sản phẩm mới </a>
                                <a href="/suga/add.category" class="btn__white">Quản lý thể loại hàng </a>
                            </div>
                        </div>
                        <div class="home__controller">
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
                                            <div class="table__product-title-price">Giá tiền</div>
                                        </th>
                                        <th>
                                            <div class="table__product-title-origin">Nơi bán</div>
                                        </th>
                                        <th>
                                            <div class="table__product-title-sold">Đã bán </div>
                                        </th>
                                        <th>
                                            <div class="table__product-title-sold">Kho hàng </div>
                                        </th>
                                        <th>
                                            <div class="table__product-title-origin">Trạng Thái</div>
                                        </th>
                                        <th>
                                            <div class="table__product-title-action " style="min-width: 120px;">Thao tác</div>
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
                                            <td>
                                                <div class="table__product-price" style="color: var(--primary-color)">${o.current_price}đ</div>
                                            </td>
                                            <td>
                                                <div class="table__product-origin"> ${o.seller_name}</div>
                                            </td>
                                            <td>
                                                <div class="table__product-sold">${o.amount_of_sold} </div>
                                            </td>
                                            <td>
                                                <div class="table__product-sold">Còn ${o.number_in_stock} sản phẩm </div>
                                            </td>
                                            <td>
                                                <div class="table__product-title-origin">${o.status}</div>
                                            </td>
                                            <td>
                                                <div class="table__product-action">
                                                    <a href="">Update</a>
                                                    <a href="#" onclick="doDelete('${o.id}')">Delete</a>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach> 
                                </table>
                            </div>
                        </div>

                        <!--manager filter pagination -->
                        <div  class="filter__pagination">
                            <div></div> 
                            <div class="manager-filter__paging" >
                                <c:if test="${tag > 1}">
                                    <a href="/suga/manager?index=${tag-1}" class="home-filter__paging-btn">
                                        <i class="page-icon fas fa-angle-left"> </i>
                                        <span>Prev</span>
                                    </a>
                                </c:if>
                                <div class="select-input">
                                    <c:forEach begin="1" end="${endP}" var="i">
                                        <a class="select-input__link" href="/suga/manager?index=${i}"> ${i}</a>
                                    </c:forEach>
                                </div>
                                <c:if test="${tag < endP}">
                                    <a href="/suga/manager?index=${tag+1}" class="home-filter__paging-btn">
                                        <span>Next</span>
                                        <i class="page-icon fas fa-angle-right"></i>
                                    </a>
                                </c:if>
                            </div>
                        </div>

                    </div>
                    <div class="home__footer" style="height: 50px;">
                        <!--space-->
                    </div>
                </div>
            </div>
        </div>
    </body>

    <script src="assests/js/main.js"></script>
    <script type="text/javascript">
                                                        function doDelete(id) {
                                                            if (confirm("Are u sure to delete this Product with id = " + id)) {
                                                                window.location = "delete?pid=" + id;
                                                            }
                                                        }
                                                        // Lấy thẻ cần ẩn đi sau một khoảng thời gian
                                                        var messageElement = document.querySelector('.home__header-mesage-del');

                                                        // Hàm ẩn thẻ sau một khoảng thời gian
                                                        function hideMessage() {
                                                            messageElement.style.display = 'none';
                                                        }
                                                        setTimeout(hideMessage, 6500); // 1000 milliseconds = 1 giây

    </script> 

</html>
