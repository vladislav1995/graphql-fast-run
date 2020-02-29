package com.miniq.example.application;

import com.miniq.example.resource.GraphQLResource;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Uladik
 */
public class Application extends ResourceConfig {

    public Application() {
        register(GraphQLResource.class);
    }

}
