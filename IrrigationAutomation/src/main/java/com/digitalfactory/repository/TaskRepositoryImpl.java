package com.digitalfactory.repository;

import java.sql.Time;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.digitalfactory.model.Plot;

@Component
public class TaskRepositoryImpl implements TaskRepository {
	
	private static final Logger log = LoggerFactory.getLogger(TaskRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Plot> findAllSwitchedOff(Time currentTime) {
		 log.info("findAllSwitchedOff Called ............");
		 String fetchQuery = "select * from plots WHERE '"+ currentTime + "' > start_time AND sensor_mode='OFF' AND switch_on_count=0";
		    return jdbcTemplate.query(fetchQuery, BeanPropertyRowMapper.newInstance(Plot.class));
	}

	@Override
	public List<Plot> findAllSwitchedOn(Time currentTime) {
		log.info("findAllSwitchedOn Called ............");
		//String fetchQuery = "select * from plots WHERE end_time > '"+ currentTime + "' AND sensor_mode='ON' AND switch_on_count=1";
		String fetchQuery = "select * from plots WHERE '"+ currentTime + "' > end_time AND sensor_mode='ON' AND switch_on_count=1";
	    return jdbcTemplate.query(fetchQuery, BeanPropertyRowMapper.newInstance(Plot.class));
	}

	@Override
	public void updateSensorMode(Long plotId, String mode,int switchOnCount) {
		log.info("updateSensorMode Called ............" + plotId);
		String updateQuery = "update plots set sensor_mode = ?,switch_on_count = ? where id = ?";
		jdbcTemplate.update(updateQuery, mode, switchOnCount, plotId);	
		 return;
	}

}
