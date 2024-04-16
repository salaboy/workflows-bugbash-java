package com.example.workflow;

import io.dapr.workflows.runtime.WorkflowActivity;
import io.dapr.workflows.runtime.WorkflowActivityContext;


public class SecondActivity implements WorkflowActivity {

    public SecondActivity() {
    }

    @Override
    public Object run(WorkflowActivityContext ctx) {
        WorkflowPayload payload = ctx.getInput(WorkflowPayload.class);
        payload.getExecutedActivities().add("Second Activity");
        return payload;
    }
}
