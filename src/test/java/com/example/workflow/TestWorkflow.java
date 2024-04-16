package com.example.workflow;


import org.slf4j.Logger;

import io.dapr.workflows.Workflow;
import io.dapr.workflows.WorkflowStub;


public class TestWorkflow extends Workflow{
  
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
      WorkflowPayload firstActivityPayload = ctx.callActivity(FirstActivity.class.getName(), payload, WorkflowPayload.class).await();

      WorkflowPayload secondActivityPayload =  ctx.callActivity(SecondActivity.class.getName(), firstActivityPayload, WorkflowPayload.class).await();

      ctx.complete(secondActivityPayload.getExecutedActivities());

    };
  }
}
