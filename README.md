# Workflows-bugbash-java

This project include a set of tests to run against Catalyst APIs. 

To run all the test included in the project you need to: 

1) Create a Catalyst Account
2) Create a project and an App ID
3) Connect to the App ID by exporting the following variables (before running the tests)

```
export DAPR_HTTP_ENDPOINT=""
export DAPR_GRPC_ENDPOINT=""
export DAPR_API_TOKEN=""
```

Then run: 

```
mvn package
```


## Scenarios

- Simple workflow with two activities (happy path)
- Recursive workflow that keeps running activities forever
- Stopping recursive workflows fail
- Running 100+ activities, runs until 29 and then nothing happens
- Consuming infinite amount of events, kinda works once, then I think I am rate limited but with no errors to the client
- Large payloads accept max around 3 mb (it works) -> create file with: `mkfile -n 2.5m /tmp/large.data`

Notes: 
- I got a lot of intermittent errors connecting to the APIs
- Once things break it is better to create a new App Id and work there, there is no way to restart or reset an app id if it gets stuck
- I didn't saw metrics when only using the workflow APIs
