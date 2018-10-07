import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClient {
    public static CloseableHttpClient httpclient;

    public static HttpResponse requestWithGet(String url, String submitToken) {
        httpclient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(url);
            if (submitToken != null) {
                request.setHeader("", submitToken);
            }

            HttpResponse response = httpclient.execute(request);
            return response;
        } catch (Exception e) {
            // exception handling
        }
        return null;
    }

    public static HttpResponse requestWithPost(String url, String submitToken, String body) {
        httpclient = HttpClients.createDefault();
        try {
            HttpPost request = new HttpPost(url);
            if (submitToken != null) {
                request.setHeader("", submitToken);
            }
            if (body != null) {
                // 1
                StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
                StringEntity entity2 = new StringEntity(body);
                request.setEntity(entity);

                // 2
//                HttpEntity entity = new ByteArrayEntity(body.getBytes("UTF-8"));
//                request.setEntity(entity);
            }

            HttpResponse response = httpclient.execute(request);
            return response;
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
