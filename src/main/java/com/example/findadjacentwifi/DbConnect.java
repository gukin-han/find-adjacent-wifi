package com.example.findadjacentwifi;

import java.sql.*;

public class DbConnect {
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:" + "SQLiteDB.db");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insert () {
            String sql = "INSERT INTO wifi(name) VALUES(?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "gukin");
            pstmt.executeUpdate();
            System.out.println("works!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        getConnection();
        insert();
        closeConnection();
    }

}
