package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;

import com.example.workflow.BooleanActivity;
import com.example.workflow.FirstActivity;
import com.example.workflow.InfiniteEventConsumerWorkflow;
import com.example.workflow.RecursiveWorkflow;
import com.example.workflow.SecondActivity;
import com.example.workflow.SimpleTestWorkflow;
import com.example.workflow.TonsOfActivitiesWorkflow;
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

	@Test
	void testRecursiveWorkflow() throws TimeoutException {
		WorkflowRuntimeBuilder builder = new WorkflowRuntimeBuilder().registerWorkflow(RecursiveWorkflow.class);
		builder.registerActivity(BooleanActivity.class);

		try (WorkflowRuntime runtime = builder.build()) {
			System.out.println("Start workflow runtime");
			runtime.start(false);
		}

		WorkflowPayload payload = new WorkflowPayload();
		String instanceId = workflowClient.scheduleNewWorkflow(RecursiveWorkflow.class, payload);
		System.out.printf("scheduled new workflow instance of Recursive with instance ID: %s%n",
			instanceId);
	
	
		workflowClient.waitForInstanceStart(instanceId, Duration.ofSeconds(10), false);
	
		// WorkflowInstanceStatus status = workflowClient.waitForInstanceCompletion(instanceId, Duration.ofSeconds(10), true);
		

		// assertEquals(WorkflowRuntimeStatus.COMPLETED, status.getRuntimeStatus() );
		
		// System.out.println("Serialized Output: " + status.getSerializedOutput());

		

	}



	// Uncomment test to terminate a workflow that is still running, replace the workflow ID
	// @Test
	// void testStopWorkflow() throws TimeoutException {
	// 	workflowClient.terminateWorkflow("85cc4655-70e1-4715-a513-0c710a43eb4f", null );
	// }

	// @Test
	// void testGetWorkflowStatus() throws TimeoutException {
	// 	WorkflowInstanceStatus status = workflowClient.getInstanceState("85cc4655-70e1-4715-a513-0c710a43eb4f", true);
	// 	System.out.println("Runtime Status: " + status.getRuntimeStatus());
	// 	System.out.println("Failure Details: " + status.getFailureDetails().getErrorType());
	// 	System.out.println("Failure Details: " + status.getFailureDetails().getErrorMessage());
	// 	System.out.println("Serialized Output: " + status.getSerializedOutput());
	// }

	// @Test
	// void testTonsOfActivitiesWorkflow() throws TimeoutException {
	// 	WorkflowRuntimeBuilder builder = new WorkflowRuntimeBuilder().registerWorkflow(TonsOfActivitiesWorkflow.class);
	// 	builder.registerActivity(FirstActivity.class);
	// 	builder.registerActivity(SecondActivity.class);
		

	// 	try (WorkflowRuntime runtime = builder.build()) {
	// 		System.out.println("Start workflow runtime");
	// 		runtime.start(false);
	// 	}

	// 	WorkflowPayload payload = new WorkflowPayload();
	// 	String instanceId = workflowClient.scheduleNewWorkflow(TonsOfActivitiesWorkflow.class, payload);
	// 	System.out.printf("scheduled new workflow instance of SimpleWorkflow with instance ID: %s%n",
	// 		instanceId);
	
	
	// 	workflowClient.waitForInstanceStart(instanceId, Duration.ofSeconds(10), false);
	
	// 	WorkflowInstanceStatus status = workflowClient.waitForInstanceCompletion(instanceId, Duration.ofSeconds(10), true);
		

	// 	assertEquals(WorkflowRuntimeStatus.COMPLETED, status.getRuntimeStatus() );

	// 	System.out.println("Activities executed: " + status.getSerializedOutput());
	// }

	// @Test
	// void testInfiniteEventConsumerWorkflow() throws TimeoutException {
		
	// 	WorkflowRuntimeBuilder builder = new WorkflowRuntimeBuilder().registerWorkflow(InfiniteEventConsumerWorkflow.class);
	// 	builder.registerActivity(FirstActivity.class);
	// 	builder.registerActivity(SecondActivity.class);
		

	// 	try (WorkflowRuntime runtime = builder.build()) {
	// 		System.out.println("Start workflow runtime");
	// 		runtime.start(false);
	// 	}

	// 	WorkflowPayload payload = new WorkflowPayload();
	// 	String instanceId = workflowClient.scheduleNewWorkflow(InfiniteEventConsumerWorkflow.class, payload);
	// 	System.out.printf("scheduled new workflow instance of InfiniteEventConsumerWorkflow with instance ID: %s%n",
	// 		instanceId);
	
	
	// 	workflowClient.waitForInstanceStart(instanceId, Duration.ofSeconds(10), false);

	// 	for(int i = 0; i < 1000; i++){
	// 		System.out.println("Raising an event with payload true");
	// 		workflowClient.raiseEvent(instanceId, "IncomingEvent", true);
	// 	}

	// 	workflowClient.raiseEvent(instanceId, "IncomingEvent", false);
	// }

	// @Test
	// void testLargePayloadWorkflow() throws TimeoutException, IOException {

	// 	WorkflowRuntimeBuilder builder = new WorkflowRuntimeBuilder().registerWorkflow(SimpleTestWorkflow.class);
	// 	builder.registerActivity(FirstActivity.class);
	// 	builder.registerActivity(SecondActivity.class);
		

	// 	try (WorkflowRuntime runtime = builder.build()) {
	// 		System.out.println("Start workflow runtime");
	// 		runtime.start(false);
	// 	}

	// 	WorkflowPayload payload = new WorkflowPayload();
	// 	payload.setPayload(new FileSystemResource(Paths.get("/tmp/large.data")).getContentAsByteArray());
	// 	String instanceId = workflowClient.scheduleNewWorkflow(SimpleTestWorkflow.class, payload);
	// 	System.out.printf("scheduled new workflow instance of SimpleTestWorkflow with instance ID: %s%n",
	// 		instanceId);
		
	// }
}


