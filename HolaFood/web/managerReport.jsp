<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <style>
            /* Modal content */
            .modal-content {
                background-color: #fefefe;
                margin: auto; /* Center the modal horizontally on the screen */
                padding: 20px;
                border: 1px solid #888;
                width: 80%; /* Adjust the width of the modal content */
                max-width: 500px; /* Set maximum width if needed */
                border-radius: 10px;
                position: fixed;
                top: 50%; /* Center vertically */
                left: 50%;
                transform: translate(-50%, -50%);
            }

            /* Close button */
            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
                position: absolute;
                top: 10px;
                right: 10px;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div class="app__manager">
            <jsp:include page="sidebar.jsp" />
            <div class="home__container">
                <div class="grid">
                    <div class="tabs">
                        <button class="tablinks btn__primary" onclick="openTab(event, 'tab1')" id="defaultOpen">Sản phẩm bị tố cáo</button> &nbsp;&nbsp;
                        <button class="tablinks btn__primary" onclick="openTab(event, 'tab2')">Tài khoản bị tố cáo</button>
                    </div>
                    <div class="home__container-main">
                        <div class="home__controller">
                            <div id="tab1" class="tabcontent">
                                <h4>Đơn tố cáo sản phẩm</h4>
                                <h5>Hiện có ${count1} đơn tố cáo chờ được xử lý</h5>
                                <div class="home__controll-main" >
                                    <table style="max-width: 100%; width: 100%;">
                                        <tr class="table__product-title">
                                            <th style="max-width: 30px; color: var(--color-infor)">
                                                <div class="table__account-id"> STT </div>
                                            </th>
                                            <th>
                                                <div class="table__account-name" >Sản phẩm bị tố cáo</div>
                                            </th>
                                            <th>
                                                <div class="table__account-password" >Lý do bị tố cáo</div>
                                            </th>
                                            <th>
                                                <div class="table__account-admin" >Người tố cáo</div>
                                            </th>
                                            <th>
                                                <div class="table__account-block" >Thời gian tố cáo</div>
                                            </th>
                                            <th>
                                                <div class="table__account-block" >Trạng thái</div>
                                            </th>
                                            <th>
                                                <div class="table__account-block" >Người xét duyệt</div>
                                            </th>
                                            <th>
                                                <div class="table__account-action" >Thao tác </div>
                                            </th>
                                        </tr>
                                        <c:set var="counter" value="1" />
                                        <c:forEach items="${listrp}" var="o">
                                            <tr class="table__account">
                                                <td>
                                                    <div class="table__account-cnt" >${counter}</div>
                                                </td>
                                                <td>
                                                    <div class="table__account-name"><c:out value="${o.reportedProduct.title}"></c:out></div>
                                                    </td>
                                                    <td>
                                                        <div class="table__account-password">${o.report_reason.report_name}</div>
                                                </td>
                                                <td>
                                                    <div class="table__account-block" ><c:out value="${o.reportAcc.username}"></c:out></div>
                                                    </td>
                                                    <td>
                                                        <div class="table__account-block" >${o.request_timestamp}</div>
                                                </td>
                                                <td>
                                                    <div class="table__account-block" style="color:
                                                         <c:choose>
                                                             <c:when test="${o.status eq 0}">black</c:when>
                                                             <c:when test="${o.status eq 1}">green</c:when>
                                                             <c:when test="${o.status eq 2}">red</c:when>
                                                             <c:otherwise>black</c:otherwise>
                                                         </c:choose>
                                                         ;">
                                                        <c:choose>
                                                            <c:when test="${o.status eq 0}">
                                                                Đang xử lý
                                                            </c:when>
                                                            <c:when test="${o.status eq 1}">
                                                                Đã duyệt
                                                            </c:when>
                                                            <c:when test="${o.status eq 2}">
                                                                Đã từ chối
                                                            </c:when>
                                                            <c:otherwise>
                                                                Không xác định
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="table__account-block"><c:choose>
                                                            <c:when test="${o.approved_id eq 0}">
                                                                Chưa có người xử lý
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="${o.approvedAcc.username}"></c:out>
                                                            </c:otherwise>
                                                        </c:choose></div>
                                                </td> 
                                                <td>
                                                    <div class="table__account-action">
                                                        <div><a href="#" class="view-detail" data-target="#myModal1">Xem chi tiết</a></div>
                                                    </div>
                                                    <c:if test="${o.status eq 0}">
                                                        <div class="table__account-action">
                                                            <div><a onclick="doHide('${o.reportedthing_id}', '${o.request_id}')">Ẩn sản phẩm</a></div>
                                                        </div>                                            
                                                        <div class="table__account-action">
                                                            <div><a href="reportHide?rid=${o.request_id}">Hủy yêu cầu</a></div>
                                                        </div>
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <c:set var="counter" value="${counter + 1}" />
                                            <div id="myModal1" class="modal">
                                                <div class="modal-content">
                                                    <span class="close">&times;</span>
                                                    <div class="form-group">
                                                        <label for="description">Description:</label>
                                                        <textarea id="description" name="description" rows="4" cols="50" required>${o.report_description}</textarea>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="proof">Proof:</label>
                                                        ${o.proof}
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>

                                    </table>
                                </div>
                            </div>
                            <div id="tab2" class="tabcontent">
                                <h4>Đơn tố cáo tài khoản</h4>
                                <h5>Hiện có ${count0} đơn tố cáo chờ được xử lý</h5>
                                <div class="home__controll-main" >
                                    <table style="max-width: 100%; width: 100%;">
                                        <tr class="table__product-title">
                                            <th style="max-width: 30px; color: var(--color-infor)">
                                                <div class="table__account-id"> STT </div>
                                            </th>
                                            <th>
                                                <div class="table__account-name" >Người bị tố cáo</div>
                                            </th>
                                            <th>
                                                <div class="table__account-password" >Lý do bị tố cáo</div>
                                            </th>
                                            <th>
                                                <div class="table__account-admin" >Người tố cáo</div>
                                            </th>
                                            <th>
                                                <div class="table__account-block" >Thời gian tố cáo</div>
                                            </th>
                                            <th>
                                                <div class="table__account-block" >Trạng thái</div>
                                            </th>
                                            <th>
                                                <div class="table__account-block" >Người xét duyệt</div>
                                            </th>
                                            <th>
                                                <div class="table__account-action" >Thao tác </div>
                                            </th>
                                        </tr>
                                        <c:set var="counter" value="1" />
                                        <c:forEach items="${listra}" var="o">
                                            <tr class="table__account">
                                                <td>
                                                    <div class="table__account-cnt" >${counter}</div>
                                                </td>
                                                <td>
                                                    <div class="table__account-name"><c:out value="${o.reportedAccount.username}"></c:out></div>
                                                    </td>
                                                    <td>
                                                        <div class="table__account-password">${o.report_reason.report_name}</div>
                                                </td>
                                                <td>
                                                    <div class="table__account-block" ><c:out value="${o.reportAcc.username}"></c:out></div>
                                                    </td>
                                                    <td>
                                                        <div class="table__account-block" >${o.request_timestamp}</div>
                                                </td>
                                                <td>
                                                    <div class="table__account-block" style="color:
                                                         <c:choose>
                                                             <c:when test="${o.status eq 0}">black</c:when>
                                                             <c:when test="${o.status eq 1}">green</c:when>
                                                             <c:when test="${o.status eq 2}">red</c:when>
                                                             <c:otherwise>black</c:otherwise>
                                                         </c:choose>
                                                         ;">
                                                        <c:choose>
                                                            <c:when test="${o.status eq 0}">
                                                                Đang xử lý
                                                            </c:when>
                                                            <c:when test="${o.status eq 1}">
                                                                Đã duyệt
                                                            </c:when>
                                                            <c:when test="${o.status eq 2}">
                                                                Đã từ chối
                                                            </c:when>
                                                            <c:otherwise>
                                                                Không xác định
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="table__account-block"><c:choose>
                                                            <c:when test="${o.approved_id eq 0}">
                                                                Chưa xử lý
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="${o.approvedAcc.username}"></c:out>
                                                            </c:otherwise>
                                                        </c:choose></div>
                                                </td> 
                                                <td>  
                                                    <div class="table__account-action">
                                                        <div><a href="#" class="view-detail" data-target="#myModal2">Xem chi tiết</a></div>
                                                    </div> 
                                                    <c:if test="${o.status eq 0}">
                                                        <div class="table__account-action">
                                                            <div><a onclick="doBlock('${o.reportedthing_id}', '${o.request_id}')">Khóa tài khoản</a></div>
                                                        </div> 
                                                        <div class="table__account-action">
                                                            <div><a href="reportBlock?rid=${o.request_id}">Hủy yêu cầu</a></div>
                                                        </div>
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <c:set var="counter" value="${counter + 1}" />
                                            <!-- Modal for tab 2 -->
                                            <div id="myModal2" class="modal">
                                                <div class="modal-content">
                                                    <span class="close">&times;</span>
                                                    <div class="form-group">
                                                        <label for="description">Description:</label>
                                                        <textarea id="description" name="description" rows="4" cols="50" required>${o.report_description}</textarea>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="proof">Proof:</label>
                                                        ${o.proof}
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
// JavaScript function to switch between tabs
            function openTab(evt, tabName) {
                var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName("tabcontent");
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("tablinks");
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(" active", "");
                }
                document.getElementById(tabName).style.display = "block";
                evt.currentTarget.className += " active";
            }

