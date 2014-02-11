package com.rocksbook.camunda.client;

import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;

public class CamundaRequestBuilder {

    private static final String REST_CONTEXT_PATH = "/engine-rest/engine";
    
	public static HttpGet buildDefaultGetRequest(String url, Map<String, String> queryParams) {
		RequestBuilder getRequest = RequestBuilder.get();
		URIBuilder uriBuilder = null;
		try {
			uriBuilder = new URIBuilder(url);
			for (Map.Entry<String, String> queryParam : queryParams.entrySet()) {
				uriBuilder.addParameter(queryParam.getKey(), queryParam.getValue());
			}
			getRequest.setUri(uriBuilder.build());
			getRequest.addHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		return (HttpGet) getRequest.build();
	}
	
	
	
}
