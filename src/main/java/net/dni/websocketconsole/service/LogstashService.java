package net.dni.websocketconsole.service;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

@Service
public class LogstashService {
    private static TransportClient client;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${logstash.hostname}")
    private String logstashHostName;
    @Value("${logstash.cluster.name}")
    private String logstashClusterName;

    @PostConstruct
    public void init() throws UnknownHostException {
        logger.info("connecting...: {}", logstashHostName);

        Settings settings = Settings.builder().put("cluster.name", logstashClusterName).build();
        List<String> hosts = Arrays.asList(logstashHostName.split(","));
        client = new PreBuiltTransportClient(settings);
        for (String esHost : hosts) {
            client = client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), 9300));
        }

        List<DiscoveryNode> discoveryNodes = client.connectedNodes();
        if (CollectionUtils.isEmpty(discoveryNodes)) {
            throw new RuntimeException("unable to connect to Logstash");
        } else {
            discoveryNodes.forEach(n -> logger.info("connected: {}", n.getAddress()));
        }
    }

    @PreDestroy
    public void cleanup() {
        logger.info("closing...");
        client.close();
        logger.info("closed");
    }


}
