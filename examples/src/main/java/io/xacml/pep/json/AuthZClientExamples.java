package io.xacml.pep.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.xacml.json.model.*;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.logging.LoggingFeature.Verbosity;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contains sample code using JAX-RS to invoke a Policy Decision Point.
 * It supports both the JSON Profile of XACML 1.0 (where the response could be either an Object or
 * an Array) and the JSON Profile of XACML 1.1 (where the response is always an array - to simplify
 * things)
 *
 * @author djob
 */
public class AuthZClientExamples {
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(AuthZClientExamples.class.getName());
        HttpAuthenticationFeature authNFeature = HttpAuthenticationFeature.basic("pdp-user", "password");
//		HttpAuthenticationFeature authNFeature = HttpAuthenticationFeature.basic("ads-user", "secret");

        LoggingFeature loggingFeature = new LoggingFeature(logger, Level.WARNING, Verbosity.PAYLOAD_ANY, null);
        Client client = ClientBuilder.newClient();
        client.register(authNFeature);
        client.register(loggingFeature);
        WebTarget webTarget = client.target("https://djob-hp:9443/asm-pdp/authorize"); // ASM-PDP
        // WebTarget webTarget = client.target("http://djob-hp:8080/asm-pdp/authorize"); // ADS
        Invocation.Builder builder = webTarget.request("application/xacml+json");
        Category subject = new Category();
        subject.addAttribute(new Attribute("username", "Alice"));
        subject.addAttribute(new Attribute("age", 15));

        Request xacmlRequest = new Request();
        xacmlRequest.addAccessSubjectCategory(subject);
        Response response = builder.post(Entity.entity(xacmlRequest, "application/xacml+json"));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree((InputStream) response.getEntity());
        if (jsonNode.get("Response") instanceof ArrayNode) {
            System.out.println("JSON Profile of XACML 1.0 MDP or JSON Profile of XACML 1.1 Single Decision / MDP");
            io.xacml.json.model.Response xacmlResponse = mapper.treeToValue(jsonNode, io.xacml.json.model.Response.class);
            for (Result r : xacmlResponse.getResults()) {
                System.out.println(r.getDecision());
            }
        } else {
            System.out.println("JSON Profile of XACML 1.0 Single Decision");
            SingleResponse xacmlResponse = mapper.treeToValue(jsonNode, SingleResponse.class);
            System.out.println(xacmlResponse.getResult().getDecision());

        }
    }
}
