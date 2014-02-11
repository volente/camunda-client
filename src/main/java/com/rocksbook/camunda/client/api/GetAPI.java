package com.rocksbook.camunda.client.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rocksbook.camunda.client.api.APIContext.HttpMethod;

public abstract class GetAPI<S, T> extends API<S, T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetAPI.class);

    public GetAPI(String engineId) {
        super(engineId);
    }

    @Override
    protected HttpResponse doExecute(APIRequester<CloseableHttpResponse> requester) throws ClientProtocolException,
            IOException {
        try {
            context.setMethod(HttpMethod.GET);
            context.setContextPath(new URI(getContextPath()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        CloseableHttpResponse response = requester.execute(super.context);
        return response;
    }

    public S addQueryParam(String name, String... values) {
        StringBuilder valueBuilder = new StringBuilder();
        if (values == null || values.length == 0) {
            LOGGER.warn("name : " + name + " ‚É‘Î‚·‚é values ‚ªÝ’è‚³‚ê‚Ä‚¢‚Ü‚¹‚ñB");
            return (S) this;
        }

        valueBuilder.append(values[0]);
        if (values.length > 1) {
            for (int i = 1; i < values.length; i++) {
                valueBuilder.append(",");
                valueBuilder.append(values[i]);
            }
        }
        this.context.addQueryParam(name, valueBuilder.toString());
        return (S) this;
    }
}
