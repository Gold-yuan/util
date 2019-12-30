package sichuan.ytf.translate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Dict {
    private static Map<String, String> dict = new HashMap<>();
    private static List<String> unTranslate = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        readDictFile("dict.txt");
        doTranslate("translation.txt");
    }

    public static void doTranslate(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(Translation.class.getResourceAsStream(fileName)));
        String line = "";
        while ((line = br.readLine()) != null) {
            translate(line);
        }
        br.close();
    }

    public static void translate(String cn) throws Exception {
//        File file = new File("en.txt");
//        if (!file.exists()) {
//            file.createNewFile();
//        }
        String en = "";
        String[] names = cn.split(",");
        for (int i = 0; i < names.length; i++) {
            if (StringUtils.isNotBlank(names[i])) {
                String nameEn = dict.get(names[i]);
                if (nameEn == null || nameEn.length() == 0) {
                    nameEn = "xxxxxxxxxxxxxxxxxx" + names[i];
                    unTranslate.add(names[i]);
                }
                if ((i + 1) == names.length) {
                    en += nameEn + "\n";
                } else {
                    en += nameEn + ",";
                }
            } else {
                en += "\n";
            }
        }
//        FileUtils.write(file, en, java.nio.charset.StandardCharsets.UTF_8, true);
        System.out.print(en);
    }

    public static void readDictFile(String fileName) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(Dict.class.getResourceAsStream(fileName)));
        String line = "";
        while ((line = br.readLine()) != null) {
            if (line == null || line.trim().length() == 0) {
                continue;
            }
            line = line.trim();
            String[] split = line.split(" ");
            if (split.length != 2) {
                continue;
            }
            String value = split[0];
            String keys = split[1];
            String[] split2 = keys.split("、");
            for (String key : split2) {
                if (dict.containsKey(key)) {
                    //System.out.println("已存在：" + key + " " + value);
                }
                dict.put(key, value);
            }
        }
        br.close();
        System.out.println(dict);
    }
}
