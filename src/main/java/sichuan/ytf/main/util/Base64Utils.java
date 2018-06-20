/**
 * 
 */
package sichuan.ytf.main.util;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Base64 编码工具类
  */
public class Base64Utils {

	public static String encode(byte[] data) {
		return new String(org.springframework.util.Base64Utils.encode(data), UTF_8);
	}

	public static byte[] decode(String data) {
		return org.springframework.util.Base64Utils.decode(data.getBytes(UTF_8));
	}
}