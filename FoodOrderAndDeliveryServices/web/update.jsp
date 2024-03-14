<%-- 
    Document   : add
    Created on : Oct 2, 2023, 8:22:45 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Suga | Quản lý sản phẩm | Thêm </title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css.manager/manager.css">
        <link rel="stylesheet" href="./assests/css.manager/add.css">

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
    <style>

    </style>

    <body>
        <div class="app__manager">
            <jsp:include page="./sidebar.jsp" />
            <div class="home__container">
                <div class="grid">
                    <div class="home__container-main">
                        <div class="home__main-header">
                            <div class="home__header-left">
                                <h4>Thông tin cơ bản</h4>
                                <p class="home__controll-msError">${ms}</p>
                            </div>
                            <div class="home__header-right">
                                <a href="manager" class="btn btn__new"> Quay lại trang trước</a>
                            </div>

                        </div>
                        <div class="home__controller container" style="padding-bottom: 30px;">
                            <form action="updatePro" method="post" class="home__controll-main" style=" margin-bottom: 50px;">
                                <div class="home__controll-input">
                                    <span class="home__controll-icon">*</span>
                                    <span class="home__controll-input-name">Hình ảnh sản phẩm</span>
                                    <input name="image" value="${detail.image}" required class="home__controll-input-text" type="text" placeholder="Thêm liên kết hình ảnh">
                                </div>

                                <div class="home__controll-input">
                                    <span class="home__controll-icon">*</span>
                                    <span class="home__controll-input-name">Tiêu đề sản phẩm</span>
                                    <input name="title" value="${detail.title}" required class="home__controll-input-text" type="text" placeholder="Nhập tiêu đề">
                                </div>
                                <div class="home__controll-input">
                                    <span class="home__controll-icon">*</span>
                                    <span class="home__controll-input-name">Mô tả sản phẩm</span>
                                    <textarea required  name="describe" class="home__controll-input-text text__describe" type="text" placeholder="Thêm mô tả sản phẩm">${detail.describe}</textarea>
                                </div> 
                                <div class="home__controll-number"  >
                                    <div class="home__controll-input" style="margin-right: 40px">
                                        <span class="home__controll-icon">*</span>
                                        <span class="home__controll-input-name">Thể loại hàng</span>
                                        <select required name="category" class="home__controll-input-text" style="width: 250px;" >
                                            <option value="-1" selected disabled style="color: rgb(177, 174, 174); ">Vui lòng chọn</option>
                                            <c:forEach items="${listC}" var="o">
                                                <c:choose>
                                                    <c:when test="${o.id eq detail.category.id}">
                                                        <option value="${o.id}" selected="">${o.name}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${o.id}" >${o.name}</option>
                                                    </c:otherwise>
                                                </c:choose>

                                            </c:forEach>
                                        </select> 
                                    </div>
                                </div>
                                <div class="home__controll-number" style="margin-bottom: 30px;">
                                    <div class="home__controll-number-input">
                                        <span class="home__controll-icon">*</span>
                                        <span class="home__controll-input-name">Giá (VND)</span>
                                        <input required name="new_price"  value="${detail.current_price}" class="home__controll-number-text" type="number" min="0" placeholder="Nhập vào">
                                    </div>
                                </div>

                                <div class="home__controll-number">
                                    <div class="home__controll-number-input" style="margin-right: 40px;">
                                        <span class="home__controll-icon">*</span>
                                        <span class="home__controll-input-name">Status</span>
                                        <select required name="status" class="home__controll-input-text" style="width: 250px;" >
                                            <option value="-1" selected disabled style="color: rgb(177, 174, 174); ">Vui lòng chọn</option>
                                            <c:choose>
                                                <c:when test="${detail.status eq 'Yêu thích'}">
                                                    <option value="Yêu thích" selected>Yêu thích</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="Yêu thích">Yêu thích</option>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${detail.status eq 'Giảm giá'}">
                                                    <option value="Giảm giá" selected>Giảm giá</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="Giảm giá">Giảm giá</option>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${detail.status eq 'Sắp về hàng'}">
                                                    <option value="Sắp về hàng" selected>Sắp về hàng</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="Sắp về hàng">Sắp về hàng</option>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${detail.status eq 'Khuyến mãi'}">
                                                    <option value="Khuyến mãi" selected>Khuyến mãi</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="Khuyến mãi">Khuyến mãi</option>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${detail.status eq 'Ẩn'}">
                                                    <option value="Ẩn" selected>Ẩn</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="Ẩn">Ẩn</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </select> 
                                    </div>
                                    <div class="home__controll-number-input">
                                        <span class="home__controll-icon">*</span>
                                        <span class="home__controll-input-name">Số lượng</span>
                                        <input required name="in_stock" value="${detail.number_in_stock}" class="home__controll-number-text" type="text" placeholder="Nhập vào">
                                    </div>
                                </div>
                                <div class="home__controll-input">
                                    <input type="hidden" name="productid" value="${detail.pid}">
                                    <input type="hidden" name="sold" value=${detail.amount_of_sold}>
                                    <input type="hidden" name="rating" value="${detail.rating}">
                                    <input type="hidden" name="current_price" value="${detail.current_price}">
                                </div>
                                <div class="home__controll-button">
                                    <div class="home__controll-button-left">
                                    </div>
                                    <div class="home__controll-button-right">
                                        <button class="btn btn__action btn__action-type" type="reset">Reset</button>
                                        <button class="btn btn__action btn__action-submit" type="submit">Xác nhận</button>
                                    </div>

                                </div>
                            </form>
                        </div>

                    </div>
                    <div class="home__footer" style="height: 50px;">

                    </div>
                </div>
            </div>
        </div>
    </body>

</html>