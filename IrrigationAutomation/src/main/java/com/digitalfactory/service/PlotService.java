package com.digitalfactory.service;

import java.util.List;

import com.digitalfactory.model.Plot;
import com.digitalfactory.response.PaginatedPlotResponse;

public interface PlotService {
	List<Plot> getPlots();
	Plot getPlot(String name);
	Plot getPlot(Long id);
	Plot createPlot(Plot plot);
	Plot updatePlot (Long id, Plot plot);
	void deleteById(Long id);
	void deleteAll();
	PaginatedPlotResponse getAllPlots(int pageNo, int pageSize, String sortBy, String sortDir);
	
}
