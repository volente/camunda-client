package com.rocksbook.camunda.client;

public interface CamundaClient<T> {

    T execute(RequestContext context);
    
}
