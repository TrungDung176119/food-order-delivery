<%-- 
    Document   : managerCate
    Created on : Oct 14, 2023, 1:53:05 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Suga</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css.manager/manager.css">
        <link rel="stylesheet" href="./assests/css.manager/managerCate.css">
        <link rel="stylesheet" href="./assests/css.manager/sidebar.css">

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
        <div class="app__manager">
            <jsp:include page="sidebar.jsp" />
            <div class="home__container">
                <div class="grid">
                    <div class="home__container-main">
                        <div class="home__main-header">
                            <div class="home__header-left">
                                <h4>Tổng ${amount_cate} thể loại hàng</h4>
                            </div>
                            <c:if test="${sessionScope.msCate != null}" >
                                <div class="home__header-mesage-del">
                                    <span>${sessionScope.msCate}</span>
                                </div>
                            </c:if>
                            <div class="home__header-right">
                                <a href="#addCategory" class="btn btn__new"> + Thêm 1 loại hàng mới </a>
                            </div>
                        </div>

                        <div class="home__controller" style="padding-bottom: 30px;">
                            <div class="home__controll-main">
                                <table class="table__category">
                                    <tr class="table__product-title">
                                        <th style="max-width: 30px; color: var(--color-infor)">
                                            <div> STT </div>
                                        </th>
                                        <th>
                                            <div> Mã loại sản phẩm </div>
                                        </th>
                                        <th>
                                            <div>Tên thể loại sản phẩm</div>
                                        </th>
                                        <th>
                                            <div>Thao tác</div>
                                        </th>
                                    </tr>
                                    <c:set var="counter" value="1" />
                                    <c:forEach items="${listC}" var="o">
                                        <tr class="table__category-item">
                                            <th class="table__category-id" style="max-width: 30px; color: var(--color-infor)">
                                                <div >${counter}</div>
                                            </th>
                                            <th>
                                                <div class="table__category-id">${o.id}</div>
                                            </th>
                                            <th>
                                                <div class="table__category-name">${o.name}</div>
                                            </th>
                                            <th>
                                                <div class="table__category-action">
                                                    <a class="" href="loadcate?cateid=${o.id}">Update</a>
                                                    <a href="" onclick="confirmDelete(${o.id})">Delete</a>
                                                </div>
                                            </th>
                                        </tr>
                                        <c:set var="counter" value="${counter + 1}" />
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="home__footer" style="height: 50px;">
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div class="dialog overlay" id="addCategory">
            <a href="#" class="overlay-close"></a>
            <form action="add.category" method="post" class="dialog-body">
                <span class="dialog-body__title">Cập nhật loại hàng mới</span>
                <a class="dialog-close-btn" href="#">&times;</a>
                <div class="dialog-body__item">
                    <label class="dialog__icon">*</label>
                    <span>Category Name: </span>
                    <input class="input__light" type="text" name="cateName">
                </div>
                <div class="dialog-body__item">
                    <input style="margin-left: 10px;" type="reset" value="Reset" class="btn__white">
                    <input style="margin-left: 10px;" type="submit" value="Lưu & Hiển thị" class="btn btn__save">
                </div>
            </form>
        </div>
    </body>

    <script>
        function confirmDelete(productId) {
            // Sử dụng hộp thoại xác nhận
            var isConfirmed = confirm("Bạn có chắc chắn muốn xóa sản phẩm này không?" + productId);

            // Nếu người dùng chọn OK, chuyển đến servlet để xóa sản phẩm
            if (isConfirmed) {
                window.location.href = "deletecategory?cateid=" + productId;
            } else {
                // Người dùng chọn Cancel hoặc đóng hộp thoại xác nhận
                // Không làm gì cả hoặc có thể xử lý theo ý của bạn
            }
        }
        // Lấy thẻ cần ẩn đi sau một khoảng thời gian
        var messageElement = document.querySelector('.home__header-mesage-del');

        // Hàm ẩn thẻ sau một khoảng thời gian
        function hideMessage() {
            messageElement.style.display = 'none';
        }
        setTimeout(hideMessage, 4000); // 1000 milliseconds = 1 giây
    </script>

</html>
