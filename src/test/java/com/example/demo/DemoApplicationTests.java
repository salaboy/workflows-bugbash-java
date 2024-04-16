package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.workflow.BooleanActivity;
import com.example.workflow.FirstActivity;
import com.example.workflow.RecursiveWorkflow;
import com.example.workflow.SecondActivity;
import com.example.workflow.SimpleTestWorkflow;
import com.example.workflow.WorkflowPayload;

import io.dapr.workflows.client.DaprWorkflowClient;
import io.dapr.workflows.client.WorkflowInstanceStatus;
import io.dapr.workflows.runtime.WorkflowRuntime;
import io.dapr.workflows.runtime.WorkflowRuntimeBuilder;
import io.dapr.workflows.runtime.WorkflowRuntimeStatus;

@SpringBootTest
class DemoApplicationTests {

	private DaprWorkflowClient workflowClient = new DaprWorkflowClient();


	// @Test
	// void testSimpleWorkflow() throws TimeoutException {
		
	// 	WorkflowRuntimeBuilder builder = new WorkflowRuntimeBuilder().registerWorkflow(SimpleTestWorkflow.class);
	// 	builder.registerActivity(FirstActivity.class);
	// 	builder.registerActivity(SecondActivity.class);
		

	// 	try (WorkflowRuntime runtime = builder.build()) {
	// 		System.out.println("Start workflow runtime");
	// 		runtime.start(false);
	// 	}

	// 	WorkflowPayload payload = new WorkflowPayload();
	// 	String instanceId = workflowClient.scheduleNewWorkflow(SimpleTestWorkflow.class, payload);
	// 	System.out.printf("scheduled new workflow instance of SimpleWorkflow with instance ID: %s%n",
	// 		instanceId);
	
	
	// 	workflowClient.waitForInstanceStart(instanceId, Duration.ofSeconds(10), false);
	
	// 	WorkflowInstanceStatus status = workflowClient.waitForInstanceCompletion(instanceId, Duration.ofSeconds(10), true);
		

	// 	assertEquals(WorkflowRuntimeStatus.COMPLETED, status.getRuntimeStatus() );
	// 	assertEquals("[\"First Activity\",\"Second Activity\"]", status.getSerializedOutput());
	// 	List outputs = status.readOutputAs(List.class);
	// 	assertTrue(outputs.contains("First Activity"));
	// 	assertTrue(outputs.contains("Second Activity"));
		
	// }

	// @Test
	// void testRecursiveWorkflow() throws TimeoutException {
	// 	WorkflowRuntimeBuilder builder = new WorkflowRuntimeBuilder().registerWorkflow(RecursiveWorkflow.class);
	// 	builder.registerActivity(BooleanActivity.class);

	// 	try (WorkflowRuntime runtime = builder.build()) {
	// 		System.out.println("Start workflow runtime");
	// 		runtime.start(false);
	// 	}

	// 	WorkflowPayload payload = new WorkflowPayload();
	// 	String instanceId = workflowClient.scheduleNewWorkflow(RecursiveWorkflow.class, payload);
	// 	System.out.printf("scheduled new workflow instance of Recursive with instance ID: %s%n",
	// 		instanceId);
	
	
	// 	workflowClient.waitForInstanceStart(instanceId, Duration.ofSeconds(10), false);
	
	// 	WorkflowInstanceStatus status = workflowClient.waitForInstanceCompletion(instanceId, Duration.ofSeconds(10), true);
		

	// 	assertEquals(WorkflowRuntimeStatus.COMPLETED, status.getRuntimeStatus() );
		
	// 	System.out.println("Serialized Output: " + status.getSerializedOutput());

		

	// }


	// Uncomment test to terminate a workflow that is still running, replace the workflow ID
	@Test
	void testStopWorkflow() throws TimeoutException {
		workflowClient.terminateWorkflow("804f2415-eab8-4893-acd9-a6fa3735f956", null );
	}

}
