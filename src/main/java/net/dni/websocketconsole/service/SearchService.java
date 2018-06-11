package net.dni.websocketconsole.service;

import net.dni.websocketconsole.model.MyDocument;
import net.dni.websocketconsole.repository.MyDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final MyDocumentRepository myDocumentRepository;

    @Autowired
    public SearchService(MyDocumentRepository myDocumentRepository) {
        this.myDocumentRepository = myDocumentRepository;
    }

    public String fuzzySearch(String input) {
        List<MyDocument> result = myDocumentRepository.findFuzzyByContent(input);
        return "[" + input + "] found: " + result.size();
    }
}
