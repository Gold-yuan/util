package sichuan.ytf.web;

/**
 * 业务异常类
 *
 */
public class BusinessException extends BaseDefineException {
	private static final long serialVersionUID = -7725705947416795409L;

	public BusinessException(ExceptionEnum businessError) {
		this.exceptionEnum = businessError;
	}

	public BusinessException(int code) {
		for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
			if (exceptionEnum.getCode() == code) {
				this.exceptionEnum = exceptionEnum;
				break;
			}
		}
	}
}

// end
