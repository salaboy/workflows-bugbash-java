package com.example.demo;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.workflow.FirstActivity;
import com.example.workflow.SecondActivity;
import com.example.workflow.TestWorkflow;
import com.example.workflow.WorkflowPayload;

import io.dapr.workflows.client.DaprWorkflowClient;
import io.dapr.workflows.client.WorkflowInstanceStatus;
import io.dapr.workflows.runtime.WorkflowRuntime;
import io.dapr.workflows.runtime.WorkflowRuntimeBuilder;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void testSimpleWorkflow() throws TimeoutException {
		DaprWorkflowClient workflowClient = new DaprWorkflowClient();
		WorkflowRuntimeBuilder builder = new WorkflowRuntimeBuilder().registerWorkflow(TestWorkflow.class);
		builder.registerActivity(FirstActivity.class);
		builder.registerActivity(SecondActivity.class);
		

		try (WorkflowRuntime runtime = builder.build()) {
			System.out.println("Start workflow runtime");
			runtime.start(false);
		}

		WorkflowPayload payload = new WorkflowPayload();
		String instanceId = workflowClient.scheduleNewWorkflow(TestWorkflow.class, payload);
		System.out.printf("scheduled new workflow instance of OrderProcessingWorkflow with instance ID: %s%n",
			instanceId);
	
	
		workflowClient.waitForInstanceStart(instanceId, Duration.ofSeconds(10), false);
	
		WorkflowInstanceStatus status = workflowClient.waitForInstanceCompletion(instanceId, Duration.ofSeconds(10), true);
		
		System.out.println("Workflow Status: " + status.getRuntimeStatus().name());
		System.out.println("Workflow output: " + status.getSerializedOutput());
		
	}

}
