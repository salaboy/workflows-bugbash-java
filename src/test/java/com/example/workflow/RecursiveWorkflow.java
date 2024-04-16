package com.example.workflow;

import io.dapr.workflows.Workflow;
import io.dapr.workflows.WorkflowStub;

import org.slf4j.Logger;


public class RecursiveWorkflow extends Workflow{
  
    @Override
    public WorkflowStub create() {
      return ctx -> {
        Logger logger = ctx.getLogger();
        String instanceId = ctx.getInstanceId();
        logger.info("Starting Workflow: " + ctx.getName());
        logger.info("Instance ID: " + instanceId);
        logger.info("Current Orchestration Time: " + ctx.getCurrentInstant());
        WorkflowPayload payload = new WorkflowPayload();
        payload.setWorkflowId(instanceId);

        
        Boolean output = ctx.callActivity(BooleanActivity.class.getName(), payload, Boolean.class).await();
        while(output){
            output = ctx.callActivity(BooleanActivity.class.getName(), payload, Boolean.class).await(); 
        }
        
        ctx.complete("");
  
      };
    }
  }
