package sichuan.ytf;

import java.util.UUID;

public class Main2 {

    public static String getRandomStr(int length) {
        if (1 > length || length > 100) {
            return "";
        }
        String str = "";
        while (str.length() < length) {
            str += UUID.randomUUID().toString().replace("-", "");
        }
        return str.substring(0, length);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {

            System.out.println(getRandomStr("db619914507c4e159866b808ba1f7665".length()));
        }
    }
}
