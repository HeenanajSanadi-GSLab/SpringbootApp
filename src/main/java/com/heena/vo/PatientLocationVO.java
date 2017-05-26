package com.heena.vo;


public class PatientLocationVO implements java.io.Serializable{
	
	private String pid;
	private String facility;
	private String building;
	private String floor;
	private String department;
	private String room;
	private String bed;
	
	
	
	public PatientLocationVO() {
			
	}
	
	
	public PatientLocationVO(String facility, String building, String floor, String department, String room,
			String bed) {
		
		this.pid = "_"+facility+"_"+building+"_"+floor+"_"+department+"_"+room+"_"+bed;
		this.facility = facility;
		this.building = building;
		this.floor = floor;
		this.department = department;
		this.room = room;
		this.bed = bed;
	}


	public String getPid() {
		return pid;
	}
	public void setPid(String f,String b,String fl,String d,String r,String be) {
		//pid="#"+getFacility()+"#"+getBuilding()+"#"+getFloor()+"#"+getDepartment()+"#"+getRoom()+"#"+getBed();
	pid="_"+f+"_"+b+"_"+fl+"_"+d+"_"+r+"_"+be;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getBed() {
		return bed;
	}
	public void setBed(String bed) {
		this.bed = bed;
	}
	@Override
	public String toString() {
		return "PatientLocationVO [pid=" + pid + ", facility=" + facility + ", building=" + building + ", floor="
				+ floor + ", department=" + department + ", room=" + room + ", bed=" + bed + "]";
	}
	
	
	

}
