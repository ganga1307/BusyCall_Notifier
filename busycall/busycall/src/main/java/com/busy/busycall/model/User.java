package com.busy.busycall.model;

import java.util.Objects;

public class User {
    private String name;
    private String phoneNumber;
    private UserState state;

    public User(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.state = UserState.FREE;
    }

    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public UserState getState() { return state; }
    public void setState(UserState state) { this.state = state; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return phoneNumber.equals(user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public String toString() {
        return name + " (" + phoneNumber + ")";
    }
}