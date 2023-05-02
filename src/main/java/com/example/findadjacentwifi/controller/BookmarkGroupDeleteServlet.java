package com.example.findadjacentwifi.controller;

import com.example.findadjacentwifi.repository.DBHandler;
import com.example.findadjacentwifi.repository.JdbcBookmarkGroupRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BookmarkGroupDeleteServlet")
public class BookmarkGroupDeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the ID of the history record to be deleted
        String bookmarkGroupId = request.getParameter("id");
        JdbcBookmarkGroupRepository jdbcBookmarkGroupRepository = new JdbcBookmarkGroupRepository();


        // Delete the history record from the database
        jdbcBookmarkGroupRepository.createConnection();
        jdbcBookmarkGroupRepository.deleteOne(bookmarkGroupId);
        jdbcBookmarkGroupRepository.closeConnection();

        // Redirect the request to the appropriate servlet
        response.sendRedirect(request.getContextPath() + "/BookmarkGroupServlet");
    }
}
