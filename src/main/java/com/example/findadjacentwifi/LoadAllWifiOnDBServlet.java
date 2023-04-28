package com.example.findadjacentwifi;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.findadjacentwifi.DBHandler.*;

@WebServlet("/LoadAllWifiOnDBServlet")
public class LoadAllWifiOnDBServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Execute the necessary Java code here

        WifiLoader service = new WifiLoader();

        service.addAllWifi();
        String wifiListSize = String.valueOf(service.wifiList.size());

        createConnection();
        // table initialize
        dropTable();
        creatTable();
        for (Wifi wifi : service.wifiList) {
            insertWifi(wifi);
        }
        closeConnection();

        // Set wifiListSize as an attribute in the request object
        request.setAttribute("wifiListSize", wifiListSize);

        // Forward the request to the appropriate JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/load-wifi.jsp");
        dispatcher.forward(request, response);
    }
}
