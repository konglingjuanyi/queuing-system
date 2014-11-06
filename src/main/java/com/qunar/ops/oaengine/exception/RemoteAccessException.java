package com.qunar.ops.oaengine.exception;

public class RemoteAccessException extends Exception {

	private static final long serialVersionUID = 1044414847190420688L;
	
	private Class<?> objectClass;

	public RemoteAccessException(String message, Class<?> objectClass) {
		this(message, objectClass, null);
	}

	public RemoteAccessException(Class<?> objectClass) {
		this(null, objectClass, null);
	}

	public RemoteAccessException(String message, Class<?> objectClass, Throwable cause) {
		super(message, cause);
		this.objectClass = objectClass;
	}

	public Class<?> getObjectClass() {
		return objectClass;
	}
}
