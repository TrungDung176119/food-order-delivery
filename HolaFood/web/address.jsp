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
    <link rel="stylesheet" href="./assests/css.profile/address.css">
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
                                    <h2>Địa chỉ của tôi</h2>
                                    <span>Cập nhật địa chỉ nhận hàng của bạn tại đây</span>
                                </div>
                                <div class="profile__right-message ">
                                ${ms}
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
                                            <span class="address-title">Lưu ý khi giao hàng: </span>
                                            <textarea style="width: 90%" name="note" class="input__light address__textarea">${AccAddr.note}</textarea>
                                        </div>
                                        <div class="address__right-btn">
                                            <span class="address-title"></span>
                                            <div>
                                                <label>
                                                    <input type="radio" name="status" value="Nhà Riêng" ${AccAddr.status == 'Nhà Riêng' ? 'checked' : ''}>
                                                    Nhà Riêng
                                                </label>
                                                <label>
                                                    <input type="radio" name="status" value="Văn Phòng" ${AccAddr.status == 'Văn Phòng' ? 'checked' : ''}>
                                                    Văn Phòng
                                                </label>
                                            </div>
                                        </div>
                                        <div class="" style="text-align: right;" >
                                            <button type="reset" class="btn__light" style="margin: 0 12px 50px 0;">Hủy</button>
                                            <button type="submit" class="btn__save"> Hoàn thành</button>
                                        </div>
                                        <input type ="text" name="addressid" value ="${AccAddr.address_id}" readonly="">
                                    </div>
                                </form>
                            </div>
                            <!-- Add this section to your address.jsp file -->
                            <div class="profile__right-address-list">
                                <!-- Each box represents an address -->
                                <c:if test="${not empty addresses}">
                                    <c:forEach var="address" items="${addresses}">
                                        <div class="profile__right-address-box">
                                            <div class="profile__right-address-info">
                                                <p><strong>Họ và tên:</strong> ${address.nickname}</p>
                                                <p><strong>Số điện thoại:</strong> ${address.phone_address}</p>
                                                <p><strong>Địa chỉ:</strong> ${address.address}</p>
                                                <p><strong>Lưu ý khi giao hàng:</strong> ${address.note}</p>
                                                <p><strong>Loại địa chỉ:</strong> ${address.status}</p>
                                            </div>
                                            <div class="profile__right-address-actions">
                                                <!-- Edit icon as a link to the edit servlet -->
                                                <a href="updateaddress?address_id=${address.address_id}">
                                                    <span class="edit-icon">&#9998;</span>
                                                </a>
                                                <!-- Delete icon as a link to the delete servlet -->
                                                <a href="deleteaddress?address_id=${address.address_id}">
                                                    <span class="delete-icon">&#128465;</span>
                                                </a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty addresses}">
                                    <p>No addresses found.</p>
                                </c:if>
                                <!-- Repeat this block for each address -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp"></jsp:include>

    </div>
</body>
</html>
