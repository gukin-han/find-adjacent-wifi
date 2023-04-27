package com.example.findadjacentwifi;

import java.sql.*;

public class DBHandler {
    private static Connection conn;

    public static Connection createConnection() {
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


    public static void creatTable() {
        String sql = "create table if not exists wifi_unit " +
                "(" +
                "distance nummeric," +
                "wrdofc text," +
                "main_nm text," +
                "adres1 text," +
                "arres2 text," +
                "instl_floor text," +
                "instl_mby text," +
                "instal_ty text," +
                "svc_se text," +
                "cmcwr text," +
                "cnstc_year text," +
                "inout_door text," +
                "remars3 text," +
                "lat text," +
                "lnt text," +
                "work_dttm text" +
                ")";

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("works!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createConnection();
        creatTable();
        closeConnection();
    }
}
