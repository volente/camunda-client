package com.rocksbook.camunda.client.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rocksbook.camunda.client.api.APIContext.HttpMethod;

public abstract class PostAPI<S, T> extends API<S, T> {

	protected PostAPI(String engineId) {
        super(engineId);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PostAPI.class);
	
	protected Map<String, Object> entity = new LinkedHashMap<String, Object>();
	
	@Override
	protected CloseableHttpResponse doExecute(APIRequester<CloseableHttpResponse> requester) {
	    CloseableHttpResponse response = null;
	    try {
	        context.setMethod(HttpMethod.POST);
            context.setContextPath(new URI(getContextPath()));
            context.setEntity(entity);
	        response = requester.execute(context);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
		
		return response;
	}
	
	protected abstract String getContextPath();

}
