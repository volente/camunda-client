package com.rocksbook.camunda.client.api;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.rocksbook.camunda.client.api.processdefinition.GetDefinitions;
import com.rocksbook.camunda.client.api.processdefinition.StartProcessInstance;
import com.rocksbook.camunda.client.api.processinstance.DeleteProcessInstance;
import com.rocksbook.camunda.client.api.processinstance.GetInstances;
import com.rocksbook.camunda.client.entity.IdentityLink;
import com.rocksbook.camunda.client.entity.ProcessDefinition;
import com.rocksbook.camunda.client.entity.ProcessInstance;
import com.rocksbook.camunda.client.entity.Task;
import com.rocksobook.camunda.client.api.task.AddIdentityLink;
import com.rocksobook.camunda.client.api.task.GetIdentityLinks;
import com.rocksobook.camunda.client.api.task.GetTasks;

public class APITest {

	@Test
	public void test() {

		List<ProcessDefinition> defs = GetDefinitions
		        .create("default")
				.execute();
		assertThat(defs.size(), greaterThan(0));
		
		List<ProcessInstance> instances = new ArrayList<ProcessInstance>();
		for (int i = 0; i < 5; i++) {
	        ProcessInstance instance = StartProcessInstance
	                .create("default", defs.get(0).getId())
	                .execute();
	        instances.add(instance);
		}
		
		int instanceCount = GetInstances.create("default").execute().size();
		
		DeleteProcessInstance.create("default", instances.get(1).getId()).execute();

		int afterInstanceCount = GetInstances.create("default").execute().size();
		
		assertThat(afterInstanceCount, is(instanceCount - 1));
		
		GetInstances.create("default").execute();
		
		List<Task> tasks = GetTasks
		        .create("default")
				.processInstanceId(instances.get(0).getId())
				.execute();
		assertThat(tasks.size(), not(0));
	
		Task task = tasks.get(0);
		
		AddIdentityLink.create("default", task.getId()).assignee("t.iwamoto").execute();
		
		List<IdentityLink> identityLinks = GetIdentityLinks
		        .create("default", task.getId())
		        .execute();
		System.out.println(identityLinks);

	}

}
