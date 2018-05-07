package net.dni.websocketconsole.service;

import net.dni.websocketconsole.model.Search;
import net.dni.websocketconsole.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class ConsoleService {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private AnalyzerService analyzerService;

    @Autowired
    private SearchRepository searchRepository;

    public String search(Search input) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        broadcast(analyzerService.analyze(input.getContent()), "Analyzer");
        return "OK";
    }

    public String add(Search input) {
        searchRepository.save(new Search(null, input.getContent(), input.getSubmitter()));
        broadcast(input);
        return "OK";
    }

    public void broadcast(Search search) {
        broadcast(search.getContent(), search.getSubmitter());
    }

    public void broadcast(String message, String speaker) {
        messagingTemplate.convertAndSend("/topic/console", speaker + ":	" + message);
    }
}
