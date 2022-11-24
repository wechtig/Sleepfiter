package at.fh.Sleepfiter;

import at.fh.Sleepfiter.entities.Sleep;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/monitoring")
public class SleepInfoController {
    final static String API_KEY = "238PNK";
    final static String API_SECRET = "de61c651987739b72aa08aedaf774668";
    final static String USER_ID = "B7RZY3";

    private static final String PROTECTED_RESOURCE_URL = "https://api.fitbit.com/1/user/" + USER_ID + "/profile.json";


    @Autowired
    private SleepService sleepService;

    @GetMapping("/data")
    public List<Sleep> getSleepData() {
        var records = sleepService.readSleepRecords();
        return records;
    }

    @GetMapping("/datatest")
    public String getSleepDataTestApiConnect() {

        final OAuth20Service service = new ServiceBuilder(API_KEY)
                .apiSecret(API_SECRET)
                .scope("profile")
                .callback("http://localhost:9000/")
                .build(FitbitApi20.instance());

        String url = "https://www.fitbit.com/oauth2/authorize?client_id=238PNK&response_type=code&scope=activity";
        final Scanner in = new Scanner(System.in);
        System.out.println("URL is: " + url);
        System.out.println("And paste the authorization code here");
        System.out.print(">>");
        final String code = in.nextLine();
        try {
            OAuth2AccessToken token = service.getAccessToken(code);
            final OAuthRequest request = new OAuthRequest(Verb.POST, PROTECTED_RESOURCE_URL);
            request.addHeader("x-li-format", "json");
            //add header for authentication (why make it so complicated, Fitbit?)
            request.addHeader("Authorization", "Bearer " + token.getAccessToken());
            request.addHeader(OAuthConstants.SCOPE, token.getScope());
            request.addHeader("client_id", API_KEY);
            request.addHeader("grant_type", "authorization_code");

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


        return "hallo";
    }
}
