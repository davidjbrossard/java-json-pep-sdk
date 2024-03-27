package io.xacml.pep.json.client.feign;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import io.xacml.json.model.Request;
import io.xacml.json.model.Response;
import io.xacml.pep.json.client.AuthZClient;
import io.xacml.pep.json.client.ClientConfiguration;

import java.util.Objects;

/**
 * Builds a Feign based client to invoke the Policy Decision Point
 */
public class FeignAuthZClient implements AuthZClient {

    protected final PDPFeignClient pdpFeignClient;

    public FeignAuthZClient(PDPFeignClient pdpFeignClient) {
        this.pdpFeignClient = pdpFeignClient;
    }

    public FeignAuthZClient(ClientConfiguration clientConfiguration) {

        Objects.requireNonNull(clientConfiguration, "Client configuration must be non-null");
        Objects.requireNonNull(clientConfiguration.getAuthorizationServiceUrl(),
                "Client configuration must contain a non-null authorizationServiceUrl URL");

        Feign.Builder builder = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(FeignAuthZClient.class));

        // Enable, if needed, basic authentication
        if (null != clientConfiguration.getUsername() && null != clientConfiguration.getPassword()) {
            builder.requestInterceptor(
                    new BasicAuthRequestInterceptor(clientConfiguration.getUsername(), clientConfiguration.getPassword()));
        }

        pdpFeignClient = builder.target(PDPFeignClient.class, clientConfiguration.getAuthorizationServiceUrl());

    }

    /**
     * Sends the request object to the PDP and returns the response from PDP
     * <p>
     * The Response object is in the format of JSON Profile of XACML 1.1,
     * where the response contains an array of results.
     *
     * @param request the XACML request object
     * @return the response object
     */
    @Override
    public Response makeAuthorizationRequest(Request request) {
        return pdpFeignClient.getResponse(request);
    }
}
