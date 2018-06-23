package net.dni.websocketconsole.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "log-analyzer", type = "app-log")
public class LogMessage {

    @Id
    private String id;
    private String message;
    private String server;

    public static String getFuzzySearchFieldName(){
        return "message";
    }

    public LogMessage() {
    }

    public LogMessage(String id, String message, String server) {
        this.id = id;
        this.message = message;
        this.server = server;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
