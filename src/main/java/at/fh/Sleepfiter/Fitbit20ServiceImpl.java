package at.fh.Sleepfiter;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Base64;

public class Fitbit20ServiceImpl  extends OAuth20Service {
    final static String API_KEY = "238PNK";
    final static String API_SECRET = "de61c651987739b72aa08aedaf774668";

    // fitbit-mail:

    final static String USER_ID = "B7RZY3";

    public Fitbit20ServiceImpl(DefaultApi20 api, OAuthConfig config) {
        super(api, config);
    }

    /**
     * ref: https://dev.fitbit.com/docs/oauth2/#access-token-request
     * @param code
     * @param request
     * @param <T>
     * @return
     */
    @Override
    protected OAuthRequest createAccessTokenRequest(String code)
    {
        final DefaultApi20 api = getApi();
        final OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getAccessTokenEndpoint());
        request.addParameter(OAuthConstants.CLIENT_ID, API_KEY);
        request.addParameter(OAuthConstants.CLIENT_SECRET, API_SECRET);
        request.addParameter(OAuthConstants.CODE, code);
        request.addParameter(OAuthConstants.REDIRECT_URI, "http://localhost:9000/");
        String scope = "activity profile";
        if (scope != null) {
            request.addParameter(OAuthConstants.SCOPE, scope);
        }

        //this is non-OAuth2 standard, but Fitbit requires it
        request.addHeader("Authorization", "Basic " + getKeyBytesForFitbitAuth());

        request.addParameter(OAuthConstants.GRANT_TYPE, OAuthConstants.AUTHORIZATION_CODE);
        return request;
    }

    /**
     * ref: https://dev.fitbit.com/docs/oauth2/#refreshing-tokens
     * @param refreshToken
     * @param request
     * @param <T>
     * @return
     */
    @Override
    protected OAuthRequest createRefreshTokenRequest(String refreshToken)
    {
        if (refreshToken != null && !refreshToken.isEmpty()) {
            final DefaultApi20 api = getApi();
            final OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getAccessTokenEndpoint());
            final OAuthConfig config = this.getConfig();
            request.addParameter(OAuthConstants.CLIENT_ID, config.getApiKey());
            request.addParameter(OAuthConstants.CLIENT_SECRET, config.getApiSecret());
            request.addParameter(OAuthConstants.REFRESH_TOKEN, refreshToken);
            request.addParameter(OAuthConstants.GRANT_TYPE, OAuthConstants.REFRESH_TOKEN);

            //this is non-OAuth2 standard, but Fitbit requires it
            request.addHeader("Authorization", "Basic " + getKeyBytesForFitbitAuth());

            return request;
        } else {
            throw new IllegalArgumentException("The refreshToken cannot be null or empty");
        }
    }

    /**
     */
    private String getKeyBytesForFitbitAuth()
    {
        final OAuthConfig config = getConfig();
        String keyAndSecret = String.format("%s:%s", new Object[] {config.getApiKey(), config.getApiSecret()});
        byte[] keyBytes = Base64.getEncoder().encode(keyAndSecret.getBytes(Charset.forName("UTF-8")));

        return new String(keyBytes);
    }

}