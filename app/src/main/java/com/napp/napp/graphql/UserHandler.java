package com.napp.napp.graphql;

import androidx.annotation.NonNull;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import java.util.concurrent.CompletableFuture;

import api.SignInMutation;
import api.UserQuery;

/**
 * Data class responsible for user-related API interactions
 */
public class UserHandler {

    /**
     * Send SignIn request to GraphQL API server
     * @param client
     * @param username
     * @param password
     * @return CompletableFuture result with JWT token if successful
     */
    public static CompletableFuture<String> signIn(ApolloClient client, String username, String password) {
        CompletableFuture<String> result = new CompletableFuture<>();

        client.mutate(new SignInMutation(username, password))
                .enqueue(new ApolloCall.Callback<SignInMutation.Data>() {
                    @Override
                    public void onResponse(@NonNull Response<SignInMutation.Data> response) {
                        if (response.getData().signIn().error() != null) {
                            System.out.println("SignIn request returned error: " + response.getData().signIn().error());
                            result.complete("");
                        } else if (response.getData().signIn().token() != null) {
                            result.complete(response.getData().signIn().token());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull ApolloException e) {
                        System.out.println("Error occurred on SignIn request: " + e.getMessage());
                        result.complete("");
                    }
                });

        return result;
    }

    public static CompletableFuture<UserQuery.User> user(ApolloClient client, String id) {
        CompletableFuture<UserQuery.User> result = new CompletableFuture<>();

        client.query(new UserQuery(id))
                .enqueue(new ApolloCall.Callback<UserQuery.Data>() {
                    @Override
                    public void onResponse(@NonNull Response<UserQuery.Data> response) {
                        if (response.getData().user() == null) {
                            result.complete(null);
                        }
                        result.complete(response.getData().user());
                    }

                    @Override
                    public void onFailure(@NonNull ApolloException e) {
                        System.out.println("Error occurred on User request: " + e.getMessage());
                        result.complete(null);
                    }
                });

        return result;
    }
}
