package net.dni.websocketconsole.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.ZonedDateTime;

@Document(indexName = "log-analyzer", type = "app-log")
public class LogMessage {

    @Id
    private String id;
    private String message;
    private String env;
    private String host;
    private String process;
    private ZonedDateTime timestamp;

    public static String getFuzzySearchFieldName(){
        return "message";
    }

    public LogMessage() {
    }

    public LogMessage(String id, String message, String host) {
        this.id = id;
        this.message = message;
        this.host = host;
    }

    public LogMessage(String id, String message, String env, String host, String process, ZonedDateTime timestamp) {
        this.id = id;
        this.message = message;
        this.env = env;
        this.host = host;
        this.process = process;
        this.timestamp = timestamp;
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

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
