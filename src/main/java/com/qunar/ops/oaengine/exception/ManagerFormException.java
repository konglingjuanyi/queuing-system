package com.qunar.ops.oaengine.exception;

public class ManagerFormException extends Exception {

	private static final long serialVersionUID = 1044414847190420688L;
	
	private Class<?> objectClass;

	public ManagerFormException(String message, Class<?> objectClass) {
		this(message, objectClass, null);
	}

	public ManagerFormException(Class<?> objectClass) {
		this(null, objectClass, null);
	}

	public ManagerFormException(String message, Class<?> objectClass, Throwable cause) {
		super(message, cause);
		this.objectClass = objectClass;
	}

	public Class<?> getObjectClass() {
		return objectClass;
	}
}
