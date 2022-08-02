package com.digitalfactory.taskservice;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digitalfactory.model.Plot;
import com.digitalfactory.repository.TaskRepository;

@Component
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public List<Plot> getAllSwitchedOff(Time currentTime) throws Exception {
		return taskRepository.findAllSwitchedOff(currentTime);
	}

	@Override
	public List<Plot> getAllSwitchedOn(Time currentTime) throws Exception {
		return taskRepository.findAllSwitchedOn(currentTime);
	}
}
