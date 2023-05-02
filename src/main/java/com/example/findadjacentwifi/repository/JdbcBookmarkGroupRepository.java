package com.example.findadjacentwifi.repository;

import com.example.findadjacentwifi.domain.BookmarkGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcBookmarkGroupRepository {
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

    public void insertOne(BookmarkGroup bookmarkGroup) {
        String sql = "INSERT INTO bookmark_group (name, sort_order) VALUES (?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookmarkGroup.getName());
            pstmt.setInt(2, bookmarkGroup.getSortOrder()); // use getInt instead of getId
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BookmarkGroup> selectAll() {
        String sql = "SELECT * FROM bookmark_group";
        List<BookmarkGroup> bookmarkGroups = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                BookmarkGroup bookmarkGroup = new BookmarkGroup();
                bookmarkGroup.setId(rs.getLong("id"));
                bookmarkGroup.setName(rs.getString("name"));
                bookmarkGroup.setSortOrder(rs.getInt("sort_order"));
                bookmarkGroup.setCreatedDate(rs.getString("created_date"));
                bookmarkGroup.setEditedDate(rs.getString("edited_date"));
                bookmarkGroups.add(bookmarkGroup);
            }
            return bookmarkGroups;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BookmarkGroup selectOne(String bookmarkGroupId) {
        String sql = "SELECT * FROM bookmark_group where id = bookmarkGroupId";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                BookmarkGroup bookmarkGroup = new BookmarkGroup();
                bookmarkGroup.setId(rs.getLong("id"));
                bookmarkGroup.setName(rs.getString("name"));
                bookmarkGroup.setSortOrder(rs.getInt("sort_order"));
                bookmarkGroup.setCreatedDate(rs.getString("created_date"));
                return bookmarkGroup;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void updateOne(BookmarkGroup bookmarkGroup) {
        String sql = "UPDATE bookmark_group SET name = ?, sort_order = ?, edited_date = ?  WHERE id = ?;\n";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookmarkGroup.getName());
            pstmt.setInt(2, bookmarkGroup.getSortOrder());
            pstmt.setString(3, bookmarkGroup.getEditedDate());
            pstmt.setLong(4, bookmarkGroup.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOne(String bookmarkGroupId) {
        String sql = "DELETE FROM bookmark_group WHERE id = ?";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookmarkGroupId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
