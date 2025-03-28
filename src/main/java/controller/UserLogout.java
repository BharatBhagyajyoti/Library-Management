package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import bean_pojo.AdminBean;
import bean_pojo.UserBean;



@WebServlet("/UserLogout")
public class UserLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UserLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

    	// ✅ Prevent caching
    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    	response.setHeader("Pragma", "no-cache");
    	response.setHeader("Expires", "0");

    	
    	
    	
        // ✅ Invalidate the session to log the user out
        HttpSession session = request.getSession(false);  // Prevent creating a new session
        UserBean user=(UserBean)session.getAttribute("userBean");
        System.out.println("User Logging out !"+user.getName());

        
        if (session != null) {
            session.invalidate();
        }

        // ✅ Redirect to the login page
        response.sendRedirect("UserLogin.jsp");
        
        System.out.println("User Logged out !");

    }

}
