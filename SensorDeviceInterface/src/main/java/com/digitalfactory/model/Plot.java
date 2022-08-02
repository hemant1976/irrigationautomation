package com.digitalfactory.model;

public class Plot {

	private long id;

	private String name;

	
	private String description;


	private float area;
	
	
	private String cropType;
	
	
	private String soilType;
	
	
	private String startTime;
	
	
	private String endTime;	
	

	private String sensorMode;
	
	public Plot() {

	}

	public Plot(String name, String description, float area, 
			String cropType, String soilType,
			String startTime, String endTime, String sensorMode) {
		this.name = name;
		this.description = description;
		this.area = area;
		this.cropType = cropType;
		this.soilType = soilType;
		this.startTime = startTime;
		this.endTime = endTime;
		this.sensorMode = sensorMode;		
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}	

	public String getSensorMode() {
		return sensorMode;
	}

	public void setSensorMode(String sensorMode) {
		this.sensorMode = sensorMode;
	}

	@Override
	public String toString() {
		return "Plot [id=" + id + ", name=" + name + ", desc=" + description + ", area=" + area + " + , "
				+ "cropType=" + cropType + ", soilType=" + soilType + ", "
				+ "startTime=" + startTime + ", endTime=" + endTime + ", sensorMode=" + sensorMode + "]";
	}

}
