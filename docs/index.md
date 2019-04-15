# XACML/JSON Policy Enforcement Point SDK for Java
This project provides a Policy Enforcement Point (PEP) SDK in Java that generates a XACML 3.0 authorization request and response in accordance with the JSON Profile of XACML 1.1.
The PEP SDK framework can generate and POST a XACML request in JSON to a REST-enabled Policy Decision Point such as Axiomatics' [Cloud-Native Authorization Service](https://www.axiomatics.com/blog/cloud-native-authorization-engine-on-kubernetes-cluster-part-1/).
## What is XACML?
XACML, the eXtensible Access Control Markup Language, is the de-facto standard for attribute-based
and policy-based access control (ABAC and PBAC respectively). Along with ALFA, its developer-friendly
syntax, it helps implement fine-grained authorization for APIs, microservices, and other applications.
Unlike other languages, it is not technology-specific (i.e. it works with any programming language).
## What are the REST and JSON Profiles of XACML?
