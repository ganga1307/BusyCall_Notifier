package com.busy.busycall.controller;

import com.busy.busycall.model.User;
import com.busy.busycall.service.CallService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class CallController {

    private final CallService service;

    public CallController(CallService service) {
        this.service = service;
    }

    @PostMapping("/call")
    public String makeCall(
            @RequestParam String callerName,
            @RequestParam String callerNumber,
            @RequestParam String receiverName,
            @RequestParam String receiverNumber
    ) {
        User caller = new User(callerName, callerNumber);
        User receiver = new User(receiverName, receiverNumber);

        return service.makeCall(caller, receiver);
    }

    @GetMapping("/notifications")
    public Set<User> notifications(@RequestParam String receiverName,
                                   @RequestParam String receiverNumber) {
        User receiver = new User(receiverName, receiverNumber);
        return service.getNotifications(receiver);
    }

    @PostMapping("/clear")
    public String clear(@RequestParam String receiverName,
                        @RequestParam String receiverNumber) {
        User receiver = new User(receiverName, receiverNumber);
        service.clearReceiver(receiver);
        return "Cleared notifications and active call for " + receiver.getName();
    }
}