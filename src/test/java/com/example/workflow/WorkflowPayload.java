package com.example.workflow;

import java.util.ArrayList;
import java.util.List;

public class WorkflowPayload{
    private String workflowId;
    private List<String> executedActivities = new ArrayList();
    private byte[] payload;

    public WorkflowPayload() {
    }

   
    public WorkflowPayload(String workflowId) {
       
        this.workflowId = workflowId;
    }

    public String getWorkflowId() {
        return workflowId;
    }


    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }


    public List<String> getExecutedActivities() {
        return executedActivities;
    }


    public void setExecutedActivities(List<String> executedActivities) {
        this.executedActivities = executedActivities;
    }


    public byte[] getPayload() {
        return payload;
    }


    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    
    

    
}
