package net.dni.websocketconsole.repository;

import net.dni.websocketconsole.model.MyDocument;

import java.util.List;

public interface MyDocumentRepositoryCustom {
    List<MyDocument> findFuzzyByContent(String content);
}
