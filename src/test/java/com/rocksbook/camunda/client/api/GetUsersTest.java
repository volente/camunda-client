package com.rocksbook.camunda.client.api;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.rocksbook.camunda.client.api.entity.User;

public class GetUsersTest {

	@Test
	public void testExecute() {
		List<User> users = GetUsers.create().tenantId("default").withAuth("demo", "demo").execute();
		System.out.println(users);
	}
	

}
