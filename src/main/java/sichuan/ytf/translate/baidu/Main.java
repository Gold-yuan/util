package sichuan.ytf.translate.baidu;


public class Main {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
//    private static final String APP_ID = "20190520000299564";
//    private static final String SECURITY_KEY = "xSJ98OjhFqkDZSnR2J0B";

    public static void main(String[] args) {
        TransApi api = new TransApi();

        String query = "高度600米";
        System.out.println(api.getTransResult(query, "auto", "en"));
    }

}
