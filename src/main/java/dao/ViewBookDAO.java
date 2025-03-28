package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean_pojo.BookBean;

public class ViewBookDAO {
	
	
	public static List<BookBean> getAllBooks()
	{
		
		System.out.println("Inside view Book DAO !");
		Connection conn=null;
		List<BookBean> bookList = new ArrayList<>();
		PreparedStatement pst = null;
        ResultSet rs = null;
		try {
			
			conn=DBconnection.getCon();
			String query = "SELECT * FROM Book";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();   // Missing query execution

			while (rs.next()) {
                BookBean book = new BookBean();
                book.setBookId(rs.getString("BookId"));
                book.setBookName(rs.getString("BookName"));
                book.setAuthor(rs.getString("Author"));
                book.setPrice(rs.getDouble("Price"));
                book.setQuantity(rs.getInt("Quantity"));
                bookList.add(book);
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bookList;
		
		
	}
	

}
