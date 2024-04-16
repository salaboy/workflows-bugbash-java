package com.example.workflow;

import io.dapr.workflows.runtime.WorkflowActivity;
import io.dapr.workflows.runtime.WorkflowActivityContext;


public class BooleanActivity implements WorkflowActivity {

    public BooleanActivity() {
    }

    @Override
    public Object run(WorkflowActivityContext ctx) {
        return true;
    }
}
