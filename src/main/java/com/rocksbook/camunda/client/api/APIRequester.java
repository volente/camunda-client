package com.rocksbook.camunda.client.api;

public interface APIRequester<T> {

    T execute(APIContext context);
    
}
