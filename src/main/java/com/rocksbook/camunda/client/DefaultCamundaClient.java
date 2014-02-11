package com.rocksbook.camunda.client;

import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import com.rocksbook.camunda.client.entity.ProcessDefinition;

class DefaultCamundaClient implements CamundaClient {

    private CamundaRequester requester;
    
	private CloseableHttpClient client;
	
	DefaultCamundaClient(CloseableHttpClient client) {
		this.client = client;
	}
	
	public List<ProcessDefinition> getProcessInstances(String engineId, Map<String, String> params) {
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = CamundaRequestBuilder.buildDefaultGetRequest(null, params);
		} finally {
			
		}

		return null;
	}
	
}
