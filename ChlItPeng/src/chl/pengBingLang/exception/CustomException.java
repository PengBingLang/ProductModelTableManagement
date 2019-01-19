package chl.pengBingLang.exception;

/**
 * 针对预期异常，自定义的异常类
 * 
 * @author 彭秉浪
 */
public class CustomException extends Exception {

	public String message; // 异常信息

	public CustomException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
