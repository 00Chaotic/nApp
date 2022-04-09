package com.napp.napp.data;

import com.apollographql.apollo.ApolloClient;
import com.napp.napp.BuildConfig;
import com.napp.napp.data.model.LoggedInUser;
import com.napp.napp.graphql.User;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(ApolloClient client, String username, String password) {

        try {
            CompletableFuture<String> authRequest = User.signIn(client, username, password);
            String authResponse = authRequest.get(BuildConfig.API_REQUEST_TIMEOUT, TimeUnit.SECONDS);

            if (authResponse.isEmpty()) {
                throw new Exception("Empty auth token");
            }

            // Auth token will only be parsed if trustworthy, otherwise throws exception
            Jws<Claims> jws;
            try {
                jws = Jwts.parserBuilder().setSigningKey(BuildConfig.JWS_SECRET_KEY).build().parseClaimsJws(authResponse);
            } catch (JwtException e) {
                throw new Exception("Error validating auth token", e);
            }

            // TODO add call to get user info (like name) once endpoint is created

            LoggedInUser.Role role = LoggedInUser.mapStringToRole((String) jws.getBody().get("role"));
            if (role == null) {
                throw new Exception("Error mapping user role");
            }

            LoggedInUser user =
                    new LoggedInUser(
                            (String) jws.getBody().get("id"),
                            "Jane Doe", // TODO change this once user details can be received
                            authResponse,
                            role);

            return new Result.Success<>(user);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }
}