// Open the default tab on page load
            document.getElementById("defaultOpen").click();

            // Get the modal
            var modals = document.querySelectorAll('.modal');

// Get the <span> element that closes the modal
            var spans = document.querySelectorAll('.close');

// When the user clicks on the button, open the modal
            document.querySelectorAll('.view-detail').forEach(item => {
                item.addEventListener('click', event => {
                    var targetModal = document.querySelector(event.target.getAttribute('data-target'));
                    targetModal.style.display = "block";
                });
            });

// When the user clicks on <span> (x), close the modal
            spans.forEach((span, index) => {
                span.onclick = function () {
                    modals[index].style.display = "none";
                }
            });
            function doBlock(id, requestId) {
                if (confirm("Bạn có muốn chặn tài khoản có id = " + id)) {
                    window.location = "reportBlock?accid=" + id + "&rid=" + requestId;
                }
                return false;
            }

            function doHide(id, requestId) {
                if (confirm("Bạn có muốn ẩn sản phẩm có id = " + id)) {
                    window.location = "reportHide?pid=" + id + "&rid=" + requestId;
                }
                return false;
            }
            var messageElement = document.querySelector('.home__header-mesage-del');

            function hideMessage() {
                messageElement.style.display = 'none';
            }
            setTimeout(hideMessage, 6500);

        </script>
    </body>
</html>