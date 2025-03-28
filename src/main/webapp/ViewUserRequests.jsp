<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, bean_pojo.RequestBookBean" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Book Requests</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="container">
    <h2>📚 Book Requests</h2>

    <table>
        <thead>
            <tr>
                <th>Request ID</th>
                <th>Book Name</th>
                <th>Author</th>
                <th>Requested By</th>
                <th>Status</th>
                <th>Request Date</th>
                <th>Action</th>
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
                <td><%= req.getRequestedBy() %></td>
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
                <td>
                    <form method="post" action="ViewBookRequests">
                        <input type="hidden" name="requestId" value="<%= req.getRequestId() %>">
                        <input type="hidden" name="bookName" value="<%= req.getBookName() %>">
                        <input type="hidden" name="author" value="<%= req.getAuthor() %>">

                        <button type="submit" name="action" value="approve" class="btn-approve">Approve</button>
                        <button type="submit" name="action" value="remove" class="btn-remove">Remove</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="7">No book requests found.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <a href="UserDashboard.jsp" class="btn">⬅️ Back to Dashboard</a>
</div>

</body>
</html>
