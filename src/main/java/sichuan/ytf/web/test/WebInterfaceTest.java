package sichuan.ytf.web.test;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import sichuan.ytf.main.util.Base64Utils;
import sichuan.ytf.main.util.GsonUtils;
import sichuan.ytf.main.util.HttpClientUtils;
import sichuan.ytf.web.test.RaRequestTestVo.Paramter;
import sichuan.ytf.web.test.RaRequestTestVo.Record;

/**
 * 一键测试 1、修改FILE_NAME字段为将要执行的内容 2、运行testStart()方法
 */
public class WebInterfaceTest {
	private static final String PATH = WebInterfaceTest.class.getResource("").getPath();

	private static final String TEST_FILE_NAME = "overall-exception-part1-init.json";
	private static final String FABRIC_NETWORK_URL = "9.186.56.71";
	private static final String CHANNEL_NAME = "channel13";

	private HttpClientUtils client = HttpClientUtils.getInstance();
	private List<RaRequestTestVo> raRequestVo;

	private Pattern pattern = Pattern.compile("(?<=(\\$\\{))[^/]+(?=\\})");
	private static Map<String, String> map = new HashMap<>();

	@Before
	public void init() throws Exception {
		// 读取json文件
		String result = FileUtils.readFileToString(new File(PATH, TEST_FILE_NAME), UTF_8);
		result = result.replaceAll("127.0.0.1", FABRIC_NETWORK_URL);
		result = result.replaceAll("channel01", CHANNEL_NAME);

		raRequestVo = GsonUtils.fromJsonToList(result, RaRequestTestVo.class);
		System.out.println(raRequestVo.toString());
	}

	@Test
	public void testStart() throws Exception {
		for (int i = 0; i < raRequestVo.size(); i++) {
			RaRequestTestVo vo = raRequestVo.get(i);

			boolean isJson = false;
			String jsonParam = "";
			String getParam = "";
			Map<String, String> mapParam = new HashMap<String, String>(10);

			for (Paramter reqParameter : vo.getParameters()) {
				String paramType = reqParameter.getParamType();

				String paramName = reqParameter.getName();
				String data = replace(reqParameter.getData().toString());
				if ("json".equals(paramType)) {
					isJson = true;
					jsonParam = data;
				} else {
					mapParam.put(paramName, data);
					getParam += "&" + paramName + "=" + data;
				}
			}

			// 请求地址
			String reqPath = replace(vo.getPath());
			System.out.println(String.format("访问【%s】", vo.getName()));
			String result = "";
			String url = "";
			switch (vo.getType().toUpperCase()) {
			case HttpPost.METHOD_NAME:
				url = reqPath;
				System.out.println(String.format("地址: %s", url));
				if (isJson) {
					System.out.println(String.format("参数: %s", jsonParam));
					result = client.sendHttpJsonPost(url, jsonParam);
				} else {
					System.out.println(String.format("参数: %s", mapParam));
					result = client.sendHttpPost(url, mapParam);
				}
				break;
			case HttpGet.METHOD_NAME:
				url = reqPath + getParam.replaceFirst("&", "?");
				System.out.println(String.format("地址: %s", url));
				result = client.sendHttpGet(url);
				break;
			case HttpPut.METHOD_NAME:
				url = reqPath;
				System.out.println(String.format("地址: %s", url));
				if (isJson) {
					System.out.println(String.format("参数: %s", jsonParam));
					result = client.sendHttpJsonPut(url, jsonParam);
				} else {
					System.out.println(String.format("参数: %s", mapParam));
					result = client.sendHttpPut(url, mapParam);
				}
				break;
			case HttpDelete.METHOD_NAME:
				url = reqPath;
				System.out.println(String.format("地址: %s", url));
				result = client.sendHttpDelete(url);
				break;
			default:
				System.out.println(String.format("无此类型: %s", vo.getType()));

			}
			System.out.println("返回结果：\n" + result);
			// 结果比对
			JsonObject json = compareExpCode(result, vo.getExpCode());

			// 保存数据
			for (Record record : vo.getRecord()) {
				String key = record.getName();
				String prop = record.getProp();
				int respCode = json.get("code").getAsInt();
				if (respCode != 200) {
					break;
				}
				String[] ps = prop.split("\\.");
				String val = "";
				JsonObject temp = new JsonParser().parse(json.toString()).getAsJsonObject();
				for (int x = 0; x < ps.length; x++) {
					if (ps[x].contains("[")) {
						String subkey = ps[x].substring(0, ps[x].indexOf("["));
						int index = Integer.parseInt(ps[x].substring(ps[x].indexOf("[") + 1, ps[x].indexOf("]")));
						temp = temp.get(subkey).getAsJsonArray().get(index).getAsJsonObject();
					} else if ((x + 1) == ps.length) {
						val = temp.get(ps[x]).getAsString();
					} else {
						temp = temp.get(ps[x]).getAsJsonObject();
					}
				}
				map.put(key, val);
			}
			if (reqPath.contains("/channels/") && reqPath.contains("/updates/")) {
				Thread.sleep(3000);
			}
		}
		if (map.containsKey("genesis")) {
			File file = new File("genesis");
			if (!file.exists()) {
				file.mkdirs();
			}
			FileUtils.writeByteArrayToFile(new File(file, "genesis.block"), Base64Utils.decode(map.get("genesis")));
		}
	}

	/**
	 * 将${name}替换成对应值
	 * 
	 * @param data
	 * @return
	 */
	private String replace(String data) {
		if (StringUtils.isBlank(data)) {
			return "";
		}
		Matcher dataMatcher = pattern.matcher(data);
		while (dataMatcher.find()) {
			String name = dataMatcher.group();
			data = data.replaceAll("\\$\\{" + name + "\\}", map.get(name));
		}
		return data;
	}

	private JsonObject compareExpCode(String result, int expCode) {
		JsonObject jsonObj = new JsonParser().parse(result).getAsJsonObject();
		int respCode = jsonObj.get("code").getAsInt();
		if (expCode == respCode) {
			System.out.println("\n与期望结果一致");
		} else {
			System.out.println("\n与期望结果不一致");
		}
		return jsonObj;
	}
}
