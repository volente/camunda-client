package com.rocksbook.camunda.client;


public interface CamundaRequester<T> {

    public T execute(RequestContext context);
    
}
