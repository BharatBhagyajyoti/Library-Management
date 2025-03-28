package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean_pojo.AdminBean;
import service.PasswordHashing;

public class AdminRegisterDAO {

	// Check if the account already exists
	public boolean isAccountExists(String email, String phone) {
		boolean exists = false;
		try {
			Connection conn = DBconnection.getCon();
			String query = "SELECT COUNT(*) FROM Admin WHERE email = ? OR phone = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, phone);

			ResultSet rs = pst.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {
				exists = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}

	// Register a new account
	public int register(AdminBean ab) {
		int k = 0;
		try {
			Connection conn = DBconnection.getCon();
			
			// Check if account already exists
			if (isAccountExists(ab.getEmail(), ab.getPhone())) {
				return -1;  // Indicates duplicate account
			}

			String query = "INSERT INTO Admin (name, email, phone, dob, password) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, ab.getName());
			pst.setString(2, ab.getEmail());
			pst.setString(3, ab.getPhone());
			pst.setDate(4, ab.getDob());
			pst.setString(5, PasswordHashing.hashPassword(ab.getPassword()));

			k = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return k;
	}
}
