# File storage app

This is a simple implementation of task "File upload".

## Overall info

Build tool: Gradle 6.8.3 
This application is based on Spring Boot with Postgres (H2 in tests).

## Testing

To build app simply run `./gradlew build`.

You can find few tests written in java, including one which covers the scenario provided in exercise description.

There is also configured `docker-compose` with Postgres DB. App will be available on `http://localhost:8090`

Finally, I added postman collection with requests to both endpoints. It is located in `postman` dir.

### Test data

For easier testing I prepared sql script which sets up data described in test case scenario. It is located in `test/resources/db/file_tags_setup.sql`. It can be used to populate data during docker-compose run.
