package com.napp.napp.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    public enum Role {
        USER,
        ADMIN
    }

    private String userId;
    private String displayName;
    private String authToken;
    private Role role;

    public LoggedInUser(String userId, String displayName, String authToken, Role role) {
        this.userId = userId;
        this.displayName = displayName;
        this.authToken = authToken;
        this.role = role;
    }

    public static Role mapStringToRole(String role) {
        switch(role) {
            case "USER": return Role.USER;
            case "ADMIN": return Role.ADMIN;
        }

        return null;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAuthToken() {
        return authToken;
    }

    public Role getRole() {
        return role;
    }
}