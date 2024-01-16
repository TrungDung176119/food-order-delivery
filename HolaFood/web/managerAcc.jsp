<%-- 
    Document   : managerAcc
    Created on : Oct 14, 2023, 8:36:47 AM
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
        <link rel="stylesheet" href="./assests/css.manager/managerAcc.css">
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
                                <h4>Tổng ${total} tài khoản </h4>
                            </div>

                            <div class="home__header-mesage-del">
                                <span>${ms}</span>
                            </div>
                            <div class="home__header-right">
                                <a href="#add" class="btn btn__new"> + Thêm 1 tài khoản mới </a>
                                <!--<a href="managerCate.jsp" class="btn__light"> Quay lại trang trước</a>-->
                            </div>
                        </div>
                        <div class="home__controller">
                            <div class="home__controll-main" >
                                <table style="max-width: 100%; width: 100%;">
                                    <tr class="table__product-title">
                                        <th style="max-width: 30px; color: var(--color-infor)">
                                            <div class="table__account-id"> STT </div>
                                        </th>
                                        <th class="cover__table-id">
                                            <div class="table__account-id"> Mã tài khoản</div>
                                        </th>
                                        <th>
                                            <div class="table__account-name" >Tên người sử dụng</div>
                                        </th>
                                        <th>
                                            <div class="table__account-password" >Mật khẩu</div>
                                        </th>
                                        <th>
                                            <div class="table__account-admin" >RoleId</div>
                                        </th>
                                        <th>
                                            <div class="table__account-block" >isBlock</div>
                                        </th>
                                         <th>
                                            <div class="table__account-block" >Lượng Xu</div>
                                        </th>
                                        <th>
                                            <div class="table__account-block" >Ngày đăng ký</div>
                                        </th>
                                        <th>
                                            <div class="table__account-action" >Thao tác </div>
                                        </th>
                                    </tr>
                                    <c:set var="counter" value="1" />
                                    <c:forEach items="${listA}" var="o">
                                        <tr class="table__account">
                                            <th>
                                                <div class="table__account-cnt" >${counter}</div>
                                            </th>
                                            <th class="cover__table-id">
                                                <div class="table__account-id" style="padding-left: 5px;">${o.accID}</div>
                                            </th>
                                            <th>
                                                <div class="table__account-name">${o.username}</div>
                                            </th>
                                            <th>
                                                <div class="table__account-password">${o.password}</div>
                                            </th>
                                            <th>
                                                <div class="table__account-admin">${o.roleid}</div>
                                            </th>
                                            <th>
                                                <div class="table__account-block">${o.isBlock}</div>
                                            </th>
                                             <th>
                                                <div class="table__account-block">${o.coin}</div>
                                            </th> 
                                            <th>
                                                <div class="table__account-block">${o.createTime}</div>
                                            </th>
                                            <th>
                                                <div class="table__account-action">
                                                    <a class="" href="update.account?accID=${o.accID}" >Update</a>
                                                    <a href="#" onclick="doDelete('${o.accID}')">Delete</a>
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
                    <!-- space  -->
                </div>
            </div>
        </div>
    </div>

    <!--modal-->
    <div class="dialog overlay" id="add">
        <a href="#" class="overlay-close"></a>
        <form class="dialog-body" action="add.account" method="post">
            <span class="dialog-body__title">Cập nhật tài khoản</span>
            <a class="dialog-close-btn" href="#">&times;</a>

            <div class="dialog-body__item">
                <label class="dialog__icon">*</label>
                <span>Tên tài khoản: </span>
                <input required class="input__light"  type="text"  name="username" placeholder="Nhập vào">
            </div>
            <div class="dialog-body__item">
                <label class="dialog__icon">*</label>
                <span>Mật khẩu: </span>
                <input required class="input__light"  type="text" name="password" placeholder="Nhập vào">
            </div>
            <div class="dialog-body__item">
                <label class="dialog__icon">*</label>
                <span>isAdmin: </span>
                <input required class="input__radio" type="radio" name="isAdmin" value="1"  >Admin &nbsp;&nbsp;&nbsp;&nbsp;
                <input class="input__radio" type="radio" name="isAdmin" value="0" > None
            </div>
            <div class="dialog-body__item">
                <label class="dialog__icon">*</label>
                <span>isBlock: </span>
                <input required class="input__radio" type="radio" name="isBlock" value="1" >Yes  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                <input class="input__radio" type="radio" name="isBlock" value="0" > No
            </div>
            <div class="dialog-body__item" style="margin-top: 20px;">
                <span></span>
                <input style="margin-left: 10px;" type="reset" value="Reset" class="btn__white">
                <input style="margin-left: 10px;" type="submit" value="Lưu & Hiển thị" class="btn btn__save">
            </div>
        </form>

    </div>
</body>
<script type="text/javascript">

    function doUpdate(id) {
        if (confirm("Are u sure to update this Product with id = " + id)) {
            window.location = "add.account?pid=" + id;
        }
    }


</script>

</html>