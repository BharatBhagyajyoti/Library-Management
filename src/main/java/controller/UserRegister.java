package controller;

import java.io.IOException;

import bean_pojo.UserBean;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // ✅ Prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        // ✅ Get form data
        String userId = request.getParameter("user_id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // ✅ Check if the email is already registered
        if (UserDAO.isEmailRegistered(email)) {
            System.out.println("⚠️ Email already exists: " + email);

            // ✅ Redirect with existing email status
            response.sendRedirect("UserRegistration.jsp?status=exists&email=" + email);
            return;
        }

        // ✅ Create UserBean object
        UserBean user = new UserBean();
        user.setUserId(userId);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);  // Will be hashed inside DAO

        // ✅ Register the user
        boolean isRegistered = UserDAO.registerUser(user);

        if (isRegistered) {
            System.out.println("✅ User Registered: " + email);

            // ✅ Redirect to JSP with success message
            response.sendRedirect("UserRegistration.jsp?status=success&email=" + email);
        } else {
            System.out.println("❌ Registration Failed: " + email);

            // ✅ Redirect with error status
            response.sendRedirect("UserRegistration.jsp?status=error");
        }
    }
}
