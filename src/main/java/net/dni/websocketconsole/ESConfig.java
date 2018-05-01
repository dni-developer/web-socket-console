package net.dni.websocketconsole;

import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


//https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.annotation
@Configuration
@EnableElasticsearchRepositories(basePackages = "net.dni.websocketconsole.repository")
public class ESConfig {

    @Bean
    public ESLocalClientFactory elasticsearchClient(){
        return new ESLocalClientFactory();
    }
}
