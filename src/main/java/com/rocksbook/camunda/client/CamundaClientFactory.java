package com.rocksbook.camunda.client;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.rocksbook.camunda.client.api.Configuration;

public class CamundaClientFactory {

    public static CamundaClient buildAuthClient(String host, int port, String userId, String password) {
        CloseableHttpClient httpClient = createHttpClient(host, port, userId, password);
        return new DefaultCamundaClient(httpClient);
    }

    /**
     * プロパティファイルからホストを設定
     * 
     * @return
     */
    public static CamundaClient buildAuthClient(String userId, String password) {
        CloseableHttpClient httpClient = createHttpClient(Configuration.getHost(), Configuration.getPort(), userId,
                password);
        return new DefaultCamundaClient(httpClient);
    }

    private static CloseableHttpClient createHttpClient(String host, int port, String userId, String password) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        AuthScope authScope = new AuthScope(host, port);
        Credentials credentials = new UsernamePasswordCredentials(userId, password);
        credentialsProvider.setCredentials(authScope, credentials);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider)
                .build();
        return httpClient;
    }

    public static CamundaClient buildDefaultClient() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return new DefaultCamundaClient(httpClient);
    }

}
