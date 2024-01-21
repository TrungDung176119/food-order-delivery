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
    <title>Hola Food</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <!-- css -->
    <link rel="stylesheet" href="./assests/css.profile/profile.css">

    <!-- font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

    <!-- icon title -->
    <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.png">
    <!-- boostrap -->
    <link rel="stylesheet" href="./assests/boostrap/bootstrap.css">
    

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
                                    <h2>Hồ Sơ Của Tôi</h2>
                                    <span>Quản lý thông tin hồ sơ để bảo mật tài khoản</span>
                                </div>
                                <div class="profile__right-message ">
                                ${ms}
                            </div>
                        </div>
                        <div class="profile__right-content" style="margin-top: 25px;">
                            <div class="profile__right-form">
                                <form action="profile" method="post">
                                    <table class="profile__right-table" style="width: 100%;">
                                        <tr>
                                            <td>
                                                <label for="">Tên đăng nhập</label>
                                                <span style="padding-left: 2px">${sessionScope.acc.username}</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="profile__right-table__name">
                                                <label>Nickname </label>
                                                <input name="nickname" value="${AccDetail.nickname}" class="profile__right-input" type="text" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="profile__right-table__email">
                                                <label>Email</label>
                                                <input name="email" value="${AccDetail.email}" class="profile__right-input" placeholder="... @gmail.com" type="text">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="profile__right-table__phone">
                                                <label>Số điện thoại</label>
                                                <input name="phone" value="${AccDetail.phone}" class="profile__right-input" type="number">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="profile__right-table__gender" style="display: flex; ">
                                                <label>Giới tính</label>
                                                <input  type="radio" name="gender" value="0"  ${AccDetail.gender == '0' ? 'checked' : ''}  />&nbsp;Nam
                                                &nbsp;&nbsp; <input type="radio" name="gender" value="1"  ${AccDetail.gender == '1' ? 'checked' : ''}  />&nbsp;Nữ
                                                &nbsp;&nbsp; <input type="radio" name="gender" value="2" ${AccDetail.gender == '2' ? 'checked' : ''}  />&nbsp;Khác
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="profile__right-table__date">
                                                <label>Ngày sinh</label>
                                                <input  name="dob" value="${AccDetail.dob}" style="max-width: 250px;" class="profile__right-input" type="date" >
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label></label>
                                                <button class="btn__save" type="submit" name="action" value="update_profile">Lưu</button>
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </div>

                            <div class="profile__right-pic" id="imageForm" enctype="multipart/form-data">
                                <input type="file" id="imageInput" accept=".jpeg, .jpg, .png" style="display: none;">
                                <img id="selectedImage" src="https://yt3.ggpht.com/yti/ADpuP3MEBtPK2w3PD74lGPnowgesuAR_VQYgWY4u0_NPcw=s88-c-k-c0x00ffffff-no-rj" alt="">
                                <button class="btn__light" style="min-width: 84px;box-shadow: 0 1px 4px 0 rgba(0,0,0,0.09);" type="button" onclick="document.getElementById('imageInput').click();">Chọn Ảnh</button>
                                <span>Dung lượng file tối đa 1 MB</span>
                                <span>Định dạng: .JPEG, .PNG</span>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>

    </div>
</body>
<script type="text/javascript">
    // Lắng nghe sự kiện khi người dùng chọn tệp ảnh
    document.getElementById('imageInput').addEventListener('change', function () {
        const selectedImage = document.getElementById('selectedImage');
        const ngoaiFormImage = document.getElementById('ngoaiFormImage');
        const ngoaiFormImage2 = document.getElementById('ngoaiFormImage2');

        const fileInput = this;

        if (fileInput.files && fileInput.files[0]) {
            const reader = new FileReader();

            reader.onload = function (e) {
                selectedImage.src = e.target.result;
                ngoaiFormImage.src = e.target.result; // Cập nhật thẻ img ngoài form
                ngoaiFormImage2.src = e.target.result; // Cập nhật thẻ img ngoài form

            };

            reader.readAsDataURL(fileInput.files[0]);
        }
    });
</script>


</html>
