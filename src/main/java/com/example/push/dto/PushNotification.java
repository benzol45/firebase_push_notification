package com.example.push.dto;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PushNotification {
    private String title;
    private String body;
    private String icon;
    private int ttlInSeconds;

    private int DEFAULT_TTL_IN_SECONDS = 24*60*60;

    public Message generateMessage(String clientToken) {
        WebpushNotification webpushNotification = WebpushNotification.builder()
                .setTitle(title)
                .setBody(body)
                .setIcon(icon)
                .build();

        WebpushConfig webpushConfig = WebpushConfig.builder()
                .putHeader("ttl", Integer.toString(ttlInSeconds>=0 ? ttlInSeconds : DEFAULT_TTL_IN_SECONDS))
                .setNotification(webpushNotification)
                .build();

        return Message.builder()
                .setToken(clientToken)
                .setWebpushConfig(webpushConfig)
                .build();
    }
}
