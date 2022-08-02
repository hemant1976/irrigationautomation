package com.digitalfactory.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalfactory.exception.RecordNotFoundException;
import com.digitalfactory.model.Config;
import com.digitalfactory.repository.ConfigRepository;


@Service
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	ConfigRepository configRepository;

	@Override
	public List<Config> getConfigs() {
		List<Config> listConfig = configRepository.findAll();
		if (!listConfig.isEmpty()) {
			return listConfig;
		}
		throw new RecordNotFoundException("Cant find any configs");

	}

	@Override
	public Config getConfig(Long id) {
		Optional<Config> config = configRepository.findById(id);
		if (config.isPresent()) {
			return config.get();
		}
		throw new EntityNotFoundException("Cant find any Config by given id");
	}

	@Override
	public Config createConfig(Config config) {
		Config _config = configRepository.save(new Config(config.getPlotArea(), config.getCropType(), config.getSoilType(),
				config.getWeather(), config.getFertilizerType(), config.getFertilizerQty(), config.getWaterQty()));
		return _config;
	}
	
	@Override
	public Config updateConfig(Long id, Config config) {
		Optional<Config> configDataDb = configRepository.findById(id);
		
		if (!configDataDb.isPresent()) {
            throw new EntityNotFoundException("Config not present in the database");
        }
		
		Config _config = configDataDb.get();
		_config.setPlotArea(config.getPlotArea());
		_config.setCropType(config.getCropType());
		_config.setSoilType(config.getSoilType());
		_config.setWeather(config.getWeather());
		_config.setFertilizerType(config.getFertilizerQty());
		_config.setFertilizerQty(config.getFertilizerQty());
		_config.setWaterQty(config.getWaterQty());		
		return _config;			
	}	

	@Override
	public void deleteById(Long id) {
		configRepository.deleteById(id);
		
	}

	@Override
	public void deleteAll() {
		configRepository.deleteAll();
		
	}

}
