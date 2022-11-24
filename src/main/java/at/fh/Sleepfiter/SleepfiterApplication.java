package at.fh.Sleepfiter;

import at.fh.Sleepfiter.entities.Sleep;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.oauth.OAuthService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class SleepfiterApplication {

	final static String API_KEY = "238PNK";
	final static String API_SECRET = "de61c651987739b72aa08aedaf774668";

	// fitbit-mail:

	final static String USER_ID = "B7RZY3";
	private static final String PROTECTED_RESOURCE_URL = "https://api.fitbit.com/1/user/" + USER_ID + "/profile.json";

	private static final boolean USE_API = false;

	public static void main(String[] args) {
		SpringApplication.run(SleepfiterApplication.class, args);

		if (USE_API) {
			Map<String, String> vars = new HashMap<>();
			vars.put("code", "8048a0405eeb361f80a98159713b9056ed113211");
			vars.put("grant_type", "authorization_code");

			RestTemplate restTemplate = new RestTemplate();
			List<LinkedHashMap> emps = restTemplate.getForObject(PROTECTED_RESOURCE_URL, List.class, vars);
			System.out.println(emps);
		}
	}
}