package dk.fitfit.remotetexting.business.service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

// Inspired by From https://istudy.io/fcm-push-notification-server-end/

@Service
public class NotificationService {
	@Autowired
	private ConfigurationService configurationService;

	public void sendFCMMessage(final String userDeviceIdKey, final JSONObject data) throws IOException {
		String authKey = configurationService.getAuthKey();
		String FMCurl = configurationService.getApiUrl();

		URL url = new URL(FMCurl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setUseCaches(false);
		connection.setDoInput(true);
		connection.setDoOutput(true);

		connection.setRequestMethod("POST");
		connection.setRequestProperty("Authorization", "key=" + authKey);
		connection.setRequestProperty("Content-Type", "application/json");

		JSONObject request = new JSONObject();
		request.put("to", userDeviceIdKey);
		request.put("data", data);

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
		outputStreamWriter.write(request.toString());
		outputStreamWriter.flush();
		connection.getInputStream();
	}

}
