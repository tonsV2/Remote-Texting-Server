package dk.fitfit.remotetexting.business.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// Inspired by http://tuhrig.de/why-using-springs-value-annotation-is-bad/

@Service
public class ConfigurationService {
	@Value("${security.oauth2.client.clientId}")
	private String clientId;

	@Value("${fcm.auth_key}")
	private String AUTH_KEY_FCM;

	@Value("${fcm.api_url}")
	private String API_URL_FCM;

	public String getClientId() {
		return clientId;
	}

	public String getAuthKey() {
		return AUTH_KEY_FCM;
	}

	public String getApiUrl() {
		return API_URL_FCM;
	}
}
