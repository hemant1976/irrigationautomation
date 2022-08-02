package com.digitalfactory.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.digitalfactory.exception.RecordNotFoundException;
import com.digitalfactory.model.Plot;
import com.digitalfactory.model.PlotDto;
import com.digitalfactory.repository.PlotRepository;
import com.digitalfactory.response.PaginatedPlotResponse;

@Service
public class PlotServiceImpl implements PlotService {

	@Autowired
	PlotRepository plotRepository;

	@Override
	public List<Plot> getPlots() {
		List<Plot> listPlot = plotRepository.findAll();
		if (!listPlot.isEmpty()) {
			return listPlot;
		}
		throw new RecordNotFoundException("Cant find any records");

	}

	@Override
	public Plot getPlot(String name) {
		Optional<Plot> plot = plotRepository.findByName(name);
		if (plot.isPresent()) {
			return plot.get();
		}
		throw new EntityNotFoundException("Cant find any plot by given name");
	}

	@Override
	public Plot getPlot(Long id) {
		Optional<Plot> plot = plotRepository.findById(id);
		if (plot.isPresent()) {
			return plot.get();
		}
		throw new EntityNotFoundException("Cant find any plot by given id");
	}

	@Override
	public Plot createPlot(Plot plot) {
		Plot _plot = plotRepository.save(new Plot(plot.getName(), plot.getDescription(), plot.getArea(),
				plot.getCropType(), plot.getSoilType(), plot.getStartTime(), plot.getEndTime()));
		return _plot;
	}
	
	@Override
	public Plot updatePlot(Long id, Plot plot) {
		Optional<Plot> plotDataDb = plotRepository.findById(id);
		
		if (!plotDataDb.isPresent()) {
            throw new EntityNotFoundException("Plot not present in the database");
        }
		
		Plot _plot = plotDataDb.get();
		_plot.setName(plot.getName());
		_plot.setDescription(plot.getDescription());
		_plot.setArea(plot.getArea());
		_plot.setCropType(plot.getCropType());
		_plot.setSoilType(plot.getSoilType());
		_plot.setStartTime(plot.getStartTime());
		_plot.setEndTime(plot.getEndTime());
		_plot.setSensorMode(plot.getSensorMode());
		return _plot;			
	}

	@Override
	public PaginatedPlotResponse getAllPlots(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		// create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Plot> plots = plotRepository.findAll(pageable);

		// get content for page object
		List<Plot> listOfPlots = plots.getContent();

		List<PlotDto> content = listOfPlots.stream().map(plot -> mapToDTO(plot)).collect(Collectors.toList());

		PaginatedPlotResponse paginatedPlotResponse = new PaginatedPlotResponse();
		paginatedPlotResponse.setContent(content);
		paginatedPlotResponse.setPageNo(plots.getNumber());
		paginatedPlotResponse.setPageSize(plots.getSize());
		paginatedPlotResponse.setTotalElements(plots.getTotalElements());
		paginatedPlotResponse.setTotalPages(plots.getTotalPages());
		paginatedPlotResponse.setLast(plots.isLast());

		return paginatedPlotResponse;
	}

	// convert Entity into DTO
	private PlotDto mapToDTO(Plot plot) {
		PlotDto plotDto = new PlotDto();
		plotDto.setId(plot.getId());
		plotDto.setName(plot.getName());
		plotDto.setDescription(plot.getDescription());
		plotDto.setArea(plot.getArea());
		plotDto.setCropType(plot.getCropType());
		plotDto.setSoilType(plot.getSoilType());

		return plotDto;
	}

	@Override
	public void deleteById(Long id) {
		plotRepository.deleteById(id);
		
	}

	@Override
	public void deleteAll() {
		plotRepository.deleteAll();
		
	}

}
