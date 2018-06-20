package sichuan.ytf.web;

/**
 * web接口返回结构
 */
public class WebResp<T> {
	/** 服务返回码 */
	private int code;
	/** 业务数据 */
	private T data;
	/** Exception异常信息，与code对应 */
	private String message;
	/** 异常解释，描述 */
	private String description;

	public WebResp() {
		code = 500;
		message = "";
		description = "";
	}

	public static <T> WebResp<T> getSuccessResp(T data) {
		WebResp<T> resp = new WebResp<>();
		resp.setData(data);
		resp.setCode(200);
		resp.setMessage("ok");
		return resp;
	}

	public static WebResp<String> getExceptionResp(Exception e) {
		WebResp<String> resp = new WebResp<>();
		resp.setCode(500);
		resp.setMessage("系统错误");
		resp.setDescription(e.getMessage() == null ? "" : e.getMessage());
		resp.setData("");
		return resp;
	}

	private static WebResp<String> getBaseDefineExceptionResp(BaseDefineException e) {
		WebResp<String> resp = new WebResp<>();
		resp.setCode(e.getErrorCode());
		resp.setMessage(e.getErrorContent());
		resp.setDescription(e.getMessage());
		resp.setData("");
		return resp;
	}

	public static WebResp<String> getBusinessExceptionResp(BusinessException e) {
		return getBaseDefineExceptionResp(e);
	}

	public static WebResp<String> getBusinessExceptionResp(ExceptionEnum em) {
		BusinessException e = new BusinessException(em);
		return getBusinessExceptionResp(e);
	}

	public static WebResp<String> getSystemExceptionResp(SystemException e) {
		return getBaseDefineExceptionResp(e);
	}

	public static WebResp<String> getSystemExceptionResp(ExceptionEnum em) {
		SystemException e = new SystemException(em);
		return getSystemExceptionResp(e);
	}

	public int getCode() {
		return code;
	}

	public WebResp<T> setCode(int code) {
		this.code = code;
		return this;
	}

	public T getData() {
		return data;
	}

	public WebResp<T> setData(T data) {
		this.data = data;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public WebResp<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public WebResp<T> setDescription(String description) {
		this.description = description;
		return this;
	}
}
