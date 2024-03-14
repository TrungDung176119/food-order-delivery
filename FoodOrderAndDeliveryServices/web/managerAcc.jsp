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
        <title>HolaFood</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css.manager/manager.css">
        <link rel="stylesheet" href="./assests/css.manager/managerAcc.css">

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
                                <h4>Tổng ${total} tài khoản </h4>
                            </div>

                            <div class="home__header-mesage-del">
                                <span>${ms}</span>
                            </div>
                            <div class="home__header-right">
                                <a href="updateAcc" class="btn btn__new"> Thêm 1 tài khoản mới </a>
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
                                            <div class="table__account-block" >Đã bị Block</div>
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
                                            <th>
                                                <div class="table__account-name">${o.username}</div>
                                            </th>
                                            <th>
                                                <div class="table__account-password">${o.password}</div>
                                            </th>
                                            <th>
                                                <div class="table__account-admin" style="color:
                                                     <c:choose>
                                                         <c:when test="${o.roleid eq 1}">green</c:when>
                                                         <c:when test="${o.roleid eq 2}">blue</c:when>
                                                         <c:when test="${o.roleid eq 3}">red</c:when>
                                                         <c:otherwise>black</c:otherwise>
                                                     </c:choose>
                                                     ;">
                                                    <c:choose>
                                                        <c:when test="${o.roleid eq 1}">
                                                            Customer
                                                        </c:when>
                                                        <c:when test="${o.roleid eq 2}">
                                                            Seller
                                                        </c:when>
                                                        <c:when test="${o.roleid eq 3}">
                                                            Admin
                                                        </c:when>
                                                        <c:otherwise>
                                                            Unknown Role
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </th>
                                            <th>
                                                <div class="table__account-block" style="color: ${o.isBlock eq 1 ? 'red' : '#24bb00'};">
                                                    <c:choose>
                                                        <c:when test="${o.isBlock eq 1}">
                                                            Có
                                                        </c:when>
                                                        <c:when test="${o.isBlock eq 0}">
                                                            Không
                                                        </c:when>
                                                        <c:otherwise>
                                                            Unknown State
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </th>
                                            <th>
                                                <div class="table__account-block">${o.coin}</div>
                                            </th> 
                                            <th>
                                                <div class="table__account-block">${o.createTime}</div>
                                            </th>
                                            <th>
                                                <div class="table__account-action">
                                                    <a class="" href="updateAcc?accID=${o.accid}">Update</a>                                                  
                                                </div>

                                                <div class="table__account-action">
                                                    <a onclick="doBlock('${o.accid}')">Block</a>                                                  
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
    <script type="text/javascript">
        function doBlock(id) {
            if (confirm("Are you sure you want to block this Account with id = " + id)) {
                window.location = "blockAcc?accID=" + id;
            }
            return false;
        }
        // Lấy thẻ cần ẩn đi sau một khoảng thời gian
        var messageElement = document.querySelector('.home__header-mesage-del');

        // Hàm ẩn thẻ sau một khoảng thời gian
        function hideMessage() {
            messageElement.style.display = 'none';
        }
        setTimeout(hideMessage, 6500); // 1000 milliseconds = 1 giây

    </script>
</body>
</html>