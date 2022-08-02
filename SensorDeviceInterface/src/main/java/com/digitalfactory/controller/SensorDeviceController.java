package com.digitalfactory.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalfactory.model.Plot;

@RestController
@RequestMapping("/api/sensordevice")
public class SensorDeviceController {

	@PostMapping("/plots")
	public String irrigatePlot(@RequestBody Plot irrigationPlot) {
		String result = "SUCCESS";
		if (irrigationPlot.getSensorMode().equalsIgnoreCase("OFF"))
			try {
				result = swithOnSensor();
			} catch (Exception e) {
				result = "FAILURE";
				e.printStackTrace();
			}
		else
			try {
				result = swithOffSensor();
			} catch (Exception e) {
				result = "FAILURE";
				e.printStackTrace();
			}
		return result;
	}

	public String swithOnSensor() throws Exception {
		return "SUCCESS";
	}

	public String swithOffSensor() throws Exception {
		return "SUCCESS";
	}

}
