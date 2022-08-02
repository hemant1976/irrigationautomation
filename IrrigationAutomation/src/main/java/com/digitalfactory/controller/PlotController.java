package com.digitalfactory.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalfactory.constants.AppConstants;
import com.digitalfactory.model.Plot;
import com.digitalfactory.response.PaginatedPlotResponse;
import com.digitalfactory.response.ResponseHandler;
import com.digitalfactory.service.PlotService;

@RestController
@RequestMapping("/api")
public class PlotController {

	@Autowired
	PlotService plotService;

	@GetMapping("/plots")
	public ResponseEntity<Object> getPlots() {
		List<Plot> plots = null;
		try {
			plots = plotService.getPlots();
			return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, plots);
		} catch (Exception exception) {
			return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}

	}

	@GetMapping("/plots/{id}")
	public ResponseEntity<Object> getPlotById(@PathVariable("id") long id) {
		try {
			Plot plot = plotService.getPlot(id);
			return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, plot);
		} catch (Exception exception) {
			return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}

	}

	@PostMapping("/plots")
	public ResponseEntity<Object> createPlot(@Valid @RequestBody Plot plot) {
		try {
			Plot _plot = plotService.createPlot(plot);
			return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, _plot);
		} catch (Exception exception) {
			return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@PutMapping("/plots/{id}")
	public ResponseEntity<Object> updatePlot(@PathVariable("id") Long id, @RequestBody Plot plot) {
		try {
			Plot _plot = plotService.updatePlot(id, plot);
			return ResponseHandler.generateResponse("Updated", HttpStatus.OK, _plot);
		} catch (Exception exception) {
			return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@GetMapping("/pagination/plots")
	public PaginatedPlotResponse getAllPlots(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		return plotService.getAllPlots(pageNo, pageSize, sortBy, sortDir);
	}

	@DeleteMapping("/plots/{id}")
	public ResponseEntity<Object> deletePlot(@PathVariable("id") long id) {
		try {
			plotService.deleteById(id);
			return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, null);
		} catch (Exception exception) {
			return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@DeleteMapping("/plots")
	public ResponseEntity<Object> deleteAllPlots() {
		try {
			plotService.deleteAll();
			return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, null);
		} catch (Exception exception) {
			return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}

	}

}
