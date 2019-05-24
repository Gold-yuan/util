package sichuan.ytf.translate.baidu;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private static final String APP_ID = "20190520000299564";
    private static final String SECURITY_KEY = "xSJ98OjhFqkDZSnR2J0B";

    private String appid;
    private String securityKey;

    public TransApi() {
        this.appid = APP_ID;
        this.securityKey = SECURITY_KEY;
    }

    public String getTransEn(String query) {
        Map<String, String> params = buildParams(query, "zh", "en");
        String json = HttpGet.get(TRANS_API_HOST, params);
        JsonParser jp = new JsonParser();
        JsonElement parse = jp.parse(json);
        JsonObject jsonObj = parse.getAsJsonObject();
        JsonArray jsonArr = jsonObj.get("trans_result").getAsJsonArray();
        for (JsonElement jsonElement : jsonArr) {
            return jsonElement.getAsJsonObject().get("dst").getAsString();
        }
        return "";
    }

    public String getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        return HttpGet.get(TRANS_API_HOST, params);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }

}
