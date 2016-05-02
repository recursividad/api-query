package es.recursividad.api.query.configuration;

import es.recursividad.api.query.client.RunscopeApiClient;
import es.recursividad.api.query.client.utils.JsonConverterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

/**
 * @author victor.hernandez @ recursividad.es
 */
@Configuration
public class ApiClientConfiguration {

    @Bean
    public Retrofit getRetrofitClient() {
        return new Retrofit.Builder()
                .baseUrl("https://api.runscope.com")
                .addConverterFactory(JsonConverterFactory.create())
                .build();
    }

    @Bean
    public RunscopeApiClient getRunscopeApiClient(Retrofit retrofit) {
        return retrofit.create(RunscopeApiClient.class);
    }
}
