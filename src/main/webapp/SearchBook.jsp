<%@ page import="java.util.List" %>
<%@ page import="bean_pojo.BookBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Book</title>
    <link rel="stylesheet" href="SearchBook.css"> <!-- External CSS -->
</head>
<body>

<div class="container">

    <!-- ✅ Header -->
    <header>
        <h1>🔎 Search Books</h1>
        <p>Find your favorite books with ease!</p>
    </header>

    <!-- ✅ Search Form -->
    <div class="search-box">
        <form action="UserSearchBook" method="post">
            <input type="text" name="bookname" id="bookname" placeholder="Enter book name..." required>
            <button type="submit">🔍 Search</button>
        </form>
    </div>

    <!-- ✅ Results Section -->
    <div class="result-container">
        <table>
            <thead>
                <tr>
                    <th>📄 ID</th>
                    <th>📚 Book Name</th>
                    <th>✍️ Author</th>
                    <th>💰 Price</th>
                    <th>📦 Quantity</th>
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
                     <td><%= book.getAvailability() %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6" class="no-results">🚫 No books found.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>

    <!-- ✅ Navigation -->
    <div class="btn-container">
        <a href="UserDashboard.jsp" class="btn">⬅️ Back to Dashboard</a>
    </div>

    <!-- ✅ Footer -->
    <footer>
        <p>&copy; 2025 Library Management System | Made with ❤️</p>
    </footer>

</div>

</body>
</html>
