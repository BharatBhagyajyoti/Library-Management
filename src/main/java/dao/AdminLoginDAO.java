package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean_pojo.AdminBean;
import service.PasswordHashing;

public class AdminLoginDAO {

    public AdminBean Login(String uName, String uPass) {
        AdminBean ab = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        System.out.println("Uname :"+uName);
        try {
            // ✅ Establish the connection
            conn = DBconnection.getCon();
            
            // ✅ Prepare the SQL statement
            pst = conn.prepareStatement("SELECT * FROM Admin WHERE EMAIL = ?");
            pst.setString(1, uName);

            rs = pst.executeQuery();

            if (rs.next()) {
                // ✅ Retrieve the stored hash password
                String storedHash = rs.getString("password");

                // ✅ Verify the hashed password
                if (PasswordHashing.checkPassword(uPass, storedHash)) {  
                    ab = new AdminBean();
                    ab.setName(rs.getString("name"));
                    ab.setEmail(rs.getString("email"));
                    ab.setPhone(rs.getString("phone"));
                    ab.setDob(rs.getDate("dob").toString());
                    
                    System.out.println("Admin Login Successful: " + ab.getEmail());
                } else {
                    System.out.println("Invalid Password!");
                }
            } else {
                System.out.println("No admin found with this email.");
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

        return ab;
    }
}
