<%@page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page import="bean_pojo.RequestBookBean"%>
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
    <title>Book Requests</title>
    <link rel="stylesheet" href="ViewBookRequest.css">
</head>
<body>

<div class="header-container">
    <a href="LoginSuccess.jsp" class="btn btn-home">🏠 Back to Home</a>
</div>



<div class="container">
    <h1>📚 Book Requests</h1>

    <table>
        <thead>
            <tr>
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
                List<RequestBookBean> bookRequests = (List<RequestBookBean>) request.getAttribute("bookRequests");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
            
            if (bookRequests != null) {
                    for (RequestBookBean req : bookRequests) {
            %>
            <tr>
                <td><%= req.getBookName() %></td>
                <td><%= req.getAuthor() %></td>
                <td><%= req.getRequestedBy() %></td>
                <td class="status"><%= req.getStatus() %></td>
                <td class="timestamp">
                        <%= (req.getRequestDate() != null) ? dateFormat.format(req.getRequestDate()) : "N/A" %>  
                 </td>
                
                <td>
                    <!-- ✅ Remove Button -->
                    <form method="post" action="ViewBookRequests" style="display:inline;">
                        <input type="hidden" name="requestId" value="<%= req.getRequestId() %>">
                        <button type="submit" name="action" value="remove" class="btn remove">❌ Remove</button>
                    </form>

                    <!-- ✅ Approve Button triggers popup -->
                    <button 
                        class="btn approve" 
                        onclick="openPopup('<%= req.getBookName() %>', '<%= req.getAuthor() %>', <%= req.getRequestId() %>)">
                        ✅ Approve
                    </button>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</div>

<!-- ✅ Popup Card Form -->
<div class="popup" id="popup">
    <div class="popup-content">
        <span class="close-btn" onclick="closePopup()">✖️</span>
        <h2>Approve Book Request</h2>
        
        <form action="ApproveBookServlet" method="post">
            <input type="hidden" id="requestId" name="requestId">
            
            <label>Book Name:</label>
            <input type="text" id="bookName" name="bookName" readonly>

            <label>Author:</label>
            <input type="text" id="author" name="author" readonly>

            <label>Book ID (Length : 6 chars):</label>
            <input type="text" name="bookId" required>

            <label>Price:</label>
            <input type="number" name="price" step="0.01" required>

            <label>Quantity:</label>
            <input type="number" name="quantity" required>

            <button type="submit" class="btn approve">Add Book</button>
        </form>
    </div>
</div>

<script>
    // ✅ Open Popup with smooth animation
    function openPopup(bookName, author, requestId) {
        const popup = document.getElementById("popup");
        const popupContent = document.querySelector(".popup-content");

        document.getElementById("bookName").value = bookName;
        document.getElementById("author").value = author;
        document.getElementById("requestId").value = requestId;
        
        popup.style.display = "flex";
        setTimeout(() => {
            popup.style.opacity = "1";
            popupContent.classList.add("slide-in");
        }, 50);
    }

    // ✅ Close Popup with smooth fade-out
    function closePopup() {
        const popup = document.getElementById("popup");
        const popupContent = document.querySelector(".popup-content");

        popupContent.classList.remove("slide-in");
        popup.style.opacity = "0";

        setTimeout(() => {
            popup.style.display = "none";
        }, 500);
    }
</script>

</body>
</html>
