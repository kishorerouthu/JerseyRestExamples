package com.css.jersey;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Kishore Routhu on 28/6/17 5:11 PM.
 */
@ApplicationPath("/rest")
public class Application extends ResourceConfig {

    public Application() {
        this.packages("com.css.jersey.rest");
    }
}
