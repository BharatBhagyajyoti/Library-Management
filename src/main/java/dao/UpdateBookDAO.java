package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean_pojo.BookBean;

public class UpdateBookDAO {

    // ✅ Check if the book exists by ID
    public boolean isBookExists(String bookId) {
        try (Connection conn = DBconnection.getCon();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM Book WHERE bookid = ?")) {
            ps.setString(1, bookId);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int update(BookBean book) {
        if (!isBookExists(book.getBookId())) {
            System.out.println("Book does not exist!");
            return -1;  // Book does not exist
        }

        int result = 0;
        StringBuilder query = new StringBuilder("UPDATE Book SET ");

        // ✅ Dynamically append only non-null fields
        boolean hasFields = false;

        if (book.getBookName() != null && !book.getBookName().isEmpty()) {
            query.append("bookname = ?, ");
            hasFields = true;
        }
        if (book.getAuthor() != null && !book.getAuthor().isEmpty()) {
            query.append("author = ?, ");
            hasFields = true;
        }
        if (book.getPrice() != 0.0) {
            query.append("price = ?, ");
            hasFields = true;
        }
        if (book.getQuantity() != 0) {
            query.append("quantity = ?, ");
            hasFields = true;
        }

        if (!hasFields) {
            System.out.println("No fields to update.");
            return 0;
        }

        // ✅ Remove trailing comma and append WHERE clause
        query.setLength(query.length() - 2);
        query.append(" WHERE bookid = ?");

        try (Connection conn = DBconnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query.toString())) {

            int index = 1;

            if (book.getBookName() != null && !book.getBookName().isEmpty()) {
                ps.setString(index++, book.getBookName());
            }
            if (book.getAuthor() != null && !book.getAuthor().isEmpty()) {
                ps.setString(index++, book.getAuthor());
            }
            if (book.getPrice() != 0.0) {
                ps.setDouble(index++, book.getPrice());
            }
            if (book.getQuantity() != 0) {
                ps.setInt(index++, book.getQuantity());
            }

            ps.setString(index, book.getBookId());

            result = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
