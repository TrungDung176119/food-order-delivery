<%-- 
    Document   : list
    Created on : 26-02-2024, 10:55:37
    Author     : truon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table{
                border-collapse: collapse
            }
            .content{
                margin: auto;
                width: 80%;
                float: left;
                margin-left: 30px;
                height: auto;
            }
        </style>
    </head>
    <body>
        <div class="app">
            <jsp:include page="menu_home.jsp"></jsp:include> 


                    <div class="content">
                        <form action="dashboard" method="post" style="width: 100%;">
                            <h1>List Of Product</h1>
                            <table border="2px" >
                                <tr>
                                    <th>Pid</th>
                                    <th>Category</th>
                                    <th>Seller_id</th>
                                    <th>Image</th>
                                    <th>Title</th>
                                    <th>Old_price</th>
                                    <th>Current_price</th>
                                    <th>Amount_of_sold</th>
                                    <th>Number_in_stock</th>
                                    <th>Status</th>
                                    <th>Describe</th>
                                    <th>Rating</th>
                                    <th>ID</th>
                                    <th>Name</th>
                                </tr>
                            <c:forEach items="${requestScope.all}"  var="p">
                                <tr>
                                    <td>${p.pid}</td>
                                    <td>${p.category}</td>
                                    <td>${p.seller_id}</td>
                                    <td><img src="${p.image}" width="80px" height="80px" /></td>
                                    <td>${p.title}</td>
                                    <td>${p.old_price}</td>
                                    <td>${p.current_price}</td>
                                    <td>${p.amount_of_sold}</td>
                                    <td>${p.number_in_stock}</td>
                                    <td>${p.status}</td>
                                    <td>${p.describe}</td>
                                    <td>${p.rating}</td>
                                    <td>${p.cid}</td>
                                    <td>${p.cname}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </form>
                    </div>
                </div>    
                </body>
                </html>
