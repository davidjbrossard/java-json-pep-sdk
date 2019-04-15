# XACML/JSON Policy Enforcement Point SDK for Java
This project provides a Policy Enforcement Point (PEP) SDK in Java that generates a XACML 3.0 authorization request and response in accordance with the JSON Profile of XACML 1.1.
The PEP SDK framework can generate and POST a XACML request in JSON to a REST-enabled Policy Decision Point such as Axiomatics' [Cloud-Native Authorization Service](https://www.axiomatics.com/blog/cloud-native-authorization-engine-on-kubernetes-cluster-part-1/).
## What is XACML?
XACML, the eXtensible Access Control Markup Language, is the de-facto standard for attribute-based and policy-based access control (ABAC and PBAC respectively). Along with [ALFA](https://en.wikipedia.org/wiki/ALFA_(XACML)), its developer-friendly syntax, it helps implement fine-grained authorization for APIs, microservices, and other applications. Unlike other languages, it is not technology-specific (i.e. it works with any programming language).
## What are the REST and JSON Profiles of XACML?
The [REST Profile of XACML v3.0 Version 1.0](http://docs.oasis-open.org/xacml/xacml-rest/v1.0/xacml-rest-v1.0.html) defines a standard means to POST a XACML authorization request either in XML or JSON to a XACML 3.0-compliant authorization service.
> Due to the pervasive nature of access control, Authorization-as-a-Service will result in many calls to the authorization servers. These servers must therefore perform and scale extremely well. Thus it makes sense to use a RESTful architecture for them.
This specification defines a profile for the use of XACML in a RESTful architecture, enabling the interoperability of RESTful Authorization-as-a-Service (AZaaS) solutions. The MIME media types [Media] available for representations of the various XACML constructs are defined separately [XACMLMedia]. [source](http://docs.oasis-open.org/xacml/xacml-rest/v1.0/cos01/xacml-rest-v1.0-cos01.html#_Toc399235410)

## Examples
### JSON XACML Request
```json
{
    "Request": {
        "AccessSubject": [{
            "Attribute": [
                {
                    "AttributeId": "com.acme.user.employeeId",
                    "Value": "Alice"
                }
            ]
        }],
        "Resource": [{
            "Attribute": [
                {
                    "AttributeId": "com.acme.object.objectType",
                    "Value": "record"
                },
                {
                    "AttributeId": "com.acme.record.recordId",
                    "Value": "123"
                }
            ]
        }],
        "Action": [{
            "Attribute": [
                {
                    "AttributeId": "com.acme.action.actionId",
                    "Value": "view"
                }
            ]
        }]
    }
}
```

### JSON XACML Response
```json
{
    "Response": {
        "Decision": "Permit",
        "Status": {
            "StatusCode": {
                "Value": "urn:oasis:names:tc:xacml:1.0:status:ok",
                "StatusCode": {
                    "Value": "urn:oasis:names:tc:xacml:1.0:status:ok"
                }
            }
        }
    }
}
```
