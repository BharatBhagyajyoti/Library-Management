<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Admin Registration Form</title>

<!-- Font Icon -->
<link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
<link rel="stylesheet" href="Registration.css">

<style>
    /* 🌟 Popup Styling */
    .popup {
        position: fixed;
        top: 20px;
        right: 20px;
        color: white;
        padding: 15px 30px;
        border-radius: 8px;
        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
        z-index: 9999;
        opacity: 0;
        transform: translateY(-30px);
        transition: all 0.5s ease-in-out;
    }
    
    .popup.success {
        background-color: #4caf50;  /* Green for success */
    }

    .popup.error {
        background-color: #e74c3c;  /* Red for error */
    }

    .popup.warning {
        background-color: #f39c12;  /* Yellow for account exists */
    }
    
    .popup.show {
        opacity: 1;
        transform: translateY(0);
    }
</style>

</head>
<body>

<!-- Popup message -->
<div id="popup" class="popup <%= request.getAttribute("status") != null ? request.getAttribute("status") : "" %>">
    <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
</div>

<div class="container">
    <div class="form-box">
        <h1>🛡️ Registration</h1>

        <form action="AdminRegistration" method="post">

            <div class="input-group">
                <label for="name">👤 Name:</label>
                <input type="text" name="name" id="name" placeholder="Enter your name" required>
            </div>

            <div class="input-group">
                <label for="email">📧 Email:</label>
                <input type="email" name="email" id="email" placeholder="Enter your email" required>
            </div>

            <div class="input-group">
                <label for="phone">📱 Phone:</label>
                <input type="tel" name="phone" id="phone" placeholder="Enter your phone number" required>
            </div>

            <div class="input-group">
                <label for="dob">🎂 DOB:</label>
                <input type="date" name="dob" id="dob" required>
            </div>

            <div class="input-group">
                <label for="password">🔒 Password:</label>
                <input type="password" name="password" id="password" placeholder="Create a strong password" required>
            </div>

            <button type="submit">🚀 Register</button>
        </form>

        <p>Already have an account? <a href="Login.jsp">Login Here</a></p>
    </div>
</div>

<script>
    // 🌟 JavaScript to handle the popup animation
    window.onload = () => {
        const popup = document.getElementById('popup');
        if (popup.textContent.trim()) {
            popup.classList.add('show');

            // Hide the popup after 4 seconds
            setTimeout(() => {
                popup.classList.remove('show');
            }, 4000);
        }
    };
</script>

</body>
</html>
