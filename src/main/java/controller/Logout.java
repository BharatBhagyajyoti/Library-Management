package controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import bean_pojo.AdminBean;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Logout() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

    	// ✅ Prevent caching
    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    	response.setHeader("Pragma", "no-cache");
    	response.setHeader("Expires", "0");

    	

    	
        // ✅ Invalidate the session to log the user out
        HttpSession session = request.getSession(false);  // Prevent creating a new session
        AdminBean admin=(AdminBean)session.getAttribute("adminBean");
        System.out.println("Admin Logging out ! :"+admin.getName());

        
        if (session != null) {
            session.invalidate();
        }

        // ✅ Redirect to the login page
        response.sendRedirect("Login.jsp");
        System.out.println("Admin Logged out !");

    }
}
