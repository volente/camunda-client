package com.rocksbook.camunda.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.basic.BasicArrowButton;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.rocksbook.camunda.client.RequestContext.HttpMethod;
import com.rocksbook.camunda.client.api.Configuration;

public class HttpClientRequester implements CamundaRequester<CloseableHttpResponse> {

    private static final String REST_URL = buildBaseUrl();
    
    private CloseableHttpClient httpClient;
    
    public HttpClientRequester() {
        this.httpClient = build();
    }

    @Override
    public CloseableHttpResponse execute(RequestContext context) {
        CloseableHttpResponse response = null;
        try {
            RequestBuilder requestBuilder = getRequestBuilder(context);
            requestBuilder.setUri(REST_URL + context.getAPIUri());
            requestBuilder.addParameters(convertToNameValuePair(context.getQueryParams()));
            
            response = this.httpClient.execute(buildRequest(context));
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }
        return response;
    }

    protected CloseableHttpClient build() {
        return null;
    }

    private RequestBuilder getRequestBuilder(RequestContext context) {
        RequestBuilder requestBuilder = null;
        HttpMethod method = context.getMethod();
        if (method == HttpMethod.GET) {
            requestBuilder = RequestBuilder.get();
        } else if (method == HttpMethod.POST) {
            requestBuilder = RequestBuilder.post();
        }
        return requestBuilder;
    }
    
    private NameValuePair[] convertToNameValuePair(Map<String, String> data) {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        if (data == null || data.isEmpty()) {
            return new BasicNameValuePair[0];
        }
        for (Map.Entry<String, String> entry : data.entrySet()) {
           pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue())); 
        }
        return pairs.toArray(new BasicNameValuePair[0]);
    }
    
    private String convertToJsonString(Map<String, Object> data) {
        return null;
    }
    
    private static String buildBaseUrl() {
        return Configuration.getProtcol() + "://" + Configuration.getHost() + ":" + Configuration.getPort() + "/engine-rest/engine";
    }
    
    private HttpUriRequest buildRequest(RequestContext context) {
        RequestBuilder requestBuilder = null;
        HttpMethod method = context.getMethod();
        if (method == HttpMethod.GET) {
            requestBuilder = RequestBuilder.get();
        } else if (method == HttpMethod.POST) {
            requestBuilder = RequestBuilder.post();
        }
        
        return null;
    }
}
