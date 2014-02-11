package com.rocksbook.camunda.client.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIContext {

    private static final URI API_BASE_URI = buildBaseUri();

    private String schema;
    
    private String host;
    
    private int port;
    
    private String userId;

    private String password;

    private URI basePath;

    private URI contextPath;

    private String engineId;
    
    private Map<String, String> queryParams;

    public enum HttpMethod {
        GET, POST, DELETE;
    }

    private HttpMethod method;

    private Map<String, Object> entity;
    
    private Map<String, Object> headers;

    public APIContext() {
        basePath = API_BASE_URI;
        
        queryParams = new HashMap<String, String>();
        headers = new HashMap<String, Object>();

        schema = Configuration.getProtcol();
        host = Configuration.getHost();
        port = Configuration.getPort();
        
        userId = Configuration.getAPIUserId();
        password = Configuration.getAPIUserPassword();
    }

    public void addQueryParam(String name, String value) {
        if (this.queryParams == null) {
            this.queryParams = new HashMap<String, String>();
        }
        this.queryParams.put(name, value);
    }

    public void addEntity(String key, Object value) {
        if (this.entity == null) {
            this.entity = new HashMap<String, Object>();
        }
        this.entity.put(key, value);
    }
    
    public void addHeader(String key, String value) {
        if (this.headers == null) {
            this.headers = new HashMap<String, Object>();
        }
        this.headers.put(key, value);
    }
    
    private static URI buildBaseUri() {
        URI uri = null;
        try {
            uri = new URI(Configuration.getProtcol() + "://" + Configuration.getHost() + ":"
                    + Configuration.getPort() + "/engine-rest/engine/");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return uri;

    }

}
