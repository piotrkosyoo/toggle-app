package pit.kos.toggle.services;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginResult;

public class LoginServices {

	public InstagramLoginResult login(Instagram4j instagram4j) throws ClientProtocolException, IOException {
		return instagram4j.login();
	}
}
