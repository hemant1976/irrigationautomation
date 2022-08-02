package com.digitalfactory.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.digitalfactory.exception.SensorDeviceServiceNotAvailableException;
import com.digitalfactory.model.Notification;
import com.digitalfactory.model.Plot;
import com.digitalfactory.repository.TaskRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class IntegrationServiceImpl implements IntegrationService {
	
	private static final Logger log = LoggerFactory.getLogger(IntegrationServiceImpl.class);
	
	final String sensorDeviceApiUrl = "http://localhost:8081/api/sensordevice/plots";
	final String notificationApiUrl = "http://localhost:10093/sendNotification";
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	ResponseEntity<String> responseEntity;

	@Override	
	public ResponseEntity<String> submitRequest(Plot irrigationPlot) {
		try {
			log.info("Calling Sensor Device API !!!");
			responseEntity = restTemplate.postForEntity(sensorDeviceApiUrl, irrigationPlot, String.class);	
			if(responseEntity.getBody().equalsIgnoreCase("SUCCESS")) {
				if(irrigationPlot.getSensorMode().equalsIgnoreCase("OFF")) {
					taskRepository.updateSensorMode(irrigationPlot.getId(),"ON",1);
				} else {
					taskRepository.updateSensorMode(irrigationPlot.getId(),"OFF",1);
				}				
			}			
		} catch (Exception exception) {
			log.info("Exception during calling Sensor Device API " + exception.getMessage());
			throw new SensorDeviceServiceNotAvailableException("Exception during calling Sensor Device API " + exception.getMessage());			
		}
		return responseEntity;		
	}

	
	@Override	
	public ResponseEntity<String> getBackendResponseFallback(Plot irrigationPlot) {
		log.info("All retries completed, so Fallback method called for sending notification !!!");
		String notificationMessage = "Sensor Device Api is down.";
		Notification notification = new Notification(notificationMessage);
		sendNotification(notification);
		return new ResponseEntity<>("Sensor Device API is down !!!", HttpStatus.OK);
	}

	private void sendNotification(Notification notification) {
		log.info("Calling Send Notification Service !!!");
		//responseEntity = restTemplate.postForEntity(notificationApiUrl, notification, String.class);	
		
	}

	

}
