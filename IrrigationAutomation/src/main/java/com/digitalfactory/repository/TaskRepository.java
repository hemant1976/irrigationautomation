package com.digitalfactory.repository;

import java.sql.Time;
import java.util.List;

import com.digitalfactory.model.Plot;

public interface TaskRepository {	
	List<Plot> findAllSwitchedOff(Time currentTime);
	List<Plot> findAllSwitchedOn(Time currentTime);
	void updateSensorMode(Long plotId, String mode, int switchOnCount);
}
