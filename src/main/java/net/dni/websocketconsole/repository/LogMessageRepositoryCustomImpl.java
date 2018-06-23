package net.dni.websocketconsole.repository;

import net.dni.websocketconsole.model.LogMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogMessageRepositoryCustomImpl implements LogMessageRepositoryCustom {
    private final ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    public LogMessageRepositoryCustomImpl(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public List<LogMessage> findFuzzyByContent(String content) {
        Criteria c = new Criteria(LogMessage.getFuzzySearchFieldName()).fuzzy(content);
        return elasticsearchTemplate.queryForList(new CriteriaQuery(c), LogMessage.class);
    }
}
