package nz.govt.studylink.mslapp.server.notifications.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * Represents each user in the system.
 * 
 * Each user have a list of devices
 * 
 * @author Rafael Almeida
 *
 */
@Entity
public class User {
	
	@Id
	private Long id;
	
	@OneToMany(mappedBy="user")
	private List<Device> listOfDevices;
	
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	
	public List<Device> getListOfDevices() {
		return listOfDevices;
	}
	public void setListOfDevices(List<Device> listOfDevices) {
		this.listOfDevices = listOfDevices;
	}
	
}
