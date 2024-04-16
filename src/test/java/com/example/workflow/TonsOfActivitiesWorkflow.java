package com.example.workflow;


import org.slf4j.Logger;

import io.dapr.workflows.Workflow;
import io.dapr.workflows.WorkflowStub;


public class TonsOfActivitiesWorkflow extends Workflow{
  
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
      for( int i = 0; i < 100; i ++){
        payload = ctx.callActivity(FirstActivity.class.getName(), payload, WorkflowPayload.class).await();
      }
      ctx.complete(payload.getExecutedActivities());

    };
  }
}
