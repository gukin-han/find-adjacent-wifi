package com.example.findadjacentwifi.controller;

import com.example.findadjacentwifi.repository.JdbcBookmarkGroupRepository;
import com.example.findadjacentwifi.repository.JdbcBookmarkRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BookmarkDeleteServlet")
public class BookmarkDeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the ID of the history record to be deleted
        String bookmarkId = request.getParameter("id");
        JdbcBookmarkRepository jdbcBookmarkRepository = new JdbcBookmarkRepository();


        // Delete the history record from the database
        jdbcBookmarkRepository.createConnection();
        jdbcBookmarkRepository.deleteOne(bookmarkId);
        jdbcBookmarkRepository.closeConnection();

        // Redirect the request to the appropriate servlet
        response.sendRedirect(request.getContextPath() + "/BookmarkViewServlet");
    }
}
