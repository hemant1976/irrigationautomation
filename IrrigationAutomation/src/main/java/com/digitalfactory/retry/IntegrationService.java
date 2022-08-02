package com.digitalfactory.retry;

import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import com.digitalfactory.exception.SensorDeviceServiceNotAvailableException;
import com.digitalfactory.model.Plot;

public interface IntegrationService {
	
	@Retryable(value = { SensorDeviceServiceNotAvailableException.class }, maxAttempts = 2, backoff = @Backoff(delay = 1000))
	public ResponseEntity<String> submitRequest(Plot irrigationPlot) throws Exception;

	@Recover
	public ResponseEntity<String> getBackendResponseFallback(Plot irrigationPlot);

}
