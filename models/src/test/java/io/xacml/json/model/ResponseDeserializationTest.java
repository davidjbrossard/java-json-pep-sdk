package io.xacml.json.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResponseDeserializationTest {
    private final ClassLoader classLoader = getClass().getClassLoader();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserialize_profile10_single_result() throws IOException {
        Response response = deserializeResource("json/response-1.0-with-single-result.json");
        assertThat(response.getResults(), allOf(notNullValue(), hasSize(1)));
        assertThat(response.getResults().get(0), notNullValue());
    }

    @Test
    public void deserialize_profile11_single_result() throws IOException {
        Response response = deserializeResource("json/response-1.1-with-single-result.json");
        assertThat(response.getResults(), allOf(notNullValue(), hasSize(1)));
        assertThat(response.getResults().get(0), notNullValue());
    }

    private Response deserializeResource(String source) throws IOException {
        return mapper.readValue(classLoader.getResourceAsStream(source), Response.class);
    }
}
