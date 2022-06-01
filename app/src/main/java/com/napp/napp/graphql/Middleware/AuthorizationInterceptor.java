package com.napp.napp.graphql.Middleware;

import com.napp.napp.MyApplication;
import com.napp.napp.R;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizationInterceptor implements Interceptor {

    private MyApplication myApplication;

    public AuthorizationInterceptor(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        // Retrieve and deserialize user json
        //String authToken = myApplication.getSharedPreferences().getString(myApplication.getString(R.string.preference_user_auth_token), null);
//        if (authToken == null) {
//            return chain.proceed(chain.request());
//        }
//
//        // Inject Authorization header with user's JWT into request
//        String authHeader = "Bearer " + authToken;
//        Request req = chain.request().newBuilder()
//                .addHeader("Authorization", authHeader)
//                .build();

        return null;//chain.proceed(req);
    }
}