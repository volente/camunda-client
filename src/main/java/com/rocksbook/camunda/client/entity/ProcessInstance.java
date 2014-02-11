package com.rocksbook.camunda.client.entity;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessInstance {

	private String id;
	
	private String definitionId;
	
	private String businessKey;
	
	private boolean ended;
	
	private boolean suspended;
	
}
