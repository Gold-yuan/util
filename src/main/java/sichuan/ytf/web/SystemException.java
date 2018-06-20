package sichuan.ytf.web;

/**
 * 系统异常类
 *
 */
public class SystemException extends BaseDefineException {
	private static final long serialVersionUID = -7725705947416795409L;

	public SystemException(ExceptionEnum systemErrorEnum) {
		this.exceptionEnum = systemErrorEnum;
	}

	public SystemException(int code) {
		for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
			if (exceptionEnum.getCode() == code) {
				this.exceptionEnum = exceptionEnum;
				break;
			}
		}
	}
}

// end
