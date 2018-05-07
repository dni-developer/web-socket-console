package net.dni.websocketconsole.service;

import net.dni.websocketconsole.model.Search;
import net.dni.websocketconsole.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyzerService {

    @Autowired
    SearchRepository searchRepository;

    public String analyze(String input) {
        List<Search> result = searchRepository.findFuzzyByContent(input);
        return "Fuzzy query found: " + result.size();
    }
}
