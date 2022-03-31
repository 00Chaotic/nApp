package com.napp.napp.graphql;

import com.apollographql.apollo3.ApolloCall;
import com.apollographql.apollo3.ApolloClient;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.rx3.Rx3Apollo;
import com.napp.napp.BuildConfig;
import com.napp.napp.api.sample.GetLocationsQuery;

import io.reactivex.rxjava3.core.Single;

public class Sample {

    /**
     * Sample function to demonstrate how to make a GraphQL API call.
     * This code is very basic and does not incorporate best practices,
     * and should only be used as a rough guide for getting started.
     */
    public static void makeCall() {
        // Create client
        ApolloClient client = new ApolloClient.Builder().serverUrl(BuildConfig.GRAPHQL_API_ENDPOINT).build();

        // Make API call
        ApolloCall queryCall = client.query(new GetLocationsQuery());
        Single<ApolloResponse> queryResponse = Rx3Apollo.single(queryCall);
        queryResponse.subscribe((apolloResponse, throwable) -> {
            // Do something with returned response
            System.out.println(apolloResponse.data);
            System.out.println(apolloResponse.hasErrors());
        });
    }
}
