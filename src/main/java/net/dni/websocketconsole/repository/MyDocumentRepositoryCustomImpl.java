package net.dni.websocketconsole.repository;

import net.dni.websocketconsole.model.MyDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyDocumentRepositoryCustomImpl implements MyDocumentRepositoryCustom {
    private final ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    public MyDocumentRepositoryCustomImpl(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public List<MyDocument> findFuzzyByContent(String content) {
        Criteria c = new Criteria("content").fuzzy(content);
        return elasticsearchTemplate.queryForList(new CriteriaQuery(c), MyDocument.class);
    }
}
