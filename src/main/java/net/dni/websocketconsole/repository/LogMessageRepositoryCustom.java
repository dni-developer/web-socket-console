package net.dni.websocketconsole.repository;

import net.dni.websocketconsole.model.LogMessage;

import java.util.List;

public interface LogMessageRepositoryCustom {
    List<LogMessage> findFuzzyByContent(String content);
}
