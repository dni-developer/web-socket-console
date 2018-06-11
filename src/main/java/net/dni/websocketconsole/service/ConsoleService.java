package net.dni.websocketconsole.service;

import net.dni.websocketconsole.model.MyDocument;
import net.dni.websocketconsole.repository.MyDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class ConsoleService {

    private static final String RESPONSE = "OK";

    private final SimpMessageSendingOperations messagingTemplate;
    private final SearchService searchService;
    private final MyDocumentRepository myDocumentRepository;

    @Autowired
    public ConsoleService(SimpMessageSendingOperations messagingTemplate, SearchService searchService, MyDocumentRepository myDocumentRepository) {
        this.messagingTemplate = messagingTemplate;
        this.searchService = searchService;
        this.myDocumentRepository = myDocumentRepository;
    }

    public String search(String input) {
        broadcast(searchService.fuzzySearch(input), "Fuzzy search");
        return RESPONSE;
    }

    public String add(MyDocument input) {
        myDocumentRepository.save(new MyDocument(null, input.getContent(), input.getSubmitter()));
        broadcast(input);
        return RESPONSE;
    }

    private void broadcast(MyDocument myDocument) {
        broadcast(myDocument.getContent(), myDocument.getSubmitter());
    }

    private void broadcast(String message, String speaker) {
        messagingTemplate.convertAndSend("/topic/console", speaker + ":	" + message);
    }
}
