<%-- 
    Document   : chartline
    Created on : Oct 29, 2023, 8:38:17 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Suga - Biểu Đồ</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- boostrap -->
        <link href="./assests/boostrap/bootstrap.css" rel="stylesheet">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css/profile.css">
        <link rel="stylesheet" href="./assests/css.cart/cart.css">
        <link rel="stylesheet" href="./assests/css.cart/chart.css">

        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
        <!-- chart -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.ico">

    </head>
    <body>
        <jsp:include page="menu_chart.jsp" ></jsp:include>

            <div class="grid__column-980 main-chart">
                <div class="chart-shape">
                    <canvas id="myLineChart"></canvas>
                    <h5 class="chart-text__describe">Thống kế số lượng sản phẩm đã bán trong 12 tháng </h5>
                </div>
                <div style="display: flex; justify-content: space-evenly; margin-top: 25px;">
                    <div class="chart-box">
                        <span class="chart-box__title">Số sản phẩm đã bán&nbsp;<i
                                style="color: rgb(193, 194, 194);margin-bottom: 8px; font-size: 1.46rem;"
                                class="fa-regular fa-circle-question"></i>
                        </span>
                        <div>
                            <span style="font-size: 2rem;"></span>
                        <jsp:useBean id="chartDao" class="dal.ChartDAO" >
                            <c:set var="allOrder" value="${chartDao.totalAmountOfSold}" />
                            <span class="chart-box__order">
                                <c:out value="${allOrder}" />
                            </span>
                        </jsp:useBean>
                    </div>
                    <div class="chart-box__calcu">
                        <span class="chart-box__infor"> so với 00:00-22:00 năm ngoái </span>
                        <span class="chart-box__percent">89.60%</span>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <%@page import="java.util.List" %>
    <%@page import="dal.ChartDAO" %>
    <%@page import="entity.chart.SellData" %>


    <%
        // Gọi phương thức DAO để lấy dữ liệu từ cơ sở dữ liệu
        ChartDAO c = new ChartDAO();
        List<SellData> salesData = c.getAllSellData(); // Đảm bảo bạn có phương thức "getAllSellData" trong DAO

        // Tạo mảng các ngày và mảng số lượng đã bán từ dữ liệu lấy từ DAO
        String dates = "";
        String quantities = "";
        for (SellData item : salesData) {
            dates += "'" + item.getSell_year() + "-" + item.getSell_date() + "',";
            quantities += item.getSell_quantity() + ",";
        }
        // Xóa dấu phẩy cuối cùng
        dates = dates.substring(0, dates.length() - 1);
        quantities = quantities.substring(0, quantities.length() - 1);
    %>
    <!-- Các phần khác của trang JSP -->

    <script>
        // Lấy dữ liệu sản phẩm đã bán từ JSP
        const dates = [<%= dates %>];
        const quantities = [<%= quantities %>];

        // Lấy phần tử canvas
        const canvas = document.getElementById("myLineChart");

        // Tạo dữ liệu biểu đồ đường
        const data = {
            labels: dates,
            datasets: [
                {
                    label: "Số lượng đã bán",
                    data: quantities,
                    fill: false,
                    borderColor: "#ee4d2d",
                    lineTension: 0.09,
                },
            ],
        };

        // Tạo biểu đồ đường
        const myLineChart = new Chart(canvas, {
            type: "line",
            data: data,
        });


    </script>
    <script src="assests/js/main.js"></script>

</body>

</html>