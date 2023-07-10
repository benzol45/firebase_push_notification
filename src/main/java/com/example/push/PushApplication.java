package com.example.push;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//https://habr.com/ru/articles/442202/
//https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages?hl=ru
//https://firebase.google.com/docs/cloud-messaging/understand-delivery?hl=ru&platform=web

//Вход по сгенерированным нами токенам (но они всё равно не дольше часа живут)
//https://firebase.google.com/docs/auth/admin/create-custom-tokens?hl=ru#java

//Умеет слать сразу на много устройств
//https://firebase.google.com/docs/cloud-messaging/send-message?hl=ru&authuser=0
//BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);

//Работа с токенами
// https://firebase.google.com/docs/cloud-messaging/manage-tokens?hl=ru

//РАБОЧАЯ ТЕОРИЯ - токен FCM живёт бесконечно.
//токен авторизации - час но нам это не важно, мы его не используем.
// хорошо бы что бы устройства иногда нам сообщали актуальный токен (рефрешили)
@SpringBootApplication
public class PushApplication {

    public static void main(String[] args) {
        SpringApplication.run(PushApplication.class, args);
    }

}
