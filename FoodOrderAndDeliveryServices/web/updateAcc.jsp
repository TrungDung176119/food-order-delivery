<%-- 
    Document   : updateAcc
    Created on : Oct 14, 2023, 11:35:45 PM
    Author     : admin
--%>

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
        <link rel="stylesheet" href="./assests/css.manager/updateAcc.css">
        <link rel="stylesheet" href="./assests/css.manager/sidebar.css">

        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.png">
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
                                <h4>Thông tin cơ bản</h4>
                            </div>
                            <div class="home__header-right">
                                <a href="managerAcc" class="btn btn__new"> Quay lại trang trước</a>
                            </div>
                        </div>
                        <div class="home__controller container" style="padding-bottom: 30px;">
                            <div class="home__controll-main" style=" margin-bottom: 50px;">
                                <form action="updateAcc" method="post"  style="margin-left: 30px;">
                                    <input type="hidden" name="accid" value="${acca.accid}" />
                                    <div class="update__account">
                                        <label class="account-icon">*</label>
                                        <span>Tên tài khoản: </span>
                                        <input title="Không thể thay đổi tên người dùng" required class="input__light" value="${acca.username}" ${acca.username != null ? 'readonly': ''} type="text" name="username" placeholder="Nhập vào username">
                                    </div>
                                    <div class="update__account">
                                        <label class="account-icon">*</label>
                                        <span>Mật khẩu: </span>
                                        <input required class="input__light" value="${acca.password}" type="text" name="password" placeholder="Nhập vào password">
                                    </div>
                                    <div class="update__account">
                                        <label class="account-icon">*</label>
                                        <span>Role: </span>
                                        <input required class="input__radio" ${acca.roleid == 2 ? 'checked': ''} type="radio" name="roleid" value="2">Seller &nbsp;&nbsp;&nbsp;&nbsp;
                                        <input class="input__radio" ${acca.roleid == 1 ? 'checked': ''} type="radio" name="roleid" value="1"> Customer
                                    </div>
                                    <div class="update__account">
                                        <label class="account-icon">*</label>
                                        <span>isBlock: </span>
                                        <input required class="input__radio" ${acca.isBlock == 1 ? 'checked': ''} type="radio" name="isBlock" value="1">Yes &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <input class="input__radio" ${acca.isBlock == 0 ? 'checked': ''} type="radio" name="isBlock" value="0"> No
                                    </div>
                                    <div class="update__account" style="margin-top: 20px;">
                                        <span></span>
                                        <input style="margin-left: 10px;" type="reset" value="Reset" class="btn__white">
                                        <input style="margin-left: 10px;" type="submit" value="Lưu" class="btn btn__save">
                                    </div>
                                </form>
                            </div> 
                        </div>

                    </div>
                    <div class="home__footer" style="height: 50px;">

                    </div>
                </div>
            </div>
        </div>
    </body>

</html>