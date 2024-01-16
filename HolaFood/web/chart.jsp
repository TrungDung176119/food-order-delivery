<%-- 
    Document   : chart
    Created on : Oct 29, 2023, 8:04:15 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>HolaFood | Chart </title>
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
                <div class="chart-shape" style="width: 90%">
                    <canvas id="barChart" width="600" height="300"></canvas>
                    <h5 class="chart-text__describe">Thống kế các thể loại hàng tồn kho </h5>
                </div>

                <div style="display: flex; justify-content: space-evenly; margin-top: 25px;">
                    <div class="chart-box">
                        <span class="chart-box__title">Số hàng tồn kho&nbsp;<i
                                style="color: rgb(193, 194, 194);margin-bottom: 8px; font-size: 1.46rem;"
                                class="fa-regular fa-circle-question"></i>
                        </span>
                        <div>
                            <span style="font-size: 2rem;"></span>
                        <jsp:useBean id="chartDao" class="dal.ChartDAO" >
                            <c:set var="allOrder" value="${chartDao.allQuantityInStock}" />
                            <span class="chart-box__order">
                                <c:out value="${allOrder}" />
                            </span>
                        </jsp:useBean>
                    </div>
                    <div class="chart-box__calcu">
                        <span class="chart-box__infor"> so với 00:00-22:00 năm ngoái </span>
                        <span class="chart-box__percent">100.00%</span>
                    </div>
                </div>
            </div>    
        </div>
    </div>

</div>

<%@page import="java.util.List" %>
<%@page import="dal.ChartDAO" %>
<%@page import="entity.chart.StockData" %>
<%@page import="java.util.Arrays" %>
<%
    ChartDAO c = new ChartDAO();
    List<StockData> stockData = c.getAllStockData();

    // Initialize arrays to hold category names and stock values
    String[] cateNames = new String[stockData.size()];
    int[] stocks = new int[stockData.size()];

    // Populate the arrays
    for (int i = 0; i < stockData.size(); i++) {
        StockData item = stockData.get(i);
        cateNames[i] = "'" + item.getCateName() +"'";
        stocks[i] = item.getStock();
    }

    // Convert arrays to JavaScript strings
    String cateNameArray = Arrays.toString(cateNames);
    String stockArray = Arrays.toString(stocks);
%>


<script>
    // Lấy dữ liệu sản phẩm đã bán từ JSP
    const cateName = <%= cateNameArray %>;
    const stock = <%= stockArray %>;

    var ctx = document.getElementById('barChart').getContext('2d');
    var barChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: cateName, // Use the category names array
            datasets: [{
                    label: 'Tồn kho',
                    data: stock, // Use the stock values array
                    backgroundColor: ['rgb(255, 107, 69)', 'rgb(255, 187, 0)', 'pink', 'rgb(148, 95, 185)', 'rgb(38, 115, 221)', 'rgb(17, 148, 17)', 'rgb(38, 170, 153)', 'olive'],
                }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
    function formatAmountSold(amountSold) {
        return amountSold.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    document.addEventListener("DOMContentLoaded", function () {
        const amountSoldElements = document.querySelectorAll(".chart-box__order");
        amountSoldElements.forEach(function (element) {
            const amountSold = parseInt(element.textContent.replace("Đã bán ", ""));
            element.textContent = formatAmountSold(amountSold);
        });
    });


</script>
</body>

</html>