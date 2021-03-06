# Musify

## Build and run it
To run the application in a docker container:

<code> ./mvnw package </code>

<code> docker build -t musify . </code>

<code> docker run -p 8080:8080 musify </code>

## mbids
f27ec8db-af05-4f36-916e-3d57f91ecf5e

bf24ca37-25f4-4e34-9aec-460b94364cfc

b83bc61f-8451-4a5d-8b8e-7e9ed295e822

45a663b5-b1cb-4a91-bff6-2bef7bbfdd76


## Tools
- Java 11
- Spring Boot
- [resilience4j](https://resilience4j.readme.io/): used this tool to introduce a circuit breaker when requesting the externals APIs
- [JMeter](https://jmeter.apache.org/): stress test

## TO-DO:
- Define creation of specific objects for all APIs responses or handle it as Object[]
    - Left this to define later, but in order to save time for implement the rest I prioritized this lower   
- Validate threads
    - Needs to monitor better the threads in order to configure then better 
- Elaborate better in the Circuit Breaker(CB), maybe have one for each adapter
    - Would be better to invest more time here to monitor and configure the CB more precisely.
- Unit test
  - coverage 100% of the user cases, including CB
- As new API will be introduced it makes sense to handle better the RestController classes (naming, pattern, ...)
