package Common;

import java.util.HashMap;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class TriangleAPI {

    private String API_URL;
    private String TOKEN;

    private class Routes {
        public static final String add = "/triangle";
        public static final String get = "/triangle/%s";
        public static final String getAll = "/triangle/all";
        public static final String delete = "/triangle/%s";
        public static final String getPerimeter = "/triangle/%s/perimeter";
        public static final String getArea = "/triangle/%s/area";
    }


    public TriangleAPI() {
        API_URL = EnvConstants.API_URL;
        TOKEN  = EnvConstants.TOKEN;
    }

    public TriangleAPI(String apiUrl, String token) {
        API_URL = apiUrl;
        TOKEN  = token;
    }

    public JSONObject add(String payload) throws Exception {
        HttpResponse<String> response
                = Unirest.post(API_URL + Routes.add)
                .header("X-User", TOKEN)
                .header("Content-type","Application/json")
                .body(payload)
                .asString();
        return new JSONObject().put("status", response.getStatus()).put("data", new JSONObject(response.getBody()));
    }

    public JSONObject get(String triangleId) throws Exception {
        HttpResponse<String> response
                = Unirest.get(API_URL+String.format(Routes.get, triangleId))
                .header("X-User", TOKEN)
                .header("Content-type","Application/json")
                .asString();
        return new JSONObject().put("status", response.getStatus()).put("data", new JSONObject(response.getBody()));
    }

    public JSONObject getAll() throws Exception {
        HttpResponse<String> response
                = Unirest.get(API_URL+Routes.getAll)
                .header("X-User", TOKEN)
                .header("Content-type","Application/json")
                .asString();
        return new JSONObject().put("status", response.getStatus()).put("data", new JSONArray(response.getBody()));
    }

    public JSONObject delete(String triangleId) throws Exception {
        HttpResponse<String> response
                = Unirest.delete(API_URL+String.format(Routes.delete, triangleId))
                .header("X-User", TOKEN)
                .header("Content-type","Application/json")
                .asString();
        return new JSONObject().put("status", response.getStatus()).put("data", response.getBody());
    }

    public JSONObject getPerimeter(String triangleId) throws Exception {
        HttpResponse<String> response
                = Unirest.get(API_URL+String.format(Routes.getPerimeter, triangleId))
                .header("X-User", TOKEN)
                .header("Content-type","Application/json")
                .asString();
        return new JSONObject().put("status", response.getStatus()).put("data", new JSONObject(response.getBody()));
    }

    public JSONObject getArea(String triangleId) throws Exception {
        HttpResponse<String> response
                = Unirest.get(API_URL+String.format(Routes.getArea, triangleId))
                .header("X-User", TOKEN)
                .header("Content-type","Application/json")
                .asString();
        return new JSONObject().put("status", response.getStatus()).put("data", new JSONObject(response.getBody()));
    }
}

