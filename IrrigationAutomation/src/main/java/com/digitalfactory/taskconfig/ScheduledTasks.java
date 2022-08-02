package com.digitalfactory.taskconfig;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.digitalfactory.model.Plot;
import com.digitalfactory.retry.IntegrationService;
import com.digitalfactory.taskservice.TaskService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	private IntegrationService integrationService;

	@Autowired
	private TaskService taskService;

	@Scheduled(fixedRate = 10000)
	public void performTask() {
		log.info("Perform Task Called ............");
		LocalTime localTime = LocalTime.now();
		Time currentTime = Time.valueOf(localTime);
		try {
			List<Plot> fetchList1 = taskService.getAllSwitchedOff(currentTime);
			if(!fetchList1.isEmpty()) {
				swithOnSensors(fetchList1, currentTime);
			}			
		} catch (Exception exception) {
			log.info("Exception thrown while fetching records from database.");
		}
		try {
			List<Plot> fetchList2 = taskService.getAllSwitchedOn(currentTime);
			if(!fetchList2.isEmpty()) {
				swithOffSensor(fetchList2, currentTime);
			}	
		} catch (Exception exception) {
			log.info("Exception thrown while fetching records from database.");
		}		
	}

	public void swithOnSensors(List<Plot> fetchList,Time currentTime) {
		log.info("swithOnSensors Called ............");
		try {
			for (Plot irrigationPlot : fetchList) {
				log.info("Irrigation Request Submited for Plot ID " + irrigationPlot.getId() + " to Switch On sensor.");
				log.info("Irrigation Request Submited for Plot Name " + irrigationPlot.getName()
						+ " to Switch On sensor.");
				integrationService.submitRequest(irrigationPlot);
			}
		} catch (Exception exception) {
			log.info("Exception thrown while switching on Sensor");
		}
	}

	public void swithOffSensor(List<Plot> fetchList,Time currentTime) {
		log.info("swithOffSensors Called ............");
		try {
			for (Plot irrigationPlot : fetchList) {
				log.info(
						"Irrigation Request Submited for Plot ID " + irrigationPlot.getId() + " to Switch Off sensor.");
				log.info("Irrigation Request Submited for Plot Name " + irrigationPlot.getName()
						+ " to Switch Off sensor.");
				integrationService.submitRequest(irrigationPlot);
			}
		} catch (Exception exception) {
			log.info("Exception thrown while switching off Sensor");
		}
	}
}
