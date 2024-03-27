package io.xacml.json.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class IdReferenceDeserializationTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserialize_empty() throws IOException {
        IdReference reference = deserializeString("{ \"Id\": \"id\" }");
        assertThat(reference.getId(), equalTo("id"));
        assertThat(reference.getVersion(), nullValue());
    }

    @Test
    public void deserialize_with_version() throws IOException {
        IdReference reference = deserializeString("{ \"Id\": \"id\", \"Version\": \"0.1.2\" }");
        assertThat(reference.getId(), equalTo("id"));
        assertThat(reference.getVersion(), equalTo("0.1.2"));
    }

    private IdReference deserializeString(String source) throws IOException {
        return mapper.readValue(source, IdReference.class);
    }
}
