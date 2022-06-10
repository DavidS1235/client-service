package com.ds.clientservice.handler;

import com.ds.clientservice.business.service.TypeProductService;
import com.ds.clientservice.document.TypeProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TypeProductHandler {

    @Autowired
    private TypeProductService service;

    public Mono<ServerResponse> list(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), TypeProduct.class);
    }
}
