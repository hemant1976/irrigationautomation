package com.digitalfactory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "plots")
public class Plot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotEmpty
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "area")
	private float area;
	
	@NotEmpty
	@Column(name = "cropType")
	private String cropType;
	
	@NotEmpty
	@Column(name = "soilType")
	private String soilType;
	
	@NotEmpty
	@Column(name = "startTime")
	private String startTime;
	
	@NotEmpty
	@Column(name = "endTime")
	private String endTime;	
	
	@Column(name = "sensorMode")
	private String sensorMode="OFF";
	
	@Column(name = "switchOnCount")
	private int switchOnCount=0;
	
	public Plot() {

	}

	public Plot(String name, String description, float area, 
			String cropType, String soilType,
			String startTime, String endTime) {
		this.name = name;
		this.description = description;
		this.area = area;
		this.cropType = cropType;
		this.soilType = soilType;
		this.startTime = startTime;
		this.endTime = endTime;				
	}	
	
	public void setId(long id) {
		this.id = id;
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

	public int getSwitchOnCount() {
		return switchOnCount;
	}

	public void setSwitchOnCount(int switchOnCount) {
		this.switchOnCount = switchOnCount;
	}

	@Override
	public String toString() {
		return "Plot [id=" + id + ", name=" + name + ", desc=" + description + ", area=" + area + " + , "
				+ "cropType=" + cropType + ", soilType=" + soilType + ", "
				+ "startTime=" + startTime + ", endTime=" + endTime + ", sensorMode=" + sensorMode + "]";
	}

}
