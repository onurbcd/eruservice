package com.onurbcd.eruservice.api.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.io.Serial;

@JsonComponent
public class EruStringDeserializer extends StringDeserializer {

    @Serial
    private static final long serialVersionUID = -5211983605179202205L;

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var deserializedValue = super.deserialize(p, ctxt);
        return StringUtils.isNotBlank(deserializedValue) ? deserializedValue.trim() : null;
    }
}
