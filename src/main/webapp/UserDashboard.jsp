<%@ page import="bean_pojo.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
    <link rel="stylesheet" href="UserDashBoard.css">
</head>
<body>

<%
    UserBean user = (UserBean) session.getAttribute("userBean");
    if (user == null) {
        response.sendRedirect("UserLogin.jsp");
        return;
    }
%>

<div class="dashboard-container">

    <!-- 🎯 Sidebar -->
    <aside class="sidebar">
        <div class="profile-section">
            <!-- ✅ Default Profile Emoji -->
            <div class="profile-emoji">👤</div>

            <h2><%= user.getName() %></h2>
            <p>📧 <%= user.getEmail() %></p>
        </div>

        <nav>
            <ul>
                <li><a href="UserViewAllBook">📚 View All Books</a></li>
                <li><a href="RequestBook.jsp">🛒 Request Book</a></li>
                <li><a href="UserBookRequests">🔍 View Your Requests</a></li>
                <li><a href="SearchBook.jsp">🔎 Search Books</a></li>
            </ul>
        </nav>

        <form action="UserLogout" method="post" class="logout-form">
            <button type="submit" class="btn-logout">🚪 Logout</button>
        </form>
    </aside>

    <!-- 🎯 Main Content -->
    <main class="main-content">
        <h1>👋 Welcome, <%= user.getName() %>!</h1>
        <p class="intro-text">Explore, request, and find your favorite books with ease!</p>

        <div class="stats">
            <div class="card">
                <h3>📚 Books Available</h3>
                <p>1,200+</p>
            </div>
            <div class="card">
                <h3>📥 Your Requests</h3>
                <p>4</p>
            </div>
            <div class="card">
                <h3>🌟 Popular Genres</h3>
                <p>Fiction, Tech, History</p>
            </div>
        </div>
    </main>

</div>

<footer>
    <p>&copy; 2025 Library Management System | Made with ❤️</p>
</footer>

</body>
</html>
