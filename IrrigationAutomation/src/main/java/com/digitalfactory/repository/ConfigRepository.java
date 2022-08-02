package com.digitalfactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalfactory.model.Config;

public interface ConfigRepository extends JpaRepository<Config, Long> {		
}
