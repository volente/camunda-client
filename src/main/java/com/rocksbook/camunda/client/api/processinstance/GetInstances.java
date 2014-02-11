package com.rocksbook.camunda.client.api.processinstance;

import java.util.List;

import com.rocksbook.camunda.client.api.GetAPI;
import com.rocksbook.camunda.client.entity.ProcessInstance;

public class GetInstances extends GetAPI<GetInstances, List<ProcessInstance>> {

	private static final String ENDPOINT = "/process-instance";
	
	private GetInstances(String engineId) {
		super(engineId);
	}

	public static GetInstances create(String engineId) {
		return new GetInstances(engineId); 
	}
	
	@Override
	protected String getContextPath() {
		return ENDPOINT;
	}
	
	
}
