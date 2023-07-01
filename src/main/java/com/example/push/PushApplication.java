package com.example.push;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//https://habr.com/ru/articles/442202/
//https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages?hl=ru
//https://firebase.google.com/docs/cloud-messaging/understand-delivery?hl=ru&platform=web
@SpringBootApplication
public class PushApplication {

    public static void main(String[] args) {
        SpringApplication.run(PushApplication.class, args);
    }

}
