package com.busy.busycall.model;

public enum UserState {
    FREE,     // User is available
    RINGING,  // Someone is calling the user
    IN_CALL   // User is already in a call
}