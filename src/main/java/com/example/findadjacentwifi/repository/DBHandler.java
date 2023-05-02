package com.example.findadjacentwifi.repository;

import com.example.findadjacentwifi.domain.History;
import com.example.findadjacentwifi.domain.Wifi;

import java.sql.*;

public class DBHandler {
    private Connection conn;

    public void createConnection() {
        if (conn == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:SQLiteDB.db");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createTable() {
        String[] sqls = new String[]{"CREATE TABLE IF NOT EXISTS wifi_unit (\n" +
                "  distance REAL,\n" +
                "  mgr_no TEXT PRIMARY KEY,\n" +
                "  wrdofc TEXT,\n" +
                "  main_nm TEXT,\n" +
                "  adres1 TEXT,\n" +
                "  adres2 TEXT,\n" +
                "  instl_floor TEXT,\n" +
                "  instl_mby TEXT,\n" +
                "  instl_ty TEXT,\n" +
                "  svc_se TEXT,\n" +
                "  cmcwr TEXT,\n" +
                "  cnstc_year TEXT,\n" +
                "  inout_door TEXT,\n" +
                "  remars3 TEXT,\n" +
                "  lat TEXT,\n" +
                "  lnt TEXT,\n" +
                "  work_dttm TEXT\n" +
                ");\n", "CREATE TABLE IF NOT EXISTS lookup_history (\n" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  lat TEXT,\n" +
                "  lnt TEXT,\n" +
                "  lookup_date DATETIME DEFAULT CURRENT_TIMESTAMP\n" +
                ");","CREATE TABLE IF NOT EXISTS bookmark_group (\n" +
                "                 id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "                 name TEXT,\n" +
                "                 sort_order INTEGER,\n" +
                "                 created_date DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "                 edited_date TEXT);","CREATE TABLE bookmark (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    bookmark_group_id INTEGER,\n" +
                "    wifi_id text,\n" +
                "    created_date DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "    FOREIGN KEY (bookmark_group_id) REFERENCES bookmark_group(id),\n" +
                "    FOREIGN KEY (wifi_id) REFERENCES wifi(mgr_no)\n" +
                ");"};

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            for (String sql : sqls) {
                stmt.execute(sql);
            }
            System.out.println("The table is created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        String[] sqls = {"drop table if exists wifi_unit;"
                ,"drop table if exists lookup_history;"
                ,"drop table if exists bookmark_group;"
                ,"drop table if exists bookmark;"};

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            for (String sql : sqls) {
                stmt.execute(sql);
            }
            System.out.println("The table is removed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertWifi (Wifi wifi) {
        String sql = "INSERT INTO wifi_unit (" +
                "distance, mgr_no, wrdofc, main_nm, adres1, adres2, instl_floor, instl_mby, instl_ty, svc_se," +
                "cmcwr, cnstc_year,inout_door, remars3, lat, lnt, work_dttm" +
                ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, 0.0);
            pstmt.setString(2, wifi.getX_SWIFI_MGR_NO());
            pstmt.setString(3, wifi.getX_SWIFI_WRDOFC());
            pstmt.setString(4, wifi.getX_SWIFI_MAIN_NM());
            pstmt.setString(5, wifi.getX_SWIFI_ADRES1());
            pstmt.setString(6, wifi.getX_SWIFI_ADRES2());
            pstmt.setString(7, wifi.getX_SWIFI_INSTL_FLOOR());
            pstmt.setString(8, wifi.getX_SWIFI_INSTL_MBY());
            pstmt.setString(9, wifi.getX_SWIFI_INSTL_TY());
            pstmt.setString(10, wifi.getX_SWIFI_SVC_SE());
            pstmt.setString(11, wifi.getX_SWIFI_CMCWR());
            pstmt.setString(12, wifi.getX_SWIFI_CNSTC_YEAR());
            pstmt.setString(13, wifi.getX_SWIFI_INOUT_DOOR());
            pstmt.setString(14, wifi.getX_SWIFI_REMARS3());
            pstmt.setString(15, wifi.getLAT());
            pstmt.setString(16, wifi.getLNT());
            pstmt.setString(17, wifi.getWORK_DTTM());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertHistory(History history) {
        String sql = "INSERT INTO lookup_history (lat, lnt) VALUES (?, ?);";

        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, history.getLat());
            pstmt.setString(2, history.getLnt());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet selectAllWifi() {

        Statement stmt;
        ResultSet resultSet = null;
        try {

            stmt = conn.createStatement();
            resultSet = stmt.executeQuery("SELECT * FROM wifi_unit");
            System.out.println("Get all wifi");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public ResultSet selectAllHistory() {

        Statement stmt;
        ResultSet resultSet = null;
        try {

            stmt = conn.createStatement();
            resultSet = stmt.executeQuery("SELECT * FROM lookup_history");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public ResultSet selectOneWifi(String mgrNo) {

        String sql = "SELECT * FROM wifi_unit where mgr_no=?";

        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mgrNo);

            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void updateDistance(double distance, String mgr_no) {
        String sql = "update wifi_unit set distance = ? where mgr_no = ?";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, distance);
            pstmt.setString(2, mgr_no);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet selectNearestWifi(double lat, double lnt) {

        Statement stmt;
        ResultSet resultSet = null;
        try {
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery("SELECT * FROM wifi_unit\n" +
                    " ORDER BY ABS(lat - 37.5670135) * ABS(lat - 37.5670135)\n" +
                    "        + ABS(lnt - 126.9783740) * ABS(lnt - 126.9783740)\n" +
                    "\t\tlimit 20\n");
            return resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }


    public void deleteHistory(String historyId) {
        String sql = "DELETE FROM lookup_history WHERE id = ?";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, historyId);
            pstmt.executeUpdate();
            System.out.println("deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
