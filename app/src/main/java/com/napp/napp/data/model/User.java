package com.napp.napp.data.model;

import java.util.ArrayList;

import api.UserQuery;

/**
 * Data class that captures user information for logged in users
 */
public class User {

    public enum Role {
        CLIENT,
        OPERATOR
    }

    private String userId;
    private String displayName;
    private String phoneNumber;
    private ArrayList<Role> roles;
    private boolean isActive;
    private UserClient userClient;

    public User(String userId, String displayName, String phoneNumber, ArrayList<Role> roles, boolean isActive, UserQuery.User_client userClient, String userClientLocation) {
        this.userId = userId;
        this.displayName = displayName;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.isActive = isActive;

        // TODO will eventually need to confirm whether these fields can be null, and add appropriate handling to prevent NullPointerExceptions being thrown
        int numChildren = 0;
        if (userClient.number_of_children() != null) {
            numChildren = userClient.number_of_children();
        }

        int predictedUsageFreq = 0;
        if (userClient.predicted_usage_frequency() != null) {
            predictedUsageFreq = userClient.predicted_usage_frequency();
        }

        int predictedUsageQuantity = 0;
        if (userClient.predicted_usage_quantity() != null) {
            predictedUsageQuantity = userClient.predicted_usage_quantity();
        }

        this.userClient = new UserClient(numChildren, predictedUsageFreq, predictedUsageQuantity, userClientLocation);
    }

    public static ArrayList<Role> mapToRoles(ArrayList<String> rolesList) {
        ArrayList<Role> roles = new ArrayList<>();

        for (String role : rolesList) {
            switch(role) {
                case "CLIENT": roles.add(Role.CLIENT);
                case "OPERATOR": roles.add(Role.OPERATOR);
            }
        }

        return roles;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public UserClient getUserClient() {
        return userClient;
    }

    public void setUserClient(UserClient userClient) {
        this.userClient = userClient;
    }
}