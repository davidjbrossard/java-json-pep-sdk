package io.xacml.pep.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.xacml.json.model.*;
import io.xacml.pep.json.client.AuthZClient;
import io.xacml.pep.json.client.ClientConfiguration;
import io.xacml.pep.json.client.DefaultClientConfiguration;
import io.xacml.pep.json.client.PDPConstants;
import io.xacml.pep.json.client.feign.FeignAuthZClient;
import io.xacml.pep.json.client.jaxrs.JaxRsAuthZClient;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.*;

/**
 * This class contains sample code using JAX-RS to invoke a Policy Decision Point.
 * It supports both the JSON Profile of XACML 1.0 and 1.1.
 */
public class AuthZClientExamples {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        final String pdpUrl = "https://djob-hp:9443/asm-pdp";
        final String username = "pdp-user";
        final String password = "password";

        ClientConfiguration clientConfiguration = DefaultClientConfiguration.builder()
                .pdpUrl(pdpUrl)
                .username(username)
                .password(password)
                .build();

        Request request = buildXACMLRequest();

        callPDPWithFeignClient(clientConfiguration, mapper, request);
        callPDPWithJaxRsClient(clientConfiguration, mapper, request);
        callPDPWithJaxRsClientStepByStep(clientConfiguration, request);
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

    private static void callPDPWithJaxRsClient(ClientConfiguration clientConfiguration, ObjectMapper mapper, Request request) {
        AuthZClient authZClient = new JaxRsAuthZClient(clientConfiguration, mapper);
        Response xacmlResponse = authZClient.makeAuthorizationRequest(request);
        for (Result r : xacmlResponse.getResults()) {
            System.out.println("Decision: " + r.getDecision());
        }
    }

    /**
     * Show the full build of a JaxRs client and use to get a PDP response
     */
    private static void callPDPWithJaxRsClientStepByStep(ClientConfiguration configuration, Request request) {
        // Enable, if needed, basic authentication
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(configuration.getUsername(), configuration.getPassword());
        Client client = ClientBuilder.newClient();
        client.register(feature);
        WebTarget webTarget = client.target(configuration.getPdpUrl() + PDPConstants.AUTHORIZATION_ENDPOINT);
        Invocation.Builder builder = webTarget.request(PDPConstants.CONTENT_TYPE);

        javax.ws.rs.core.Response response = builder.post(Entity.entity(request, "application/xacml+json"));
        Response xacmlResponse = response.readEntity(io.xacml.json.model.Response.class);
        for (Result r : xacmlResponse.getResults()) {
            System.out.println("Decision: " + r.getDecision());
        }
    }
}
