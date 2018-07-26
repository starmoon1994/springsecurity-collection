package com.company.testss13.support.exception;

public class UnloginException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int actionCode = -1;// 上行指令码

	public UnloginException(String errorInfo) {
		super(errorInfo);
	}

	public UnloginException(int actionCode, String errorInfo) {
		super(errorInfo);
		this.actionCode = actionCode;
	}

	public UnloginException(int actionCode, Throwable cause) {
		super(cause);
		this.actionCode = actionCode;
	}

	public UnloginException(int actionCode, String message, Throwable cause) {
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
