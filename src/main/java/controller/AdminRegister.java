package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import bean_pojo.AdminBean;
import dao.AdminRegisterDAO;

@WebServlet("/AdminRegistration")
public class AdminRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminRegister() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ✅ Prevent caching
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		
		
		
		
		AdminBean ab = new AdminBean();
		ab.setName(request.getParameter("name"));
		ab.setEmail(request.getParameter("email"));
		ab.setPhone(request.getParameter("phone"));
		ab.setDob(request.getParameter("dob"));
		ab.setPassword(request.getParameter("password"));

		int k = new AdminRegisterDAO().register(ab);

		// Handle different registration cases
		if (k > 0) {
			System.out.println("Registration Successful!");
			request.setAttribute("message", "🎉 Registration Successful!");
			request.setAttribute("status", "success");
		} else if (k == -1) {  // Account already exists
			System.out.println("Account already exists!");
			request.setAttribute("message", "⚠️ Account already exists!");
			request.setAttribute("status", "warning");
		} else {
			System.out.println("Registration Failed!");
			request.setAttribute("message", "❌ Registration Failed!");
			request.setAttribute("status", "error");
		}

		// Forward to the registration page
		RequestDispatcher rd = request.getRequestDispatcher("Registration.jsp");
		rd.forward(request, response);
	}
}
