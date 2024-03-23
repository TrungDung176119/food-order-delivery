<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

        <title>HolaFood</title>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- boostrap -->
        <link href="./assests/boostrap/bootstrap.css" rel="stylesheet">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css/main.css">
        <link rel="stylesheet" href="./assests/css.manager/manager.css">
        <link rel="stylesheet" href="./assests/css.manager/sidebar.css">

        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
        <link rel="stylesheet" href="./assests/css.manager/manageOrder.css">

        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.ico">
        <style>
            .disabled-link {
                pointer-events: none; /* Không cho phép sự kiện click */
                opacity: 0.5; /* Mờ đi */
            }
        </style>
    </head>
    <body class="app__manager">
        <jsp:include page="./sidebar.jsp" />

        <div class="home__container">

            <div class="container-fluid px-4">
                <h1 class="mt-4">Biểu đồ</h1>
                <section class="mb-4" id="doanhThuThang">
                    <div class="card">
                        <div class="card-header py-3">
                            <h5 class="mb-0 text-center"><strong>Doanh thu theo tháng</strong></h5>
                        </div>
                        <div class="card-body">
                            <canvas id="horizontalBar" style="height: 500px!important; width: 1000px !important"></canvas>
                        </div>
                    </div>
                </section>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-chart-pie me-1"></i>
                                Biểu đồ doanh thu tròn
                            </div>
                            <div class="card-body"><canvas id="PieChart" width="100%" height="50"></canvas></div>
                            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-chart-pie me-1"></i>
                                Biểu đồ tỷ lệ đánh giá 
                            </div>
                            <div class="card-body"><canvas id="myPieChart" width="100%" height="50"></canvas></div>
                            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script >
            //Charts-area-demo
            new Chart(document.getElementById("horizontalBar"), {
                type: "bar",
                data: {
                    labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"],
                    datasets: [{
                            label: "Doanh thu VNÐ",
                            data: [${totalMonth.month1}, ${totalMonth.month2}, ${totalMonth.month3}, ${totalMonth.month4}, ${totalMonth.month5}, ${totalMonth.month6}, ${totalMonth.month7}, ${totalMonth.month8}, ${totalMonth.month9}, ${totalMonth.month10}, ${totalMonth.month11}, ${totalMonth.month12}],
                            backgroundColor: [
                                // Đổi các màu ở đây, ví dụ:
                                "rgba(0, 0, 255, 0.6)", // Đỏ
                                "rgba(0, 255, 0, 0.6)", // Xanh lá cây
                                "rgba(0, 0, 255, 0.6)", // Xanh dương
                                "rgba(255, 255, 0, 0.6)", // Vàng
                                "rgba(255, 165, 0, 0.6)", // Cam
                                "rgba(128, 0, 128, 0.6)" // Tím
                            ],
                            borderColor: [
                                // Đổi các màu đường biên ở đây, tương tự như backgroundColor
                            ],
                            borderWidth: 1
                        }]
                },
                options: {
                    scales: {
                        xAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                    }
                }
            });


            // Pie Chart Example

            var ctx = document.getElementById("PieChart");
            var myPieChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: ["Cơm Rang / Thường", "Bún / Phở", "Đồ Uống", "Đồ Ăn Nhanh", "Bánh Bao / Bánh Mỳ / Xôi"],
                    datasets: [{
                            data: [
            ${requestScope.totalCate1},
            ${requestScope.totalCate2},
            ${requestScope.totalCate3},
            ${requestScope.totalCate4},
            ${requestScope.totalCate5}],
                            backgroundColor: ['#007bff', '#ffc107', '#28a745', '#FF0000']
                        }]
                }
            });

            // Pie Chart Example

            var ctx = document.getElementById("myPieChart");
            var myPieChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: ["Đánh giá xấu", "Đánh giá tốt"],
                    datasets: [{
                            data: [
            ${requestScope.badFB},
            ${requestScope.goodFB}],
                            backgroundColor: ['#FF0000', '#007bff']
                        }]
                }
            });

        </script>
    </body>
</html>
