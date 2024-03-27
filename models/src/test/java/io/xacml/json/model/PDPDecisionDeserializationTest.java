package io.xacml.json.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PDPDecisionDeserializationTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserialize_deny_decision() throws IOException {
        PDPDecision decision = deserializeString("\"Deny\"");
        assertThat(decision, equalTo(PDPDecision.DENY));
    }

    @Test
    public void deserialize_permit_decision() throws IOException {
        PDPDecision decision = deserializeString("\"Permit\"");
        assertThat(decision, equalTo(PDPDecision.PERMIT));
    }

    @Test
    public void deserialize_not_applicable_decision() throws IOException {
        PDPDecision decision = deserializeString("\"NotApplicable\"");
        assertThat(decision, equalTo(PDPDecision.NOT_APPLICABLE));
    }

    @Test
    public void deserialize_indetrminate_decision() throws IOException {
        PDPDecision decision = deserializeString("\"Indeterminate\"");
        assertThat(decision, equalTo(PDPDecision.INDETERMINATE));
    }

    private PDPDecision deserializeString(String source) throws IOException {
        return mapper.readValue(source, PDPDecision.class);
    }
}
