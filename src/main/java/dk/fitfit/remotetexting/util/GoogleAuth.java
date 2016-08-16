package dk.fitfit.remotetexting.util;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;


@Component
public class GoogleAuth {
	// TODO: http://tuhrig.de/why-using-springs-value-annotation-is-bad/
	@Value("${security.oauth2.client.clientId}")
	private String clientId;

	public String getUserId(String idTokenString) throws GeneralSecurityException, IOException {
		GoogleIdTokenVerifier verifier = getTokenVerifier();
		GoogleIdToken idToken = verifier.verify(idTokenString);
		if (idToken != null) {
			GoogleIdToken.Payload payload = idToken.getPayload();
			return payload.getSubject();
		} else {
			return null;
		}
	}

	public String getEmail(String idTokenString) throws GeneralSecurityException, IOException {
		GoogleIdTokenVerifier verifier = getTokenVerifier();
		GoogleIdToken idToken = verifier.verify(idTokenString);
		if (idToken != null) {
			GoogleIdToken.Payload payload = idToken.getPayload();
			return payload.getEmail();
		} else {
			return null;
		}
	}

	public GoogleIdTokenVerifier getTokenVerifier() {
		NetHttpTransport transport = new NetHttpTransport();
		return new GoogleIdTokenVerifier.Builder(transport, new JacksonFactory())
				.setAudience(Collections.singletonList(clientId))
				.build();
	}

}
