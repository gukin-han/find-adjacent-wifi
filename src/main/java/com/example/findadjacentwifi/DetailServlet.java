package com.example.findadjacentwifi;

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

@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retrieve the ID parameter
        String mgr_no = request.getParameter("mgr_no");

        // do something with the ID, e.g. retrieve the corresponding wifi information from a database
        DBHandler dbHandler = new DBHandler();
        dbHandler.createConnection();
        Wifi wifi = new Wifi();

        try {
            ResultSet resultSet = dbHandler.selectOneWifi(mgr_no);

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

            request.setAttribute("wifi", wifi);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbHandler.closeConnection();

        // forward the request to the detail view JSP page
        request.getRequestDispatcher("/wifi-detail.jsp").forward(request, response);
    }

}
