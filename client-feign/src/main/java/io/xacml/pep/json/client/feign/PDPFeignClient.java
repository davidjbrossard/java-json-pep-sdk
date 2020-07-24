package io.xacml.pep.json.client.feign;

import feign.Headers;
import feign.RequestLine;
import io.xacml.json.model.Request;
import io.xacml.json.model.Response;

import static io.xacml.pep.json.client.PDPConstants.AUTHORIZATION_ENDPOINT;
import static io.xacml.pep.json.client.PDPConstants.CONTENT_TYPE;

/**
 * A Feign Client definition of the Policy Decision Point Authorize Endpoint
 */
public interface PDPFeignClient {

    @Headers("Content-Type: " + CONTENT_TYPE)
    @RequestLine("POST " + AUTHORIZATION_ENDPOINT)
    Response getResponse(Request request);

}
