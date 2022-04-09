package com.napp.napp;

import android.content.Context;
import android.content.SharedPreferences;

import com.apollographql.apollo.ApolloClient;

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

        // Create the client
        setApolloClient(ApolloClient.builder().serverUrl(BuildConfig.GRAPHQL_API_ENDPOINT).build());

        // Initialise SharedPreferences variable
        initSharedPreferences();
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
        this.sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }
}
