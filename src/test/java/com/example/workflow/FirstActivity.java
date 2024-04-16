package com.example.workflow;

import io.dapr.workflows.runtime.WorkflowActivity;
import io.dapr.workflows.runtime.WorkflowActivityContext;


public class FirstActivity implements WorkflowActivity {

    public FirstActivity() {
    }

    @Override
    public Object run(WorkflowActivityContext ctx) {
        WorkflowPayload payload = ctx.getInput(WorkflowPayload.class);
        payload.getExecutedActivities().add("First Activity");
        return payload;
    }
}
