# Java JSON PEP SDK for XACML
NOTE: This is currently in development.
This project provides a sample PEP SDK for Java that generates a XACML request and response in accordance with the JSON Profile of XACML WD22.
It can then be used to POST a request to a Policy Decision Point such as the Axiomatics Cloud Native PDP.
## How to Use the SDK


```java
// Enable, if needed, basic authentication
HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("ads-user", "secret");
Client client = ClientBuilder.newClient();
client.register(feature);
WebTarget webTarget = client.target("http://djob-hp:8080/asm-pdp/authorize");
Invocation.Builder builder = webTarget.request("application/xacml+json");

// Start building the XACML request.
Request xacmlRequest = new Request();
// Create user attributes
Category subject = new Category();
subject.addAttribute(new Attribute("username", "Alice"));
// Add user attributes to the request.
xacmlRequest.addAccessSubjectCategory(subject);
Response response = builder.post(Entity.entity(xacmlRequest, "application/xacml+json"));
com.axiomatics.xacml.json.model.Response xacmlResponse = response.readEntity(com.axiomatics.xacml.json.model.Response.class);
for (Result r : xacmlResponse.getResults()) {
    System.out.println(r.getDecision());
}
```