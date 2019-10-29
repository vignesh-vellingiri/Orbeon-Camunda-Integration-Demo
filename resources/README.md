# Resources for the project

Contains additional resource for project set up

## Installation

### database

Create a database with below parameters
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=postgres

run TABLE_ORBEON_META_DATA_CREATE.sql for table creation

### bpm

demo-bpm-process.bpmn can be deployed to local host with token.
Has dependency with camunda-JavaDelegate-demo-service which holds the JavaDelegate implementation for the service used.


### keycloak

OrbeonRealm-export.json contains the groups, roles and clients.

