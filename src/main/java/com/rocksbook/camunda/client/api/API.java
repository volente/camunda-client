package com.rocksbook.camunda.client.api;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rocksbook.camunda.client.api.exception.APIException;

/**
 * Camunda API のベース。
 * 
 * @auther Takeshi Iwamoto
 */
public abstract class API<S, T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(API.class);

    protected APIContext context;
    
    private APIRequester<CloseableHttpResponse> requester;

    @SuppressWarnings("unchecked")
    protected API(String engineId) {
        this.context = new APIContext();
        this.context.setEngineId(engineId);
        try {
            String className = Configuration.getRequestClassName();
            Class<?> clazz = Class.forName(className);
            this.requester = (APIRequester<CloseableHttpResponse>) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public T execute() {
        HttpResponse response = null;

        T returnValue = null;
        try {
            
            response = doExecute(this.requester);

            int statusCode = response.getStatusLine().getStatusCode();
            LOGGER.debug("RESPONSE CODE : " + statusCode);
            if (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_NO_CONTENT) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.setSerializationInclusion(Include.NON_NULL);
                String entity = EntityUtils.toString(response.getEntity());
                ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
                returnValue = (T) mapper.readValue(entity,
                        mapper.getTypeFactory().constructType(type.getActualTypeArguments()[1]));
            } else if (statusCode == HttpStatus.SC_BAD_REQUEST) {
                String reason = response.getStatusLine().getReasonPhrase();
                throw new APIException("001", "リクエストパラメータに不正な値が検出されました", statusCode, reason);
            } else {
                String reason = response.getStatusLine().getReasonPhrase();
                throw new APIException("002", "システム内でエラーが発生しした。", statusCode, reason);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
        }
        return returnValue;
    }

    protected abstract HttpResponse doExecute(APIRequester<CloseableHttpResponse> requester) throws ClientProtocolException, IOException;

    protected abstract String getContextPath();
}
