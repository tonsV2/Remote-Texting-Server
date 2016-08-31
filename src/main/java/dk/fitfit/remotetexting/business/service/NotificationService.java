package dk.fitfit.remotetexting.business.service;

import com.google.android.gcm.server.InvalidRequestException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import dk.fitfit.remotetexting.util.CurrentUserHolder;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;

/**
 * Stolen/Inspired from http://viveksoni.net/sending-gcm-notification-from-server-spring-framework-java/
 */

@Service
public class NotificationService {

	@Autowired
	private CurrentUserHolder currentUserHolder;

	/**
	 * gcmRegId is the id which android app will give to server (one time)
	 **/
	public boolean sendGCMMessage(String gcmRegId, String message) {
		final String GCM_API_KEY = "sdfadfsdafsdafsdafasdfsdfsdaflsadfd";
		final int retries = 3;
		Sender sender = new Sender(GCM_API_KEY);
		Message msg = new Message.Builder().addData("message", message).build();

		try {
			if (currentUserHolder.getUser().getFcmRegId() != null) {
				/**
				 * if you want to send to multiple then use below method
				 * send(Message message, List<String> regIds, int retries)
				 **/
				Result result = sender.send(msg, gcmRegId, retries);

				if (StringUtils.isEmpty(result.getErrorCodeName())) {
					System.out.println("GCM Notification is sent successfully" + result.toString());
					return true;
				}

				System.out.println("Error occurred while sending push notification :" + result.getErrorCodeName());

			}
		} catch (InvalidRequestException e) {
			System.out.println("Invalid Request");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
		return false;

	}

	// TODO: From https://istudy.io/fcm-push-notification-server-end/
	// Method to send Notifications from server to client end.
	// TODO: http://tuhrig.de/why-using-springs-value-annotation-is-bad/
	@Value("${fcm.auth_key}")
	private String AUTH_KEY_FCM;
	@Value("${fcm.api_url}")
	private String API_URL_FCM;

// userDeviceIdKey is the device id you will query from your database

	public void sendFCMMessage(String userDeviceIdKey) throws Exception {

		String authKey = AUTH_KEY_FCM;   // You FCM AUTH key
		String FMCurl = API_URL_FCM;

		URL url = new URL(FMCurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + authKey);
		conn.setRequestProperty("Content-Type", "application/json");

		JSONObject data = new JSONObject();
		data.put("key1", "value1");
		data.put("key2", "value2");
		data.put("ts", String.valueOf(Instant.now().getEpochSecond()));

		JSONObject json = new JSONObject();
		json.put("to", userDeviceIdKey.trim());
//		JSONObject info = new JSONObject();
//		info.put("title", "Notificatoin Title");   // Notification title
//		info.put("body", "Hello Test notification"); // Notification body
//		json.put("notification", info);
		json.put("data", data);

		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(json.toString());
		wr.flush();
		conn.getInputStream();
	}

}
