package com.heena.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heena.entity.Location;
import com.heena.entity.PatientLocation;
import com.heena.enumerations.LocationType;
import com.heena.repository.LocationRepository;
import com.heena.repository.PatientLocationRepository;
import com.heena.vo.PatientLocationVO;

@Service
public class PatientLocationService {
	
	@Autowired
	private PatientLocationRepository patientLocationRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	public List<PatientLocationVO> getAllPatientLocations(){
		List<PatientLocation> patientLocations=new ArrayList<PatientLocation>();
		List<PatientLocationVO> patientLocationVOs=new ArrayList<PatientLocationVO>();
		 patientLocationRepository.findAll().forEach(patientLocations::add);
		 for(PatientLocation patientLocation:patientLocations){
			 patientLocationVOs.add(voConverter(patientLocation));
		 }
		 return patientLocationVOs;
	}


	private PatientLocationVO voConverter(PatientLocation patientLocation) {
		PatientLocationVO patientLocationVO = new PatientLocationVO(patientLocation.getFacility().getLocationId(), patientLocation.getBuilding().getLocationId(), patientLocation.getFloor().getLocationId(), patientLocation.getDepartment().getLocationId(), patientLocation.getRoom().getLocationId(), patientLocation.getBed().getLocationId());
		return patientLocationVO;
	}


	public void addPatientLocation(PatientLocationVO patientLocationVO) {
	patientLocationRepository.save(entityConverter(patientLocationVO));
		
	}


	private PatientLocation entityConverter(PatientLocationVO patientLocationVO) {
		
		Location facility=locationRepository.findByLocationIdAndLocationType(patientLocationVO.getFacility(),LocationType.FACILITY);
		Location building=locationRepository.findByLocationIdAndLocationType(patientLocationVO.getBuilding(),LocationType.BUILDING);
		Location floor=locationRepository.findByLocationIdAndLocationType(patientLocationVO.getFloor(),LocationType.FLOOR);
		Location department=locationRepository.findByLocationIdAndLocationType(patientLocationVO.getDepartment(),LocationType.DEPARTMENT);
		Location room = locationRepository.findByLocationIdAndLocationType(patientLocationVO.getRoom(),LocationType.ROOM);
		Location bed=locationRepository.findByLocationIdAndLocationType(patientLocationVO.getBed(),LocationType.BED);
	
		PatientLocation patientLocation=new PatientLocation(facility,building,floor,department,room,bed);
		
		
		return patientLocation;
	}


	public void isAllLocationsExist(PatientLocationVO patientLocationVO) {
		
		if(locationRepository.findByLocationIdAndLocationType(patientLocationVO.getFacility(), LocationType.FACILITY)==null){
			locationRepository.save(new Location(patientLocationVO.getFacility(),LocationType.FACILITY));
		}
		if(locationRepository.findByLocationIdAndLocationType(patientLocationVO.getBuilding(), LocationType.BUILDING)==null){
			locationRepository.save(new Location(patientLocationVO.getBuilding(),LocationType.BUILDING));
		}
		if(locationRepository.findByLocationIdAndLocationType(patientLocationVO.getFloor(), LocationType.FLOOR)==null){
			locationRepository.save(new Location(patientLocationVO.getFloor(),LocationType.FLOOR));
		}
		if(locationRepository.findByLocationIdAndLocationType(patientLocationVO.getDepartment(), LocationType.DEPARTMENT)==null){
			locationRepository.save(new Location(patientLocationVO.getDepartment(),LocationType.DEPARTMENT));
		}
		if(locationRepository.findByLocationIdAndLocationType(patientLocationVO.getRoom(), LocationType.ROOM)==null){
			locationRepository.save(new Location(patientLocationVO.getRoom(),LocationType.ROOM));
		}
		if(locationRepository.findByLocationIdAndLocationType(patientLocationVO.getBed(), LocationType.BED)==null){
			locationRepository.save(new Location(patientLocationVO.getBed(),LocationType.BED));
		}
		
	}


	public PatientLocation isPatientLocationExist(PatientLocationVO patientLocationVO) {
		
		return patientLocationRepository.findByPid(entityConverter(patientLocationVO).getPid());
	}


	public PatientLocationVO getPatientLocationByPid(String pid) {
		PatientLocation patientLocation=patientLocationRepository.findByPid(pid);
		if(patientLocation!=null)
		return voConverter(patientLocation);
		else
			return null;
	}


