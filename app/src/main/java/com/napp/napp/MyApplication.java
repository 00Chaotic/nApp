package com.napp.napp;

import android.content.Context;
import android.content.SharedPreferences;

import com.apollographql.apollo.ApolloClient;
import com.napp.napp.graphql.Middleware.AuthorizationInterceptor;

import okhttp3.OkHttpClient;

/**
 * Custom implementation of android.app.Application class.
 * Allows for sharing of variables across the application,
 * as well as overriding the onCreate method for custom
 * application startup operation.
 *
 * NOTE: Some fields will not follow conventional getter/setter
 * pattern, e.g. if they are meant to be immutable.
 */
public class MyApplication extends android.app.Application {

    private ApolloClient apolloClient;
    private SharedPreferences sharedPreferences;

    /**
     * This method is executed once when the application is started (including
     * when the application is resumed after being closed or put to sleep).
     */
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialise SharedPreferences variable
        initSharedPreferences();
        this.getApplicationSharedPreferences().edit().clear().apply();

        // Create ApolloClient
        ApolloClient client = ApolloClient.builder()
                .serverUrl(BuildConfig.GRAPHQL_API_ENDPOINT)
                .okHttpClient(new OkHttpClient.Builder()
                    .addInterceptor(new AuthorizationInterceptor(this))
                    .build())
                .build();

        // Set ApolloClient
        setApolloClient(client);
    }

    public ApolloClient getApolloClient() {
        return apolloClient;
    }

    /**
     * Private setter because this variable should not be set by external entities
     * @param client
     */
    private void setApolloClient(ApolloClient client) {
        this.apolloClient = client;
    }

    public SharedPreferences getApplicationSharedPreferences() {
        return sharedPreferences;
    }

    /**
     * Private initializer, as this should only initialised once and shared across the application
      */
    private void initSharedPreferences() {
        this.sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE);
    }
}
