package net.dni.websocketconsole.repository;

import net.dni.websocketconsole.model.Search;

import java.util.List;

public interface SearchRepositoryCustom {
    List<Search> findFuzzyByContent(String content);
}
