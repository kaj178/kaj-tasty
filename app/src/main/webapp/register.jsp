<!DOCTYPE html>
<html lang="en">
<head>
    <title>Register Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<%
    if (session.getAttribute("usernameSession") != null) {
        response.sendRedirect("/app/product");
    } 
%>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <h3 class="text-center text-secondary mt-5 mb-3">User Register</h3>
            <form method="POST" action="/app/register" class="border rounded w-100 mb-5 mx-auto px-3 pt-3 bg-light">
                <div class="form-group">
                    <label for="fullname" class="font-weight-bold">Fullname</label>
                    <input name="fullname" id="fullname" type="text" class="form-control" placeholder="Your name">
                </div>
                <div class="form-group">
                    <label for="email" class="font-weight-bold">Email</label>
                    <input name="email" id="email" type="text" class="form-control" placeholder="Email">
                </div>
                <div class="form-group">
                    <label for="password" class="font-weight-bold">Password</label>
                    <input name="password" id="password" type="password" class="form-control" placeholder="Password">
                </div>
                <div class="form-group">
                    <label for="password" class="font-weight-bold">Confirm password</label>
                    <input name="confirm-password" id="confirm-password" type="password" class="form-control" placeholder="Password">
                </div>
                <div class="form-group">
                    <button class="btn btn-success px-5">Register</button>
                </div>
                <div class="form-group">
                    <p>Have an account? <a href="/app/login.jsp">Login</a></p>
                </div>
            </form>

        </div>
    </div>
</div>
<%
    String error = (String) request.getAttribute("registerError");
    if (error != null) {
%>
    <script>
        let message = '<%= error %>'
        window.addEventListener("load", function(){
                alert(message);
        })
    </script>
<% } %>
</body>
</html>