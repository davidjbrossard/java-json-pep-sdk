package io.xacml.json.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ObligationOrAdviceDeserializationTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserialize_no_assignment_member() throws IOException {
        ObligationOrAdvice object = deserializeString("{ \"Id\": \"id\" }");
        assertThat(object.getId(), equalTo("id"));
        assertThat(object.getAttributeAssignments(), nullValue());
    }

    @Test
    public void deserialize_empty_assignment() throws IOException {
        ObligationOrAdvice object = deserializeString("{ \"Id\": \"id\", \"AttributeAssignment\": [] }");
        assertThat(object.getId(), equalTo("id"));
        assertThat(object.getAttributeAssignments(), allOf(notNullValue(), hasSize(0)));
    }

    @Test
    public void deserialize_single_assignment() throws IOException {
        ObligationOrAdvice object = deserializeString("{ \"Id\": \"id\", \"AttributeAssignment\": [ { \"AttributeId\": \"aid\", \"Value\": 123 } ] }");
        assertThat(object.getId(), equalTo("id"));
        assertThat(object.getAttributeAssignments(), allOf(notNullValue(), hasSize(1)));
        AttributeAssignment assignment = object.getAttributeAssignments().get(0);
        assertThat(assignment.getAttributeId(), equalTo("aid"));
        assertThat(assignment.getValue(), equalTo(123));
    }

    private ObligationOrAdvice deserializeString(String source) throws IOException {
        return mapper.readValue(source, ObligationOrAdvice.class);
    }
}
