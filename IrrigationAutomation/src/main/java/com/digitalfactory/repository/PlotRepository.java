package com.digitalfactory.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalfactory.model.Plot;

@Repository
public interface PlotRepository extends JpaRepository<Plot, Long> {		
	Optional<Plot> findByName(String name);  	
}
