package com.kaj.api;

import java.util.HashSet;
import java.util.Set;

import com.kaj.api.controller.Hello;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/public")
public class MainApp extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<Class<?>>();
        resources.add(Hello.class);
        return resources;
    }
}
