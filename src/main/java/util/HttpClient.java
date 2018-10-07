package util;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClient {
    public static CloseableHttpClient httpclient;

    public static String requestWithGet(String url, String submitToken) {
        httpclient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(url);
            if (submitToken != null) {
                request.setHeader("X-Auth-Token", submitToken);
            }

            HttpResponse response = httpclient.execute(request);
            String json = JsonUtils.responseToJson(url, response);
            httpclient.close();
            return json;
        } catch (Exception e) {
            // exception handling
        }
        return null;
    }

    public static String requestWithPost(String url, String submitToken, String body) {
        httpclient = HttpClients.createDefault();
        try {
            HttpPost request = new HttpPost(url);
            if (submitToken != null) {
                request.setHeader("X-Auth-Token", submitToken);
            }
            if (body != null) {
                // 1
                request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
                request.setEntity(entity);

                // 2
//                HttpEntity entity = new ByteArrayEntity(body.getBytes("UTF-8"));
//                request.setEntity(entity);
            }

            HttpResponse response = httpclient.execute(request);
            String json = JsonUtils.responseToJson(url, response);
            httpclient.close();
            return json;
        } catch (Exception e) {
            // exception handling
        }
        return null;
    }

    public static void close() {
        try {
            httpclient.close();
        } catch (Exception e) {
            // exception handling
        }
    }
}