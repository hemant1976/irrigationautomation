package com.digitalfactory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalfactory.model.Config;
import com.digitalfactory.response.ResponseHandler;
import com.digitalfactory.service.ConfigService;

@RestController
@RequestMapping("/api")
public class ConfigController {

	@Autowired
	ConfigService configService;

	@GetMapping("/configs")
	public ResponseEntity<Object> getConfigs() {
		List<Config> configs = null;
		try {
				configs = configService.getConfigs();
				return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, configs);
			} catch (Exception exception) {
				return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
			}				
	}

	@GetMapping("/configs/{id}")
	public ResponseEntity<Object> getConfigById(@PathVariable("id") long id) {
		try {
			Config config = configService.getConfig(id);
			return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, config);
		} catch (Exception exception) {
			return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}

	}

	@PostMapping("/configs")
	public ResponseEntity<Object> createConfig(@RequestBody Config config) {
		try {
			Config _config = configService.createConfig(config);
			return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, _config);
		} catch (Exception exception) {
			return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	@PutMapping("/configs/{id}")
    public ResponseEntity<Object> updateConfig(@PathVariable("id") Long id, @RequestBody Config config) {
		try {
			 Config _config=configService.updateConfig(id, config);
			 return ResponseHandler.generateResponse("Updated", HttpStatus.OK, _config);
		} catch (Exception exception) {
			return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}        
    }

	@DeleteMapping("/configs/{id}")
	public ResponseEntity<Object> deleteConfig(@PathVariable("id") long id) {
		try {
			configService.deleteById(id);
			return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, null);			
		} catch (Exception exception) {
			return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@DeleteMapping("/configs")
	public ResponseEntity<Object> deleteAllConfigs() {
		try {
			configService.deleteAll();
			return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, null);			
		} catch (Exception exception) {
			return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}

	}

}
