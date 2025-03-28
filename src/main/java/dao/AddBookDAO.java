package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean_pojo.BookBean;

public class AddBookDAO {

    // ✅ Check if book already exists by ID
    public boolean isBookExists(String bookId) {
        boolean exists = false;
        try (Connection conn = DBconnection.getCon();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM book WHERE bookid = ?")) {
            ps.setString(1, bookId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                exists = rs.getInt(1) > 0;  // Check if count > 0
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    // ✅ Add Book Method
    public int add(BookBean book) {
        // Check for duplicate book ID
        if (isBookExists(book.getBookId())) {
            System.out.println("Book already exists!");
            return -1;  // Return -1 for duplicate entry
        }

        int result = 0;
        String query = "INSERT INTO Book (bookid, bookname, author, price, quantity) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBconnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, book.getBookId());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthor());
            ps.setDouble(4, book.getPrice());
            ps.setInt(5, book.getQuantity());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
