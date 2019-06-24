package com.uniovi.entities;

public class Location {

	private double latitude, longitude;
	private Long id;
	
	public Location(double latitude, double longitude, Long id) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "{"+latitude + ";" + longitude + ";" + id+"}";
	}
	
}
