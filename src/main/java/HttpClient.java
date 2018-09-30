import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClient {
    public static CloseableHttpClient httpclient;

    public static String requestWithGet(String url) {
        httpclient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(url);
            request.setHeader("", "");

            HttpResponse response = httpclient.execute(request);
            String jsonResponse = JsonParser.responseToJson(response);

            httpclient.close();
            return jsonResponse;
        } catch (Exception e) {
            // exception handling
        }
        return null;
    }

    public static String requestWithPost(String url, String body) {
        httpclient = HttpClients.createDefault();
        try {
            HttpPost request = new HttpPost(url);
            HttpEntity entity = new ByteArrayEntity(body.getBytes("UTF-8"));
            request.setEntity(entity);

            HttpResponse response = httpclient.execute(request);
            String jsonResponse = JsonParser.responseToJson(response);

            httpclient.close();
            return jsonResponse;
        } catch (Exception e) {
            // exception handling
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(requestWithGet("https://httpbin.org/get"));
    }
}
