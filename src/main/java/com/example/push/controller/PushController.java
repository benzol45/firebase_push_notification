package com.example.push.controller;

import com.example.push.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PushController {
    private final PushService pushService;

    @Autowired
    public PushController(PushService pushService) {
        this.pushService = pushService;
    }

    @GetMapping
    public String getMainPage() {
        return "main";
    }

    @PostMapping("/subscribe")
    public String subscribe(@RequestParam("token") String token) {
        System.out.println("recived token: " + token);
        pushService.startSendingTestNotification(token);

        return "main";
    }
}
