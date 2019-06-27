# Java JSON PEP SDK for XACML

This project provides a PEP SDK for Java, that generates a XACML request and response in accordance with the 
JSON Profile of XACML 1.1.
The project is organized into a number of submodule projects using Apache Maven. Please see their brief descriptions 
below, or see the submodule project's READMEs.

The `models` can be used to `POST` a request to a Policy Decision Point (such as the Axiomatics Cloud Native PDP), 
using the client of your choice.

Examples of clients are provided int the `client-*` projects

## Project Contents
- [Client-Core](client-core) 
- [Client-JaxRs](client-jaxrs)
- [Client-Feign](client-feign)
- [Example code using clients and models](examples)
- [Models](models)

More information can be found at [OASIS's JSON Profile of XACML v 1.1](http://docs.oasis-open.org/xacml/xacml-json-http/v1.1/xacml-json-http-v1.1.html).

## How to Use the SDK

See example code in the [Example code using clients and models](examples) subproject

## Future work
- Java Builders for the request-related models to assist in creation of requests
- Kotlin Builder to provide a DSL-style builder, useful in testing. 
