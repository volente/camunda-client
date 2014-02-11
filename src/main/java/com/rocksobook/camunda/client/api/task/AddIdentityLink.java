package com.rocksobook.camunda.client.api.task;

import com.rocksbook.camunda.client.api.PostAPI;

public class AddIdentityLink extends PostAPI<AddIdentityLink, Object> {

    private String id; 
    
    protected AddIdentityLink(String engineId, String id) {
        super(engineId);
        this.id = id;
    }
    
    public static AddIdentityLink create(String engineId, String taskId) {
        return new AddIdentityLink(engineId, taskId);
    }

    public AddIdentityLink assignee(String userId) {
        addEntity("userId", userId);
        addEntity("groupId", null);
        addEntity("type", "assignee");
        return this;
    }
    
    public AddIdentityLink candidateGroup(String groupId) {
        addEntity("userId", null);
        addEntity("groupId", groupId);
        addEntity("type", "candidate");
        return this;
    }

    public AddIdentityLink candidateUser(String userId) {
        addEntity("userId", userId);
        addEntity("groupId", null);
        addEntity("type", "candidate");
        return this;
    }

    @Override
    protected String getContextPath() {
        return "/task/" + id + "/identity-links";
    }

}
