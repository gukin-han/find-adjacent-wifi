package com.example.findadjacentwifi.controller;

import com.example.findadjacentwifi.domain.BookmarkGroup;
import com.example.findadjacentwifi.repository.JdbcBookmarkGroupRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/BookmarkGroupEditServlet")
public class BookmarkGroupEditServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the input values from the JSP
        String bookmarkName = request.getParameter("bookmarkName");
        String sortOrder = request.getParameter("sortOrder");
        String id = request.getParameter("id");

        // Execute the necessary Java code here
        JdbcBookmarkGroupRepository jdbcBookmarkGroupRepository = new JdbcBookmarkGroupRepository();

        BookmarkGroup bookmarkGroup = new BookmarkGroup();
        bookmarkGroup.setName(bookmarkName);
        bookmarkGroup.setId(Long.parseLong(id));
        bookmarkGroup.setSortOrder(Integer.parseInt(sortOrder));
        // Set the edited date as a string

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String editedDate = formatter.format(new Date());
        bookmarkGroup.setEditedDate(editedDate);

        jdbcBookmarkGroupRepository.createConnection();
        jdbcBookmarkGroupRepository.updateOne(bookmarkGroup);
        jdbcBookmarkGroupRepository.closeConnection();

        // Redirect the request to the appropriate servlet
        response.sendRedirect(request.getContextPath() + "/BookmarkGroupServlet");
    }
}
