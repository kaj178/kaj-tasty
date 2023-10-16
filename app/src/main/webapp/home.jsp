<%@ page import="com.kaj.app.entity.Product" %>
<%@ page import="java.util.List, java.lang.String" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Management</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="./js/delete_product.js"></script>
    <style>
        tr, th, td {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <%
        List<Product> products = (List<Product>) request.getAttribute("products");
    %>
    <div class="row">
        <div class="col-12">
            <div class="header d-flex justify-content-between align-items-center">
                <div class="info">
                    <span class="h2">Product Management</span>
                </div>
                <div class="info">
                    <span class="h6">
                        <!-- String username = (String) request.getAttribute("username"); -->
                        <% 
                            String username = (String) session.getAttribute("usernameSession");
                            if (username != null) {
                        %>
                        Welcome <span style="color: red"><%= username %>
                    </span>|
                    <a href="/app/logout">Logout</a>
                    <%
                        } else {
                            response.sendRedirect("/app/login.jsp");
                        }
                    %>
                    </span>
                </div>
            </div>
        </div>
    </div>

    <div class="row content mt-5" style="margin-top: 150px">
        <div class="col-12 col-sm-4">
            <form action="/app/product" method="post" class="card card-body p-3 shadow p-3 mb-5 bg-body rounded">
                <h3 class="text-secondary text-center mb-3">
                    Add Product
                </h3>

                <div class="form-group">
                    <label for="name">Product's Name</label>
                    <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
                </div>

                <div class="form-group">
                    <label for="price">Product's Price</label>
                    <input type="text" class="form-control" id="price" placeholder="Enter price" name="price">
                </div>

                <button type="submit" class="btn btn-primary mt-3">Add Product</button>
                <% if (request.getAttribute("message") != null) { %>
                <div id="flash-message" class='<%= "alert mt-3 " + "alert-" + request.getAttribute("type") %>' role="alert">
                    <%= request.getAttribute("message") %>
                </div>
                <% } %>
            </form>
        </div>

        <div class="col-12 col-sm-8">
            <div class="card shadow p-3 mb-5 bg-body rounded">
                <h3 class="card-header text-success " style="background-color: transparent">Product Available</h3>
                <table class="table table-sm table-hover w-100 mb-0">

                    <thead>
                    <tr>
                        <th>STT</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                    </thead>

                    <tbody>
                    <%
                        if (products != null) {
                            for (Product product : products) { %>
                    <tr>
                        <td><%= product.getId() %></td>
                        <td class="text-primary"><%= product.getName() %></td>
                        <td><%= "$" + product.getPrice() %></td>
                        <td>
                            <!--  -->
                            <a href="product?action=del&id=<%= product.getId() %>" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>