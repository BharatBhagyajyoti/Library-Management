package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.DeleteBookDAO;

@WebServlet("/DeleteBook")
public class DeleteBook extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteBook() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	// ✅ Prevent caching
    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    	response.setHeader("Pragma", "no-cache");
    	response.setHeader("Expires", "0");

    	
    	
    	
    	
    	
    	String bookId = request.getParameter("bookid");

        DeleteBookDAO dao = new DeleteBookDAO();
        boolean isDeleted = dao.deleteBook(bookId);

        // ✅ Set message as request attribute for popup display
        if (isDeleted) {
            request.setAttribute("message", "✅ Book Deleted Successfully!");
        } else {
            request.setAttribute("message", "❌ Book Not Found or Deletion Failed!");
        }

        // ✅ Forward the request back to the form page
        request.getRequestDispatcher("DeleteBook.jsp").forward(request, response);
    }
}
