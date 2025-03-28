package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean_pojo.BookBean;


public class UserBookDAO {
	
	
	// ✅ Fetch all books from the database
    public static List<BookBean> getAllBooks() {
        List<BookBean> bookList = new ArrayList<>();

        String query = "SELECT * FROM book";

        try (Connection conn = DBconnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BookBean book = new BookBean();
                book.setBookId(rs.getString("bookid"));
                book.setBookName(rs.getString("bookname"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getDouble("price"));
                book.setQuantity(rs.getInt("quantity"));
                book.setAvailability(rs.getString("availability"));

                bookList.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }
    
    
    
    
 // ✅ Method to search books by name
    public static List<BookBean> searchBooksByName(String bookName) {
        List<BookBean> bookList = new ArrayList<>();

        String query = "SELECT * FROM book WHERE LOWER(bookname) LIKE LOWER(?)";

        try (Connection conn = DBconnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, "%" + bookName + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BookBean book = new BookBean();
                    book.setBookId(rs.getString("bookid"));
                    book.setBookName(rs.getString("bookname"));
                    book.setAuthor(rs.getString("author"));
                    book.setPrice(rs.getDouble("price"));
                    book.setQuantity(rs.getInt("quantity"));
                    book.setAvailability(rs.getString("availability"));

                    bookList.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookList;
    }
    
    
    
    
	
	

}
