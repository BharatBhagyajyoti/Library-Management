<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, bean_pojo.BookBean" %>

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
    <title>Book List</title>
    <link rel="stylesheet" href="ViewBook.css">
</head>
<body>

<div class="container">
    <h2>📚 Book List</h2>

    <%
        System.out.println("Inside JSP bookview");
        List<BookBean> bookList = (List<BookBean>) request.getAttribute("books");
    %>

    <table>
        <thead>
            <tr>
                <th>📖 Book ID</th>
                <th>📚 Book Name</th>
                <th>✍️ Author</th>
                <th>💰 Price</th>
                <th>📦 Quantity</th>
            </tr>
        </thead>
        <tbody>
        <%
        if (bookList != null && !bookList.isEmpty()) {
            for (BookBean book : bookList) {
        %>
            <tr>
                <td><%= book.getBookId() %></td>
                <td><%= book.getBookName() %></td>
                <td><%= book.getAuthor() %></td>
                <td>₹ <%= book.getPrice() %></td>
                <td><%= book.getQuantity() %></td>
            </tr>
        <%
            }
        } else {
        %>
            <tr>
                <td colspan="5" class="no-books">🚫 No books found!</td>
            </tr>
        <%
        }
        %>
        </tbody>
    </table>

    <div class="btn-container">
        <a href="AddBook.jsp" class="btn">➕ Add New Book</a>
        <a href="LoginSuccess.jsp" class="btn">🏠 Back to Home</a>
    </div>
</div>

</body>
</html>
