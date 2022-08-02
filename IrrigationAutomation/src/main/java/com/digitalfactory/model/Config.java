package com.digitalfactory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "config")
public class Config {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "plotArea")
	private float plotArea;
	
	@Column(name = "cropType")
	private String cropType;
	
	@Column(name = "soilType")
	private String soilType;
	
	@Column(name = "weather")
	private String weather;	
	
	@Column(name = "fertilizerType")
	private String fertilizerType;	
	
	@Column(name = "fertilizerQty")
	private String fertilizerQty;	
	
	@Column(name = "waterQty")
	private String waterQty;	
	
	public Config() {

	}

	public Config(float plotArea, String cropType, String soilType, String weather, 
			String fertilizerType, String fertilizerQty, String waterQty) {
		this.plotArea = plotArea;
		this.cropType = cropType;
		this.soilType = soilType;
		this.weather = weather;
		this.fertilizerType = fertilizerType;
		this.fertilizerQty = fertilizerQty;
		this.waterQty = waterQty;		
	}

	public long getId() {
		return id;
	}	

	public float getPlotArea() {
		return plotArea;
	}

	public void setPlotArea(float plotArea) {
		this.plotArea = plotArea;
	}

	public String getCropType() {
		return cropType;
	}

	public void setCropType(String cropType) {
		this.cropType = cropType;
	}	

	public String getSoilType() {
		return soilType;
	}

	public void setSoilType(String soilType) {
		this.soilType = soilType;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getFertilizerType() {
		return fertilizerType;
	}

	public void setFertilizerType(String fertilizerType) {
		this.fertilizerType = fertilizerType;
	}

	public String getFertilizerQty() {
		return fertilizerQty;
	}

	public void setFertilizerQty(String fertilizerQty) {
		this.fertilizerQty = fertilizerQty;
	}

	public String getWaterQty() {
		return waterQty;
	}

	public void setWaterQty(String waterQty) {
		this.waterQty = waterQty;
	}

	@Override
	public String toString() {
		return "ConfigData [id=" + id + ", plotArea=" + plotArea + ", cropType=" + cropType + ","
				+ "soilType=" + soilType + ", weather=" + weather + ","	
				+ "fertilizerType=" + fertilizerType + ", fertilizerQty=" + fertilizerQty + ","
				+ "waterQty=" + waterQty + "]";
	}

}
