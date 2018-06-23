package net.dni.websocketconsole.repository;

import net.dni.websocketconsole.model.LogMessage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogMessageRepository extends ElasticsearchRepository<LogMessage, String>, LogMessageRepositoryCustom {

}
