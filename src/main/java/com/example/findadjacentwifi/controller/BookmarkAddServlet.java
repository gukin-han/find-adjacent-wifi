package com.example.findadjacentwifi.controller;

import com.example.findadjacentwifi.domain.Bookmark;
import com.example.findadjacentwifi.domain.BookmarkGroup;
import com.example.findadjacentwifi.repository.JdbcBookmarkGroupRepository;
import com.example.findadjacentwifi.repository.JdbcBookmarkRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BookmarkAddServlet")
public class BookmarkAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the input values from the JSP
        String groupId = request.getParameter("group");
        String mgrNo = request.getParameter("mgr_no");

        // Execute the necessary Java code here
        JdbcBookmarkRepository jdbcBookmarkRepository = new JdbcBookmarkRepository();

        Bookmark bookmark = new Bookmark();
        bookmark.setBookmarkGroupId(groupId);
        bookmark.setWifiId(mgrNo);

        jdbcBookmarkRepository.createConnection();
        jdbcBookmarkRepository.insertOne(bookmark);
        jdbcBookmarkRepository.closeConnection();

        // Redirect the request to the appropriate servlet
        response.sendRedirect(request.getContextPath() + "/BookmarkViewServlet");
    }
}
