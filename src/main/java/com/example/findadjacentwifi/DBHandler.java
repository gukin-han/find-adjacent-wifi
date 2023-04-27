package com.example.findadjacentwifi;

import java.sql.*;
import java.util.List;

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

    public static void insertWifi (Wifi wifi) {
        String sql = "INSERT INTO " +
                "wifi_unit " +
                "(" +
                "distance," +
                "mgr_no," +
                "wrdofc," +
                "main_nm," +
                "adres1," +
                "arres2," +
                "instl_floor," +
                "instl_mby," +
                "instal_ty," +
                "svc_se," +
                "cmcwr," +
                "cnstc_year," +
                "inout_door," +
                "remars3," +
                "lat," +
                "lnt," +
                "work_dttm" +
                ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, null);
            pstmt.setObject(2, wifi.getX_SWIFI_MGR_NO());
            pstmt.setObject(3, wifi.getX_SWIFI_WRDOFC());
            pstmt.setObject(4, wifi.getX_SWIFI_MAIN_NM());
            pstmt.setObject(5, wifi.getX_SWIFI_ADRES1());
            pstmt.setObject(6, wifi.getX_SWIFI_ADRES2());
            pstmt.setObject(7, wifi.getX_SWIFI_INSTL_FLOOR());
            pstmt.setObject(8, wifi.getX_SWIFI_INSTL_MBY());
            pstmt.setObject(9, wifi.getX_SWIFI_INSTL_TY());
            pstmt.setObject(10, wifi.getX_SWIFI_SVC_SE());
            pstmt.setObject(11, wifi.getX_SWIFI_CMCWR());
            pstmt.setObject(12, wifi.getX_SWIFI_CNSTC_YEAR());
            pstmt.setObject(13, wifi.getX_SWIFI_INOUT_DOOR());
            pstmt.setObject(14, wifi.getX_SWIFI_REMARS3());
            pstmt.setObject(15, wifi.getLAT());
            pstmt.setObject(16, wifi.getLNT());
            pstmt.setObject(17, wifi.getWORK_DTTM());

            pstmt.executeUpdate();
            System.out.println("works!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void creatTable() {
        String sql = "create table if not exists wifi_unit" +
                "(" +
                "distance nummeric," +
                "mgr_no text," +
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

    public static void dropTable() {
        String sql = "drop table if exists wifi_unit";

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
        dropTable();
        closeConnection();
    }
}
