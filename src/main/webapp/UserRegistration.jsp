<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <link rel="stylesheet" href="UserRegistration.css">
    
    <!-- ✅ SweetAlert CDN -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

<div class="container">
    <div class="form-box">
        <h2>👤 User Registration</h2>

        <!-- ✅ Registration Form -->
        <form action="UserRegister" method="post">
            <label for="user_id">User ID (Max 15 chars):</label>
            <input type="text" name="user_id" id="user_id" placeholder="Enter User ID" maxlength="15" required>

            <label for="name">Name:</label>
            <input type="text" name="name" id="name" placeholder="Enter Full Name" required>

            <label for="email">Email:</label>
            <input type="email" name="email" id="email" placeholder="Enter Email" required>

            <label for="password">Password:</label>
            <input type="password" name="password" id="password" placeholder="Enter Password" required>

            <button type="submit" class="btn-register">Register</button>
        </form>

        <p>Already have an account? 
            <a href="UserLogin.jsp" class="login-link">Login here</a>
        </p>
    </div>
</div>

<!-- ✅ SweetAlert Popup Script -->
<script>
    window.addEventListener('DOMContentLoaded', () => {
        const urlParams = new URLSearchParams(window.location.search);
        const status = urlParams.get('status');
        const email = urlParams.get('email');

        if (status === 'success' && email) {
            console.log(`✅ User Registered: ${email}`);
            
            Swal.fire({
                icon: 'success',
                title: 'Registration Successful',
                text: `Welcome, ${email}! You can now login.`,
                showConfirmButton: true,
                confirmButtonText: 'Go to Login'
            }).then(() => {
                window.location.href = 'UserLogin.jsp';  // Redirect to login
            });

        } else if (status === 'error') {
            console.error('❌ Registration Failed');

            Swal.fire({
                icon: 'error',
                title: 'Registration Failed',
                text: 'Please try again.',
                showConfirmButton: true
            });

        } else if (status === 'exists' && email) {
            console.warn(`⚠️ Email already exists: ${email}`);
            
            Swal.fire({
                icon: 'warning',
                title: 'Email Already Registered',
                text: `The email ${email} is already registered. Please use a different email.`,
                showConfirmButton: true
            });
        }
    });
</script>

</body>
</html>
