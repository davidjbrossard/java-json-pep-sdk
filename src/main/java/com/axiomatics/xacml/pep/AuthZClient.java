package com.axiomatics.xacml.pep;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.axiomatics.xacml.json.model.Attribute;
import com.axiomatics.xacml.json.model.Category;
import com.axiomatics.xacml.json.model.Request;
import com.axiomatics.xacml.json.model.Result;

/**
 * This class contains sample code using JAX-RS to invoke the PDP
 * @author djob
 *
 */
public class AuthZClient {
	public static void main(String[] args) {
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("ads-user", "secret");
		Client client = ClientBuilder.newClient();
		client.register(feature);
		WebTarget webTarget = client.target("http://djob-hp:8080/asm-pdp/authorize");
		Invocation.Builder builder = webTarget.request("application/xacml+json");
		Category subject = new Category("urn:oasis:names:tc:xacml:1.0:subject-category:access-subject");
		subject.addAttribute(new Attribute("username", "Alice"));

		Request xacmlRequest = new Request();
		xacmlRequest.addAccessSubjectCategory(subject);
		Response response = builder.post(Entity.entity(xacmlRequest, "application/xacml+json"));
		com.axiomatics.xacml.json.model.Response xacmlResponse = response.readEntity(com.axiomatics.xacml.json.model.Response.class);
		for (Result r : xacmlResponse.getResults()) {
			System.out.println(r.getDecision());
		}
	}
}
