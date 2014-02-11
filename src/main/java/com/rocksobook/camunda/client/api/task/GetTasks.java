package com.rocksobook.camunda.client.api.task;

import java.util.List;

import com.rocksbook.camunda.client.api.GetAPI;
import com.rocksbook.camunda.client.entity.Task;

public class GetTasks extends GetAPI<GetTasks, List<Task>> {

	private static final String ENDPOINT = "/task";
	
	public GetTasks(String engineId) {
		super(engineId);
	}
	
	public static GetTasks create(String engindId) {
		return new GetTasks(engindId);
	}
	
	public GetTasks processInstanceId(String processInstanceId) {
		addQueryParam("processInstanceId", processInstanceId);
		return this;
	}
	
	public GetTasks processInstanceBusinessKey(String processInstanceBusinessKey) {
		addQueryParam("processInstanceBusinessKey", processInstanceBusinessKey);
		return this;
	}
	
	public GetTasks processDefinitionId(String processDefinitionId) {
		addQueryParam("processDefinitionId", processDefinitionId);
		return this;
	}
	
	@Override
	protected String getContextPath() {
		return ENDPOINT;
	}

}
