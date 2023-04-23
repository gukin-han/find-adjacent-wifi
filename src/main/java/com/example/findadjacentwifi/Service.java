package com.example.findadjacentwifi;

import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.Arrays;
import java.util.List;

public class Service {

    public static void main(String[] args) {
        Service service = new Service();
        String json = service.getUserInfo("434665544267756b34325141564f69");
        service.parseJSON(json);


    }

    public String getUserInfo(String key) {
        final String TYPE = "json";


        try {
            String url = "http://openapi.seoul.go.kr:8088/"+ key +"/"+ TYPE + "/TbPublicWifiInfo/1/2/";

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
//                    System.out.println(body.string());
                    return body.string();
                }
            }
            else
                System.err.println("Error Occurred");

//            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }

//        return false;
        return "";
    }



    public void parseJSON(String json) {

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonElement tbPublicWifiInfo = element.getAsJsonObject().get("TbPublicWifiInfo");
        JsonElement rows = tbPublicWifiInfo.getAsJsonObject().get("row");

        Gson gson = new Gson();
        Wifi[] array = gson.fromJson(rows, Wifi[].class);
        List<Wifi> list = Arrays.asList(array);

    }

}
