package io.xacml.pep.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.xacml.json.model.*;
import io.xacml.pep.json.client.AuthZClient;
import io.xacml.pep.json.client.ClientConfiguration;
import io.xacml.pep.json.client.DefaultClientConfiguration;
import io.xacml.pep.json.client.feign.FeignAuthZClient;
import io.xacml.pep.json.client.jaxrs.JaxRsAuthZClient;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.*;

/**
 * This class contains sample code using JAX-RS to invoke a Policy Decision Point.
 * It supports both the JSON Profile of XACML 1.0 (where the response could be either an Object or
 * an Array) and the JSON Profile of XACML 1.1 (where the response is always an array - to simplify
 * things)
 *
 * @author djob
 */
@Slf4j
public class AuthZClientExamples {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        ClientConfiguration clientConfiguration = DefaultClientConfiguration.builder()
                .pdpUrl("https://djob-hp:9443/asm-pdp/authorize")
                .username("pdp-user")
                .password("password")
                .build();

        Category subject = new Category();
        subject.addAttribute(new Attribute("username", "Alice"));
        subject.addAttribute(new Attribute("age", 15));

        Request xacmlRequest = new Request();
        xacmlRequest.addAccessSubjectCategory(subject);

        callPDPWithFeignClient(clientConfiguration, mapper, xacmlRequest);
        callPDPWithJaxRsClient(clientConfiguration, mapper, xacmlRequest);
        callPDPWithJaxRsClient2();
    }

    private static void callPDPWithFeignClient(ClientConfiguration clientConfiguration, ObjectMapper mapper, Request request) {
        AuthZClient authZClient = new FeignAuthZClient(clientConfiguration, mapper);
        Response xacmlResponse = authZClient.makeAuthorizationRequest(request);
        for (Result r : xacmlResponse.getResults()) {
            log.debug("Decision: {}", r.getDecision());
        }
    }

    private static void callPDPWithJaxRsClient(ClientConfiguration clientConfiguration, ObjectMapper mapper, Request request) {
        AuthZClient authZClient = new JaxRsAuthZClient(clientConfiguration, mapper);
        Response xacmlResponse = authZClient.makeAuthorizationRequest(request);
        for (Result r : xacmlResponse.getResults()) {
            log.debug("Decision: {}", r.getDecision());
        }
    }

    /**
     * Show the full build of a JaxRs client and use to get a PDP response
     */
    private static void callPDPWithJaxRsClient2() {
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
        javax.ws.rs.core.Response response = builder.post(Entity.entity(xacmlRequest, "application/xacml+json"));
        Response xacmlResponse = response.readEntity(io.xacml.json.model.Response.class);
        for (Result r : xacmlResponse.getResults()) {
            log.debug("Decision: {}", r.getDecision());
        }
    }
}
