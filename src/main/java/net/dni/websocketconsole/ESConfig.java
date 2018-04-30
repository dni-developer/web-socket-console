package net.dni.websocketconsole;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "net.dni.websocketconsole.repository")
public class ESConfig {

}
