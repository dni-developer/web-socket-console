package net.dni.websocketconsole.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "spring-boot", type = "requests")
public class SearchRequest {

    @Id
    private String id;
    private String content;
    private String submitter;

    public SearchRequest() {
    }

    public SearchRequest(String id, String content, String submitter) {
        this.id = id;
        this.content = content;
        this.submitter = submitter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }
}
