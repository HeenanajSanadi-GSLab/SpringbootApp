package com.heena.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heena.entity.ErrorMessage;
import com.heena.exception.DataFoundException;
import com.heena.exception.DataNotFoundException;
import com.heena.exception.NotAcceptableDataException;
import com.heena.service.PatientLocationService;
import com.heena.vo.PatientLocationVO;

@RestController
public class PatientLocationController {
	
	private Log logger = LogFactory.getLog(PatientLocationController.class);
	
	@Autowired
	PatientLocationService patientLocationService;
	
	@RequestMapping(value = "/patientlocation/", method = RequestMethod.GET)
	public List<PatientLocationVO> listAllPatientLocations() {
		
		
		return patientLocationService.getAllPatientLocations();
		
		
	}
	
	@RequestMapping(value = "/patientlocation/", method = RequestMethod.POST)
	public void addPatientLocation(@RequestBody PatientLocationVO patientLocationVO){
		
		if (patientLocationService.isPatientLocationExist(patientLocationVO)!=null) {
			logger.error("->PatientLocation : " + patientLocationVO + " already exist");
			throw new DataFoundException("PatientLocation : " +patientLocationVO  + " already exist");
		}
		
		
		patientLocationService.isAllLocationsExist(patientLocationVO);
		
		patientLocationService.addPatientLocation(patientLocationVO);
	}

	@RequestMapping(value = "/patientlocation/{pid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  public PatientLocationVO getPatientLocation(@PathVariable("pid") String pid) {

		logger.info("**Fetching PatientLocaion with pid " + pid);
		PatientLocationVO patientLocationVO = patientLocationService.getPatientLocationByPid(pid);
		if (patientLocationVO == null) {
			logger.error("->PatientLocation with pid " + pid + " not found");
			throw new DataNotFoundException("PatientLocation with pid " + pid + " not found");
		}
		logger.info("**Successfully compeleted GET operation on PatientLocation with pid :"+pid);
		return patientLocationVO;
	}
	
	@RequestMapping(value = "/patientlocation/{pid}", method = RequestMethod.PUT)
	public PatientLocationVO updatePatientLocation(@PathVariable("pid") String pid, @RequestBody PatientLocationVO patientLocationVO) {
		
		logger.info("**Fetching PatientLocaion with pid " + pid);

		PatientLocationVO currentPatientLocationVO =patientLocationService.getPatientLocationByPid(pid);

		if (currentPatientLocationVO==null) {
			logger.error("->PatientLocation with pid " + pid + " not found");
			throw new DataNotFoundException("PatientLocation with pid " + pid + " not found");
		}
	
		patientLocationService.isAllLocationsExist(patientLocationVO);
		currentPatientLocationVO=new PatientLocationVO(patientLocationVO.getFacility(), patientLocationVO.getBuilding(), patientLocationVO.getFloor(),patientLocationVO.getDepartment(), patientLocationVO.getRoom(),patientLocationVO.getBed());
		patientLocationService.updatePatientLocation(pid,currentPatientLocationVO);
		return currentPatientLocationVO;
	}

	@RequestMapping(value = "/patientlocation/{pid}", method = RequestMethod.DELETE)
	public PatientLocationVO deletePatientLocation(@PathVariable("pid") String pid) {
		
		logger.info("**Fetching & Deleting Patient with pid " + pid);
		PatientLocationVO patientLocationVO=patientLocationService.getPatientLocationByPid(pid);
		if (patientLocationVO == null) {
			logger.error("Unable to delete. patient with pid :" + pid + " not found");
			throw new DataNotFoundException("Unable to delete. patient with pid :" + pid + " not found");
		}

		patientLocationService.deletePatientLocation(pid);
		logger.info("**Successfully Deleted PatientLocation with pid :"+pid);
		return patientLocationVO;
	}
	
	
	@ExceptionHandler(DataFoundException.class)
	public ResponseEntity<ErrorMessage> handleDataFoundException(HttpServletRequest req,DataFoundException ex){
		logger.error("[LOGGER]->Request : "+req.getRequestURL()+" Raised : "+ex);
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.FOUND.value(), ex.getMsg());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.FOUND);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleDataNotFoundException(HttpServletRequest req,DataNotFoundException ex){
		logger.error("[LOGGER]->Request : "+req.getRequestURL()+" Raised : "+ex);
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMsg());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotAcceptableDataException.class)
	public ResponseEntity<ErrorMessage> handleInvalidFacilityException(HttpServletRequest req,NotAcceptableDataException ex){
		logger.error("[LOGGER]->Request : "+req.getRequestURL()+" Raised : "+ex);
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMsg());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_ACCEPTABLE);
	}
}
