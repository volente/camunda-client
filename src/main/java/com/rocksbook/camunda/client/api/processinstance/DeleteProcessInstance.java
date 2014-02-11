package com.rocksbook.camunda.client.api.processinstance;

import com.rocksbook.camunda.client.api.DeleteAPI;

public class DeleteProcessInstance extends DeleteAPI<DeleteProcessInstance, Object> {

    private String id;
    
    public DeleteProcessInstance(String engineId, String id) {
        super(engineId);
        this.id = id;
    }

    public static DeleteProcessInstance create(String engineId, String id) {
        return new DeleteProcessInstance(engineId, id);
    }
    
    @Override
    protected String getContextPath() {
        return "/process-instance/" + id;
    }
    
}
