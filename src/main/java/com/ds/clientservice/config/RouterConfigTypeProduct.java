package com.ds.clientservice.config;

import com.ds.clientservice.handler.TypeProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class RouterConfigTypeProduct {


  @Bean
  public RouterFunction<ServerResponse> routes2(TypeProductHandler handler) {
    return RouterFunctions
            .route(GET("/api/TypeProduct"), handler::list);
  }


}
