package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import bean_pojo.BookBean;
import dao.UpdateBookDAO;

@WebServlet("/UpdateBook")
public class UpdateBook extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateBook() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Inside UpdateBook!");

     // ✅ Prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        
        
        
        // ✅ Retrieve form values
        String bookId = request.getParameter("bookid");
        String bookName = request.getParameter("bookname");
        String author = request.getParameter("author");

        // ✅ Handle null or empty values for bookName and author
        bookName = (bookName != null && !bookName.trim().isEmpty()) ? bookName : null;
        author = (author != null && !author.trim().isEmpty()) ? author : null;

        // ✅ Handle optional price and quantity
        String priceStr = request.getParameter("price");
        String quantityStr = request.getParameter("quantity");

        double price = (priceStr != null && !priceStr.isEmpty()) ? Double.parseDouble(priceStr) : 0.0;
        int quantity = (quantityStr != null && !quantityStr.isEmpty()) ? Integer.parseInt(quantityStr) : 0;

        // ✅ Set values in BookBean
        BookBean book = new BookBean();
        book.setBookId(bookId);
        book.setBookName(bookName);
        book.setAuthor(author);
        book.setPrice(price);
        book.setQuantity(quantity);

        // ✅ Perform the update
        int k = new UpdateBookDAO().update(book);

        if (k > 0) {
            request.setAttribute("message", "✅ Book Updated Successfully!");
            System.out.println("Book Updated!");
        } else if (k == -1) {
            request.setAttribute("message", "⚠️ Book does not exist!");
            System.out.println("Book Not Exist!");
        } else {
            request.setAttribute("message", "❌ Failed to Update Book!");
            System.out.println("Book Update Failed!");
        }

        RequestDispatcher rd = request.getRequestDispatcher("UpdateBook.jsp");
        rd.include(request, response);
    }
}
