package sichuan.ytf.web;

/**
 * 异常类
 *
 */
public class BaseDefineException extends Exception {
	private static final long serialVersionUID = 1L;

	protected ExceptionEnum exceptionEnum;

	public Integer getErrorCode() {
		return exceptionEnum.getCode();
	}

	public String getErrorContent() {
		return exceptionEnum.getContent();
	}

	public String getName() {
		return exceptionEnum.name();
	}

	public ExceptionEnum getBusinessError() {
		return exceptionEnum;
	}

	@Override
	public String getMessage() {
		return exceptionEnum.name() + " - error code : " + exceptionEnum.getCode() + ", " + exceptionEnum.getContent();
	}
}