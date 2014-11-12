package com.qunar.ops.oaengine.exception;

public class ErrorParamterException extends Exception {

	private static final long serialVersionUID = 1044414847190420688L;
	
	private Class<?> objectClass;

	public ErrorParamterException(String message, Class<?> objectClass) {
		this(message, objectClass, null);
	}

	public ErrorParamterException(Class<?> objectClass) {
		this(null, objectClass, null);
	}

	public ErrorParamterException(String message, Class<?> objectClass, Throwable cause) {
		super(message, cause);
		this.objectClass = objectClass;
	}

	public Class<?> getObjectClass() {
		return objectClass;
	}
}
