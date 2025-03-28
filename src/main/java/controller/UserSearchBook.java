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

@WebServlet("/UserSearchBook")
public class UserSearchBook extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bookName = request.getParameter("bookname");

        // Fetch books matching the search term
        List<BookBean> books = UserBookDAO.searchBooksByName(bookName);

        // Set the search results as an attribute
        request.setAttribute("books", books);

        // Forward to the JSP page for displaying search results
        request.getRequestDispatcher("SearchBook.jsp").forward(request, response);
    }
}
