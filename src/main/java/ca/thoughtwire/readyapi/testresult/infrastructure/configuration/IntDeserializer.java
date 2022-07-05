package ca.thoughtwire.readyapi.testresult.infrastructure.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class IntDeserializer extends JsonDeserializer<Integer> {

    @Override
    public Integer deserialize(JsonParser p, DeserializationContext context) {
        int result = 0;
        try {
            String value = p.getValueAsString();
            result = Integer.parseInt(value);
        } catch (Exception ignore) {
        }
        return result;
    }

}