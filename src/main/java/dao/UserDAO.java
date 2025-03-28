package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean_pojo.AdminBean;
import bean_pojo.UserBean;
import service.PasswordHashing;

public class UserDAO {

	// ✅ Check if Email Exists
    public static boolean isEmailRegistered(String email) {
        String query = "SELECT 1 FROM library_user WHERE email = ?";
        try (Connection conn = DBconnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            // ✅ Return true if email exists
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Register a New User
    public static boolean registerUser(UserBean user) {
        String query = "INSERT INTO library_user (user_id, name, email, password, role) VALUES (?, ?, ?, ?, 'USER')";

        try (Connection conn = DBconnection.getCon();
             PreparedStatement ps = conn.prepareStatement(query)) {

            // ✅ Hash password before storing
            String hashedPassword = PasswordHashing.hashPassword(user.getPassword());

            ps.setString(1, user.getUserId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, hashedPassword);

            int result = ps.executeUpdate();
            
            // ✅ Logging
            if (result > 0) {
                System.out.println("✅ User Registered: " + user.getEmail());
            } else {
                System.out.println("❌ Failed to Register: " + user.getEmail());
            }

            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Validate User Login
    public UserBean Login(String uName, String uPass) {
    	UserBean user = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        System.out.println("Uname :"+uName);
        try {
            // ✅ Establish the connection
            conn = DBconnection.getCon();
            
            // ✅ Prepare the SQL statement
            pst = conn.prepareStatement("SELECT * FROM library_user WHERE EMAIL = ? OR USER_ID=?");
            pst.setString(1, uName);
            pst.setString(2, uName);

            rs = pst.executeQuery();

            if (rs.next()) {
                // ✅ Retrieve the stored hash password
                String storedHash = rs.getString("password");

                // ✅ Verify the hashed password
                if (PasswordHashing.checkPassword(uPass, storedHash)) {  
                	user = new UserBean();
                	user.setName(rs.getString("name"));
                	user.setEmail(rs.getString("email"));
                	user.setRole(rs.getString("ROLE"));
                	user.setUserId(rs.getString("USER_ID"));
                    
                    System.out.println("User Login Successful: " + user.getEmail());
                } else {
                    System.out.println("Invalid Password!");
                }
            } else {
                System.out.println("No User found with this email or username.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // ✅ Close resources properly to avoid connection leaks
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }
    
    
    
    
    
    
    public UserBean getUserById(String userId) {
        UserBean user = null;

        try (Connection conn = DBconnection.getCon();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT user_id, name, email, profile_pic FROM library_user WHERE user_id = ?")) {

            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new UserBean();
                user.setUserId(rs.getString("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
