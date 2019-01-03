package com.axiomatics.xacml.pep;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import com.axiomatics.xacml.json.model.Attribute;
import com.axiomatics.xacml.json.model.Category;
import com.axiomatics.xacml.json.model.Request;
import com.axiomatics.xacml.json.model.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class contains sample code using JAX-RS to invoke the PDP
 * @author djob
 *
 */
public class AuthZClient {
	public static void main(String[] args) throws JsonProcessingException {
		Logger logger = Logger.getLogger(AuthZClient.class.getName());
		HttpAuthenticationFeature authNFeature = HttpAuthenticationFeature.basic("ads-user", "secret");
		LoggingFeature loggingFeature = new LoggingFeature(logger, Level.WARNING, null, null);
		Client client = ClientBuilder.newClient();
		client.register(authNFeature);
		client.register(loggingFeature);
		WebTarget webTarget = client.target("http://djob-hp:8080/asm-pdp/authorize");
		Invocation.Builder builder = webTarget.request("application/xacml+json");
		Category subject = new Category();
		subject.addAttribute(new Attribute("username", "Alice"));
		subject.addAttribute(new Attribute("age", 15));

		Request xacmlRequest = new Request();
		xacmlRequest.addAccessSubjectCategory(subject);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(xacmlRequest));
		Response response = builder.post(Entity.entity(xacmlRequest, "application/xacml+json"));
		com.axiomatics.xacml.json.model.Response xacmlResponse = response.readEntity(com.axiomatics.xacml.json.model.Response.class);
		for (Result r : xacmlResponse.getResults()) {
			System.out.println(r.getDecision());
		}
	}
}
