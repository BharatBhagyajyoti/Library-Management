<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    // ✅ Session checking
    HttpSession sessionCheck = request.getSession(false);
    if (sessionCheck == null || sessionCheck.getAttribute("adminBean") == null) {
        response.sendRedirect("Login.jsp");  // Redirect to login if no session
        return;
    }

    // ✅ Cache-Control Headers<%
    // 🌟 Prevent browser caching
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", -1);


%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Book Form</title>
    <link rel="stylesheet" type="text/css" href="AddBook.css">
    
    <!-- ✅ Add SweetAlert Library -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>
<body>

<div class="form-container">
    <h2>📚 Book Details Form</h2>

    <!-- ✅ Form Section -->
    <form action="AddBook" method="post">  
        <label for="bookid">Book ID (Length : 6 chars):</label>
        <input type="text" id="bookid" name="bookid" placeholder="Enter Book ID" maxlength="6" required>

        <label for="bookname">Book Name:</label>
        <input type="text" id="bookname" name="bookname" placeholder="Enter Book Name" maxlength="30" required>

        <label for="author">Author:</label>
        <input type="text" id="author" name="author" placeholder="Enter Author Name" maxlength="45" required>

        <label for="price">Price:</label>
        <input type="number" id="price" name="price" placeholder="Enter Price" min="0" step="0.01" required>

        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" placeholder="Enter Quantity" min="0" required>

        <button type="submit">🛒 Submit</button>
    </form>
</div>

<!-- ✅ Popup Message Handling -->
<script>
    <% 
        String message = (String) request.getAttribute("message");
        if (message != null && !message.isEmpty()) { 
            if (message.contains("Successfully")) { 
    %>
                Swal.fire({
                    icon: 'success',
                    title: 'Success!',
                    text: '<%= message %>',
                    showConfirmButton: true,
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: 'OK'
                });
    <% 
            } else if (message.contains("Failed") || message.contains("exists")) { 
    %>
                Swal.fire({
                    icon: 'error',
                    title: 'Oops!',
                    text: '<%= message %>',
                    showConfirmButton: true,
                    confirmButtonColor: '#d33',
                    confirmButtonText: 'OK'
                });
    <% 
            }
        } 
    %>
</script>

</body>
</html>
