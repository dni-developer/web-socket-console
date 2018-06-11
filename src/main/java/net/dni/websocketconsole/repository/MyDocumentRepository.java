package net.dni.websocketconsole.repository;

import net.dni.websocketconsole.model.MyDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MyDocumentRepository extends ElasticsearchRepository<MyDocument, String>, MyDocumentRepositoryCustom {

}
