package com.ds.clientservice.config;

import com.ds.clientservice.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfigProduct {


@Bean
public RouterFunction<ServerResponse> routes(ProductHandler handler) {
    return RouterFunctions
            .route(GET("/api/product"), handler::list)
            .andRoute(GET("/api/product/{id}"), handler::find)
            .andRoute(POST("/api/product"), handler::createProduct)
            .andRoute(PUT("/api/product/{id}"), handler::update)
            .andRoute(DELETE("/api/product/{id}"), handler::delete);
    }


}
