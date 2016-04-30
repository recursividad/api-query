package es.recursividad.api.query.client.utils;


import com.fasterxml.jackson.databind.*;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * {@link retrofit2.Converter.Factory} that allows to get {@link Converter} for both {@link ResponseBody}
 * and {@link RequestBody} objects.
 *
 * @author victor.hernandez @ recursividad.es
 */
public class JsonConverterFactory extends Converter.Factory {
    private final ObjectMapper mapper;

    public static JsonConverterFactory create() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return create(objectMapper);
    }

    public static JsonConverterFactory create(ObjectMapper mapper) {
        return new JsonConverterFactory(mapper);
    }

    private JsonConverterFactory(ObjectMapper mapper) {
        if(mapper == null) {
            throw new NullPointerException("mapper == null");
        } else {
            this.mapper = mapper;
        }
    }

    /**
     * Get a {@link ResponseBody} {@link Converter} for the requested type.
     *
     * @param type Type.
     * @param annotations Annotations.
     * @param retrofit Retrofit.
     *
     * @return {@link JsonResponseBodyConverter} for the requested input values.
     */
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        JavaType javaType = this.mapper.getTypeFactory().constructType(type);
        ObjectReader reader = this.mapper.readerFor(javaType);
        return new JsonResponseBodyConverter(reader);
    }

    /**
     * Get a {@link RequestBody} {@link Converter} for the requested type.
     *
     * @param type Type.
     * @param annotations Annotations.
     * @param retrofit Retrofit.
     *
     * @return {@link JsonRequestBodyConverter} for the requested input values.
     */
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        JavaType javaType = this.mapper.getTypeFactory().constructType(type);
        ObjectWriter writer = this.mapper.writerFor(javaType);
        return new JsonRequestBodyConverter(writer);
    }
}
