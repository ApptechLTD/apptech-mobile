package com.apptech.first.server.notifications;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.apache.camel.Body;
import org.apache.camel.Consume;
import org.apache.camel.Headers;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.apptech.first.server.notifications.model.Device;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.notnoop.apns.APNS;

/**
 * Class responsible to send messages to Apple APNS service.
 * 
 * @author Rafael Almeida
 */
public class ApnsSenderImpl implements ApnsSender {

	private static final String PAYLOAD2 = "PAYLOAD";
	
	private static final String PARAMETERS = "parameters";

	private static final String CAMEL_APNS_MESSAGE_TYPE = "CamelApnsMessageType";

	private static final String CAMEL_APNS_TOKENS = "CamelApnsTokens";
	
	private static final String MESSAGE_TYPE = "messageType";
	
	private static final String APPLICATION_ID = "applicationID";

	protected static Logger log = Logger.getLogger(ApnsSenderImpl.class
			.getName());

	@Produce(uri = "direct:mslApnsSender")
	protected ProducerTemplate producer;
	
	@Produce(uri = "direct:mslGcmSender")
	protected ProducerTemplate producerAndroid;

	@Autowired
	protected NotificationsService notificationsService;

	@Transactional(readOnly = true)
	@Override
	public void push(Long studentId, String message, Map<String, String> parameters) throws NotificationsException {
		
		StringBuilder iosDeviceTokens = new StringBuilder("");
		List<String> androidDeviceTokens = new ArrayList<String>();
		try {
			// find the device tokens
			List<Device> devices = notificationsService.getDevices(studentId);

			// making a list of device tokens
			for (Device d : devices) {
				if (d.getDeviceType().equalsIgnoreCase("iPhone")) {
					// Build the ios device token list to push
					iosDeviceTokens.append(d.getToken());
					iosDeviceTokens.append(";");
				}
				else if (d.getDeviceType().equalsIgnoreCase("Android"))
				{
					// Build the android device token list
					androidDeviceTokens.add(d.getToken());
				}
				
			}

			// We have ios device which need to push
			if (iosDeviceTokens.length() > 0)
			{
				// build the payload
				String payload = APNS.newPayload().alertBody(message).badge(1)
						.customFields(parameters).build();

				// send the message an
				Map<String, Object> headers = new HashMap<String, Object>();
				headers.put(CAMEL_APNS_TOKENS, iosDeviceTokens);
				headers.put(CAMEL_APNS_MESSAGE_TYPE, PAYLOAD2);

				log.info("Push message to APNS - studentId:" + studentId
						+ " deviceTokens: " + iosDeviceTokens + " message: " + message
						+ " parameters: " + parameters);

				producer.sendBodyAndHeaders(payload, headers);
			}
			
			if (androidDeviceTokens.size() > 0)
			{
				Map<String, Object> headers = new HashMap<String, Object>();
				headers.put(CAMEL_APNS_TOKENS, androidDeviceTokens);
				headers.put(PARAMETERS, parameters);
				producerAndroid.sendBodyAndHeaders(message, headers);
			}
				
			
			
		} 
		catch (NotificationsException t) {
			
			log.log(Level.SEVERE, "NotificationsException() : Push message to APNS - studentId:" + studentId
					+ " deviceTokens: " + iosDeviceTokens + " message: " + message
					+ " parameters: " + parameters, t);
			
			throw t;
		}
		catch (Throwable t) {
			
			log.log(Level.SEVERE, "UNKNOWN ERROR on Push message to APNS - studentId:" + studentId
					+ " deviceTokens: " + iosDeviceTokens + " message: " + message
					+ " parameters: " + parameters, t);
			
			throw new NotificationsException( "UNKNOWN ERROR sending notification to APNS : " + t.getMessage(), t);
		}

	}
	
	@Consume(uri="direct:gcmNotify")
	public void NotifyGCM(@Body String paylaod, @Headers Map<String, Object> headers) {
		log.log(Level.INFO, "NOTIFY GCM =============>>>>>");
		
		Map<String, String> parameters = (HashMap<String, String>)headers.get(PARAMETERS);
		
		String applicationID = (String) parameters.get(APPLICATION_ID);
		
		String messageType = (String) parameters.get(MESSAGE_TYPE);
		
		List<String> deviceTokens = (List<String>) headers.get(CAMEL_APNS_TOKENS);
				
		Sender sender = new Sender("AIzaSyAcoHmNu3bxqgGCqDPO8XXt5Xv5kc2Hvos"); //AIzaSyAPhsnYaI2rV3c5ROe0bQ95yjctGDVkRkY
		
		JSONObject json = new JSONObject();
		json.put("messageType", messageType);
		json.put("applicationID", applicationID);
		
		Message message = new Message.Builder().collapseKey("do_not_collapse")
				.timeToLive(0)
				.addData("title", paylaod)
				.addData("alert", paylaod)
				.addData("u", json.toJSONString())
				.build();
		try {
			MulticastResult result = sender.send(message, deviceTokens, 5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}