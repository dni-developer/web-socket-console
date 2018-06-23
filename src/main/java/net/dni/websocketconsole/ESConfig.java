package net.dni.websocketconsole;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.util.CollectionUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Configuration
@EnableElasticsearchRepositories(basePackages = "net.dni.websocketconsole.repository")
public class ESConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${log.analyzer.hostname}")
    private String logAnalyzerHostName;

    @Value("${log.analyzer.cluster.name}")
    private String logAnalyzerClusterName;

    @Bean
    public Client client() throws UnknownHostException {
        logger.info("connecting...: {}", logAnalyzerHostName);

        Settings settings = Settings.builder()
                .put("cluster.name", logAnalyzerClusterName)
                .build();

        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(logAnalyzerHostName), 9300));
        List<DiscoveryNode> discoveryNodes = client.connectedNodes();
        if (CollectionUtils.isEmpty(discoveryNodes)) {
            throw new RuntimeException("unable to connect to ES");
        } else {
            discoveryNodes.forEach(n -> logger.info("connected: {}", n.getAddress()));
        }

        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }

}
