package com.lovo.exception;

/**
 * 数据库会话异常
 * @author 骆昊
 *
 */
public class DbSessionException extends RuntimeException {
	private static final long serialVersionUID = -598543721389943669L;
	
	public DbSessionException(Throwable cause) {
		super(cause);
	}

	public DbSessionException(String message) {
		super(message);
	}

	public DbSessionException(String message, Throwable cause) {
		super(message, cause);
	}

}
