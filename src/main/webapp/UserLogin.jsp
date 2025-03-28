<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Login</title>
    <link rel="stylesheet" href="UserLogin.css">  <!-- Updated CSS -->
</head>
<body>

<div class="container">

    <div class="form-box">

        <h2>🔑 User Login</h2>

        <form action="UserLogin" method="post">

            <label for="uName">User ID or Email:</label>
            <input type="text" id="uName" name="uName" placeholder="Enter User ID or Email" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter Password" required>

            <button type="submit" class="btn-login">Login</button>

        </form>

        <p>Don't have an account? 
            <a href="UserRegistration.jsp" class="register-link">Register here</a>
        </p>

    </div>

</div>

</body>
</html>
