package com.napp.napp.data;

import com.google.gson.Gson;
import com.napp.napp.MyApplication;
import com.napp.napp.R;
import com.napp.napp.data.model.User;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private User user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
    }

    private void setLoggedInUser(MyApplication myApplication, User user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore

        // Serialise user object to JSON string
        String userJson = new Gson().toJson(user);

        // Write serialised user JSON to SharedPreferences
        myApplication.getApplicationSharedPreferences().edit().putString(myApplication.getString(R.string.preference_user), userJson).apply();
    }

    public Result<User> login(MyApplication myApplication, String username, String password) {
        // Handle login
        Result<User> result = dataSource.login(myApplication, username, password);

        if (result instanceof Result.Success) {
            setLoggedInUser(myApplication, ((Result.Success<User>) result).getData());
        } else {
            Result.Error errResult = (Result.Error) result;
            System.out.println(errResult.getError().toString());
        }
        return result;
    }
}