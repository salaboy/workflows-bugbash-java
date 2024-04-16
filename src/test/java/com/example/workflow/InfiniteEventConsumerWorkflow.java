package com.example.workflow;


import java.time.Duration;

import org.slf4j.Logger;

import io.dapr.workflows.Workflow;
import io.dapr.workflows.WorkflowStub;


public class InfiniteEventConsumerWorkflow extends Workflow{
  
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

      
      Boolean eventPayload =  ctx.waitForExternalEvent("IncomingEvent", Duration.ofMinutes(5), Boolean.class).await();
      while(eventPayload){
        System.out.println("Incoming event received with payload: " + eventPayload );
        eventPayload =  ctx.waitForExternalEvent("IncomingEvent", Duration.ofMinutes(5), Boolean.class).await();
      }  
      
      ctx.complete(payload.getExecutedActivities());

    };
  }
}
