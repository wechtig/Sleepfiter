package at.fh.Sleepfiter;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuthService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class SleepfiterApplication {

	final static String API_KEY = "238PNK";
	final static String API_SECRET = "de61c651987739b72aa08aedaf774668";
	public static void main(String[] args) {
		SpringApplication.run(SleepfiterApplication.class, args);

		OAuthService service = new ServiceBuilder(API_KEY)
				.apiSecret(API_SECRET)
				.build(FitbitApi20.instance());

		OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, "/");

		try {
			var response = service.execute(oAuthRequest);
			System.out.println("res: " + response.getBody());
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}