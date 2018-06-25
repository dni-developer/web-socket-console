package net.dni.websocketconsole.service;

import net.dni.websocketconsole.model.LogMessage;
import net.dni.websocketconsole.repository.LogMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class ConsoleService {

    private static final String RESPONSE = "OK";

    private final SimpMessageSendingOperations messagingTemplate;
    private final SearchService searchService;
    private final LogMessageRepository logMessageRepository;

    @Autowired
    public ConsoleService(SimpMessageSendingOperations messagingTemplate, SearchService searchService, LogMessageRepository logMessageRepository) {
        this.messagingTemplate = messagingTemplate;
        this.searchService = searchService;
        this.logMessageRepository = logMessageRepository;
    }

    public String search(String input) {
        broadcast(searchService.fuzzySearch(input), "Fuzzy search");
        return RESPONSE;
    }

    public String add(LogMessage input) {
        logMessageRepository.save(new LogMessage(null, input.getMessage(), input.getHost()));
        broadcast(input);
        return RESPONSE;
    }

    private void broadcast(LogMessage logMessage) {
        broadcast(logMessage.getMessage(), logMessage.getHost());
    }

    private void broadcast(String message, String speaker) {
        messagingTemplate.convertAndSend("/topic/console", speaker + ":	" + message);
    }
}
