package nz.govt.studylink.mslapp.server.notifications;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.component.apns.ApnsComponent;
import org.apache.camel.component.apns.factory.ApnsServiceFactory;
import org.apache.camel.component.apns.util.ApnsUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.EnhancedApnsNotification;
import com.notnoop.apns.utils.ApnsServerStub;
import com.notnoop.apns.utils.FixedCertificates;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-notifications-context.xml" })
@Transactional
@Repository	
public class ApnsSenderImplTest {

	private static final String FAKE_TOKEN = "19308314834701ACD8313AEBD92AEFDE192120371FE13982392831701318B943";
	
	public static final String IPHONE = "iPhone";

    ApnsServerStub server;

    @Autowired
	NotificationsService notificationsService;
    
    @Before
    public void startup() throws Exception {
        server = ApnsUtils.prepareAndStartServer(FixedCertificates.TEST_GATEWAY_PORT, FixedCertificates.TEST_FEEDBACK_PORT);
        
        //setup a mock APNS component
        ApnsServiceFactory apnsServiceFactory = ApnsUtils.createDefaultTestConfiguration(camelContext);
        ApnsService apnsService = apnsServiceFactory.getApnsService();

        ApnsComponent apnsComponent = (ApnsComponent)camelContext.getComponent("apns");
        apnsComponent.setApnsService(apnsService);
    }

    @After
    public void stop() {
        server.stop();
    }
	
	@Autowired
	ApnsSender apnsSender;
	
	@Autowired
	CamelContext camelContext;
	
	@Test
	public void testPush() throws InterruptedException, NotificationsException{
		Long studentId 	= 1l ;
		
		//setup the notifications
		notificationsService.setupNotifications(FAKE_TOKEN, "user1", ApnsSenderImplTest.IPHONE);
		
		String message = "New mail arrived!";
		
		Map<String, String> parameters = new HashMap<String, String>();
		
		//message type
		parameters.put("messageType", "mail");
		parameters.put("mailId", "100");
		
		//payload used to assert
		String messagePayload = APNS.newPayload().
				alertBody(message).
				badge(1).
				customFields(parameters).
				build();
		
		
        EnhancedApnsNotification apnsNotification = new EnhancedApnsNotification(1, EnhancedApnsNotification.MAXIMUM_EXPIRY, FAKE_TOKEN, messagePayload);
        server.stopAt(apnsNotification.length());
		
		apnsSender.push(studentId, message, parameters);
		
		//asserts
		server.messages.acquire();
        assertArrayEquals(apnsNotification.marshall(), server.received.toByteArray());
	}
	
}
