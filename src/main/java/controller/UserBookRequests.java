package controller;

import java.io.IOException;
import java.util.List;

import bean_pojo.RequestBookBean;
import dao.RequestBookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UserBookRequests")
public class UserBookRequests extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            // ✅ Fetch only the logged-in user's requests
            List<RequestBookBean> bookRequests = RequestBookDAO.getRequestsByUser(userId);

            request.setAttribute("bookRequests", bookRequests);
            request.getRequestDispatcher("UserBookRequests.jsp").forward(request, response);
        } else {
            response.sendRedirect("UserLogin.jsp");
        }
    }
}
