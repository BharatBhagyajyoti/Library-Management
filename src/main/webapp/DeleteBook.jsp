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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Book</title>
    <link rel="stylesheet" href="DeleteBook.css">  <!-- External CSS -->
</head>
<body>

<!-- ✅ Pop-up message -->
<% String message = (String) request.getAttribute("message"); %>
<% if (message != null) { %>
<div class="popup <%= message.contains("✅") ? "success" : "error" %>">
    <%= message %>
</div>
<script>
    // ✅ Auto-hide the popup after 3 seconds
    setTimeout(() => {
        document.querySelector('.popup').style.display = 'none';
    }, 3000);
</script>
<% } %>

<div class="form-container">
    <h2>❌ Delete Book</h2>

    <form action="DeleteBook" method="post">
        <label for="bookid">Book ID:</label>
        <input type="text" id="bookid" name="bookid" placeholder="Enter Book ID" required>

        <button type="submit">🗑️ Delete</button>
    </form>

    <a href="ViewBook">📚 View All Books</a>
</div>

</body>
</html>
