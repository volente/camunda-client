package com.rocksbook.camunda.client;

import java.util.Map;

public interface RequestContext {

    public enum HttpMethod {
        GET, POST, DELETE;
    }
    
    void addQueryParam(String name, String value);

    Map<String, String> getQueryParams();
    
    void addFormParam(String name, Object value);
    
    Map<String, Object> getFormParams();
    
    HttpMethod getMethod();
    
    String getAPIUri();
    
    
}
