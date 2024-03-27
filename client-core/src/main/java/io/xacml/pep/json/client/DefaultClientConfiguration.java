package io.xacml.pep.json.client;

import java.util.Objects;

/**
 * Standard Client configuration needed to make requests to the PDP.
 * <p>
 * Should be provided to the constructor of {@link AuthZClient} implementations where HTTP clients are built
 */
public class DefaultClientConfiguration implements ClientConfiguration {
    /**
     * Full, absolute, URL of the authorization service.
     * <p>
     * Should not be null
     * <p>
     * ex: https://api.pdp.example.io/authorize
     * <p>
     * ex: https://api.example.io/asm-pdp/authorize
     */
    String authorizationServiceUrl;

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

    DefaultClientConfiguration(String authorizationServiceUrl, String username, String password) {
        this.authorizationServiceUrl =
                Objects.requireNonNull(authorizationServiceUrl, "The authorization service URL cannot be null.");
        this.username = username;
        this.password = password;
    }

    public static DefaultClientConfigurationBuilder builder() {
        return new DefaultClientConfigurationBuilder();
    }

    @Override
    public String getAuthorizationServiceUrl() {
        return authorizationServiceUrl;
    }

    public void setAuthorizationServiceUrl(String url) {
        this.authorizationServiceUrl =
                Objects.requireNonNull(url, "The authorization service URL cannot be null.");
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DefaultClientConfiguration)) return false;
        final DefaultClientConfiguration other = (DefaultClientConfiguration) o;
        if (!Objects.equals(this.getAuthorizationServiceUrl(), other.getAuthorizationServiceUrl())) return false;
        if (!Objects.equals(this.getUsername(), other.getUsername())) return false;
        return Objects.equals(this.getPassword(), other.getPassword());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $authorizationServiceUrl = this.getAuthorizationServiceUrl();
        result = result * PRIME + ($authorizationServiceUrl == null ? 43 : $authorizationServiceUrl.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        return "DefaultClientConfiguration(authorizationServiceUrl=" + this.authorizationServiceUrl
                + ", username=" + this.getUsername()
                + ", password=" + this.getPassword() + ")";
    }

    public static class DefaultClientConfigurationBuilder {
        private String pdpUrl;
        private String authorizationServiceUrl;
        private String username;
        private String password;

        DefaultClientConfigurationBuilder() {
        }

        public DefaultClientConfigurationBuilder pdpUrl(String pdpUrl) {
            if (authorizationServiceUrl != null) {
                throw new IllegalStateException("An authorization service URL has already been set using authorizationServiceUrl()." +
                        " Either use authorizationServiceUrl() or pdpUrl(), but not both.");
            }
            this.pdpUrl = Objects.requireNonNull(pdpUrl, "The authorization service URL cannot be null.");
            return this;
        }

        public DefaultClientConfigurationBuilder authorizationServiceUrl(String url) {
            if (pdpUrl != null) {
                throw new IllegalStateException("A PDP URL has already been set using pdpUrl()." +
                        " Either use authorizationServiceUrl() or pdpUrl(), but not both.");
            }
            this.authorizationServiceUrl = url;
            return this;
        }

        public DefaultClientConfigurationBuilder username(String username) {
            this.username = username;
            return this;
        }

        public DefaultClientConfigurationBuilder password(String password) {
            this.password = password;
            return this;
        }

        public DefaultClientConfiguration build() {
            final String serviceUrl;
            if (authorizationServiceUrl == null && pdpUrl == null) {
                throw new IllegalStateException("An authorization service URL has not been set." +
                        " Set it using this.authorizationService(url) this.pdpUrl(url)." +
                        " Prefer using authorizationService(url), since pdpUrl(url) is deprecated.");
            }
            if (authorizationServiceUrl == null) {
                serviceUrl = pdpUrl + PDPConstants.AUTHORIZATION_ENDPOINT;
            } else {
                serviceUrl = authorizationServiceUrl;
            }
            return new DefaultClientConfiguration(serviceUrl, username, password);
        }

        public String toString() {
            return "DefaultClientConfiguration.DefaultClientConfigurationBuilder(pdpUrl=" + this.pdpUrl
                    + ", authorizationServiceUrl=" + this.authorizationServiceUrl
                    + ", username=" + this.username + ", password=" + this.password + ")";
        }
    }
}
