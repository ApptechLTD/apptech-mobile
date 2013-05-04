package nz.govt.studylink.mslapp.shared.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="root")
public class UserList {

	private List<UserModel> users;

	public UserList(){
		
	}
	
	public UserList(List<UserModel> users){
		this.users = users;
	};
	
	@XmlElementWrapper(name="userList")
	@XmlElement
	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}
	
	
}
