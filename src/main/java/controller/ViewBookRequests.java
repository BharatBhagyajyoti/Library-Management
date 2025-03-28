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

@WebServlet("/ViewBookRequests")
public class ViewBookRequests extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("-> Inside ViewBookRequests servlet");

        List<RequestBookBean> bookRequests = RequestBookDAO.getAllRequests();

        request.setAttribute("bookRequests", bookRequests);

        request.getRequestDispatcher("ViewBookRequests.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        int requestId = Integer.parseInt(request.getParameter("requestId"));

        if ("remove".equals(action)) {
            // ✅ Remove the request
            RequestBookDAO.removeRequest(requestId);

        } else if ("approve".equals(action)) {
            // ✅ Store request ID and display popup form
            String bookName = request.getParameter("bookName");
            String author = request.getParameter("author");

            request.setAttribute("bookName", bookName);
            request.setAttribute("author", author);
            request.setAttribute("requestId", requestId);

            request.getRequestDispatcher("ViewBookRequests.jsp").forward(request, response);
            return;
        }

        response.sendRedirect("ViewBookRequests");
    }
}
