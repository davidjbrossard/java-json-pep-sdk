package io.xacml.pep.json.client.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.xacml.json.model.*;
import io.xacml.pep.json.client.AuthZClient;
import io.xacml.pep.json.client.ClientConfiguration;
import io.xacml.pep.json.client.DefaultClientConfiguration;

/**
 * This class contains sample code using JAX-RS to invoke a Policy Decision Point.
 * It supports both the JSON Profile of XACML 1.0 and 1.1.
 */
public class AuthZClientExample {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        final String pdpUrl = "http://localhost:8080";
        final String username = "enforcer";
        final String password = "secret";

        ClientConfiguration clientConfiguration = DefaultClientConfiguration.builder()
                .pdpUrl(pdpUrl)
                .username(username)
                .password(password)
                .build();

        Request request = buildXACMLRequest();

        callPDPWithFeignClient(clientConfiguration, mapper, request);
    }

    private static Request buildXACMLRequest() {
        Category subject = new Category();
        subject.addAttribute(new Attribute("username", "Alice"));
        subject.addAttribute(new Attribute("age", 15));

        Request request = new Request();
        request.addAccessSubjectCategory(subject);
        return request;
    }

    private static void callPDPWithFeignClient(ClientConfiguration clientConfiguration, ObjectMapper mapper, Request request) {
        AuthZClient authZClient = new FeignAuthZClient(clientConfiguration, mapper);
        Response xacmlResponse = authZClient.makeAuthorizationRequest(request);
        for (Result r : xacmlResponse.getResults()) {
            System.out.println("Decision: " + r.getDecision());
        }
    }
}
