package com.heena.entity;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



@Entity
public class PatientLocation implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk;
	
	@Column(name = "pid")
	private String pid;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER, optional=true)
	private Location facility;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER, optional=true)
	private Location building;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER, optional=true)
	private Location floor;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER, optional=true)
	private Location department;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER, optional=true)
	private Location room;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER, optional=true)
	private Location bed;

	
	public PatientLocation() {
		// TODO Auto-generated constructor stub
	}

	public PatientLocation(Location facility, Location building, Location floor,
			Location department, Location room, Location bed) {
		
		
		this.pid = "_"+facility+"_"+building+"_"+floor+"_"+department+"_"+room+"_"+bed;
		this.facility = facility;
		this.building = building;
		this.floor = floor;
		this.department = department;
		this.room = room;
		this.bed = bed;
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}


	public Location getFacility() {
		return facility;
	}

	public void setFacility(Location facility) {
		this.facility = facility;
	}

	public Location getBuilding() {
		return building;
	}

	public void setBuilding(Location building) {
		this.building = building;
	}

		public Location getFloor() {
		return floor;
	}

	public void setFloor(Location floor) {
		this.floor = floor;
	}

	public Location getDepartment() {
		return department;
	}

	public void setDepartment(Location department) {
		this.department = department;
	}

	public Location getRoom() {
		return room;
	}

	public void setRoom(Location room) {
		this.room = room;
	}

	public Location getBed() {
		return bed;
	}

	public void setBed(Location bed) {
		this.bed = bed;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "\nPatientLocation [pk=" + pk + ", pid=" + pid + "]";
	}

	

	/*public void setPid(Location f,Location b,Location fl,Location d,Location r,Location be) {
		pid="#"+f+"#"+b+"#"+fl+"#"+d+"#"+r+"#"+be;
	}*/
	
	


}
