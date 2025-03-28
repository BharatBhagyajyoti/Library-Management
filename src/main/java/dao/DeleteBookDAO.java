package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteBookDAO {

    // ✅ Check if book already exists by ID
    public boolean isBookExists(String bookId) {
        try (Connection conn = DBconnection.getCon();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM book WHERE bookid = ?")) {
            
            ps.setString(1, bookId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                return true;  // Book exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Book doesn't exist
    }

    // ✅ Method to delete a book by ID
    public boolean deleteBook(String bookId) {
        if (!isBookExists(bookId)) {
            return false;  // Book not found
        }

        String query = "DELETE FROM Book WHERE bookid = ?";
        
        try (Connection conn = DBconnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, bookId);
            int rowsAffected = ps.executeUpdate();
            
            return rowsAffected > 0; // ✅ True if deleted
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
