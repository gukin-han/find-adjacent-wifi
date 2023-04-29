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

@WebServlet("/DistanceCalculatorServlet")
public class DistanceCalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String latStr = request.getParameter("lat");
        String lntStr = request.getParameter("lnt");

        double lat1 = Double.parseDouble(latStr);
        double lnt1 = Double.parseDouble(lntStr);

        DistanceCalculator distanceCalculator = new DistanceCalculator();

        DBHandler dbHandler = new DBHandler();

        dbHandler.createConnection();
        ResultSet resultSet = dbHandler.selectNearestWifi(lat1, lnt1);
        List<Wifi> nearestWifi = new ArrayList<>();

        // iterate over the wifi locations from the database and calculate the distance for each one
        // update the database with the calculated distances

        try {
            while (resultSet.next()) {
                double lat2 = resultSet.getDouble("lat");
                double lnt2 = resultSet.getDouble("lnt");
                double distance = distanceCalculator.distance(lat1, lnt1, lat2, lnt2);

                Wifi wifi = new Wifi();
                wifi.setDistance(distance);
                wifi.setX_SWIFI_MGR_NO(resultSet.getString("mgr_no"));
                wifi.setX_SWIFI_WRDOFC(resultSet.getString("wrdofc"));
                wifi.setX_SWIFI_MAIN_NM(resultSet.getString("main_nm"));
                wifi.setX_SWIFI_ADRES1(resultSet.getString("adres1"));
                wifi.setX_SWIFI_ADRES2(resultSet.getString("adres2"));
                wifi.setX_SWIFI_INSTL_FLOOR(resultSet.getString("instl_floor"));
                wifi.setX_SWIFI_INSTL_TY(resultSet.getString("instl_ty"));
                wifi.setX_SWIFI_INSTL_MBY(resultSet.getString("instl_mby"));
                wifi.setX_SWIFI_CMCWR(resultSet.getString("cmcwr"));
                wifi.setX_SWIFI_SVC_SE(resultSet.getString("svc_se"));
                wifi.setX_SWIFI_CNSTC_YEAR(resultSet.getString("cnstc_year"));
                wifi.setX_SWIFI_INOUT_DOOR(resultSet.getString("inout_door"));
                wifi.setX_SWIFI_REMARS3(resultSet.getString("remars3"));
                wifi.setLAT(resultSet.getString("lat"));
                wifi.setLNT(resultSet.getString("lnt"));
                wifi.setWORK_DTTM(resultSet.getString("work_dttm"));

                nearestWifi.add(wifi);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        dbHandler.closeConnection();


// set the attribute for the table in the index.jsp
        request.setAttribute("lat", latStr);
        request.setAttribute("lnt", lntStr);
        request.setAttribute("nearestWifi", nearestWifi);

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);

    }
}

