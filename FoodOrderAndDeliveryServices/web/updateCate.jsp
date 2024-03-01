<%-- 
    Document   : updateCate
    Created on : Oct 16, 2023, 9:00:50 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Suga</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">   
        <link rel="stylesheet" href="./assests/css.manager/manager.css">
        <link rel="stylesheet" href="./assests/css.manager/updateAcc.css">

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
                                <h4>Thông tin cơ bản</h4>
                            </div>
                            <c:if test="${msUpdate != null}">
                                <div class="home__header-mesage-del">
                                    <span>${msUpdate}</span>
                                </div>    
                            </c:if>
                            <div class="home__header-right">
                                <a href="add.category" class="btn btn__new"> Quay lại trang trước</a>
                            </div>

                        </div>
                        <div class="home__controller container" style="padding-bottom: 30px;">
                            <div  class="home__controll-main" >
                                <form action="updatecategory" method="post" style="margin-left: 30px;">
                                    <div style="display: none">
                                        <input name="cateid" value="${cate.id}" />
                                    </div>
                                    <div class="update__account">
                                        <label class="account-icon">*</label>
                                        <span>Mã thể loại: </span>
                                        <span style="color: black">${cate.id}</span>
                                    </div>
                                    <div class="update__account">
                                        <label class="account-icon">*</label>
                                        <span>Tên thể loại: </span>
                                        <input required class="input__light" value="${cate.name}"  type="text" name="catename" placeholder="Nhập vào">
                                    </div>
                                    <div class="update__account" style="margin-top: 20px;">
                                        <span></span>
                                        <input style="margin-left: 10px;" type="reset" value="Reset" class="btn__white">
                                        <button style="margin-left: 10px;" type="submit" class="btn btn__save">Lưu & Hiển thị</button>
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