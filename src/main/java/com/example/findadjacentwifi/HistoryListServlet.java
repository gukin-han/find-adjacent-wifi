package com.example.findadjacentwifi;

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

@WebServlet("/HistoryListServlet")
public class HistoryListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Execute the necessary Java code here
        DBHandler dbHandler = new DBHandler();
        List<History> historyList = new ArrayList<>();

        dbHandler.createConnection();
        ResultSet resultSet = dbHandler.selectAllHistory();

        try {
            while (resultSet.next()) {
                History history = new History();
                history.setLat(resultSet.getString("lat"));
                history.setLnt(resultSet.getString("lnt"));
                history.setLookupDate(resultSet.getString("lookup_date"));
                history.setId(resultSet.getString("id"));

                historyList.add(history);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbHandler.closeConnection();




        // Set wifiListSize as an attribute in the request object
        request.setAttribute("historyList", historyList);

        // Forward the request to the appropriate JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/history-list.jsp");
        dispatcher.forward(request, response);
    }
}
