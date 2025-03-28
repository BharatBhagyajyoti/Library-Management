<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    
    //After Hosting:
    //Remove this part if it isn't allowing to go back .
    
    
    // ✅ Redirect to dashboard if already logged in
    HttpSession sessionCheck = request.getSession(false);
    if (sessionCheck != null && sessionCheck.getAttribute("adminBean") != null) {
        response.sendRedirect("LoginSuccess.jsp");
        return;
    }
%>

    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
    <link rel="stylesheet" href="Login.css"> 
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> <!-- SweetAlert -->
</head>
<body>

<div class="container">
    <div class="form-box">
        <h1>🔑 Admin Login</h1>

        <form action="AdminLogin" method="post">
            <div class="input-group">
                <label for="email">📧 Email:</label>
                <input type="email" name="email" id="email" placeholder="Enter your email" required>
            </div>

            <div class="input-group">
                <label for="password">🔒 Password:</label>
                <input type="password" name="password" id="password" placeholder="Enter your password" required>
            </div>

            <button type="submit">🚀 Login</button>
        </form>

        <p>Don't have an account? <a href="Registration.jsp">Register here</a></p>
    </div>
</div>

<script>
    // 🌟 Display SweetAlert error message if exists
    window.onload = () => {
        <% 
        // ✅ Use proper null check to avoid 500 errors
        String errorMessage = (String) session.getAttribute("errorMessage");
        if (errorMessage != null) { 
        %>
            Swal.fire({
                icon: 'error',
                title: 'Login Failed',
                text: '<%= errorMessage %>',
                confirmButtonText: 'Try Again',
                confirmButtonColor: '#d33'
            });

            // ✅ Remove the message immediately after displaying it
            <% session.removeAttribute("errorMessage"); %>
        <% 
        } 
        %>
    };
</script>

</body>
</html>