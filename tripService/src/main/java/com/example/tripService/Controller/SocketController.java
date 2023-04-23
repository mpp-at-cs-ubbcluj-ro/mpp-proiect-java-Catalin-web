package com.example.tripService.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@Controller
public class SocketController {

    @MessageMapping("/process-message")
    @SendTo("/topic/messages")
    public String processMessage(String incomingMessage) throws Exception{
        return incomingMessage;
    }
}
