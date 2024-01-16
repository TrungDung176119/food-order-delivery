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
        <title>Suga | Quản lý sản phẩm | Cập nhật </title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css/manager.css">
        <link rel="stylesheet" href="./assests/css/add.css">

        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.ico">
        <!-- boostrap -->
        <link href="./assests/boostrap/bootstrap.css" rel="stylesheet">
        <script src="ckeditor/ckeditor.js"></script>
    </head>

    <body>
        <div class="app__manager">
            <div class="container__heading">
                <div class="header__manager">
                    <div class="header__manager-left">
                        <a href="home" class="header__manager-item">
                            <i class="fa-brands fa-apple"></i>
                            <span class="header__manager-item__name">Suga</span>
                        </a>
                        <span class="header__manager-title">Cập nhật sản phẩm</span>
                    </div>
                    <div class="header__manager-right">
                        <div class="header__manager-user">
                            <img class="header__manager-user-img"
                                 src="https://yt3.ggpht.com/yti/ADpuP3MEBtPK2w3PD74lGPnowgesuAR_VQYgWY4u0_NPcw=s88-c-k-c0x00ffffff-no-rj" alt="">
                            <c:if test="${(sessionScope.AccDetail.nickname == null) || (sessionScope.AccDetail.nickname == '')}">
                                <span class="header__manager-user-name"> ${sessionScope.acc.username}</span>
                            </c:if>
                            <c:if test="${sessionScope.AccDetail.nickname != null}">
                                <span class="header__manager-user-name">${sessionScope.AccDetail.nickname}</span>
                            </c:if>
                        </div>
                        <ul class="header__manager-user-menu">
                            <li class="header__manager-user-item">
                                <a href="home" style="padding-top: 10px;">Trang chủ</a>
                            </li>
                            <li class="header__manager-user-item">
                                <a href="profile">Tài khoản của tôi</a>
                            </li>
                            <li class="header__manager-user-item">
                                <a href="logout">Đăng xuất</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="home__container">
                <div class="grid">
                    <div class="home__container-main">
                        <div class="home__main-header">
                            <div class="home__header-left">
                                <h4>Thông tin cơ bản</h4>
                                <p class="home__controll-msError">${message__error}</p>
                            </div>
                            <div class="home__header-right">
                                <a href="manager" class="btn btn__new"> Quay lại trang trước</a>
                            </div>
                        </div>
                        <div class="home__controller container" style="padding-bottom: 30px;">
                            <form action="update" method="post" class="home__controll-main" style=" margin-bottom: 50px;">
                                <div style="display: none">
                                    <input name="id" value="${detail.id}" />
                                </div> 
                                <div class="home__controll-input">
                                    <span class="home__controll-icon">*</span>
                                    <span class="home__controll-input-name">Mã sản phẩm</span>
                                    <span>${detail.id}</span>
                                </div>
                                <div class="home__controll-input">
                                    <span class="home__controll-icon">*</span>
                                    <span class="home__controll-input-name">Hình ảnh sản phẩm</span>
                                    <input name="image" value="${detail.image}" required class="home__controll-input-text" type="text" placeholder="Thêm liên kết hình ảnh">
                                </div>
                                <div class="home__controll-input">
                                    <div class="home__controll-label" style="  display: flex;line-height: 40px;">
                                        <span class="home__controll-icon">*</span>
                                        <span class="home__controll-input-name">Hình ảnh chi tiết</span>
                                    </div>
                                    <div  style="width: 100%; display: flex">
                                        <input required name="detail_img1" value="${pd.detail_img1}" class="home__controll-input-text" style="width: 28%; margin-right: 20px;" type="text"placeholder="Thêm hình ảnh chi tiết 1">
                                        <input required name="detail_img2" value="${pd.detail_img2}" class="home__controll-input-text" style="width: 28%; margin-right: 20px;" type="text" placeholder="Thêm hình ảnh chi tiết 2">
                                        <input required name="detail_img3" value="${pd.detail_img3}" class="home__controll-input-text" style="width: 28%;" type="text" placeholder="Thêm hình ảnh chi tiết 3">
                                    </div>
                                </div>
                                <div class="home__controll-input">
                                    <span class="home__controll-icon">*</span>
                                    <span class="home__controll-input-name">Tiêu đề sản phẩm</span>
                                    <input name="title" value="${detail.title}" required class="home__controll-input-text" type="text" placeholder="Nhập tiêu đề">
                                </div>
                                <div class="home__controll-number"  >
                                    <div class="home__controll-input" style="margin:0 40px 0 0;">
                                        <span class="home__controll-icon">*</span>
                                        <span class="home__controll-input-name">Thể loại hàng</span>
                                        <select required name="category" class="home__controll-input-text" style="width: 250px;" >
                                            <option value="-1" selected disabled style="color: rgb(177, 174, 174); ">Vui lòng chọn</option>
                                            <c:forEach items="${listC}" var="o">
                                                <option value="${o.cid}" >${o.cname}</option>
                                            </c:forEach>
                                        </select> 
                                    </div>
                                    <div class="home__controll-number-input">
                                        <span class="home__controll-icon">*</span>
                                        <span class="home__controll-input-name">Màu sắc</span>
                                        <div class="select-input">
                                            <span class="select-input__label select-color-label flex">Vui lòng chọn
                                                <i class="select-input__icon fas fa-angle-down" style="font-size: 1.3rem"></i>
                                            </span>
                                            <ul class="select-input__list select-color">
                                                <jsp:useBean id="s" class="dal.CartDAO" >
                                                    <c:forEach items="${s.allColorProduct}" var="s">
                                                        <div class="select__input-color">
                                                            <input type="checkbox" name="color" value="${s.color_id}" class="input_checkbox-color" />
                                                            <span> ${s.color_name}</span>
                                                        </div>
                                                    </c:forEach>
                                                </jsp:useBean>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="home__controll-input">
                                    <span class="home__controll-icon">*</span>
                                    <span class="home__controll-input-name">Mô tả sản phẩm</span>
                                    <textarea required  name="describe" class="home__controll-input-text text__describe ckeditor" type="text" placeholder="Thêm mô tả sản phẩm">${detail.describe}</textarea>
                                </div>
                                <div class="home__controll-number" style="margin-bottom: 30px;">
                                    <div class="home__controll-number-input" style="margin-right: 40px;">
                                        <span class="home__controll-icon">*</span>
                                        <span class="home__controll-input-name">Giá bán</span>
                                        <input required name="price" value="${detail.currentPrice}" class="home__controll-number-text" type="number" min="0" placeholder="Nhập vào">
                                    </div>
                                    <div class="home__controll-number-input">
                                        <span class="home__controll-icon">*</span>
                                        <span class="home__controll-input-name">Kho hàng</span>
                                        <input required name="stock"  value="${detail.quantity_in_stock}" class="home__controll-number-text" type="number" min="0" placeholder="Nhập vào">
                                    </div>
                                </div>

                                <div class="home__controll-number">
                                    <div class="home__controll-number-input" style="margin-right: 40px;">
                                        <span class="home__controll-icon">*</span>
                                        <span class="home__controll-input-name">Status</span>
                                        <!-- <input class="home__controll-number-text" type="text" placeholder="Nhập vào"> -->
                                        <select required name="status" class="home__controll-input-text" style="width: 250px;" >
                                            <option value="-1" selected disabled style="color: rgb(177, 174, 174); ">Vui lòng chọn</option>
                                            <option value="Yêu thích" >Yêu thích</option>
                                            <option value="Yêu thích+" >Yêu thích+</option>
                                            <option value="Sale" >Sale</option>
                                            <option value="Sắp về hàng" >Sắp về hàng</option>
                                            <option value="Khuyến mãi" >Khuyến mãi</option>
                                        </select> 
                                    </div>
                                    <div class="home__controll-number-input">
                                        <span class="home__controll-icon">*</span>
                                        <span class="home__controll-input-name">Địa điểm bán</span>
                                        <input required name="address" value="${detail.origin}" class="home__controll-number-text" type="text" placeholder="Nhập vào">
                                    </div>
                                </div>

                                <div class="home__controll-button">
                                    <div class="home__controll-button-left">
                                        <!-- space  -->
                                    </div>
                                    <div class="home__controll-button-right">
                                        <a class="btn btn__action btn__action-type" href="manager" >Hủy</a>
                                        <!--<button class="btn btn__action btn__action-type" type="reset">Reset</button>-->
                                        <button class="btn btn__action btn__action-submit" type="submit"> Lưu & Hiển thị</button>
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
    <script src="assests/js/Jquery.js"></script>
    <script src="assests/js/bootstrap.min.js"></script>
    <script src="assests/js/main.js"></script>

</html>