package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean_pojo.RequestBookBean;

public class RequestBookDAO {

    // ✅ Method to insert book request into the book_request table
    public static boolean requestBook(RequestBookBean requestBook) {
        String query = "INSERT INTO book_request (bookname, author, requested_by, status, request_date) " +
                       "VALUES (?, ?, ?, 'PENDING', ?)";

        try (Connection conn = DBconnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, requestBook.getBookName());
            ps.setString(2, requestBook.getAuthor());
            ps.setString(3, requestBook.getRequestedBy());  // ✅ Use user_id
            ps.setTimestamp(4, requestBook.getRequestDate());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    // ✅ Method to retrieve all book requests
    public static List<RequestBookBean> getAllRequests() {
        List<RequestBookBean> requests = new ArrayList<>();
        String query = "SELECT request_id, bookname, author, requested_by, status, request_date FROM book_request";

        try (Connection conn = DBconnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RequestBookBean request = new RequestBookBean();
                request.setRequestId(rs.getInt("request_id"));
                request.setBookName(rs.getString("bookname"));
                request.setAuthor(rs.getString("author"));
                request.setRequestedBy(rs.getString("requested_by"));
                request.setStatus(rs.getString("status"));
                request.setRequestDate(rs.getTimestamp("request_date"));
                
                requests.add(request);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return requests;
    }


    // ✅ Method to get requests for a specific user
    public static List<RequestBookBean> getRequestsByUser(String userId) {
        List<RequestBookBean> requests = new ArrayList<>();
        String query = "SELECT * FROM book_request WHERE requested_by = ?";

        try (Connection conn = DBconnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RequestBookBean request = new RequestBookBean();
                    request.setRequestId(rs.getInt("request_id"));
                    request.setBookName(rs.getString("bookname"));
                    request.setAuthor(rs.getString("author"));
                    request.setRequestedBy(rs.getString("requested_by"));
                    request.setStatus(rs.getString("status"));
                    request.setRequestDate(rs.getTimestamp("request_date"));

                    requests.add(request);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return requests;
    }


 // ✅ Method to approve request: Add book and remove request
    public static void approveRequest(int requestId, String bookId, double price, int quantity) {
        String selectQuery = "SELECT bookname, author FROM book_request WHERE request_id = ?";
        String insertQuery = "INSERT INTO Book (BOOKID, BOOKNAME, AUTHOR, PRICE, QUANTITY) VALUES (?, ?, ?, ?, ?)";
        String deleteQuery = "DELETE FROM book_request WHERE request_id = ?";

        try (Connection conn = DBconnection.getCon();
             PreparedStatement selectPs = conn.prepareStatement(selectQuery)) {

            selectPs.setInt(1, requestId);

            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                    String bookName = rs.getString("bookname");
                    String author = rs.getString("author");

                    // ✅ Insert into Book table
                    try (PreparedStatement insertPs = conn.prepareStatement(insertQuery)) {
                        insertPs.setString(1, bookId);
                        insertPs.setString(2, bookName);
                        insertPs.setString(3, author);
                        insertPs.setDouble(4, price);
                        insertPs.setInt(5, quantity);
                        insertPs.executeUpdate();
                    }

                    // ✅ Remove request
                    try (PreparedStatement deletePs = conn.prepareStatement(deleteQuery)) {
                        deletePs.setInt(1, requestId);
                        deletePs.executeUpdate();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Remove request by ID
    public static void removeRequest(int requestId) {
        String query = "DELETE FROM book_request WHERE request_id = ?";
        
        try (Connection conn = DBconnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, requestId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
