<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Request Book</title>
    <link rel="stylesheet" href="RequestBook.css"> <!-- Separate CSS for styling -->
</head>
<body>

<div class="container">

    <!-- ✅ Header Section -->
    <header>
        <h1>📚 Request a Book</h1>
    </header>

    <!-- ✅ Book Request Form -->
    <form action="RequestBook" method="post" class="request-form">
        
        <div class="form-group">
            <label for="bookname">📖 Book Name:</label>
            <input type="text" id="bookname" name="bookname" placeholder="Enter Book Name" required>
        </div>

        <div class="form-group">
            <label for="author">✍️ Author:</label>
            <input type="text" id="author" name="author" placeholder="Enter Author Name" required>
        </div>

        <button type="submit" class="btn-submit">📩 Submit Request</button>
    </form>

    <!-- ✅ Display Success/Error Message -->
    <%
        if (request.getParameter("success") != null) { 
    %>
        <p class="success-message">✅ Book request submitted successfully!</p>
    <%
        } else if (request.getParameter("error") != null) { 
    %>
        <p class="error-message">❌ Failed to submit book request!</p>
    <%
        }
    %>

    <!-- 🎯 Go Back Button -->
    <div class="btn-container">
        <a href="UserDashboard.jsp" class="btn-back">🏠 Go to Home</a>
    </div>

    <footer>
        <p>&copy; 2025 Library Management System | Made with ❤️</p>
    </footer>

</div>

</body>
</html>
