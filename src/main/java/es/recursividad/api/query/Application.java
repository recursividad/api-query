package es.recursividad.api.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author victor.hernandez @ recursividad.es
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "es.recursividad.api.query")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
