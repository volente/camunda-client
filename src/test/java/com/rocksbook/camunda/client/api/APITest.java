package com.rocksbook.camunda.client.api;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.rocksbook.camunda.client.api.processdefinition.GetDefinitions;
import com.rocksbook.camunda.client.api.processdefinition.StartProcessInstance;
import com.rocksbook.camunda.client.entity.ProcessDefinition;
import com.rocksbook.camunda.client.entity.ProcessInstance;
import com.rocksbook.camunda.client.entity.Task;
import com.rocksobook.camunda.client.api.task.GetTasks;

public class APITest {

	@Test
	public void test() {

		List<ProcessDefinition> defs = GetDefinitions
		        .create("default")
				.execute();
		assertThat(defs.size(), greaterThan(0));
		
		ProcessInstance instance = StartProcessInstance
				.create("default", defs.get(0).getId())
				.execute();
		assertThat(instance, notNullValue());
		
		List<Task> tasks = GetTasks
		        .create("default")
				.processInstanceId(instance.getId())
				.execute();
		assertThat(tasks.size(), not(0));

	}

}
