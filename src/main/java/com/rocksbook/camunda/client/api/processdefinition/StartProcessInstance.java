package com.rocksbook.camunda.client.api.processdefinition;

import com.rocksbook.camunda.client.api.PostAPI;
import com.rocksbook.camunda.client.entity.ProcessInstance;

public class StartProcessInstance extends PostAPI<StartProcessInstance, ProcessInstance>{

	private static final String ENDPOINT = "/process-definition";
	
	private boolean latest = false;
	
	private String key;
	
	private String id;
	
	private StartProcessInstance(String engineId, String id) {
        super(engineId);
		this.id = id;
	}
	
	@Override
	protected String getContextPath() {
		String endPoint = ENDPOINT;
		if (latest) {
			endPoint = endPoint + "/key/" + key; 
		} else {
			endPoint = endPoint + "/" + this.id;
		}
		return endPoint + "/start";
	}

	public static StartProcessInstance create(String engineId, String id) {
		return new StartProcessInstance(engineId, id);
	}
}
