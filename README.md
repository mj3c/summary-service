# Summary Service

Summary Service is a service that accumulates blog post summaries. It consists of a web API, and a worker component that deals with storing data and notifying Slack users.

RabbitMQ is used for communication, MongoDB for storage and Docker for containerizing both components.

## Run locally

Maven (or mvnw) and Docker (docker-compose) are required as prerequisites. Then, inside the project's directory, run:

```
mvn clean install
docker-compose up
```

## Usage

Existing summaries can be viewed by visiting `http://localhost:8080/summaries`.

New summaries can be added by POST-ing the blog post 'text' and desired summary 'length' as JSON to the same endpoint, like so:

```
curl -d '{"text":"This is a full blog post.", "length":12}' -H "Content-Type: application/json" -X POST http://localhost:8080/summaries
```

The worker component's log file can be fetched from its running docker container with:

```
docker cp summary-service.worker:worker/log/application.log ./worker.log
```