	public void updatePatientLocation(String pid, PatientLocationVO currentPatientLocationVO) {
		
		PatientLocation sustainPL=patientLocationRepository.findByPid(pid);
		PatientLocation patientLocation=patientLocationRepository.findByPid(pid);
		PatientLocation newPatientLocation=entityConverter(currentPatientLocationVO);
		patientLocationRepository.delete(patientLocationRepository.findByPid(pid));
		
		List<Location> beds=new ArrayList<>();
		List<Location> rooms=new ArrayList<>();
		List<Location> departments=new ArrayList<>();
		List<Location> floors=new ArrayList<>();
		List<Location> buildings=new ArrayList<>();
		List<Location> facilities=new ArrayList<>();
		
		List<PatientLocation> patientLocations=(List<PatientLocation>) patientLocationRepository.findAll();
		
		for(PatientLocation p :patientLocations){
			if(p.getBed().getLocationId().equals(sustainPL.getBed().getLocationId())){
				beds.add(p.getBed());
			}
			if(p.getRoom().getLocationId().equals(sustainPL.getRoom().getLocationId())){
				rooms.add(p.getRoom());
			}
			if(p.getDepartment().getLocationId().equals(sustainPL.getDepartment().getLocationId())){
				departments.add(p.getDepartment());
			}
			if(p.getFloor().getLocationId().equals(sustainPL.getFloor().getLocationId())){
				floors.add(p.getFloor());
			}
			if(p.getBuilding().getLocationId().equals(sustainPL.getBuilding().getLocationId())){
				buildings.add(p.getBuilding());
			}
			if(p.getFacility().getLocationId().equals(sustainPL.getFacility().getLocationId())){
				facilities.add(p.getFacility());
			}
		}
		
	patientLocation=new PatientLocation(newPatientLocation.getFacility(), newPatientLocation.getBuilding(), newPatientLocation.getFloor(), newPatientLocation.getDepartment(), newPatientLocation.getRoom(), newPatientLocation.getBed());
	patientLocationRepository.save(patientLocation);
	if(facilities.size()<=1){
		if(patientLocationRepository.findPatientLocationByFacilityLocationId(sustainPL.getFacility().toString()).isEmpty()){
			locationRepository.delete(locationRepository.findByLocationIdAndLocationType(sustainPL.getFacility().getLocationId(), LocationType.FACILITY));
			}
			
	}
	if(buildings.size()<=1){
		if(patientLocationRepository.findPatientLocationByBuildingLocationId(sustainPL.getBuilding().toString()).isEmpty()){
			locationRepository.delete(locationRepository.findByLocationIdAndLocationType(sustainPL.getBuilding().getLocationId(), LocationType.BUILDING));
			}
	}
	if(floors.size()<=1){
		if(patientLocationRepository.findPatientLocationByFloorLocationId(sustainPL.getFloor().toString()).isEmpty()){
			locationRepository.delete(locationRepository.findByLocationIdAndLocationType(sustainPL.getFloor().getLocationId(), LocationType.FLOOR));
			}
	}
	if(departments.size()<=1){
		if(patientLocationRepository.findPatientLocationByDepartmentLocationId(sustainPL.getDepartment().toString()).isEmpty()){
			locationRepository.delete(locationRepository.findByLocationIdAndLocationType(sustainPL.getDepartment().getLocationId(), LocationType.DEPARTMENT));
			}
	}
	if(rooms.size()<=1){
		if(patientLocationRepository.findPatientLocationByRoomLocationId(sustainPL.getRoom().toString()).isEmpty()){
			locationRepository.delete(locationRepository.findByLocationIdAndLocationType(sustainPL.getRoom().getLocationId(), LocationType.ROOM));
			}
	}
	if(beds.size()<=1){
		if(patientLocationRepository.findPatientLocationByBedLocationId(sustainPL.getBed().toString()).isEmpty()){
			locationRepository.delete(locationRepository.findByLocationIdAndLocationType(sustainPL.getBed().getLocationId(), LocationType.BED));
			}
	}
	
	
		
	}


	public void deletePatientLocation(String pid) {
		
		PatientLocation patientLocation=patientLocationRepository.findByPid(pid);
		
		List<Location> beds=new ArrayList<>();
		List<Location> rooms=new ArrayList<>();
		List<Location> departments=new ArrayList<>();
		List<Location> floors=new ArrayList<>();
		List<Location> buildings=new ArrayList<>();
		List<Location> facilities=new ArrayList<>();
		
		List<PatientLocation> plist=(List<PatientLocation>) patientLocationRepository.findAll();
		plist.remove(patientLocation);
		
		for(PatientLocation p :plist){
			if(!(p.getPid().equals(patientLocation.getPid()))  &&(p.getBed().getLocationId().equals(patientLocation.getBed().getLocationId()))){
				beds.add(p.getBed());
			}
			if(!(p.getPid().equals(patientLocation.getPid()))  &&(p.getRoom().getLocationId().equals(patientLocation.getRoom().getLocationId()))){
				rooms.add(p.getRoom());
			}
			if(!(p.getPid().equals(patientLocation.getPid()))  &&(p.getDepartment().getLocationId().equals(patientLocation.getDepartment().getLocationId()))){
				departments.add(p.getDepartment());
			}
			if(!(p.getPid().equals(patientLocation.getPid()))  &&(p.getFloor().getLocationId().equals(patientLocation.getFloor().getLocationId()))){
				floors.add(p.getFloor());
			}
			if(!(p.getPid().equals(patientLocation.getPid()))  &&(p.getBuilding().getLocationId().equals(patientLocation.getBuilding().getLocationId()))){
				buildings.add(p.getBuilding());
			}
			if(!(p.getPid().equals(patientLocation.getPid()))  &&(p.getFacility().getLocationId().equals(patientLocation.getFacility().getLocationId()))){
				facilities.add(p.getFacility());
			}



		}
		
		
		patientLocationRepository.delete(patientLocation);
		
		if(beds.size()==0){
			locationRepository.delete(locationRepository.findByLocationIdAndLocationType(patientLocation.getBed().getLocationId(), LocationType.BED));
		}
		if(rooms.size()==0){
			locationRepository.delete(locationRepository.findByLocationIdAndLocationType(patientLocation.getRoom().getLocationId(), LocationType.ROOM));
			
		}
		if(departments.size()==0){
			locationRepository.delete(locationRepository.findByLocationIdAndLocationType(patientLocation.getDepartment().getLocationId(), LocationType.DEPARTMENT));
			
		}
		if(floors.size()==0){
			locationRepository.delete(locationRepository.findByLocationIdAndLocationType(patientLocation.getFloor().getLocationId(), LocationType.FLOOR));
			
		}
		if(buildings.size()==0){
			locationRepository.delete(locationRepository.findByLocationIdAndLocationType(patientLocation.getBuilding().getLocationId(), LocationType.BUILDING));
			
		}
		if(facilities.size()==0){
			locationRepository.delete(locationRepository.findByLocationIdAndLocationType(patientLocation.getFacility().getLocationId(), LocationType.FACILITY));
			
		}
		
		
	}
	
	
}
