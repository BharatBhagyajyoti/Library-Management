package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    // ✅ Constructor is private to prevent instantiation
    private DBconnection() {}

    // ✅ Always create a fresh connection
    public static Connection getCon() {
        Connection conn = null;
        try {
            Class.forName(DBinfo.DBdriver);
            conn = DriverManager.getConnection(DBinfo.DBurl, DBinfo.DBuser, DBinfo.DBpassword);

            // ✅ Validate the connection before returning it
            if (conn != null && !conn.isClosed() && conn.isValid(5)) {
                System.out.println("✅ Connection Valid and Active");
            } else {
                System.out.println("🔴 Connection is closed or invalid. Reconnecting...");
                conn = DriverManager.getConnection(DBinfo.DBurl, DBinfo.DBuser, DBinfo.DBpassword);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
