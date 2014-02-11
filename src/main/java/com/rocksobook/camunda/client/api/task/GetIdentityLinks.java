package com.rocksobook.camunda.client.api.task;

import java.util.List;

import com.rocksbook.camunda.client.api.GetAPI;
import com.rocksbook.camunda.client.entity.IdentityLink;

public class GetIdentityLinks extends GetAPI<GetIdentityLinks, List<IdentityLink>> {

    private String id;
    
    private GetIdentityLinks(String engineId, String taskId) {
        super(engineId);
        this.id = taskId;
    }
    
    public GetIdentityLinks type(String type) {
        addQueryParam("type", type);
        return this;
    }
    
    @Override
    protected String getContextPath() {
        return "/task/" + this.id + "/identity-links";
    }
    
    public static GetIdentityLinks create(String engineId, String taskId) {
        return new GetIdentityLinks(engineId, taskId);
    }

}
