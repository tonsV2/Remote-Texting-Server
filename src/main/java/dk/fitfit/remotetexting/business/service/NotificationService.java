package dk.fitfit.remotetexting.business.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

// Inspired by From https://istudy.io/fcm-push-notification-server-end/

@Service
public class NotificationService {
	@Autowired
	private ConfigurationService configurationService;

	public void sendFCMMessage(final String userDeviceIdKey, final Map<String, String> data) throws IOException {
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

		ObjectMapper mapper = new ObjectMapper();

		Map<String, Object> request = new HashMap<>();
		request.put("to", userDeviceIdKey);
		request.put("data", data);

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
		outputStreamWriter.write(mapper.writeValueAsString(request));
		outputStreamWriter.flush();
		// TODO: Assert 200
		connection.getInputStream();
	}

}
