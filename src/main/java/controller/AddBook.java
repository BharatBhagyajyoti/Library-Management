package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import bean_pojo.BookBean;
import dao.AddBookDAO;

@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddBook() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        System.out.println("-> Inside Add Book Servlet");

     // ✅ Prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        
        
        
        
        BookBean book = new BookBean();
        book.setBookId(request.getParameter("bookid"));
        book.setBookName(request.getParameter("bookname"));
        book.setAuthor(request.getParameter("author"));
        book.setPrice(Double.parseDouble(request.getParameter("price")));
        book.setQuantity(Integer.parseInt(request.getParameter("quantity")));

        int k = new AddBookDAO().add(book);

        if (k > 0) {
            request.setAttribute("message", "✅ Book Added Successfully!");
            System.out.println("Book Added!");
        } else if (k == -1) {
            request.setAttribute("message", "⚠️ Book already exists!");
            System.out.println("Book Already Exist!");
        } else {
            request.setAttribute("message", "❌ Failed to Add Book!");
            System.out.println("Book Add Failed!");
        }

        RequestDispatcher rd = request.getRequestDispatcher("AddBook.jsp");
        rd.forward(request, response);
    }
}
