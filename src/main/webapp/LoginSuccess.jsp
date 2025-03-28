<%@page import="bean_pojo.AdminBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    // ✅ Session checking
    HttpSession sessionCheck = request.getSession(false);
    if (sessionCheck == null || sessionCheck.getAttribute("adminBean") == null) {
        response.sendRedirect("Login.jsp");  // Redirect to login if no session
        return;
    }

    // ✅ Cache-Control Headers
    // 🌟 Prevent browser caching
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", -1);
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>🌟 Admin Dashboard</title>
    <link rel="stylesheet" href="LoginSuccess.css"> <!-- External CSS -->
</head>
<body>

<div class="wrapper">

    <!-- Navigation Sidebar -->
    <div class="sidebar">
    <h2>📚 Admin Panel</h2>
    <ul>
        <li><a href="AddBook.jsp"><span class="icon">➕</span> Add Book</a></li>
        <li><a href="DeleteBook.jsp"><span class="icon">🗑️</span> Delete Book</a></li>
        <li><a href="UpdateBook.jsp"><span class="icon">✏️</span> Update Book</a></li>
        <li><a href="ViewBook"><span class="icon">📖</span> View Books</a></li>
        <li><a href="ViewBookRequests"><span class="icon">📚</span> View Book Requests</a></li>
        
        <li>
            <form action="Logout" method="post" class="logout-form">
                <button type="submit" class="btn-logout"><span class="icon">🚪</span> Logout</button>
            </form>
        </li>
    </ul>
</div>


    <!-- Main Content -->
    <div class="main-content">
        <div class="header">
            <h1>👋 Welcome, Admin!</h1>
            <%
            AdminBean ab = (AdminBean) session.getAttribute("adminBean");
            if (ab != null) {
                out.println("<h2>" + ab.getName() + "</h2>");
            } else {
                out.println("<h2>Unknown Admin</h2>");
            }
            %>
        </div>

        <div class="cards-container">
            <div class="card">
                <h3>➕ Add Book</h3>
                <p>Add new books to the library.</p>
                <a href="AddBook.jsp" class="btn">Go</a>
            </div>
            
            <div class="card">
                <h3>🗑️ Delete Book</h3>
                <p>Remove books from the library.</p>
                <a href="DeleteBook.jsp" class="btn">Go</a>
            </div>

            <div class="card">
                <h3>✏️ Update Book</h3>
                <p>Modify book details.</p>
                <a href="UpdateBook.jsp" class="btn">Go</a>
            </div>

            <div class="card">
                <h3>📚 View Books</h3>
                <p>Check available books.</p>
                <a href="ViewBook" class="btn">Go</a>
            </div>
        </div>

    </div>

</div>

</body>
</html>
