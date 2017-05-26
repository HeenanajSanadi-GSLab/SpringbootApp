package com.heena.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.heena.entity.PatientLocation;
import com.heena.vo.PatientLocationVO;


public interface PatientLocationRepository extends CrudRepository<PatientLocation, Serializable>{

	public PatientLocation findByPid(String pid);
	
	public List<PatientLocation> findPatientLocationByFacilityLocationId(String locationId);

	public List<PatientLocation> findPatientLocationByBuildingLocationId(String locationId);

	public List<PatientLocation> findPatientLocationByFloorLocationId(String locationId);

	public List<PatientLocation> findPatientLocationByDepartmentLocationId(String locationId);

	public List<PatientLocation> findPatientLocationByRoomLocationId(String locationId);

	public List<PatientLocation> findPatientLocationByBedLocationId(String locationId);

	//public List<PatientLocationVO> findPatientLocationByBuilding(Location building);

	
}
