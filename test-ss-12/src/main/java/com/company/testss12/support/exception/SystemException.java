package com.company.testss12.support.exception;

public class SystemException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String key;
	private Object values[];

	public Object[] getValues() {
		return values;
	}

	public String getKey() {
		return key;
	}

	public SystemException() {
		super();
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}
	
	public SystemException(String message,String key) {
		super(message);
		this.key=key;
	}
	public SystemException(String message,String key,Object values) {
		super(message);
		this.key=key;
		this.values=new Object[]{values};
	}
	public SystemException(String message,String key,Object[] values) {
		super(message);
		this.key=key;
		this.values=values;
	}
}
