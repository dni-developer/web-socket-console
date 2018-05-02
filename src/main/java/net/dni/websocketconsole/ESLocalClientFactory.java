package net.dni.websocketconsole;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.FactoryBean;

import java.util.HashSet;
import java.util.Set;

public class ESLocalClientFactory implements FactoryBean<Client> {

    private Set<Node> nodes = new HashSet<>();

    @Override
    public Client getObject() {
        Settings settings = Settings.builder()
                .put("path.home", "elasticsearch")
                .put("transport.type", "local")
                .put("http.enabled", "true")
                .build();
        Node node = NodeBuilder.nodeBuilder().local(true).settings(settings).node();
        node.start();
        nodes.add(node);
        return node.client();
    }

    @Override
    public Class<?> getObjectType() {
        return Client.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void destroy() {
        for (Node node : nodes) {
            node.close();
        }
    }
}
