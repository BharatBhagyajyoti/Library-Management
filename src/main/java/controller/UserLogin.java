package controller;

import java.io.IOException;

import bean_pojo.UserBean;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // ✅ Prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        String userName = request.getParameter("uName");
        String password = request.getParameter("password");

        UserBean user = new UserDAO().Login(userName, password);

        HttpSession session = request.getSession();

        if (user != null) {
            session.setAttribute("userBean", user);
            session.setAttribute("userId", user.getUserId());  // ✅ Store user ID in session
            response.sendRedirect("UserDashboard.jsp");

        } else {
            session.setAttribute("errorMessage", "Invalid email or password!");
            response.sendRedirect("UserLogin.jsp");
        }
    }
}
