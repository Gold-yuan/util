package sichuan.ytf.usual.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 发送http、https请求工具类
 * 
 * 可发送get post delete put类请求， 参数形式包括url、map、json、file
 * 
 * @author adminytf
 *
 */
public class HttpClientUtil {
	/** 超时时间 */
	private RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(300000).setConnectTimeout(300000)
			.setConnectionRequestTimeout(300000).build();

	private static HttpClientUtil instance = null;

	private HttpClientUtil() {
	}

	public static HttpClientUtil getInstance() {
		if (instance == null) {
			instance = new HttpClientUtil();
		}
		return instance;
	}

	/**
	 * 发送http、https请求 包括 get post delete put
	 * 
	 * @param {@link
	 * 			HttpRequestBase}
	 * @return
	 * @throws IOException
	 *             连接失败
	 */
	private String sendHttpReq(HttpRequestBase req) throws IOException {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = "";

		// 创建默认的httpClient实例.
		httpClient = HttpClients.createDefault();
		req.setConfig(requestConfig);
		// 执行请求
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			req.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(req);
			entity = response.getEntity();
			if (entity != null) {
				responseContent = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * 发送 post 无参数请求
	 * 
	 * @param httpUrl
	 *            地址
	 */
	public String sendHttpPost(String httpUrl) throws IOException {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		return sendHttpReq(httpPost);
	}

	/**
	 * 发送 post 类get请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param params
	 *            参数(格式:key1=value1&key2=value2)
	 */
	public String sendHttpPost(String httpUrl, String params) throws IOException {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			StringEntity stringEntity = new StringEntity(params, "UTF-8");
			stringEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(stringEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpReq(httpPost);
	}

	/**
	 * 发送 post json请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param json
	 * @return
	 */
	public String sendHttpJsonPost(String httpUrl, String json) throws IOException {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			StringEntity stringEntity = new StringEntity(json, "UTF-8");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpReq(httpPost);
	}

	/**
	 * 发送 post 带参数map请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param maps
	 *            参数
	 */
	public String sendHttpPost(String httpUrl, Map<String, String> maps) throws IOException {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		// 创建参数队列
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : maps.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpReq(httpPost);
	}

	/**
	 * 发送 post 带文件请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param maps
	 *            参数
	 * @param fileLists
	 *            附件
	 */
	public String sendHttpFilePost(String httpUrl, Map<String, String> maps, Map<String, File> fileLists)
			throws IOException {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		for (String key : maps.keySet()) {
			meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
		}
		for (String key : fileLists.keySet()) {

			FileBody fileBody = new FileBody(fileLists.get(key));
			meBuilder.addPart(key, fileBody);
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
		return sendHttpReq(httpPost);
	}

	/**
	 * 使用SOAP1.1发送消息
	 * 
	 * @param postUrl
	 *            wsdl地址
	 * @param soapXml
	 *            请求soapxml
	 * @param soapAction
	 *            ""
	 * @return
	 */
	public String sendHttpSoapPost(String postUrl, String soapXml, String soapAction) throws IOException {
		HttpPost httpPost = new HttpPost(postUrl);
		httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
		httpPost.setHeader("SOAPAction", soapAction);
		StringEntity data = new StringEntity(soapXml, Charset.forName("UTF-8"));
		httpPost.setEntity(data);
		return sendHttpReq(httpPost);
	}

	protected String buildSoapRequestData() {
		StringBuffer soapRequestData = new StringBuffer();
		soapRequestData.append(
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"webserviceNameSpace\">");// 命名空间如：http://xxx.xxx/xxx
		soapRequestData.append("<soapenv:Header/>");
		soapRequestData.append("<soapenv:Body>");
		soapRequestData.append("<web:methodName>");// 方法名
		soapRequestData.append("<arg0>这里是参数</arg0>"); // 参数，可多个 arg0 arg1 arg2...
		soapRequestData.append(" </web:methodName>");
		soapRequestData.append("</soapenv:Body>");
		soapRequestData.append("</soapenv:Envelope>");
		return soapRequestData.toString();
	}

	/**
	 * 发送 get请求
	 * 
	 * @param httpUrl
	 */
	public String sendHttpGet(String httpUrl) throws IOException {
		HttpGet httpGet = new HttpGet(httpUrl);
		return sendHttpReq(httpGet);
	}

	/**
	 * 发送 Http Delete请求
	 * 
	 * @param httpUrl
	 *            地址
	 */
	public String sendHttpDelete(String httpUrl) throws IOException {
		HttpDelete httpDelete = new HttpDelete(httpUrl);
		return sendHttpReq(httpDelete);
	}

	/**
	 * 发送 Http PUT请求
	 * 
	 * @param httpUrl
	 * @return
	 * @throws IOException
	 */
	public String sendHttpPut(String httpUrl) throws IOException {
		HttpPut httpPut = new HttpPut(httpUrl);
		return sendHttpReq(httpPut);
	}

	public String sendHttpPut(String httpUrl, Map<String, String> maps) throws IOException {
		HttpPut httpPut = new HttpPut(httpUrl);
		// 创建参数队列
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : maps.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
		}
		try {
			httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpReq(httpPut);
	}

	public String sendHttpJsonPut(String httpUrl, String json) throws IOException {
		HttpPut httpPut = new HttpPut(httpUrl);
		// 设置参数
		StringEntity stringEntity = new StringEntity(json, "UTF-8");
		stringEntity.setContentType("application/json");
		httpPut.setEntity(stringEntity);
		return sendHttpReq(httpPut);
	}
}
