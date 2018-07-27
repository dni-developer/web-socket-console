package net.dni.websocketconsole.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.dni.websocketconsole.model.LogMessage;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.*;

@Service
public class PullLogService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${log.puller.hostname}")
	private String hostname;

	@Value("${log.puller.cluster.name}")
	private String cluster;

	@Value("${log.puller.index.name}")
	private String index;

	private RestClient restClient;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@PostConstruct
	public void init() {
		restClient = RestClient.builder(new HttpHost(hostname, 9200, "http")).build();
	}

	@PreDestroy
	public void cleanup() throws IOException {
		restClient.close();
	}

	public List<LogMessage> pullErrorLogs() throws IOException {
		List<LogMessage> logs = new ArrayList<>();

		String jsonBody = "{\"query\":{\"bool\":{\"must\":[{\"query_string\":{\"default_field\":\"_all\",\"query\":\"UNCAUGHT_EXCEPTION_MARKER\"}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":5,\"sort\":[],\"aggs\":{}}";
		try (NStringEntity entity = new NStringEntity(jsonBody, ContentType.APPLICATION_JSON)) {
			Response response = restClient.performRequest("GET", index, Collections.emptyMap(), entity);

			JsonParser jsonParser = JsonParserFactory.getJsonParser();
			Map<String, Object> result = jsonParser.parseMap(EntityUtils.toString(response.getEntity()));
			Map<String, Object> hits = (LinkedHashMap<String, Object>) result.get("hits");
			List<Map<String, Object>> documents = (ArrayList<Map<String, Object>>) hits.get("hits");


			documents.forEach(d -> {
				try {
					String t = objectMapper.writeValueAsString(d.get("_source"));
					logger.info(t);
					logs.add(objectMapper.readValue(t, LogMessage.class));

				} catch (IOException e) {
					logger.error("", e);
				}
			});
		}

		return logs;
	}


}
