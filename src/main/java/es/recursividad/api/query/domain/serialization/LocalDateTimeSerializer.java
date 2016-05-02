package es.recursividad.api.query.domain.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author victor.hernandez @ recursividad.es
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    /**
     * Converts a {@link LocalDateTime} object to string using {@link DateTimeFormatter#ISO_OFFSET_DATE_TIME} format.
     *
     * @param dateTime Datetime to be converted.
     * @param generator Json generator.
     * @param provider Serializer provider.
     * @throws IOException
     */
    @Override
    public void serialize(LocalDateTime dateTime, JsonGenerator generator, SerializerProvider provider) throws IOException {
        String dateString = dateTime.atOffset(ZoneOffset.UTC).format(formatter);
        generator.writeString(dateString);
    }
}
