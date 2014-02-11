package com.rocksbook.camunda.client.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IdentityLink {

    private String userId;
    
    private String groupId;
    
    private String type;
    
}
