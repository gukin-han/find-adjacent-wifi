package com.example.findadjacentwifi.controller;

import com.example.findadjacentwifi.domain.BookmarkGroup;
import com.example.findadjacentwifi.domain.History;
import com.example.findadjacentwifi.repository.DBHandler;
import com.example.findadjacentwifi.repository.JdbcBookmarkGroupRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/BookmarkGroupServlet")
public class BookmarkGroupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Execute the necessary Java code here
        JdbcBookmarkGroupRepository jdbcBookmarkGroupRepository = new JdbcBookmarkGroupRepository();

        jdbcBookmarkGroupRepository.createConnection();
        List<BookmarkGroup> bookmarkGroupList = jdbcBookmarkGroupRepository.selectAll();
        jdbcBookmarkGroupRepository.closeConnection();




        // Set wifiListSize as an attribute in the request object
        request.setAttribute("bookmarkGroupList", bookmarkGroupList);

        // Forward the request to the appropriate JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark-group.jsp");
        dispatcher.forward(request, response);
    }
}

