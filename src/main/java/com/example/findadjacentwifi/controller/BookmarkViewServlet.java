package com.example.findadjacentwifi.controller;

import com.example.findadjacentwifi.domain.Bookmark;
import com.example.findadjacentwifi.domain.BookmarkGroup;
import com.example.findadjacentwifi.domain.BookmarkJoined;
import com.example.findadjacentwifi.repository.JdbcBookmarkGroupRepository;
import com.example.findadjacentwifi.repository.JdbcBookmarkRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/BookmarkViewServlet")
public class BookmarkViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Execute the necessary Java code here
        JdbcBookmarkRepository jdbcBookmarkRepository = new JdbcBookmarkRepository();

        jdbcBookmarkRepository.createConnection();
        List<BookmarkJoined> bookmarkList = jdbcBookmarkRepository.selectJoinedAll();
        jdbcBookmarkRepository.closeConnection();




        // Set wifiListSize as an attribute in the request object
        request.setAttribute("bookmarkList", bookmarkList);

        // Forward the request to the appropriate JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark-view.jsp");
        dispatcher.forward(request, response);
    }
}

