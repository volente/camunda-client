package com.rocksbook.camunda.client.api;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class AutoAuthRequester extends BaseHttpClientRequester {

    @Override
    protected CloseableHttpClient buildClient(APIContext context) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        AuthScope authScope = new AuthScope(context.getHost(), context.getPort());
        Credentials credentials = new UsernamePasswordCredentials(context.getUserId(), context.getPassword());
        credentialsProvider.setCredentials(authScope, credentials);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider)
                .build();
        return httpClient;
    }

}
