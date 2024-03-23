<%-- 
    Document   : chartline
    Created on : Oct 29, 2023, 8:38:17 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Suga - Thống kê doanh thu</title>
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
        <jsp:include page="menu_chart.jsp"></jsp:include>

            <div class="grid__column-980 main-chart">
                <div class="chart-shape">
                    <canvas id="myLineChart"></canvas>
                    <h5 class="chart-text__describe">Thống kế doanh thu sản phẩm đã bán trong 12 tháng</h5>
                </div>

                <div style="display: flex; justify-content: space-evenly; margin-top: 25px;">
                    <div class="chart-box">
                        <span class="chart-box__title">Tổng doanh thu&nbsp;<i
                                style="color: rgb(193, 194, 194);margin-bottom: 8px; font-size: 1.46rem;"
                                class="fa-regular fa-circle-question"></i>
                        </span>
                        <div>
                            <span style="font-size: 2rem;">₫</span>
                        <jsp:useBean id="chartDao" class="dal.ChartDAO" >
                            <c:set var="totalRevenue" value="${chartDao.totalRevenue}" />
                            <span class="chart-box__price">
                                <c:out value="${totalRevenue}" />
                            </span>
                        </jsp:useBean>
                    </div>
                    <div class="chart-box__calcu">
                        <span class="chart-box__infor"> so với 00:00-22:00 năm ngoái </span>
                        <span class="chart-box__percent">100.00%</span>
                    </div>
                </div>
                <div class="chart-box" style="border-top: 4px solid rgb(255, 187, 0);">
                    <span class="chart-box__title">Số đơn hàng&nbsp;<i
                            style="color: rgb(193, 194, 194);margin-bottom: 8px; font-size: 1.46rem;"
                            class="fa-regular fa-circle-question"></i>
                    </span>
                    <div>
                        <span style="font-size: 2rem;"></span>
                        <c:set var="allOrder" value="${chartDao.allOrder}" />
                        <span class="chart-box__order">
                            <c:out value="${allOrder}" />
                        </span>
                    </div>  
                    <div class="chart-box__calcu">
                        <span class="chart-box__infor"> so với 00:00-22:00 năm ngoái</span>
                        <span class="chart-box__percent">98.20%</span>
                    </div>
                </div>
                <div class="chart-box" style="border-top: 4px solid rgb(255, 107, 69);">
                    <span class="chart-box__title">Số hàng đã bán&nbsp;<i
                            style="color: rgb(193, 194, 194);margin-bottom: 8px; font-size: 1.46rem;"
                            class="fa-regular fa-circle-question"></i></span>
                    <div>
                        <span style="font-size: 2rem;"></span>
                        <c:set var="allOrder" value="${chartDao.totalAmountOfSold}" />
                        <span class="chart-box__order">
                            <c:out value="${allOrder}" />
                        </span>
                    </div>
                    <div class="chart-box__calcu">
                        <span class="chart-box__infor"> so với 00:00-22:00 năm ngoái</span>
                        <span class="chart-box__percent">96.40%</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@page import="java.util.List" %>
<%@page import="dal.ChartDAO" %>
<%@page import="entity.chart.RevenueData" %>
<%@page import="com.google.gson.JsonArray" %>
<%@page import="com.google.gson.Gson" %>


<%
    ChartDAO chartDAO = new ChartDAO();
    List<RevenueData> revenueData = chartDAO.getAllRevenueData();
    Gson gson = new Gson();
    String jsonData = gson.toJson(revenueData);
%>

<%-- JavaScript để vẽ biểu đồ --%>
<script>
    // Lấy dữ liệu từ request
    var revenueData = <%= jsonData %>;

    // Lấy danh sách các category_id và tháng
    const categoryIds = [...new Set(revenueData.map(item => item.categoryId))];
    const months = [...new Set(revenueData.map(item => item.orderYear + "-" + item.orderMonth))];

    // Tạo mảng dữ liệu cho biểu đồ
    const datasets = categoryIds.map(categoryId => {
        const categoryData = revenueData.filter(item => item.categoryId === categoryId);
        const dataPoints = months.map(months => {
            const dataPoint = categoryData.find(item => item.orderYear + "-" + item.orderMonth === months);
            return dataPoint ? dataPoint.totalPrice : 0;
        });

        return {
            label: "Category " + categoryId,
            data: dataPoints,
            borderColor: getRandomColor(), // Tạo màu ngẫu nhiên cho mỗi dòng
            fill: false,
            lineTension: 0.1,
        };
    });

    // Tạo biểu đồ
    const canvas = document.getElementById("myLineChart");
    const chart = new Chart(canvas, {
        type: "line",
        data: {
            labels: months,
            datasets: datasets,
        },
    });

    // Hàm tạo màu ngẫu nhiên
    function getRandomColor() {
        const letters = "0123456789ABCDEF";
        let color = "#";
        for (let i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }

    function formatPrice(price) {
        return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    }

    document.addEventListener("DOMContentLoaded", function () {
        const priceElements = document.querySelectorAll(".chart-box__price ");
        priceElements.forEach(function (element) {
            const price = parseInt(element.textContent.replace(".", ""));
            element.textContent = formatPrice(price);
        });
    });
</script>
<script src="assests/js/main.js"></script>

</body>
</html>
