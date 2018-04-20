package net.dni.websocketconsole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class ConsoleService {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    public String process(String command) {
        send(command);
        return "OK";
    }

    public void send(String message) {
        messagingTemplate.convertAndSend("/topic/console", LocalTime.now().toString() + ":	" + message);
    }
}
