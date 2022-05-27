package com.napp.napp.data.model;

/**
 * Data class that captures user client information for logged in client users
 */
public class UserClient {
    private int numberOfChildren;
    private int predictedUsageFrequency;
    private int predictedUsageQuantity;
    private String location;

    public UserClient(int numberOfChildren, int predictedUsageFrequency, int predictedUsageQuantity, String location) {
        this.numberOfChildren = numberOfChildren;
        this.predictedUsageFrequency = predictedUsageFrequency;
        this.predictedUsageQuantity = predictedUsageQuantity;
        this.location = location;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public int getPredictedUsageFrequency() {
        return predictedUsageFrequency;
    }

    public void setPredictedUsageFrequency(int predictedUsageFrequency) {
        this.predictedUsageFrequency = predictedUsageFrequency;
    }

    public int getPredictedUsageQuantity() {
        return predictedUsageQuantity;
    }

    public void setPredictedUsageQuantity(int predictedUsageQuantity) {
        this.predictedUsageQuantity = predictedUsageQuantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
