package com.mub.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by manodyas on 2/6/2018.
 */
@SpringBootApplication
@ConfigurationProperties
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
       // loadAJCOMSDetails();
    }
}
