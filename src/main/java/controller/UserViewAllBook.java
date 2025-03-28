package controller;

import java.io.IOException;
import java.util.List;
import bean_pojo.BookBean;
import dao.UserBookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserViewAllBook")
public class UserViewAllBook extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Fetch all books from DAO
        List<BookBean> books = UserBookDAO.getAllBooks();

        // Set the book list as an attribute
        request.setAttribute("books", books);

        // Forward to the JSP page
        request.getRequestDispatcher("UserViewAllBook.jsp").forward(request, response);
    }
}
