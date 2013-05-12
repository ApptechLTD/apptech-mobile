package com.apptech.first.server.notifications;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.apptech.first.server.notifications.NotificationsException;
import com.apptech.first.server.notifications.NotificationsService;
import com.apptech.first.server.notifications.model.Device;
import com.apptech.first.server.notifications.model.User;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-notifications-context.xml" })
@Transactional
@Repository
public class NotificationsServiceImplTest {
	
	@Autowired
	NotificationsService notificationsService;
	
	@PersistenceContext
	private EntityManager em;
	
	String deviceToken_1 = "0015632e82e2954d831d6a3538b53b513a7e9403f11f4bbaa739c23ec13ccbXX";
	String deviceToken_2 = "0025632e82e2954d831d6a3538b53b513a7e9403f11f4bbaa739c23ec13ccbXX";
	String deviceToken_3 = "0035632e82e2954d831d6a3538b53b513a7e9403f11f4bbaa739c23ec13ccbXX";
	
	public static final String IPHONE = "iPhone";
	
	
	@Test(expected=NotificationsException.class)
	public void testgetDevices_wrongUserId() throws NotificationsException{
		notificationsService.getDevices(new Long(100l));
	}
	
	@Test
	public void testgetDevices() throws NotificationsException{
		notificationsService.setupNotifications(deviceToken_1, "user1", NotificationsServiceImplTest.IPHONE);
		notificationsService.setupNotifications(deviceToken_2, "user1", NotificationsServiceImplTest.IPHONE);
		
		List<Device> list = notificationsService.getDevices(new Long(1));
		
		assertNotNull(list);
		assertEquals("2 devices added", 2, list.size());
	}
	
	@Test(expected=NotificationsException.class)
	public void testSetupNotifications_wrongDeviceType() throws NotificationsException{
		notificationsService.setupNotifications(deviceToken_1, "user1", "wrong type");
	}
	
//	@Test(expected=NotificationsException.class)
//	public void testSetupNotifications_wrongDeviceToken() throws NotificationsException{
//		notificationsService.setupNotifications("wrong token", "user1", NotificationsServiceImplTest.IPHONE);
//	}
	
	@Test(expected=NotificationsException.class)
	public void testSetupNotifications_nullUser() throws NotificationsException{
		notificationsService.setupNotifications(deviceToken_1, null, NotificationsServiceImplTest.IPHONE);
	}
	
	@Test(expected=NotificationsException.class)
	public void testSetupNotifications_emotyUser() throws NotificationsException{
		notificationsService.setupNotifications(deviceToken_1, "", NotificationsServiceImplTest.IPHONE);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testSetupNotifications_1() throws NotificationsException{
		
		notificationsService.setupNotifications(deviceToken_1, "user1", NotificationsServiceImplTest.IPHONE);
		
		//verify user
		Query q = em.createQuery ("SELECT user FROM " + User.class.getName() + " user ");
		List list = q.getResultList();
		
		assertNotNull(list);
		assertEquals("1 user added", 1, list.size());
		
		//verify device
		q = em.createQuery ("SELECT d FROM " + Device.class.getName() + " d ");
		list = q.getResultList();
		
		assertNotNull(list);
		assertEquals("1 device added", 1, list.size());
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testSetupNotifications_secondDeviceSameUser() throws NotificationsException{
		
		notificationsService.setupNotifications(deviceToken_1, "user1", NotificationsServiceImplTest.IPHONE);
		notificationsService.setupNotifications(deviceToken_2, "user1", NotificationsServiceImplTest.IPHONE);
		
		//second time should be ignored for the same token
		notificationsService.setupNotifications(deviceToken_2, "user1", NotificationsServiceImplTest.IPHONE);
		
		//verify user
		Query q = em.createQuery ("SELECT user FROM " + User.class.getName() + " user ");
		List list = q.getResultList();
		
		assertNotNull(list);
		assertEquals("1 user added", 1, list.size());
		
		//verify device
		q = em.createQuery ("SELECT d FROM " + Device.class.getName() + " d ");
		list = q.getResultList();
		
		assertNotNull(list);
		assertEquals("2 devices added", 2, list.size());
	}
	
}
