package io.xacml.pep.json.client;

import io.xacml.json.model.Request;
import io.xacml.json.model.Response;
import io.xacml.json.model.Result;
import io.xacml.json.model.SingleResponse;

import java.util.Collections;


/**
 * Interface defining the usage of the AuthZClient
 */
public interface AuthZClient {


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
    Response makeAuthorizationRequest(Request request);

    /**
     * Given a (nullable) single {@link Result}, return a JSON Profile V1.1 {@link Response} object
     *
     * @param singleResult the single result
     * @return the V1.1 response
     */
    default Response mapSingleResultToResponse(Result singleResult) {
        Response response = new Response();
        response.setResults(null != singleResult ? Collections.singletonList(singleResult) : Collections.emptyList());
        return response;
    }

}
