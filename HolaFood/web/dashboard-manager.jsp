<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <body>

        <jsp:include page="./sidebar.jsp" />

        <div class="home__container">
            <div class="container mt-5">
                <div class="row">
                    <div class="col-md-6">
                        <canvas id="revenueChart"></canvas>
                    </div>
                    <div class="col-md-6">
                        <canvas id="orderCountChart"></canvas>
                    </div>
                </div>
            </div>
        </div>


        <script>
            // Function to get the month name based on month index
            function getMonthName(monthIndex) {
                var monthNames = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
                return monthNames[monthIndex];
            }

            // Calculate month names for the last 6 months (excluding the current month)
            var currentDate = new Date();
            var monthNames = [];
            var currentMonthIndex = currentDate.getMonth();
            for (var i = 5; i >= 0; i--) {
                var monthIndex = (currentMonthIndex - i + 12) % 12; // Ensure month index is in the range [0, 11]
                var monthName = getMonthName(monthIndex);
                monthNames.push(monthName);
            }

            // Get revenue data from JSP request
            var revenueData = ${revenueData};
            // Get order count data from JSP request
            var orderCountData = ${orderCountData};

            // Render revenue chart
            var revenueCtx = document.getElementById('revenueChart').getContext('2d');
            var revenueChart = new Chart(revenueCtx, {
                type: 'line',
                data: {
                    labels: monthNames,
                    datasets: [{
                            label: 'Revenue',
                            data: revenueData,
                            borderColor: 'rgb(255, 99, 132)',
                            borderWidth: 2,
                            fill: false
                        }]
                },
                options: {
                    scales: {
                        yAxes: [{
                                scaleLabel: {
                                    display: true,
                                    labelString: 'Amount'
                                }
                            }]
                    }
                }
            });

            // Render order count chart
            var orderCountCtx = document.getElementById('orderCountChart').getContext('2d');
            var orderCountChart = new Chart(orderCountCtx, {
                type: 'bar',
                data: {
                    labels: monthNames,
                    datasets: [{
                            label: 'Order Count',
                            data: orderCountData,
                            backgroundColor: 'rgb(54, 162, 235)',
                            borderWidth: 2
                        }]
                },
                options: {
                    scales: {
                        yAxes: [{
                                scaleLabel: {
                                    display: true,
                                    labelString: 'Number of Orders'
                                }
                            }]
                    }
                }
            });
        </script>
    </body>
</html>
