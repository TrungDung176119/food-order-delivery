
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
                                ${ms}
                            </div>
                        </div>
                        <div class="profile__right-content" style="margin-top: 25px;">
                            <div class="profile__right-form">
                                <form action="address" method="post">
                                    <div class="profile__right-table" style="width: 100%;">
                                        <input type="hidden" name="addressid" value="${AccAddr.address_id}">
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
                                            <span class="address-title">Ghi chú: </span>
                                            <textarea style="width: 90%" name="note" class="input__light address__textarea">${AccAddr.note}</textarea>
                                        </div>
                                        <div class="address__right-btn">
                                            <span class="address-title"></span>
                                            <div class="flex address__right-input">
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
                            <div class="address-list">
                                <h3>Địa chỉ đã lưu</h3>
                                <c:forEach items="${listA}" var="o">
                                <div class="address-card">
                                    <div class="address-details">
                                        <p><strong>Tên người nhận:</strong> ${o.nickname}</p>
                                        <p><strong>Số điện thoại:</strong> ${o.phone_address}</p>
                                        <p><strong>Địa chỉ:</strong> ${o.address}</p>
                                        <p><strong>Địa chỉ cụ thể:</strong> ${o.note}</p>
                                        <p><strong>Loại địa chỉ:</strong> ${o.status}</p>
                                    </div>
                                    <div class="address-actions">
                                        <a href="address?address_id=${o.address_id}" class="btn__save">Sửa</a>
                                        <a href="address_delete?address_id=${o.address_id}" class="btn__light">Xóa</a>
                                    </div>
                                </div>
                                </c:forEach>
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
