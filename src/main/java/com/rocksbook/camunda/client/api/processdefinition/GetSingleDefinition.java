package com.rocksbook.camunda.client.api.processdefinition;

import com.rocksbook.camunda.client.api.GetAPI;
import com.rocksbook.camunda.client.entity.ProcessDefinition;

public class GetSingleDefinition extends GetAPI<GetSingleDefinition, ProcessDefinition> {

	private static final String ENDPOINT = "/process-definition";
	
	private String id;
	
	private String key;
	
	private boolean latest = false;
	
	public GetSingleDefinition(String engineId, String id) {
		super(engineId);
		this.id = id;
	}
	
	public GetSingleDefinition latest(String key) {
		latest = true;
		this.key = key;
		return this;
	}
	
	public GetSingleDefinition category(String category) {
		addQueryParam("category", category);
		return this;
	}
	
	public GetSingleDefinition description(String description) {
		addQueryParam("discription", description);
		return this;
	}
	
	@Override
	protected String getContextPath() {
		String endPoint = ENDPOINT;
		if (latest) {
			endPoint = endPoint + "/key/" + key; 
		} else {
			endPoint = endPoint + "/" + id;
		}
		return endPoint;
	}
	
	public static GetSingleDefinition create(String engineId, String id) {
		return new GetSingleDefinition(engineId, id);
	}

}
