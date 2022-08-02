package com.digitalfactory.taskservice;

import java.sql.Time;
import java.util.List;

import com.digitalfactory.model.Plot;

public interface TaskService {	
	public List<Plot> getAllSwitchedOff(Time currentTimeStamp) throws Exception;
	public List<Plot> getAllSwitchedOn(Time currentTimeStamp) throws Exception;
}