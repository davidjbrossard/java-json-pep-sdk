package io.xacml.pep.json.client;

import lombok.Builder;
import lombok.Data;

/**
 * Standard Client configuration needed to make requests to the PDP.
 * <p>
 * Should be provided to the constructor of {@link AuthZClient} implementations where HTTP clients are built
 *
 * @author Julio Cesar Villalta III <jvillalta@nvisia.com>
 */
@Data
@Builder
public class DefaultClientConfiguration implements ClientConfiguration {

    /**
     * URL of the the PDP.
     * If running in application server like Tomcat where an application suffix is added, it should be included
     * <p>
     * Should not be null
     * <p>
     * ex: https://api.pdp.example.io
     * ex: https://api.example.io/asm-pdp
     */
    String pdpUrl;

    /**
     * The username of the PEP user who can access the pdp for policy decisions
     * <p>
     * ex: pep-user
     */
    String username;

    /**
     * The PEP user's password
     */
    String password;
}
