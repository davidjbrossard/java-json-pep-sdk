package io.xacml.json.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StatusDeserializationTest {
    private final ClassLoader classLoader = getClass().getClassLoader();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserialize_status_empty() throws IOException {
        Status status = deserializeString("{}");
        assertThat(status.getStatusCode(), nullValue());
        assertThat(status.getStatusMessage(), nullValue());
        assertThat(status.getStatusDetail(), nullValue());
    }

    @Test
    public void deserialize_simple_status_code() throws IOException {
        Status status = deserializeString("{ \"StatusCode\": { \"Value\": \"urn:some:status\" } }");
        assertThat(status.getStatusCode().getValue(), equalTo("urn:some:status"));
        assertThat(status.getStatusCode().getStatusCode(), nullValue());
    }

    @Test
    public void deserialize_nested_status_code() throws IOException {
        Status status = deserializeResource("json/status-nested-status-code.json");
        assertThat(status.getStatusCode().getValue(), equalTo("urn:some:status"));
        assertThat(status.getStatusCode().getStatusCode().getValue(), equalTo("urn:some:other:status"));
        assertThat(status.getStatusCode().getStatusCode().getStatusCode(), nullValue());
    }

    @Test
    public void deserialize_status_message() throws IOException {
        Status status = deserializeString("{ \"StatusMessage\": \"some status message\" }");
        assertThat(status.getStatusMessage(), equalTo("some status message"));
    }

    @Test
    public void deserialize_profile10_status_detail_simple_string() throws IOException {
        Status status = deserializeString("{ \"StatusDetail\": \"detail\" }");
        assertThat(status.getStatusDetail(), arrayContaining("detail"));
    }

    @Test
    public void deserialize_profile11_status_detail_simple_string() throws IOException {
        Status status = deserializeString("{ \"StatusDetail\": [ \"detail\" ] }");
        assertThat(status.getStatusDetail().length, equalTo(1));
        assertThat(status.getStatusDetail(), arrayContaining("detail"));
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void deserialize_status_detail_missing_attribute_object() throws IOException {
        Status status = deserializeString("{ \"StatusDetail\": [ {\"AttributeId\": \"document-id\", \"Category\":  \"resource\"} ] }");
        assertThat(status.getStatusDetail(), arrayContaining(instanceOf(Map.class)));
        final Map detail = (Map) status.getStatusDetail()[0];
        assertThat(detail.get("AttributeId"), equalTo("document-id"));
        assertThat(detail.get("Category"), equalTo("resource"));
    }

    private Status deserializeString(String source) throws IOException {
        return mapper.readValue(source, Status.class);
    }

    private Status deserializeResource(String source) throws IOException {
        return mapper.readValue(classLoader.getResourceAsStream(source), Status.class);
    }
}
