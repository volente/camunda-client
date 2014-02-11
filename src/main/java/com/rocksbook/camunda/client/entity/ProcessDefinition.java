package com.rocksbook.camunda.client.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProcessDefinition {

	private String id;
	
	private String key;
	
	private String category;
	
	private String description;
	
	private String name;
	
	private int version;
	
	private String resource;
	
	private String deploymentId;
	
	private String diagram;
	
	private boolean suspended;
	
}
