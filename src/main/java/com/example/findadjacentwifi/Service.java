package com.example.findadjacentwifi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Service {

    public static void main(String[] args) {
        Service service = new Service();
        service.getUserInfo("434665544267756b34325141564f69");
    }

    public boolean getUserInfo(String key) {
        final String TYPE = "json";


        try {
            String url = "http://openapi.seoul.go.kr:8088/"+ key +"/"+ TYPE + "/TbPublicWifiInfo/1/5/";

            // OkHttp 클라이언트 객체 생성
            OkHttpClient client = new OkHttpClient();

            // GET 요청 객체 생성
            Request.Builder builder = new Request.Builder().url(url).get();
//            builder.addHeader("password", "BlahBlah");
            Request request = builder.build();

            // OkHttp 클라이언트로 GET 요청 객체 전송
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // 응답 받아서 처리
                ResponseBody body = response.body();
                if (body != null) {
                    System.out.println("Response:" + body.string());
                }
            }
            else
                System.err.println("Error Occurred");

            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }


}
