package com.company.testss12.support.exception;

public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int actionCode = -1;// 上行指令码

	public BusinessException(String errorInfo) {
		super(errorInfo);
	}

	public BusinessException(int actionCode, String errorInfo) {
		super(errorInfo);
		this.actionCode = actionCode;
	}

	public BusinessException(int actionCode, Throwable cause) {
		super(cause);
		this.actionCode = actionCode;
	}

	public BusinessException(int actionCode, String message, Throwable cause) {
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