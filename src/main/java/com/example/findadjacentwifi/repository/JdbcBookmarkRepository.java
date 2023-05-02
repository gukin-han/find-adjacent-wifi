package com.example.findadjacentwifi.repository;

import com.example.findadjacentwifi.domain.Bookmark;
import com.example.findadjacentwifi.domain.BookmarkGroup;
import com.example.findadjacentwifi.domain.BookmarkJoined;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcBookmarkRepository {
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

    public void insertOne(Bookmark bookmark) {
        String sql = "INSERT INTO bookmark (bookmark_group_id, wifi_id) VALUES (?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookmark.getBookmarkGroupId());
            pstmt.setString(2, bookmark.getWifiId()); // use getInt instead of getId
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bookmark> selectAll() {
        String sql = "SELECT * FROM bookmark";
        List<Bookmark> bookmarks = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Bookmark bookmark = new Bookmark();
                bookmark.setId(rs.getLong("id"));
                bookmark.setBookmarkGroupId(rs.getString("bookmark_group_id"));
                bookmark.setWifiId(rs.getString("wifi_id"));
                bookmark.setCreatedDate(rs.getString("created_date"));
                bookmarks.add(bookmark);
            }
            return bookmarks;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BookmarkJoined> selectJoinedAll() {
        String sql = "SELECT bookmark.*, wifi_unit.main_nm, bookmark_group.name \n" +
                "FROM bookmark \n" +
                "INNER JOIN wifi_unit ON bookmark.wifi_id = wifi_unit.mgr_no \n" +
                "INNER JOIN bookmark_group ON bookmark.bookmark_group_id = bookmark_group.id;";
        List<BookmarkJoined> bookmarksJoined = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                BookmarkJoined bookmarkJoined = new BookmarkJoined();
                bookmarkJoined.setId(rs.getLong("id"));
                bookmarkJoined.setWifiName(rs.getString("main_nm"));
                bookmarkJoined.setBookmarkGroupName(rs.getString("name"));
                bookmarkJoined.setBookmarkGroupId(rs.getString("bookmark_group_id"));
                bookmarkJoined.setWifiId(rs.getString("wifi_id"));
                bookmarkJoined.setCreatedDate(rs.getString("created_date"));
                bookmarksJoined.add(bookmarkJoined);
            }
            return bookmarksJoined;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void deleteOne(String bookmarkId) {
        String sql = "DELETE FROM bookmark WHERE id = ?";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookmarkId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
