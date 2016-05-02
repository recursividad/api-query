package es.recursividad.api.query.domain.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author victor.hernandez @ recursividad.es
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    /**
     * Converts a string that represents a date time in {@link DateTimeFormatter#ISO_OFFSET_DATE_TIME} format to
     * a {@link LocalDateTime} object.
     *
     * @param parser Json parser.
     * @param context Deserialization context.
     * @return LocalDate object with the input value converted.
     * @throws IOException
     */
    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        ObjectCodec objectCodec = parser.getCodec();
        TextNode textNode = objectCodec.readTree(parser);
        String dateString = textNode.textValue();

        return LocalDateTime.parse(dateString, formatter);
    }
}
