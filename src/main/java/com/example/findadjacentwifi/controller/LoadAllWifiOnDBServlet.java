package com.example.findadjacentwifi.controller;

import com.example.findadjacentwifi.repository.DBHandler;
import com.example.findadjacentwifi.domain.Wifi;
import com.example.findadjacentwifi.service.WifiLoader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoadAllWifiOnDBServlet")
public class LoadAllWifiOnDBServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Execute the necessary Java code here

        WifiLoader service = new WifiLoader();
        DBHandler dbHandler = new DBHandler();

        service.addAllWifi();
        String wifiListSize = String.valueOf(service.wifiList.size());

        dbHandler.createConnection();
        // table initialize
        dbHandler.dropTable();
        dbHandler.createTable();
        for (Wifi wifi : service.wifiList) {
            dbHandler.insertWifi(wifi);
        }
        dbHandler.closeConnection();

        // Set wifiListSize as an attribute in the request object
        request.setAttribute("wifiListSize", wifiListSize);

        // Forward the request to the appropriate JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/load-wifi.jsp");
        dispatcher.forward(request, response);
    }
}
