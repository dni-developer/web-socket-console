package net.dni.websocketconsole.repository;

import net.dni.websocketconsole.model.SearchRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RequestRepository extends ElasticsearchRepository<SearchRequest, String> {

}
