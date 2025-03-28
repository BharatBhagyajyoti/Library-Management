package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

import bean_pojo.RequestBookBean;
import bean_pojo.UserBean;
import dao.RequestBookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RequestBook")
public class RequestBook extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("-> Inside RequestBook servlet");

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("userBean");  // Get the user details from session

        System.out.println(user.toString());
        // ✅ Ensure user is logged in
        if (user == null) {
            response.sendRedirect("UserLogin.jsp");
            return;
        }

        String bookName = request.getParameter("bookname");
        String author = request.getParameter("author");

        // Create RequestBookBean object
        RequestBookBean requestBook = new RequestBookBean();
        requestBook.setBookName(bookName);
        requestBook.setAuthor(author);
        requestBook.setRequestedBy(user.getUserId());  // ✅ Store using user ID
        requestBook.setRequestDate(Timestamp.from(Instant.now()));  // Set current timestamp

        // Call DAO to insert the request
        boolean isRequested = RequestBookDAO.requestBook(requestBook);

        if (isRequested) {
            response.sendRedirect("RequestBook.jsp?success=1");
        } else {
            response.sendRedirect("RequestBook.jsp?error=1");
        }
    }
}
