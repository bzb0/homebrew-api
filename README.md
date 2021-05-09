# Homebrew API

### Description

The Homebrew API is a demo Spring Boot project that acts as a wrapper for the Open Brewery API. More Info about the Open Brewery API can be
found [here](https://www.openbrewerydb.org/).

The Homebrew application utilizes the following technologies:

* Spring MVC (REST APIs)
* [Feign Client](https://github.com/OpenFeign/feign)
* [JSON Problem Details](https://github.com/zalando/problem-spring-web)
* [OpenAPI Specification](https://swagger.io/specification/)
* [OpenAPI Generator plugin](https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator-gradle-plugin)
* [Lombok](https://projectlombok.org/)
* [MapStruct](https://mapstruct.org/)
* [Spock Framework](https://spockframework.org/)

### Starting the application and running the tests

As the Homebrew API is implemented as a Spring Boot application, to run it simply execute:

```
./gradlew bootRun
```

To run all unit tests execute:

```
./gradlew test
```

and to run all integration tests execute:

```
./gradlew integrationTest
```

### OAS3 API specification

The Homebrew REST API is written in the OAS3 specification in YAML format and can be found in the
directory `src/main/resources/contract/brewery-api.yaml`. The OAS3 contract is used to generate the server stubs, and can also be used to generate a
HTTP client.
