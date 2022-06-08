package com.ds.clientservice.handler;

import com.ds.clientservice.business.service.ProductService;
import com.ds.clientservice.business.service.TypeProductService;
import com.ds.clientservice.document.Product;
import com.ds.clientservice.document.TypeProduct;
import java.net.URI;
import java.util.Date;
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
                // findAll() retorna un Flux<Producto>
                .body(service.findAll(), TypeProduct.class);
    }
}
