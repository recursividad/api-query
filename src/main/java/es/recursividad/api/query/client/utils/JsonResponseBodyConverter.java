package es.recursividad.api.query.client.utils;

import com.fasterxml.jackson.databind.ObjectReader;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * {@link Converter} that transforms a Json {@link ResponseBody} into a {@link T} object.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final ObjectReader adapter;

    public JsonResponseBodyConverter(ObjectReader adapter) {
        this.adapter = adapter;
    }

    /**
     * Convert a {@link ResponseBody} into a {@link T} object.
     *
     * @param value Capture body to be converted.
     * @return Java object with the response contentPlaceholder.
     *
     * @throws IOException
     */
    public T convert(ResponseBody value) throws IOException {
        BufferedReader reader = new BufferedReader(value.charStream());

        T convertedValue;
        try {
            convertedValue = this.adapter.readValue(reader);
        } finally {
            reader.close();
        }

        return convertedValue;
    }
}
