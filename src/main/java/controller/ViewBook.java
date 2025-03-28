package controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import bean_pojo.BookBean;
import dao.ViewBookDAO;

@WebServlet("/ViewBook")
public class ViewBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ViewBook() {
        super();
        // TODO Auto-generated constructor stub
    }


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
		
		// ✅ Prevent caching
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		
		
		
		System.out.println("inside ViewBook Servlet !");

        List<BookBean> bookList = ViewBookDAO.getAllBooks();
        System.out.println(bookList);
     // ✅ Print the book data to verify retrieval
        if (bookList != null && !bookList.isEmpty()) {
            System.out.println("Retrieved Books from DB:");
            for (BookBean book : bookList) {
                System.out.println("ID: " + book.getBookId() + 
                                   ", Name: " + book.getBookName() + 
                                   ", Author: " + book.getAuthor() + 
                                   ", Price: " + book.getPrice() + 
                                   ", Quantity: " + book.getQuantity());
            }
        } else {
            System.out.println("No books found or DB connection issue.");
        }
        
        
        
        
        // Set the book list as an attribute
        request.setAttribute("books", bookList);

        // Forward to JSP
        request.getRequestDispatcher("ViewBook.jsp").forward(request, response);
    }

}
