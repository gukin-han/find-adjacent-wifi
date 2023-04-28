package com.example.findadjacentwifi;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/distanceCalculator")
public class DistanceCalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String latStr = request.getParameter("lat");
        String lonStr = request.getParameter("lon");

        double lat = Double.parseDouble(latStr);
        double lon = Double.parseDouble(lonStr);

        DistanceCalculator distanceCalculator = new DistanceCalculator();

        // iterate over the wifi locations from the database and calculate the distance for each one
        // update the database with the calculated distances

        // set the attribute for the table in the index.jsp
//        request.setAttribute("wifiTable", wifiTable);

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}

