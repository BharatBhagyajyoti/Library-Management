package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import bean_pojo.AdminBean;
import dao.AdminLoginDAO;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    public AdminLogin() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

    	
    	// ✅ Prevent caching
    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    	response.setHeader("Pragma", "no-cache");
    	response.setHeader("Expires", "0");

    	
    	
    	
        String userName = request.getParameter("email");
        String password = request.getParameter("password");

        AdminBean ab = new AdminLoginDAO().Login(userName, password);
        HttpSession session = request.getSession();
        if (ab != null) {
            
            session.setAttribute("adminBean", ab);

            request.setAttribute("status", "success");
            request.setAttribute("message", "Welcome Back, " + ab.getName() + "!");
            
//            RequestDispatcher rd = request.getRequestDispatcher("LoginSuccess.jsp");
//            rd.forward(request, response);
            // ✅ Use redirect to prevent form resubmission
            response.sendRedirect("LoginSuccess.jsp");

        } else {
            request.setAttribute("status", "error");
            request.setAttribute("errorMessage", "Invalid email or password!");
            
//            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
//            rd.forward(request, response);
         // ✅ Store the error message temporarily in the session
            session.setAttribute("errorMessage", "Invalid email or password!");
            
            response.sendRedirect("Login.jsp");

        }
    }
}
