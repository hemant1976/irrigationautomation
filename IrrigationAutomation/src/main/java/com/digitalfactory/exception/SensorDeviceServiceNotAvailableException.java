package com.digitalfactory.exception;

public class SensorDeviceServiceNotAvailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SensorDeviceServiceNotAvailableException(String msg) {
		super(msg);
	}

	public SensorDeviceServiceNotAvailableException(String msg, Exception ex) {
		super(msg, ex);
	}
}
