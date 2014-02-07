package com.rocksbook.camunda.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class API<S, T> {

	protected static final String BASE_URL = Configration.getProtcol() + "://" + Configration.getHost()
			+ ":" + Configration.getPort() + "/" + Configration.getContextPath();
	
	protected List<Header> headers;

	protected String tenantId;

	private boolean useAuth = false;

	private String authUserId;

	private String authPassword;

	protected API() {
		this.headers = new ArrayList<Header>();
	}

	public T execute() {
		HttpResponse response = null;
		CloseableHttpClient client = null;

		if (useAuth) {
			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			credentialsProvider.setCredentials(new AuthScope(
					Configration.getHost(), Configration.getPort()),
					new UsernamePasswordCredentials(authUserId, authPassword));
			client = HttpClients.custom()
					.setDefaultCredentialsProvider(credentialsProvider).build();
		} else {
			client = HttpClients.createDefault();
		}
		
		T returnValue = null;
		try {
			response = doExecute(client);
			ObjectMapper mapper = new ObjectMapper();
			String entity = EntityUtils.toString(response.getEntity());
			returnValue = mapper.readValue(entity, new TypeReference<T>() {
			});
		} catch (ParseException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (client != null) client.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public S withAuth(String userId, String password) {
		this.useAuth = true;
		this.authUserId = userId;
		this.authPassword = password;
		return (S) this;
	}

	@SuppressWarnings("unchecked")
	public S header(String name, String value) {
		this.headers.add(new BasicHeader(name, value));
		return (S) this;
	}

	protected abstract HttpResponse doExecute(HttpClient client);
}
