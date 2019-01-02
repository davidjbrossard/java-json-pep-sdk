# Java JSON PEP SDK
NOTE: This is currently in development.
This project provides a sample PEP SDK for Java that generates a XACML request and response in accordance with the JSON Profile of XACML WD22.
## How to Use the SDK
TODO

```java
HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("ads-user", "secret");
Client client = ClientBuilder.newClient();
client.register(feature);
WebTarget webTarget = client.target("http://djob-hp:8080/asm-pdp/authorize");
Invocation.Builder builder = webTarget.request("application/xacml+json");
Category subject = new Category("urn:oasis:names:tc:xacml:1.0:subject-category:access-subject");
subject.addAttribute(new Attribute("username", "Alice"));

Request xacmlRequest = new Request();
xacmlRequest.addAccessSubjectCategory(subject);
Response response = builder.post(Entity.entity(xacmlRequest, "application/xacml+json"));
com.axiomatics.xacml.json.model.Response xacmlResponse = response.readEntity(com.axiomatics.xacml.json.model.Response.class);
for (Result r : xacmlResponse.getResults()) {
    System.out.println(r.getDecision());
}
```