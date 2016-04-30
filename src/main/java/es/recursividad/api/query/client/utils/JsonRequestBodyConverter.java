package es.recursividad.api.query.client.utils;

import com.fasterxml.jackson.databind.ObjectWriter;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

import java.io.IOException;

/**
 * {@link Converter} that transforms a {@link T} object into a Json {@link RequestBody}.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private final ObjectWriter adapter;

    public JsonRequestBodyConverter(ObjectWriter adapter) {
        this.adapter = adapter;
    }

    /**
     * Convert a {@link T} object into a {@link RequestBody}.
     *
     * @param value Object to be converted.
     * @return Request body containing the input object serialized as a Json object.
     *
     * @throws IOException
     */
    @Override
    public RequestBody convert(T value) throws IOException {
        byte[] bytes = this.adapter.writeValueAsBytes(value);
        return RequestBody.create(MEDIA_TYPE, bytes);
    }
}

