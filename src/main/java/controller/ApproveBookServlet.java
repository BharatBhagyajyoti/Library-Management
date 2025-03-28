package controller;

import java.io.IOException;

import dao.RequestBookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ApproveBookServlet")
public class ApproveBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ Retrieve form data
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String bookId = request.getParameter("bookId");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // ✅ Approve the book request (insert into Book table and remove from book_request)
        RequestBookDAO.approveRequest(requestId, bookId, price, quantity);

        // ✅ Redirect to the book requests page
        response.sendRedirect("ViewBookRequests");
    }
}
