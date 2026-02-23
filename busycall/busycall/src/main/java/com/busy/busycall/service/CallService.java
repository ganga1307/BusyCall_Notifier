package com.busy.busycall.service;

import com.busy.busycall.model.CallLog;
import com.busy.busycall.model.User;
import com.busy.busycall.model.UserState;
import com.busy.busycall.repository.CallLogRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CallService {

    private final CallLogRepository repository;
    private final Map<String, User> activeCalls = new HashMap<>();
    private final Map<String, Set<User>> parallelAttempts = new HashMap<>();

    public CallService(CallLogRepository repository) {
        this.repository = repository;
    }

    public String makeCall(User caller, User receiver) {

        if (!activeCalls.containsKey(receiver.getPhoneNumber())) {
            receiver.setState(UserState.RINGING);
            activeCalls.put(receiver.getPhoneNumber(), caller);
            repository.save(new CallLog(caller.getName(), receiver.getName()));

            return receiver.getName() + " is now ringing by " + caller.getName();
        }

        parallelAttempts
                .computeIfAbsent(receiver.getPhoneNumber(), k -> new HashSet<>())
                .add(caller);

        repository.save(new CallLog(caller.getName(), receiver.getName()));

        return receiver.getName() + " is busy. Attempt recorded from " + caller.getName();
    }

    public Set<User> getNotifications(User receiver) {
        return parallelAttempts.getOrDefault(receiver.getPhoneNumber(), new HashSet<>());
    }

    public void clearReceiver(User receiver) {
        activeCalls.remove(receiver.getPhoneNumber());
        parallelAttempts.remove(receiver.getPhoneNumber());
    }
}