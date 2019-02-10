package io.xacml.pep.json.client;

/**
 * @author Julio Cesar Villalta III <jvillalta@nvisia.com>
 */
public interface ClientConfiguration {

    /**
     * URL of the the PDP.
     * If running in application server like Tomcat where an application suffix is added, it should be included
     * <p>
     * Should not be null
     * <p>
     * ex: https://api.pdp.example.io
     * ex: https://api.example.io/asm-pdp
     */
    String getPdpUrl();

    /**
     * The username of the PEP user who can access the pdp for policy decisions
     * <p>
     * ex: pep-user
     */
    String getUsername();

    /**
     * The PEP user's password
     */
    String getPassword();
}
