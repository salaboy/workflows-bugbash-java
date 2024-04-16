package com.example.workflow;

import java.util.ArrayList;
import java.util.List;

public class WorkflowPayload{
    private String workflowId;
    private List<String> executedActivities = new ArrayList();

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

    

    
}
