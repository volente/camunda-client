package com.rocksbook.camunda.client.api;

import java.util.List;

import com.rocksbook.camunda.client.GetAPI;
import com.rocksbook.camunda.client.api.entity.User;

public class GetUsers extends GetAPI<GetUsers, List<User>> {

	private static final String ENDPOINT = "/user";

	private GetUsers() {
	}

	public GetUsers tenantId(String tenantId) {
		this.tenantId = tenantId;
		return this;
	}

	public GetUsers firstName(String firstName) {
		queryParams.put("firstName", firstName);
		return this;
	}

	public static GetUsers create() {
		return new GetUsers();
	}

	@Override
	protected String path() {
		StringBuilder sb = new StringBuilder();
		sb.append(tenantId);
		sb.append(ENDPOINT);
		return sb.toString();
	}
}
