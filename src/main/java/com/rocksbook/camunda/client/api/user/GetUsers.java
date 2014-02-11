package com.rocksbook.camunda.client.api.user;

import java.util.List;

import com.rocksbook.camunda.client.api.GetAPI;
import com.rocksbook.camunda.client.entity.User;

public class GetUsers extends GetAPI<GetUsers, List<User>> {

	private static final String ENDPOINT = "/user";

	private GetUsers(String engineId) {
		super(engineId);
	}

	public GetUsers firstName(String firstName) {
		addQueryParam("firstName", firstName);
		return this;
	}

	public static GetUsers create(String tenantId) {
		return new GetUsers(tenantId);
	}

	@Override
	protected String getContextPath() {
	    return ENDPOINT;
	}
}
