<%-- 
    Document   : manageSeller
    Created on : Feb 4, 2024, 9:15:13 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>HolaFood | Quản Lý Seller</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css/main.css">
        <link rel="stylesheet" href="./assests/css.manager/manager.css">
        <link rel="stylesheet" href="./assests/css/order.css">
        <link rel="stylesheet" href="./assests/css/common.css">
        <link rel="stylesheet" href="./assests/css.manager/manageSeller.css">

        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.png">
        <!-- boostrap -->
        <link href="./assests/boostrap/bootstrap.css" rel="stylesheet">

        <style>
/*            .manage__order-btn {
                border: none;
                background-color: transparent;
                padding: 0;
                color: var(--color-link);
                cursor: pointer;
            }
            .manage__order-btn:hover {
                color: var(--primary-color);
            }*/
        </style>
    </head>
    <body>
        <div class="app__manager">
            <jsp:include page="sidebar.jsp" />
            <div class="home__container">
                <div class="grid">
                    <div class="home__container-main" style="margin-top: 44px; padding-top:1px ;">
                        <div class="manage__seller-menu-tabs">
                            <nav class="manage__seller-menu flex ">
                                <a href="managerSeller?is_active=2" class="manage__seller-menu-link ${tag_page == 2? "manage__seller-menu-link--active":""}">
                                    <span class="manage__seller-menu-name">Tất cả</span>
                                </a>
                                <a href="managerSeller?is_active=1" class="manage__seller-menu-link ${tag_page == 1? "manage__seller-menu-link--active":""}">
                                    <span class="manage__seller-menu-name">Đang hoạt động (${active})</span>
                                </a>
                                <a href="managerSeller?is_active=0" class="manage__seller-menu-link ${tag_page == 0? "manage__seller-menu-link--active":""}">
                                    <span class="manage__seller-menu-name">Chờ duyệt (${unactive})</span>
                                </a>
                                <a href="managerSeller?is_active=-1" class="manage__seller-menu-link ${tag_page == -1? "manage__seller-menu-link--active":""}">
                                    <span class="manage__seller-menu-name">Vi phạm (${violate})</span>
                                </a>
                                <a href="managerSeller?is_active=-2" class="manage__seller-menu-link ${tag_page == -2? "manage__seller-menu-link--active":""}">
                                    <span class="manage__seller-menu-name">Đã ẩn (${hidden})</span>
                                </a>
                            </nav>
                        </div>
                        <form action="" method="get" class="order-menu__filter" style="margin: 20px 26px;">
                            <button type="submit" name="action" value="filter" style="background: transparent; border: none" >
                                <i class="fa-solid fa-magnifying-glass" style="margin-top: 0"></i>
                            </button>
                            <input type="text" name="keyword" class="order-menu__filter-input" placeholder="Bạn có thể tìm kiếm theo ID Shop, tên Shop hoặc Số điện thoại">
                        </form>

                        <div class="home__main-header" style="padding: 0;">
                            <div class="manage__seller-head-title">
                                <h4>Tổng 20 cửa hàng </h4>
                            </div>
                            <c:if test="${ms != null}">
                                <div class="home__header-mesage-del">
                                    <span>${ms}</span>
                                </div>
                            </c:if>
                             <div class="home__header-right">
                                <a href="#" class="btn btn__new">+ Thêm 1 cửa hàng mới </a>
                            </div> 
                        </div>
                        <div class="home__controller">
                            <table class="home__controll-main">
                                <tr class="manage__seller-table-header">
                                    <th>
                                        <div class="manage__seller-table-title"> ID </div>
                                    </th>
                                    <th>
                                        <div class="manage__seller-table-title">Thông tin</div>
                                    </th>
                                    <th>
                                        <div class="manage__seller-table-title">Số điện thoại</div>
                                    </th>
                                    <th>
                                        <div class="manage__seller-table-title">Ngày đăng kí</div>
                                    </th>
                                    <th>
                                        <div class="manage__seller-table-title" style="text-align: center;">Email</div>
                                    </th>
                                    <th>
                                        <div class="manage__seller-table-title">Kho hàng</div>
                                    </th>
                                    <th>
                                        <div class="manage__seller-table-title">Đánh giá</div>
                                    </th>
                                    <th>
                                        <div class="manage__seller-table-action">
                                            Thao tác
                                        </div>
                                    </th>
                                </tr>
                                <c:forEach items="${listSeller}" var="s">
                                    <form  action="managerSeller" method="post">
                                        <input type="hidden" name="seller_id" value="${s.seller_id}"/>
                                        <tr class="manage__seller-table-container">
                                            <td>
                                                <a href="shoppage?sid=${s.seller_id}" class="manage__seller-table-id"> #${s.seller_id} </a>
                                            </td>
                                            <td>
                                                <div class="manage__seller-table-item flex" style="max-width: 256px;">
                                                    <img src="https://cf.shopee.vn/file/e87892e1b559685f3a891b90b8fd4ed4" class="manage__seller-table-img" alt="">
                                                    <div class="manage__seller-table-info" >
                                                        <span class="manage__seller-table-name">${s.store_name} </span>
                                                        <span class="manage__seller-table-address text__overflow-2">${s.address_store}</span>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="manage__seller-table-item">${s.phone_store}</div>
                                            </td>
                                            <td>
                                                <div class="manage__seller-table-item">${s.store_opentime}</div>
                                            </td>
                                            <td style="min-width: 180px">
                                                <div class="manage__seller-table-item">${s.email_store}</div>
                                            </td>
                                            <td>
                                                <div class="manage__seller-table-item">Tổng ${s.number_of_foods} sp</div>
                                            </td>
                                            <td>
                                                <div class="manage__seller-table-id">${s.rating_store} <i class="fas fa-star" style="color: var(--star-color);"></i></div>
                                            </td>
                                            <td>
                                                <c:if test="${s.is_active == 0}" >
                                                    <div class="table__product-action">
                                                        <input type="submit" name="action" value="Accept" class="manage__order-btn" />
                                                        <a href="#reason-deny-${s.seller_id}" class="manage__order-btn" >Deny</a>
                                                    </div> 
                                                </c:if>
                                                <c:if test="${s.is_active == 1}" >
                                                    <div class="table__product-action">
                                                        <a href="#reason-hide-${s.seller_id}" class="manage__order-btn" >Hide</a>
                                                        <a href="#reason-report-${s.seller_id}" class="manage__order-btn" >Report</a>
                                                        <!--<input type="submit" name="action" value="Report" class="manage__order-btn" />-->
                                                    </div> 
                                                </c:if>
                                            </td>
                                        </tr> 
                                        <!-- The modal reason deny shop -->
                                        <div class="dialog overlay" id="reason-deny-${s.seller_id}">
                                            <div class="dialog-body">
                                                <span class="dialog-body-reason__title">Lý Do Từ Chối Cửa Hàng #${s.seller_id}</span>
                                                <div>
                                                    <textarea name="reason-deny" maxlength="200" class="dialog__order-reason__input" placeholder="Điền tại đây.."></textarea>
                                                </div>
                                                <div class="flex" style="justify-content: flex-end;">
                                                    <input type="submit" name="action" value="Gửi" class="btn__primary"/>
                                                    <a href="#" class="btn__white dialog-detail__btn">Quay lại</a>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- The modal reason hidden shop -->
                                        <div class="dialog overlay" id="reason-hide-${s.seller_id}">
                                            <div class="dialog-body">
                                                <span class="dialog-body-reason__title">Lý Do Ẩn Cửa Hàng #${s.seller_id}</span>
                                                <div>
                                                    <textarea name="reason-hide" maxlength="200" class="dialog__order-reason__input" placeholder="Điền tại đây.."></textarea>
                                                </div>
                                                <div class="flex" style="justify-content: flex-end;">
                                                    <input type="submit" name="action" value="Xác nhận" class="btn__primary"/>
                                                    <a href="#" class="btn__white dialog-detail__btn">Quay lại</a>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- The modal reason report shop -->
                                        <div class="dialog overlay" id="reason-report-${s.seller_id}">
                                            <div class="dialog-body">
                                                <span class="dialog-body-reason__title">Lý Do Báo Xấu #${s.seller_id}</span>
                                                <div>
                                                    <textarea name="reason-report" maxlength="200" class="dialog__order-reason__input" placeholder="Điền tại đây.."></textarea>
                                                </div>
                                                <div class="flex" style="justify-content: flex-end;">
                                                    <input type="submit" name="action" value="Báo Xấu" class="btn__primary"/>
                                                    <a href="#" class="btn__white dialog-detail__btn">Quay lại</a>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                    <!--manager filter pagination -->
                    <div  class="filter__pagination">
                        <div></div> 
                        <div class="manager-filter__paging" >
                            <c:if test="${tag > 1}">
                                <a href="/suga/manager?index=${tag-1}" class="home-filter__paging-btn">
                                    <i class="page-icon fas fa-angle-left"> </i>
                                    <span>Prev</span>
                                </a>
                            </c:if>
                            <div class="select-input">
                                <c:forEach begin="1" end="${endP}" var="i">
                                    <a class="select-input__link" href="/suga/manager?index=${i}"> ${i}</a>
                                </c:forEach>
                            </div>
                            <c:if test="${tag < endP}">
                                <a href="/suga/manager?index=${tag+1}" class="home-filter__paging-btn">
                                    <span>Next</span>
                                    <i class="page-icon fas fa-angle-right"></i>
                                </a>
                            </c:if>
                        </div>
                    </div>
                    <div class="home__footer" style="height: 50px;">

                    </div>
                </div>
            </div>

        </div>
    </body>
</html>
