package com.digitalfactory.exception;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String msg) {
		super(msg);
	}

	public RecordNotFoundException(String msg, Exception ex) {
		super(msg, ex);
	}
}
