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

/**
 * This class contains sample code using JAX-RS to invoke the PDP
 * @author djob
 *
 */
public class AuthZClient {
	public static void main(String[] args) {
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("pdp-user", "password");
		Client client = ClientBuilder.newClient();
		client.register(feature);
		WebTarget webTarget = client.target("https://djob-hp:9443/asm-pdp/authorize");
		Invocation.Builder builder = webTarget.request("application/xacml+json").accept("application/xacml+json");
		Request r = new Request();
		Category subject = new Category("urn:oasis:names:tc:xacml:1.0:subject-category:access-subject");
		
		Attribute attribute = new Attribute("username", "Alice");
		subject.addAttribute(attribute );
		r.addAccessSubjectCategory(subject);
		Response response = builder.post(Entity.entity(r, "application/xacml+json"));
		System.out.println(response.readEntity(String.class));
//		response.readEntity(com.axiomatics.xacml.json.model.Response.class);
	}
}
