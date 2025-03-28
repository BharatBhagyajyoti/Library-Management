<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>📚 Update Book</title>
    <link rel="stylesheet" href="UpdateBook.css"> <!-- External CSS -->
</head>
<body>

<div class="container">
    <h2>📚 Update Book Details</h2>

	<!-- ✅ Display the message below the form -->
    <% if (request.getAttribute("message") != null) { %>
        <div style="color: <%= request.getAttribute("message").toString().contains("Failed") ? "red" : "green" %>;">
            <h3><%= request.getAttribute("message") %></h3>
        </div>
    <% } %>

    <form action="UpdateBook" method="post">
        
        <!-- Book ID (non-editable) -->
        <label for="bookid">📖 Book ID (Max Length : 6 chars):</label>
        <input type="text" id="bookid" name="bookid" placeholder="Enter Book ID" maxlength="6" required >

        <!-- Book Name -->
        <label for="bookname">📚 Book Name:</label>
        <input type="text" id="bookname" name="bookname" placeholder="Enter Book Name" maxlength="30" >

        <!-- Author -->
        <label for="author">✍️ Author:</label>
        <input type="text" id="author" name="author" placeholder="Enter Author Name" maxlength="45" >

        <!-- Price -->
        <label for="price">💰 Price:</label>
        <input type="number" id="price" name="price" placeholder="Enter Price" min="0" step="0.01" >

        <!-- Quantity -->
        <label for="quantity">📦 Quantity:</label>
        <input type="number" id="quantity" name="quantity" placeholder="Enter Quantity" min="0" >

        <div class="btn-container">
            <button type="submit" class="btn update-btn">✅ Update</button>
            <button type="reset" class="btn reset-btn">🔄 Reset</button>
        </div>
    </form>

    <a href="LoginSuccess.jsp" class="back-link">⬅️ Back to Book List</a>

</div>

</body>
</html>
