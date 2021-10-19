package io.xacml.pep.json.client.jaxrs;

import io.xacml.json.model.Request;
import io.xacml.json.model.Response;
import io.xacml.pep.json.client.AuthZClient;
import io.xacml.pep.json.client.ClientConfiguration;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import java.util.Objects;

import static io.xacml.pep.json.client.PDPConstants.CONTENT_TYPE;

/**
 * Builds a JAX-RS to invoke a Policy Decision Point.
 * It supports both the JSON Profile of XACML 1.0 (where the response could be either an Object or
 * an Array) and the JSON Profile of XACML 1.1 (where the response is always an array - to simplify
 * things)
 *
 * @author djob
 */
public class JaxRsAuthZClient implements AuthZClient {

    private final Invocation.Builder requestInvocationBuilder;

    public JaxRsAuthZClient(Invocation.Builder requestInvocationBuilder) {
        this.requestInvocationBuilder = requestInvocationBuilder;
    }

    public JaxRsAuthZClient(ClientConfiguration clientConfiguration) {

        Objects.requireNonNull(clientConfiguration, "Client configuration must be non-null");
        Objects.requireNonNull(clientConfiguration.getAuthorizationServiceUrl(),
                "Client configuration must contain a non-null authorizationServiceUrl URL");

        Client client = ClientBuilder.newClient();

        // Username (and Password) should be provided if PDP requires Basic Authentication
        if (null != clientConfiguration.getUsername()) {
            client.register(HttpAuthenticationFeature.basic(
                    clientConfiguration.getUsername(),
                    clientConfiguration.getPassword())
            );
        }
        this.requestInvocationBuilder = client
                .target(clientConfiguration.getAuthorizationServiceUrl())
                .request(CONTENT_TYPE);
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
        return requestInvocationBuilder.post(Entity.entity(request, CONTENT_TYPE), Response.class);
    }
}
