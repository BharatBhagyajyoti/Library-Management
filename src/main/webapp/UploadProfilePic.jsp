<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Upload Profile Picture</title>
    <link rel="stylesheet" href="UserDashboard.css">
</head>
<body>

<div class="upload-container">
    <h2>📷 Upload Profile Picture</h2>
    <form action="UploadProfilePic" method="post" enctype="multipart/form-data">
        <label>Select Profile Picture:</label>
        <input type="file" name="profilePic" accept="image/*" required>
        <button type="submit">Upload</button>
    </form>

    <a href="UserDashboard.jsp" class="btn-back">⬅️ Back to Dashboard</a>
</div>

</body>
</html>
