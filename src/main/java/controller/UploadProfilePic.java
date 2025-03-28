package controller;



import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import dao.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/UploadProfilePic")  // ✅ Servlet mapping
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB threshold
    maxFileSize = 1024 * 1024 * 10,       // Max file size 10MB
    maxRequestSize = 1024 * 1024 * 50     // Max request size 50MB
)
public class UploadProfilePic extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");  // Fetch user ID from form
        Part filePart = request.getPart("profilePic");

        // ✅ File Upload Directory
        String uploadDirectory = "D:/LibraryManagement/profile_pics";
        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // ✅ Save Image with User ID in Filename
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String filePath = uploadDirectory + File.separator + userId + "_" + fileName;
        filePart.write(filePath);

        // ✅ Store Image Path in Database
        try (Connection conn = DBconnection.getCon();
             PreparedStatement ps = conn.prepareStatement("UPDATE library_user SET image_path = ? WHERE user_id = ?")) {

            ps.setString(1, "profile_pics/" + userId + "_" + fileName);
            ps.setString(2, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ✅ Redirect Back to Dashboard
        response.sendRedirect("UserDashboard.jsp");
    }
}
