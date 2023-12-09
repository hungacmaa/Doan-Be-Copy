package com.be_project.controller;

import com.be_project.entity.Message;
import com.be_project.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class SocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/notify")
    public void notify(Notification notification) {
            String destinationReceive = "/notify/" + notification.getReceiver().getId();
            simpMessagingTemplate.convertAndSend(destinationReceive, notification);
    }

    @MessageMapping("/message")
    public void message(Message message) {
        String destinationReceive = "/message/" + message.getReceiver().getId();
        simpMessagingTemplate.convertAndSend(destinationReceive, message);
    }
}
