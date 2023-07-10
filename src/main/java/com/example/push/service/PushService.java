package com.example.push.service;

import com.example.push.dto.PushNotification;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

@Service
public class PushService {
    private String fileName = "rmt-mobile-firebase.json";

    public PushService() throws IOException {
        InputStream credentialsStream = new ClassPathResource(fileName).getInputStream();;
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(credentialsStream);
        FirebaseOptions firebaseOptions = FirebaseOptions.builder().setCredentials(googleCredentials).build();
        FirebaseApp.initializeApp(firebaseOptions);
    }

    public String sendToClient(PushNotification notification, String clientToken) throws ExecutionException, InterruptedException, FirebaseMessagingException {

        String response = FirebaseMessaging.getInstance()
                //.send(notification.generateMessage(clientToken));     //можно и так, синхронно
                .sendAsync(notification.generateMessage(clientToken))
                .get();

        return response;
    }

    public void startSendingTestNotification(String token) {
        int counter = 0;
        while (true) {
            try {
                Thread.sleep(5*1000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }

            counter++;
            PushNotification pushNotification = PushNotification.builder()
                    .title("Notification # " + counter)
                    .body("This notification created at " + LocalDateTime.now())
                    .ttlInSeconds(24*60*60)
                    .build();

            try {
                String result = sendToClient(pushNotification, token);
                System.out.println("Push notification sent. Answer from Firebase: " + result);
            } catch (ExecutionException | InterruptedException | FirebaseMessagingException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /*public String sendByTopic(PushNotifyConf conf, String topic)
            throws InterruptedException, ExecutionException {

        Message message = Message.builder().setTopic(topic)
                .setWebpushConfig(WebpushConfig.builder()
                        .putHeader("ttl", conf.getTtlInSeconds())
                        .setNotification(createBuilder(conf).build())
                        .build())
                .build();

        String response = FirebaseMessaging.getInstance()
                .sendAsync(message)
                .get();
        return response;
    }*/

    /*public void subscribeUsers(String topic, List<String> clientTokens)
            throws  FirebaseMessagingException {
        for (String token : clientTokens) {
            TopicManagementResponse response = FirebaseMessaging.getInstance()
                    .subscribeToTopic(Collections.singletonList(token), topic);
        }
    }*/
}
