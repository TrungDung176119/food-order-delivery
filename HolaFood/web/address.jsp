
<%-- 
    Document   : profile
    Created on : Oct 6, 2023, 8:56:51 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HolaFood</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <!-- css -->
    <link rel="stylesheet" href="./assests/css.profile/address.css">

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
    <div class="app">
        <jsp:include page="menu_home.jsp"></jsp:include> 

            <!-- container profile -->
            <div class="container__profile ">
                <div class="grid">
                    <div class="grid__row row">
                    <jsp:include page="profile_menu.jsp"></jsp:include> 

                        <div class="profile__right grid__column-980">
                            <div class="profile__right-head">
                                <div>
                                    <h2>Địa chỉ của tôi</h2>
                                    <span>Cập nhật địa chỉ nhận hàng của bạn tại đây</span>
                                </div>
                                <div class="profile__right-message ">
                                ${msSuccess}
                            </div>
                        </div>
                        <div class="profile__right-content" style="margin-top: 25px;">
                            <div class="profile__right-form">
                                <form action="address" method="post">
                                    <div class="profile__right-table" style="width: 100%;">
                                        <div class="address__right-phone_name">
                                            <div style="display: flex;">
                                                <span class="address-title"> Họ và tên: </span>
                                                <input name="nickname" value="${AccAddr.nickname}" class="input__light address-input__name" type="text" placeholder="Tên người nhận">
                                            </div>
                                            <div style="display: flex;">
                                                <span class="address-title">Số điện thoại: </span>
                                                <input name="phone" value="${AccAddr.phone_address}" class="input__light address-input__name" type="number" min="0">
                                            </div>
                                        </div>
                                        <div class="address__right-content">
                                            <span class="address-title">Địa chỉ: </span>
                                            <input name="addr" value="${AccAddr.address}" class="input__light " type="text">
                                        </div>
                                        <div class="address__right-content">
                                            <span class="address-title">Địa chỉ cụ thể: </span>
                                            <textarea style="width: 90%" name="note" class="input__light address__textarea">${AccAddr.note}</textarea>
                                        </div>
                                        <div class="address__right-btn">
                                            <span class="address-title"></span>
                                            <div class="flex address__right-input">
<!--                                                <input name="home" class="btn__light" value="Nhà Riêng" ${AccAddr.status == 'Nhà Riêng' ? 'checked' : ''} onclick="updateAriaChecked(this)"  />
                                                <input name="office" class="btn__light" value="Văn Phòng" ${AccAddr.status == 'Văn Phòng' ? 'checked' : ''} onclick="updateAriaChecked(this)"  />
                                                -->
                                                <div class="flex">
                                                    <input type="radio" name="status" class="address-input__status" value="Nhà Riêng" ${AccAddr.status == 'Nhà Riêng' ? 'checked' : ''} /> 
                                                    <span>Nhà Riêng</span>
                                                </div>
                                                <div class="flex">
                                                    <input type="radio" name="status" class="address-input__status" value="Văn Phòng" ${AccAddr.status == 'Văn Phòng' ? 'checked' : ''}/> 
                                                    <span>Văn Phòng</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="" style="text-align: right;" >
                                            <button type="reset" class="btn__light" style="margin: 0 12px 50px 0;">Hủy</button>
                                            <button type="submit" class="btn__save"> Hoàn thành</button>
                                        </div>
                                    </div>
                                    <input type="hidden" id="selectedOption" name="selectedOption" value="">

                                </form>
                            </div>
                            <div class="profile__right-pic">
                                <img src="https://yt3.ggpht.com/yti/ADpuP3MEBtPK2w3PD74lGPnowgesuAR_VQYgWY4u0_NPcw=s88-c-k-c0x00ffffff-no-rj"
                                     alt="">
                                <button class="btn__light" type="submit">Chọn Ảnh</button>
                                <span>Dụng lượng file tối đa 1 MB</span>
                                <span> Định dạng:.JPEG, .PNG &nbsp;&nbsp;&nbsp;&nbsp; </span>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp"></jsp:include>

    </div>
</body>
<script>
    function updateAriaChecked(element) {
        // Lặp qua tất cả các thẻ input trong cùng tên
        var inputs = document.querySelectorAll('input[name="' + element.name + '"]');
        for (var i = 0; i < inputs.length; i++) {
            // Đặt aria-checked="false" cho tất cả các thẻ input
            inputs[i].setAttribute('aria-checked', 'false');
        }

        // Đặt aria-checked="true" cho thẻ input được chọn
        element.setAttribute('aria-checked', 'true');

        // Lấy giá trị của nút radio được chọn và lưu vào biến hoặc trường ẩn
        var selectedValue = element.value;
        document.getElementById('selectedOption').value = selectedValue;
    }

</script>

</html>
