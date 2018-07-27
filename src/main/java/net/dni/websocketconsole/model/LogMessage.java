package net.dni.websocketconsole.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "log-analyzer", type = "app-log")
public class LogMessage {

	@Id
	private String id;
	private String message;
	private String env;
	private String host;
	private String process;
	private Timestamp timestamp;
	private String path;
	private String type;
	private String system;
	private String level;
	private String method;
	private String line;

	public static String getFuzzySearchFieldName() {
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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
}
