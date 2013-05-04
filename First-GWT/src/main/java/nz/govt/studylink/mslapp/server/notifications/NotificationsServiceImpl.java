package nz.govt.studylink.mslapp.server.notifications;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nz.govt.studylink.mslapp.server.notifications.model.Device;
import nz.govt.studylink.mslapp.server.notifications.model.User;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class NotificationsServiceImpl implements NotificationsService {

	private static final String IPHONE = "iPhone";

	private static final String ANDROID = "Android";

	protected static Logger logger = Logger.getLogger(NotificationsServiceImpl.class.getName());
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings({"unchecked" })
	@Override
	public List<Device> getDevices(Long studentId) throws NotificationsException {
		
		Query q = em.createQuery ("SELECT user FROM " + User.class.getName() + " user where user.id = :studentId");
		q.setParameter("studentId", studentId);
		
		List<User> list = q.getResultList();
		
		if(list == null || list.size() == 0){
			throw new NotificationsException("StudentId not found : " + studentId);
		}
		
		List<Device> devices = list.get(0).getListOfDevices();
		
		if(devices == null || devices.size() == 0){
			throw new NotificationsException("No devices found for studentID: " + studentId);
		}
		
		return devices;
	}
	
	@Override
	@Transactional
	public void setupNotifications(String deviceToken, String authToken, String deviceType) throws NotificationsException{
		
		validateDeviceType(deviceType);
		
		validateDeviceToken(deviceToken);
		
		validateAuthToken(authToken);
		
		//1 : get user id
		Long studentId = getStudentId(authToken);
		
		//2 : check if there is a User entity
		User user = em.find(User.class, studentId);
		if(user == null){
			//create a user instance
			user = new User();
			user.setId(studentId);
			em.persist(user);
		}
		
		//remove any record with this deviceToken
		//to make sure no duplicates deviceToken exists
		Query q = em.createQuery ("DELETE FROM " + Device.class.getName() + " d where d.token = :token");
		q.setParameter("token", deviceToken);
		int deleted = q.executeUpdate ();
		if(deleted > 0){
			logger.fine("previous found and deleted devices with same deviceToken : " + deleted);
		}
		
		//map device to user
		Device device = new Device();
		device.setUser(user);
		device.setDeviceType(deviceType);
		device.setToken(deviceToken);
		em.persist(device);
		
		if(user.getListOfDevices() == null){
			user.setListOfDevices(new ArrayList<Device>());
		}
		user.getListOfDevices().add(device);
		em.persist(user);
		
		logger.info("user setup sucesfully ! authToken: " + authToken + " deviceToken: " + deviceToken);
	}

	private void validateAuthToken(String value) throws NotificationsException{
		if(value != null && value.trim().length() > 0){
			//valid
		}
		else{
			//not valid
			throw new NotificationsException("Invalid authToken.");
		}
	}

	private void validateDeviceToken(String value) throws NotificationsException {
		if(value != null && value.trim().length() == 64){
			//valid
		}
		else{
			//not valid
			//TODO:We don't have a way to know the device token length in android
			//throw new NotificationsException("Invalid deviceToken.");
		}
	}

	private void validateDeviceType(String deviceType) throws NotificationsException{
		if(IPHONE.equalsIgnoreCase(deviceType)){
			//valid
		}
		else if(ANDROID.equalsIgnoreCase(deviceType)){
			//valid
		}
		else{
			//not valid
			throw new NotificationsException("Invalid deviceType.");
		}
	}

	//TODO: Mock for now
	private Long getStudentId(String authToken) {
		String id = authToken.substring(authToken.length() - 1);
		return new Long(id);
	}

	

}
