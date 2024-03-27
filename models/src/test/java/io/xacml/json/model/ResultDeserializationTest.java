package io.xacml.json.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResultDeserializationTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deserialize_simple_decision() throws IOException {
        Result response = deserializeString("{ \"Decision\": \"Deny\" }");
        assertThat(response.getDecision(), equalTo(PDPDecision.DENY));
        assertThat(response.getStatus(), nullValue());
        assertThat(response.getCategories(), nullValue());
        assertThat(response.getObligations(), nullValue());
        assertThat(response.getAssociatedAdvice(), nullValue());
        assertThat(response.getPolicyIdentifierList(), nullValue());
    }

    @Test
    public void deserialize_with_status() throws IOException {
        Result response = deserializeString("{ \"Decision\": \"Deny\", \"Status\": { \"StatusCode\": { \"Value\": \"urn:some:status\" } } }");
        assertThat(response.getDecision(), equalTo(PDPDecision.DENY));
        assertThat(response.getStatus(), notNullValue());
        Status status = response.getStatus();
        assertThat(status.getStatusCode().getValue(), equalTo("urn:some:status"));
    }

    @Test
    public void deserialize_with_empty_obligations() throws IOException {
        Result response = deserializeString("{ \"Decision\": \"Deny\", \"Obligations\": [] }");
        assertThat(response.getDecision(), equalTo(PDPDecision.DENY));
        assertThat(response.getObligations(), hasSize(0));
    }

    @Test
    public void deserialize_with_non_empty_obligations() throws IOException {
        Result response = deserializeString("{ \"Decision\": \"Deny\", \"Obligations\": [ { \"Id\": \"id\" } ] }");
        assertThat(response.getDecision(), equalTo(PDPDecision.DENY));
        assertThat(response.getObligations(), hasSize(1));
        assertThat(response.getObligations().get(0), notNullValue());
    }

    @Test
    public void deserialize_with_empty_category_array() throws IOException {
        Result response = deserializeString("{ \"Decision\": \"Deny\", \"Category\": [] }");
        assertThat(response.getDecision(), equalTo(PDPDecision.DENY));
        assertThat(response.getCategories(), hasSize(0));
    }

    @Test
    public void deserialize_with_profile10_single_category_object() throws IOException {
        Result response = deserializeString("{ \"Decision\": \"Deny\", \"Category\": { \"CategoryId\": \"category\" } }");
        assertThat(response.getDecision(), equalTo(PDPDecision.DENY));
        assertThat(response.getCategories(), hasSize(1));
        assertThat(response.getCategories().get(0), notNullValue());
    }

    @Test
    public void deserialize_with_profile11_single_category_array() throws IOException {
        Result response = deserializeString("{ \"Decision\": \"Deny\", \"Category\": [ { \"CategoryId\": \"category\" } ] }");
        assertThat(response.getDecision(), equalTo(PDPDecision.DENY));
        assertThat(response.getCategories(), hasSize(1));
        assertThat(response.getCategories().get(0), notNullValue());
    }

    @Test
    public void deserialize_with_multiple_categories() throws IOException {
        Result response = deserializeString("{ \"Decision\": \"Deny\", \"Category\": [ { \"CategoryId\": \"category\" }, { \"CategoryId\": \"category2\" } ] }");
        assertThat(response.getDecision(), equalTo(PDPDecision.DENY));
        assertThat(response.getCategories(), hasSize(2));
        assertThat(response.getCategories().get(0), notNullValue());
        assertThat(response.getCategories().get(1), notNullValue());
    }

    @Test
    public void deserialize_with_empty_advice() throws IOException {
        Result response = deserializeString("{ \"Decision\": \"Deny\", \"AssociatedAdvice\": [] }");
        assertThat(response.getDecision(), equalTo(PDPDecision.DENY));
        assertThat(response.getAssociatedAdvice(), hasSize(0));
    }

    @Test
    public void deserialize_with_non_empty_advice() throws IOException {
        Result response = deserializeString("{ \"Decision\": \"Deny\", \"AssociatedAdvice\": [ { \"Id\": \"id\" } ] }");
        assertThat(response.getDecision(), equalTo(PDPDecision.DENY));
        assertThat(response.getAssociatedAdvice(), hasSize(1));
        assertThat(response.getAssociatedAdvice().get(0), notNullValue());
    }

    @Test
    public void deserialize_v11_with_policy_identifiers() throws IOException {
        Result response = deserializeString("{ \"Decision\": \"Permit\", \"PolicyIdentifierList\": { \"PolicyIdReference\": [{\"Id\": \"id\"}], \"PolicySetIdReference\": [] } }");
        assertThat(response.getDecision(), equalTo(PDPDecision.PERMIT));
        assertThat(response.getPolicyIdentifierList(), notNullValue());
        assertThat(response.getPolicyIdentifierList().getPolicyIdReferences(), contains(notNullValue()));
        assertThat(response.getPolicyIdentifierList().getPolicySetIdReferences(), empty());
    }

    @Test
    public void deserialize_v10_with_policy_identifiers() throws IOException {
        Result response = deserializeString("{ \"Decision\": \"Permit\", \"PolicyIdentifier\": { \"PolicyIdReference\": [{\"Id\": \"id\"}], \"PolicySetIdReference\": [] } }");
        assertThat(response.getDecision(), equalTo(PDPDecision.PERMIT));
        assertThat(response.getPolicyIdentifierList(), notNullValue());
        assertThat(response.getPolicyIdentifierList().getPolicyIdReferences(), contains(notNullValue()));
        assertThat(response.getPolicyIdentifierList().getPolicySetIdReferences(), empty());
    }

    private Result deserializeString(String source) throws IOException {
        return mapper.readValue(source, Result.class);
    }
}
