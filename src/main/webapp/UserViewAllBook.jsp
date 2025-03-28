<%@ page import="java.util.List" %>
<%@ page import="bean_pojo.BookBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Books</title>
    <link rel="stylesheet" href="UserViewAllBook.css"> <!-- Separate CSS for book styling -->
</head>
<body>

<div class="container">

    <!-- ✅ Header Section -->
    <header>
        <h1>📚 All Available Books</h1>
    </header>

    <div class="book-container">
        <table>
            <thead>
                <tr>
                    <th>📖 Book ID</th>
                    <th>📚 Book Name</th>
                    <th>✍️ Author</th>
                    <th>💰 Price</th>
                    <th>🔢 Quantity</th>
                    <th>✅ Availability</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<BookBean> books = (List<BookBean>) request.getAttribute("books");
                    if (books != null && !books.isEmpty()) {
                        for (BookBean book : books) {
                %>
                <tr>
                    <td><%= book.getBookId() %></td>
                    <td><%= book.getBookName() %></td>
                    <td><%= book.getAuthor() %></td>
                    <td>₹<%= book.getPrice() %></td>
                    <td><%= book.getQuantity() %></td>
                    <td>
                        <span class="<%= book.getAvailability().equalsIgnoreCase("Available") ? "available" : "unavailable" %>">
                            <%= book.getAvailability() %>
                        </span>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6" class="no-books">No books available.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>

    <!-- 🎯 Button to go back to UserDashboard -->
    <div class="btn-container">
        <a href="UserDashboard.jsp" class="btn-back">🏠 Go to Home</a>
    </div>

    <footer>
        <p>&copy; 2025 Library Management System | Made with ❤️</p>
    </footer>

</div>

</body>
</html>
