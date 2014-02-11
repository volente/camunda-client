package com.rocksbook.camunda.client;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

public abstract class BaseCamundaClient<T> implements CamundaClient<T> {

    protected CamundaRequester<CloseableHttpResponse> requester;
    
    protected CamundaResponseParser<HttpEntity> parser;
    
    protected BaseCamundaClient(CamundaRequester<CloseableHttpResponse> requester, 
            CamundaResponseParser<HttpEntity> parser) {
        this.requester = requester;
        this.parser = parser;
    }
    
    @Override
    public T execute(RequestContext context) {
        CloseableHttpResponse response = null;
        T entity = null;
        try {
           response = requester.execute(context);
           entity = (T) 
                   this.parser.readValue(response.getEntity(), getClass().getGenericSuperclass().getClass());
        } finally {
            
        }
        return entity;
    }
    
}
