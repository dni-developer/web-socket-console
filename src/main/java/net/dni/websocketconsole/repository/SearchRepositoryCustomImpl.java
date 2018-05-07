package net.dni.websocketconsole.repository;

import net.dni.websocketconsole.model.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.List;

public class SearchRepositoryCustomImpl implements SearchRepositoryCustom {
    @Autowired
    protected ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<Search> findFuzzyByContent(String content) {
        Criteria c = new Criteria("content").fuzzy(content);
        return elasticsearchTemplate.queryForList(new CriteriaQuery(c), Search.class);
    }
}
