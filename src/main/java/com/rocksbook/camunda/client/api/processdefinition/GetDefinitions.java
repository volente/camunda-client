package com.rocksbook.camunda.client.api.processdefinition;

import java.util.List;

import com.rocksbook.camunda.client.api.GetAPI;
import com.rocksbook.camunda.client.entity.ProcessDefinition;

public class GetDefinitions extends GetAPI<GetDefinitions, List<ProcessDefinition>> {

	private static final String ENDPOINT = "/process-definition";

	GetDefinitions(String engineId) {
		super(engineId);
	}
	
	public static GetDefinitions create(String engineId) {
		return new GetDefinitions(engineId);
	}
	
	public GetDefinitions name(String name) {
		addQueryParam("name", name);
		return this;
	}
	
	@Override
	protected String getContextPath() {
		return ENDPOINT;
	}

}
