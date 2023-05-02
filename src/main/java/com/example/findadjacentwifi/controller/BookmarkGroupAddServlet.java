package com.example.findadjacentwifi.controller;

import com.example.findadjacentwifi.domain.BookmarkGroup;
import com.example.findadjacentwifi.repository.JdbcBookmarkGroupRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BookmarkGroupAddServlet")
public class BookmarkGroupAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the input values from the JSP
        String bookmarkName = request.getParameter("bookmarkName");
        String sortOrder = request.getParameter("sortOrder");

        // Execute the necessary Java code here
        JdbcBookmarkGroupRepository jdbcBookmarkGroupRepository = new JdbcBookmarkGroupRepository();

        BookmarkGroup bookmarkGroup = new BookmarkGroup();
        bookmarkGroup.setName(bookmarkName);
        bookmarkGroup.setSortOrder(Integer.parseInt(sortOrder));

        jdbcBookmarkGroupRepository.createConnection();
        jdbcBookmarkGroupRepository.insertOne(bookmarkGroup);
        jdbcBookmarkGroupRepository.closeConnection();

        // Redirect the request to the appropriate servlet
        response.sendRedirect(request.getContextPath() + "/BookmarkGroupServlet");
    }
}
