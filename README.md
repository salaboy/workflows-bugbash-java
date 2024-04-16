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

Reported Issues: 
- https://github.com/diagridio/issues/issues/3649

