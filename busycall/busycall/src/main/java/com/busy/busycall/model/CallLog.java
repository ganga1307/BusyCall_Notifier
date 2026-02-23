package com.busy.busycall.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CallLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caller;
    private String receiver;
    private LocalDateTime timestamp;

    public CallLog() {}

    public CallLog(String caller, String receiver) {
        this.caller = caller;
        this.receiver = receiver;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getCaller() { return caller; }
    public void setCaller(String caller) { this.caller = caller; }
    public String getReceiver() { return receiver; }
    public void setReceiver(String receiver) { this.receiver = receiver; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}