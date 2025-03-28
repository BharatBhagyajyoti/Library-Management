<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, bean_pojo.RequestBookBean" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Book Requests</title>
    <link rel="stylesheet" href="UserBookRequests.css"> <!-- External CSS -->
</head>
<body>

<div class="container">

    <!-- ✅ Header Section -->
    <header>
        <h1>📚 My Book Requests</h1>
    </header>

    <!-- ✅ Book Requests Table -->
    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <th>📄 Request ID</th>
                    <th>📖 Book Name</th>
                    <th>✍️ Author</th>
                    <th>📌 Status</th>
                    <th>🗓️ Request Date</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<RequestBookBean> requests = (List<RequestBookBean>) request.getAttribute("bookRequests");

                    if (requests != null && !requests.isEmpty()) {
                        for (RequestBookBean req : requests) {
                %>
                <tr>
                    <td><%= req.getRequestId() %></td>
                    <td><%= req.getBookName() %></td>
                    <td><%= req.getAuthor() %></td>
                    <td>
                        <% 
                            String status = req.getStatus();
                            String statusClass = "";
                            if ("PENDING".equals(status)) {
                                statusClass = "pending";
                            } else if ("APPROVED".equals(status)) {
                                statusClass = "approved";
                            } else {
                                statusClass = "rejected";
                            }
                        %>
                        <span class="<%= statusClass %>"><%= status %></span>
                    </td>
                    <td><%= req.getRequestDate() %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5" class="no-requests">🚫 No book requests found.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>

    <!-- ✅ Back to Dashboard Button -->
    <div class="btn-container">
        <a href="UserDashboard.jsp" class="btn">⬅️ Back to Dashboard</a>
    </div>

    <footer>
        <p>&copy; 2025 Library Management System | Made with ❤️</p>
    </footer>

</div>

</body>
</html>
