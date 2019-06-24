package com.uniovi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Representation {
	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	
	@Column(length=8192)
	private String description;
	
	@Column(length=8192)
	private String history;
	
	@Column(length=8192)
	private String interestInfo;
	
	@Column(length=8192)
	private String technicalInfo;
	
	private double longitude, latitude;
	
	public Representation() {}
	
	public Representation(String title, String description, String history,
			String interestInfo, String technicalInfo, double longitude, double latitude) {
		this.title = title;
		this.description = description;
		this.history = history;
		this.interestInfo = interestInfo;
		this.technicalInfo = technicalInfo;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getInterestInfo() {
		return interestInfo;
	}

	public void setInterestInfo(String interestInfo) {
		this.interestInfo = interestInfo;
	}

	public String getTechnicalInfo() {
		return technicalInfo;
	}

	public void setTechnicalInfo(String technicalInfo) {
		this.technicalInfo = technicalInfo;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double altitude) {
		this.longitude = altitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "{\"id\":" + id + ", \"title\":" + title + ", \"description\":" + description + ", \"history\":" + history
				+ ", \"interestInfo\":" + interestInfo + ", \"technicalInfo\":" + technicalInfo + ", \"latitude\":" + latitude
				+ ", \"longitude\":" + longitude + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((history == null) ? 0 : history.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((interestInfo == null) ? 0 : interestInfo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((technicalInfo == null) ? 0 : technicalInfo.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Representation other = (Representation) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (history == null) {
			if (other.history != null)
				return false;
		} else if (!history.equals(other.history))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (interestInfo == null) {
			if (other.interestInfo != null)
				return false;
		} else if (!interestInfo.equals(other.interestInfo))
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (technicalInfo == null) {
			if (other.technicalInfo != null)
				return false;
		} else if (!technicalInfo.equals(other.technicalInfo))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
