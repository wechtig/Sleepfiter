package at.fh.Sleepfiter;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.oauth.OAuthService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class SleepfiterApplication {

	final static String API_KEY = "238PNK";
	final static String API_SECRET = "de61c651987739b72aa08aedaf774668";
	private static final String PROTECTED_RESOURCE_URL = "https://api.fitbit.com/1/user/" + USER_ID + "/profile.json";
	public static void main(String[] args) {
		SpringApplication.run(SleepfiterApplication.class, args);

		OAuth20Service service = new ServiceBuilder(API_KEY)
				.apiSecret(API_SECRET)
				.build(FitbitApi20.instance());

		final Scanner in = new Scanner(System.in);
		final String authorizationUrl = service.getAuthorizationUrl();
		final String code = in.nextLine();

		try {
			final OAuth2AccessToken accessToken = service.getAccessToken(code);
			final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
			request.addHeader("x-li-format", "json");
			request.addHeader("Authorization", "Bearer " + accessToken.getAccessToken());
			final Response response = service.execute(request);
			System.out.println();
			System.out.println(response.getCode());
			System.out.println(response.getBody());

		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
		/*OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, "/");

		try {
			var response = service.execute(oAuthRequest);
			System.out.println("res: " + response.getBody());
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}*/
	}
}