package com.digitalfactory.service;

import java.util.List;

import com.digitalfactory.model.Config;

public interface ConfigService {
	List<Config> getConfigs();
	Config getConfig(Long id);
	Config createConfig(Config config);
	Config updateConfig(Long id, Config config);
	void deleteById(Long id);
	void deleteAll();	
}
