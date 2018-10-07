package test;

import com.google.gson.JsonArray;
import util.HttpClient;
import util.JsonUtils;

public class KakaoModule extends Thread {
    private String submitToken;
    private String documentAPIResponse;
    private String currentURL;

    public KakaoModule(String submitToken, String documentAPIResponse, String currentURL) {
        this.submitToken = submitToken;
        this.documentAPIResponse = documentAPIResponse;
        this.currentURL = currentURL;
    }

    @Override
    public void run() {
        while(true) {
            String nextURL = JsonUtils.getValue(documentAPIResponse, "next_url");
            JsonArray images = JsonUtils.getJsonArray(documentAPIResponse, "images");

            if (currentURL.equals(nextURL) && images.isJsonNull()) {
                // 몇 번 재요청 할지 생각
            }

            // 이미지 처리
            for (int i = 0; i < images.size(); i++) {
                String type = JsonUtils.getValue(images.get(i), "type");
                String id = JsonUtils.getValue(images.get(i), "id");

            }

            documentAPIResponse = HttpClient.requestWithGet(Main.defaultURL + nextURL, submitToken);
            currentURL = nextURL;
        }

    }
}
