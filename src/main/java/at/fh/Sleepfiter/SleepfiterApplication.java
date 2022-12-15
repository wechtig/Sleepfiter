package at.fh.Sleepfiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import java.util.*;

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
		} else {

		}
	}
}