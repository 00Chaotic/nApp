package com.napp.napp.data;

import com.napp.napp.BuildConfig;
import com.napp.napp.MyApplication;
import com.napp.napp.R;
import com.napp.napp.data.model.User;
import com.napp.napp.graphql.UserHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import api.UserQuery;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<User> login(MyApplication myApplication, String username, String password) {

        try {
            CompletableFuture<String> authRequest = UserHandler.signIn(myApplication.getApolloClient(), username, password);
            String authResponse = authRequest.get(BuildConfig.API_REQUEST_TIMEOUT, TimeUnit.SECONDS);

            if (authResponse.isEmpty()) {
                return new Result.Error(new Exception("Empty auth token"));
            }

            // Write auth token to SharedPreferences
            myApplication.getApplicationSharedPreferences().edit().putString(myApplication.getString(R.string.preference_user_auth_token), authResponse).apply();

            // Auth token will only be parsed if trustworthy, otherwise throws exception
            Jws<Claims> jws;
            try {
                jws = Jwts.parserBuilder().setSigningKey(BuildConfig.JWS_SECRET_KEY).build().parseClaimsJws(authResponse);
            } catch (JwtException e) {
                return new Result.Error(new Exception("Error validating auth token: " + e.getMessage()));
            }

            ArrayList<User.Role> roles = User.mapToRoles((ArrayList<String>) jws.getBody().get("role"));
            if (roles.isEmpty()) {
                return new Result.Error(new Exception("Error mapping user roles"));
            };

            UserQuery.User userData = UserHandler.user(myApplication.getApolloClient(), (String) jws.getBody().get("id")).get(BuildConfig.API_REQUEST_TIMEOUT, TimeUnit.SECONDS);
            if (userData == null) {
                return new Result.Error(new Exception("Error retrieving user data"));
            }

            User user =
                    new User(
                            userData.id(),
                            userData.name(),
                            userData.phone_number(),
                            roles,
                            userData.is_active());

            return new Result.Success<>(user);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in: " + e.getMessage()));
        }
    }
}