package com.rocksbook.camunda.client.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rocksbook.camunda.client.api.APIContext.HttpMethod;

public abstract class BaseHttpClientRequester implements APIRequester<CloseableHttpResponse> {

    private static Logger LOGGER = LoggerFactory.getLogger(BaseHttpClientRequester.class);
    
    private static ObjectMapper mapper = new ObjectMapper();
    
    static {
        mapper.setSerializationInclusion(Include.NON_DEFAULT);
    }
    
    @Override
    public CloseableHttpResponse execute(APIContext context) {
        CloseableHttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder();
            String basePath = context.getBasePath().toString();
            String engineId = context.getEngineId();
            String contextPath = context.getContextPath().toString();
            uriBuilder.setPath(basePath + engineId + contextPath);
            RequestBuilder requestBuilder = null;
            {
                HttpMethod method = context.getMethod();
                if (method == APIContext.HttpMethod.GET) {
                    requestBuilder = RequestBuilder.get();
                    if (!context.getQueryParams().isEmpty()) {
                        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
                        for (Map.Entry<String, String> entry : context.getQueryParams().entrySet()) {
                            uriBuilder.addParameter(entry.getKey(), entry.getValue());
                        }
                        uriBuilder.addParameters(parameters);
                    }
                } else if (method == APIContext.HttpMethod.POST) {
                    requestBuilder = RequestBuilder.post();
                    String entityString = mapper.writeValueAsString(context.getEntity());
                    requestBuilder.setEntity(new StringEntity(entityString));
                } else if (method == APIContext.HttpMethod.DELETE) {
                    requestBuilder = RequestBuilder.delete();
                    if (!context.getQueryParams().isEmpty()) {
                        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
                        for (Map.Entry<String, String> entry : context.getQueryParams().entrySet()) {
                            uriBuilder.addParameter(entry.getKey(), entry.getValue());
                        }
                        uriBuilder.addParameters(parameters);
                    }
                }
                URI requestUri = uriBuilder.build();
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Request : " + context.getMethod().toString() + "  " + requestUri);
                }
                requestBuilder.setUri(uriBuilder.build());
                requestBuilder.addHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
            }
            CloseableHttpClient client = buildClient(context);
            response = client.execute(requestBuilder.build());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    protected CloseableHttpClient buildClient(APIContext context) {
        CloseableHttpClient client = HttpClients.createDefault();
        return client;
    }
}
