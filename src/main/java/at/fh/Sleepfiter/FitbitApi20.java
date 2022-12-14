package at.fh.Sleepfiter;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.oauth.OAuth20Service;

public class FitbitApi20 extends DefaultApi20 {

    protected FitbitApi20() {
    }

    private static class InstanceHolder {
        private static final FitbitApi20 INSTANCE = new FitbitApi20();
    }

    public static FitbitApi20 instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://api.fitbit.com/oauth2/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://www.fitbit.com/oauth2/authorize";
    }

/*
    @Override
    public OAuth20Service createService(OAuthConfig config) {
        return new Fitbit20ServiceImpl(this, config);
    }*/
}