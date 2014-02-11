package com.rocksbook.camunda.client.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Task {

	private String id;
	
	private String name;
	
	private String assignee;
	
	private String owner;
	
	private String created;
	
	private String due;
	
	private String followUp;
	
	private String delegationState;
	
	private String description;
	
	private String executionId;
	
	private String parentTaskId;
	
	private int priority;
	
	private String processDefinitionId;
	
	private String processInstanceId;
	
	private String taskDefinitionKey;
	
}
