package net.dni.websocketconsole.service;

import net.dni.websocketconsole.model.SearchRequest;
import net.dni.websocketconsole.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class ConsoleService {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private RequestRepository requestRepository;

    public String process(String command) {
        requestRepository.save(new SearchRequest(null, command, "test"));
        send(command);
        return "OK";
    }

    public void send(String message) {
        messagingTemplate.convertAndSend("/topic/console", LocalTime.now().toString() + ":	" + message);
    }
}
