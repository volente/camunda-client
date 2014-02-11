package com.rocksbook.camunda.client;

public interface CamundaResponseParser<I> {

    <T> T readValue(I source, Class<T> mappingClass);
    
}
