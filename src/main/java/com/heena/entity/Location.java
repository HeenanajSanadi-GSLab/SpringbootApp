package com.heena.entity;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.heena.enumerations.LocationType;

@Entity
@Table(name = "location",uniqueConstraints= @UniqueConstraint(columnNames={"locationId", "locationType"}))
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk;
	
	
	private String locationId;
	
	
	@Enumerated(value = EnumType.STRING)
	private LocationType locationType;

	
	
	
	public Location() {
		}
	
	

	public Location(String locationId, LocationType locationType) {
		super();
		this.locationId = locationId;
		this.locationType = locationType;
	}



	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	@Override
	public String toString() {
		return "" + locationId + "";
	}
	

}
