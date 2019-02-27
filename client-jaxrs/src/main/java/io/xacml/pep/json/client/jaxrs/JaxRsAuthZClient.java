package io.xacml.pep.json.client.jaxrs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.xacml.json.model.Request;
import io.xacml.json.model.Response;
import io.xacml.json.model.Result;
import io.xacml.json.model.SingleResponse;
import io.xacml.pep.json.client.AuthZClient;
import io.xacml.pep.json.client.ClientConfiguration;
import io.xacml.pep.json.client.ResponseParsingException;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.logging.LoggingFeature.Verbosity;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.xacml.pep.json.client.PDPConstants.AUTHORIZATION_ENDPOINT;
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

    private static Logger logger = Logger.getLogger(JaxRsAuthZClient.class.getName());

    private final Invocation.Builder requestInvocationBuilder;

    private final ObjectMapper mapper;

    public JaxRsAuthZClient(Invocation.Builder requestInvocationBuilder, ObjectMapper mapper) {
        this.requestInvocationBuilder = requestInvocationBuilder;
        this.mapper = mapper;
    }

    public JaxRsAuthZClient(ClientConfiguration clientConfiguration, ObjectMapper mapper) {

        Objects.requireNonNull(clientConfiguration, "Client configuration must be non-null");
        Objects.requireNonNull(clientConfiguration, "Client configuration must contain a non-null PDP URL");

        this.mapper = mapper;
        Client client = ClientBuilder.newClient();
        client.register(new LoggingFeature(logger, Level.WARNING, Verbosity.PAYLOAD_ANY, null));

        // Username (and Password) should be provided if PDP requires Basic Authentication
        if (null != clientConfiguration.getUsername()) {
            client.register(HttpAuthenticationFeature.basic(
                    clientConfiguration.getUsername(),
                    clientConfiguration.getPassword())
            );
        }
        this.requestInvocationBuilder = client
                .target(clientConfiguration.getPdpUrl() + AUTHORIZATION_ENDPOINT)
                .request(CONTENT_TYPE);
    }

    /**
     * Sends the request object to the PDP and returns the response from PDP
     * <p>
     * Response object will be in the format of JSON Profile of XACML 1.1 (where the response is always an array -
     * to simplify things).
     * <p>
     * Implementations are free to support the JSON Profile of XACML 1.0 (where the response could be either an
     * Object or an Array), which is modeled with {@link SingleResponse}. However, they should map
     * the {@link SingleResponse} to a {@link Response} to simplify PEP response parsing
     *
     * @param request the XACML request object
     * @return the response object
     */
    @Override
    public Response makeAuthorizationRequest(Request request) {
        javax.ws.rs.core.Response response = requestInvocationBuilder.post(Entity.entity(request, CONTENT_TYPE));

        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree((InputStream) response.getEntity());
        } catch (IOException e) {
            throw new ResponseParsingException("Could not read the response as a JSON node", e);
        }

        return mapJsonToResponse(jsonNode);
    }

    /**
     * Uses the jsonNode to return a Response object.
     * <p>
     * if the jsonNode is a {@link SingleResponse}, it will create a {@link Response} from it
     *
     * @param jsonNode the jsonNode extracted from the client
     * @return the response
     */
    private Response mapJsonToResponse(JsonNode jsonNode) {

        Response xacmlResponse;
        if (jsonNode.get("Response") instanceof ArrayNode) {
            try {
                xacmlResponse = mapper.treeToValue(jsonNode, Response.class);
            } catch (JsonProcessingException e) {
                throw new ResponseParsingException("Could not map JSON node to Response", e);
            }
        } else {
            try {
                Result singleResult = mapper.treeToValue(jsonNode, SingleResponse.class).getResult();
                xacmlResponse = mapSingleResultToResponse(singleResult);
            } catch (JsonProcessingException e) {
                throw new ResponseParsingException("Could not map JSON node to Single Response", e);
            }
        }
        return xacmlResponse;
    }
}
