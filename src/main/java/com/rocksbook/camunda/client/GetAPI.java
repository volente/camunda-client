package com.rocksbook.camunda.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHeader;

public abstract class GetAPI<S, T> extends API<S, T> {

	protected Map<String, String> queryParams = new HashMap<String, String>();

	@Override
	protected HttpResponse doExecute(HttpClient client) {
		HttpResponse response = null;
		try {
			HttpGet get = new HttpGet(BASE_URL + path());
			get.setHeaders(headers.toArray(new BasicHeader[0]));
			response = client.execute(get);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return response;
	}

	protected abstract String path();

}
