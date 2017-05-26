package com.heena.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.heena.entity.Location;
import com.heena.enumerations.LocationType;

public interface LocationRepository extends CrudRepository<Location, Serializable>{

	public Location findByLocationIdAndLocationType(String location_id,LocationType type);

	/*public void deleteLocation(String locationId, LocationType facility);*/
	
	}
