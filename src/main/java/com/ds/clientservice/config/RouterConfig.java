package com.ds.clientservice.config;

import com.ds.clientservice.handler.TypeProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfig {

//    @Bean
//    public RouterFunction<ServerResponse> routes(ProductHandler productHandler) {
//        return RouterFunctions
//                .route(POST("/api/product/crear"), productHandler::create);
//
//    }
@Bean
public RouterFunction<ServerResponse> routes(TypeProductHandler handler) {
    return RouterFunctions
            .route(GET("/api/TypeProduct"), handler::list);
    }


}
