package io.xacml.pep.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.xacml.json.model.*;
import io.xacml.pep.json.client.AuthZClient;
import io.xacml.pep.json.client.ClientConfiguration;
import io.xacml.pep.json.client.DefaultClientConfiguration;
import io.xacml.pep.json.client.jaxrs.JaxRsAuthZClient;
import lombok.extern.slf4j.Slf4j;

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

        callPDPWithJaxRsClient(clientConfiguration, mapper, xacmlRequest);
        callPDPWithFeignClient(clientConfiguration, mapper, xacmlRequest);
    }

    private static void callPDPWithJaxRsClient(ClientConfiguration clientConfiguration, ObjectMapper mapper, Request request) {
        AuthZClient authZClient = new JaxRsAuthZClient(clientConfiguration, mapper);
        Response xacmlResponse = authZClient.makeAuthorizationRequest(request);
        for (Result r : xacmlResponse.getResults()) {
            log.debug("Decision: {}", r.getDecision());
        }
    }

    private static void callPDPWithFeignClient(ClientConfiguration clientConfiguration, ObjectMapper mapper, Request request) {
        AuthZClient authZClient = new JaxRsAuthZClient(clientConfiguration, mapper);
        Response xacmlResponse = authZClient.makeAuthorizationRequest(request);
        for (Result r : xacmlResponse.getResults()) {
            log.debug("Decision: {}", r.getDecision());
        }
    }
}
