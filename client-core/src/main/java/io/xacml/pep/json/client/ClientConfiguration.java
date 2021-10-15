package io.xacml.pep.json.client;

/**
 * @author Julio Cesar Villalta III <jvillalta@nvisia.com>
 */
public interface ClientConfiguration {

    /**
     * Full, absolute, URL of the authorization service.
     * <p>
     * Should not be null
     * <p>
     * ex: https://api.pdp.example.io/authorize
     * ex: https://api.example.io/asm-pdp/authorize
     */
    String getAuthorizationServiceUrl();

    /**
     * The username of the PEP user who can access the pdp for policy decisions.
     * <p>
     * Should be provided if Basic Authentication is required for PDP requests.
     * <p>
     * ex: pep-user
     */
    String getUsername();

    /**
     * The PEP user's password
     */
    String getPassword();
}
