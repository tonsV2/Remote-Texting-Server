package dk.fitfit.remotetexting.business.service;

import com.google.android.gcm.server.InvalidRequestException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import dk.fitfit.remotetexting.business.domain.User;
import dk.fitfit.remotetexting.util.CurrentUserHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Stolen/Inspired from http://viveksoni.net/sending-gcm-notification-from-server-spring-framework-java/
 */

@Service
public class NotificationService {

	@Autowired
	private CurrentUserHolder currentUserHolder;

	private User account;

	@PostConstruct
	public void initialize() {
		account = currentUserHolder.getUser();
	}

	/**
	 * gcmRegId is the id which android app will give to server (one time)
	 **/
	public boolean pushNotificationToGCM(String gcmRegId, String message) {
		final String GCM_API_KEY = "sdfadfsdafsdafsdafasdfsdfsdaflsadfd";
		final int retries = 3;
		Sender sender = new Sender(GCM_API_KEY);
		Message msg = new Message.Builder().addData("message", message).build();

		try {
			if (account.getGcmRegId() != null) {
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

}
