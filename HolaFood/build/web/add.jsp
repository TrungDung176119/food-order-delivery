<%-- 
    Document   : add
    Created on : Oct 2, 2023, 8:22:45 PM
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
<script src="./assests/ckeditor/ckeditor.js"></script>

<body>
    <div class="app__manager">
        <jsp:include page="sidebar.jsp"/>

        <div class="home__container">
            <div class="grid">
                <div class="home__container-main">
                    <div class="home__main-header">
                        <div class="home__header-left">
                            <h4>Thông tin cơ bản</h4>
                            <p class="home__controll-msError">Không được để trống.</p>
                        </div>
                        <div class="home__header-right">
                            <a href="manager.jsp" class="btn btn__new"> Quay lại trang trước</a>
                        </div>

                    </div>
                    <div class="home__controller container" style="padding-bottom: 30px;">
                        <form class="home__controll-main" style=" margin-bottom: 50px;">
                            <div class="home__controll--cover">
                                <div class="home__controll-label">
                                    <span class="home__controll-icon">*</span>
                                    <span class="home__controll-input-name">Hình ảnh sản phẩm</span>
                                </div>
                                <div>
                                    <input class="home__controll-input-text" type="text"
                                        placeholder="Thêm liên kết hình ảnh">
                                    <!-- <p style="margin: 0; font-size: 1.2rem; color: red;" class="home__controll-msError"> Không được để trống.</p> -->
                                </div>
                            </div>
                            <div class="home__controll--cover">
                                <div class="home__controll-label">
                                    <span class="home__controll-icon">*</span>
                                    <span class="home__controll-input-name">Tiêu đề sản phẩm</span>
                                </div>
                                <div>
                                    <input class="home__controll-input-text" type="text"
                                        placeholder="Nhập vào tên sản phẩm">
                                    <!-- <p style=" margin: 0; font-size: 1.2rem; color: red;" class="home__controll-msError">Không được để trống.</p> -->
                                </div>
                            </div>
                            <div class="flex" style="align-items: center;">
                                <div class="home__controll--cover" style="margin-right: 40px;">
                                    <div class="home__controll-label">
                                        <span class="home__controll-icon">*</span>
                                        <span class="home__controll-input-name">Thể loại hàng</span>
                                    </div>
                                    <!-- <input class="home__controll-input-text" type="text" placeholder="Thể loại sản phẩm"> -->
                                    <select class="home__controll-input-text" style="width: 250px;" name="op__category">
                                        <option value="-1" selected disabled style="color: rgb(177, 174, 174); ">Vui
                                            lòng
                                            chọn</option>
                                        <option value="ip15">Apple Iphone 15</option>
                                        <option value="ip14">Apple Iphone 14</option>
                                        <option value="ip13">Apple Iphone 13</option>
                                        <option value="ip12">Apple Iphone 12</option>
                                        <option value="ip11">Apple Iphone 11</option>
                                    </select>
                                </div>
                                <div class="home__controll-number-input">
                                    <span class="home__controll-icon">*</span>
                                    <span class="home__controll-input-name">Nhập giờ mở cửa</span>
                                    <div class="select-input">
                                        <input class="home__controll-input-text" type="text"
                                        placeholder="Nhập vào tên sản phẩm">
                                    </div>
                                </div>
                            </div>


                            <div class="home__controll--cover">
                                <div class="home__controll-label">
                                    <span class="home__controll-icon">*</span>
                                    <span class="home__controll-input-name">Mô tả sản phẩm</span>
                                </div>
                                <div>
                                    <textarea name="editor" class="home__controll-input-text text__describe ckeditor"
                                        type="text" placeholder="Thêm mô tả sản phẩm"></textarea>
                                    <!-- <p style=" margin: 0; font-size: 1.2rem; color: red;" class="home__controll-msError">Không được để trống.</p> -->
                                </div>
                            </div>
                            <div class="home__controll-number">
                                <div class="home__controll-number-input" style="margin-right: 40px;">
                                    <span class="home__controll-icon">*</span>
                                    <span class="home__controll-input-name">Giá bán</span>
                                    <input class="home__controll-number-text" type="number" min="0"
                                        placeholder="Nhập vào">
                                </div>
                                <div class="home__controll-number-input">
                                    <span class="home__controll-icon">*</span>
                                    <span class="home__controll-input-name">Kho hàng</span>
                                    <input class="home__controll-number-text" type="number" min="0"
                                        placeholder="Nhập vào">
                                </div>
                            </div>

                            <div class="home__controll-number">
                                <div class="home__controll-number-input" style="margin-right: 40px;">
                                    <span class="home__controll-icon">*</span>
                                    <span class="home__controll-input-name">Status</span>
                                    <select title="select" class="home__controll-input-text" style="width: 250px;"
                                        name="op__status">
                                        <option value="-1" selected disabled style="color: rgb(177, 174, 174); ">Vui
                                            lòng chọn</option>
                                        <option value="favor">Yêu thích</option>
                                        <option value="sale">Sale</option>
                                        <option value="coming">Sắp về hàng</option>
                                        <option value="promotion">Khuyến mãi</option>
                                    </select>
                                </div>
                                <div class="home__controll--cover">
                                    <div class="home__controll-label">
                                        <span class="home__controll-icon">*</span>
                                        <span class="home__controll-input-name">Địa điểm bán</span>
                                    </div>
                                    <div>
                                        <input class="home__controll-number-text" type="text" placeholder="Nhập vào">
                                        <!-- <p style=" margin: 0; font-size: 1.2rem; color: red;" class="home__controll-msError">Không được để trống.</p> -->
                                    </div>
                                </div>

                            </div>

                            <div class="home__controll-button">
                                <div class="home__controll-button-left">
                                    <!-- space  -->
                                </div>
                                <div class="home__controll-button-right">
                                    <button class="btn btn__action btn__action-type" type="submit">Hủy</button>
                                    <button class="btn btn__action btn__action-type" type="reset">Reset</button>
                                    <button class="btn btn__action btn__action-submit" type="submit"> Thêm</button>
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