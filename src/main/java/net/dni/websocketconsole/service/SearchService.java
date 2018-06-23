package net.dni.websocketconsole.service;

import net.dni.websocketconsole.model.LogMessage;
import net.dni.websocketconsole.repository.LogMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final LogMessageRepository logMessageRepository;

    @Autowired
    public SearchService(LogMessageRepository logMessageRepository) {
        this.logMessageRepository = logMessageRepository;
    }

    public String fuzzySearch(String input) {
        logger.info("fuzzySearch: [{}]", input);
        List<LogMessage> result = logMessageRepository.findFuzzyByContent(input);
        return "[" + input + "] found: " + result.size();
    }
}
