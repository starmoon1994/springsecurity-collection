package com.company.testss11.support.exception;

public class AuthDeniedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int actionCode = -1;// 上行指令码

	public AuthDeniedException(String errorInfo) {
		super(errorInfo);
	}

	public AuthDeniedException(int actionCode, String errorInfo) {
		super(errorInfo);
		this.actionCode = actionCode;
	}

	public AuthDeniedException(int actionCode, Throwable cause) {
		super(cause);
		this.actionCode = actionCode;
	}

	public AuthDeniedException(int actionCode, String message, Throwable cause) {
		super(message, cause);
		this.actionCode = actionCode;
	}

	public int getActionCode() {
		return actionCode;
	}

	public void setActionCode(int actionCode) {
		this.actionCode = actionCode;
	}

}
