package net.dni.websocketconsole.repository;

import net.dni.websocketconsole.model.Search;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SearchRepository extends ElasticsearchRepository<Search, String>, SearchRepositoryCustom {

}
