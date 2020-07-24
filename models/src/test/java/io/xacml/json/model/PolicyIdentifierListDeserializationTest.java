package io.xacml.json.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;

public class PolicyIdentifierListDeserializationTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserialize_no_members() throws IOException {
        PolicyIdentifierList identifiers = deserializeString("{ }");
        assertThat(identifiers.getPolicyIdReferences(), nullValue());
        assertThat(identifiers.getPolicySetIdReferences(), nullValue());
    }

    @Test
    public void deserialize_with_empty_policy_identifiers() throws IOException {
        PolicyIdentifierList identifiers = deserializeString("{ \"PolicyIdReference\": [] }");
        assertThat(identifiers.getPolicyIdReferences(), hasSize(0));
    }

    @Test
    public void deserialize_with_non_empty_policy_identifiers() throws IOException {
        PolicyIdentifierList identifiers = deserializeString("{ \"PolicyIdReference\": [{\"Id\": \"id\"}] }");
        assertThat(identifiers.getPolicyIdReferences(), hasSize(1));
    }

    @Test
    public void deserialize_with_empty_policy_set_identifiers() throws IOException {
        PolicyIdentifierList identifiers = deserializeString("{ \"PolicySetIdReference\": [] }");
        assertThat(identifiers.getPolicySetIdReferences(), hasSize(0));
    }

    @Test
    public void deserialize_with_non_empty_policy_set_identifiers() throws IOException {
        PolicyIdentifierList identifiers = deserializeString("{ \"PolicySetIdReference\": [{\"Id\": \"id\"}] }");
        assertThat(identifiers.getPolicySetIdReferences(), hasSize(1));
    }

    private PolicyIdentifierList deserializeString(String source) throws IOException {
        return mapper.readValue(source, PolicyIdentifierList.class);
    }
}
