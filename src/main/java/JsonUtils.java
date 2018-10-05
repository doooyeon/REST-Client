import com.google.gson.*;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {
    static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
    static Gson gson = new Gson();

    public static String responseToJson(String url, HttpResponse response) {
        if (response == null) return null;

        try {
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == HttpStatus.SC_OK || responseCode == HttpStatus.SC_FORBIDDEN) {
                String json = EntityUtils.toString(response.getEntity(), "UTF-8");
                return json;
            } else {
                log.debug("url", url);
                log.debug("responseCode", responseCode);
            }
        } catch (Exception e) {
            // exception handling
        }
        return null;
    }

    public static JsonObject jsonElementToObject(JsonElement jsonElement) {
        return jsonElement.getAsJsonObject();
    }

    public static String getValue(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsString();
    }

    public static JsonArray getJsonArray(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsJsonArray();
    }

    public static JsonObject jsonToObject(String json) {
        return jsonElementToObject(new JsonParser().parse(json));
    }

    public static String getValue(String json, String key) {
        return getValue(jsonToObject(json), key);
    }

    public static JsonArray getJsonArray(String json, String key) {
        return getJsonArray(jsonToObject(json), key);
    }

    public static JsonObject createJsonObject(String key, JsonElement jsonElement) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(key, jsonElement);
        return jsonObject;
    }

    public static JsonObject createJsonObject(String key, String value) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(key, value);
        return jsonObject;
    }

    public static String jsonObjectToJson(JsonObject jsonObject) {
        return gson.toJson(jsonObject);
    }

    public static String createJson(String key, JsonElement jsonElement) {
        return jsonObjectToJson(createJsonObject(key, jsonElement));
    }

    public static String createJson(String key, String value) {
        return jsonObjectToJson(createJsonObject(key, value));
    }
}
