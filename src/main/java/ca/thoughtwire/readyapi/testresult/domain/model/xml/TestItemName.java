package ca.thoughtwire.readyapi.testresult.domain.model.xml;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class TestItemName {

    private final String[] parts;

    @JsonCreator
    public TestItemName(String value) {
        parts = value == null ? new String[]{} : value.split(":");
    }

    public TestItemName(int size) {
        parts = new String[size];
    }

    @Override
    @JsonValue
    public String toString() {
        return parts == null ? "" : String.join(":", parts);
    }

    public String getPart(int index) {
        return parts.length <= index ? null : parts[index];
    }

    public void setPart(int index, String value) {
        parts[index] = value;
    }

}
