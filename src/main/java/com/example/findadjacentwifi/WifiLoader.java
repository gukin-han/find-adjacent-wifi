package com.example.findadjacentwifi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WifiLoader {
    public String wifiJson;
    public List<Wifi> wifiList = new ArrayList<>();

    private final String APIKEY = "434665544267756b34325141564f69";

    public boolean requestWifiAsJSON(int start, int end) {
        final String TYPE = "json";

        try {
            String startIdx = String.valueOf(start);
            String endIdx = String.valueOf(end);
            String url = "http://openapi.seoul.go.kr:8088/"
                    + APIKEY + "/"
                    + TYPE
                    + "/TbPublicWifiInfo/"
                    + startIdx + "/"
                    + endIdx + "/";

            // OkHttp 클라이언트 객체 생성
            OkHttpClient client = new OkHttpClient();

            // GET 요청 객체 생성
            Request.Builder builder = new Request.Builder().url(url).get();
            Request request = builder.build();

            // OkHttp 클라이언트로 GET 요청 객체 전송
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // 응답 받아서 처리
                ResponseBody body = response.body();
                if (body != null) {
                    this.wifiJson = body.string();
                }
            } else
                System.err.println("Error Occurred");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void parseJSONAndAddToList() {
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(this.wifiJson);
        JsonElement tbPublicWifiInfo = element.getAsJsonObject().get("TbPublicWifiInfo");
        JsonElement rows = tbPublicWifiInfo.getAsJsonObject().get("row");

        Gson gson = new Gson();
        Wifi[] array = gson.fromJson(rows, Wifi[].class);
        this.wifiList.addAll(Arrays.asList(array));
    }

    public int getWifiListTotalCount() {
        requestWifiAsJSON(1, 1);
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(this.wifiJson);
        JsonElement tbPublicWifiInfo = element.getAsJsonObject().get("TbPublicWifiInfo");
        return Integer.parseInt(tbPublicWifiInfo.getAsJsonObject().get("list_total_count").toString());
    }

    public void addAllWifi() {
        int wifiListTotalCount = getWifiListTotalCount();

        int startIdx = 1;
        int endIdx = 1000;

        for (int i = 0; i < wifiListTotalCount/1000; i++) {
            requestWifiAsJSON(startIdx, endIdx);
            parseJSONAndAddToList();
            startIdx += 1000;
            endIdx += 1000;
        }

        int remainder = wifiListTotalCount % 1000 - 1;
        endIdx = startIdx + remainder;
        requestWifiAsJSON(startIdx, endIdx);
        parseJSONAndAddToList();
    }
}
