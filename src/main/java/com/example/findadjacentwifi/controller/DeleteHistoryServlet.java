package com.example.findadjacentwifi.controller;

import com.example.findadjacentwifi.repository.DBHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteHistoryServlet")
public class DeleteHistoryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the ID of the history record to be deleted
        String historyId = request.getParameter("id");

        // Delete the history record from the database
        DBHandler dbHandler = new DBHandler();
        dbHandler.createConnection();
        dbHandler.deleteHistory(historyId);
        dbHandler.closeConnection();

        // Redirect the user back to the history list page
        response.sendRedirect("HistoryListServlet");
    }
}
