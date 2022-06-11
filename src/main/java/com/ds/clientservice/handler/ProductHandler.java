package com.ds.clientservice.handler;

import com.ds.clientservice.business.service.ProductService;
import com.ds.clientservice.document.Product;
import java.net.URI;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler{

    private final Logger log = LoggerFactory.getLogger("ClientServiceApplication");
    @Autowired
    private ProductService service;


    public Mono<ServerResponse> list(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Product.class);
    }

    public Mono<ServerResponse> find(ServerRequest request) {
        String id = request.pathVariable("id");
        return service.findById(id)
                .flatMap( p -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .syncBody(p)
                )
                .switchIfEmpty(ServerResponse.notFound().build())
                ;
    }

    public Mono<ServerResponse> createProduct(ServerRequest request){
        Mono<Product> product = request.bodyToMono(Product.class);

        return product
                .flatMap( p -> {
                    if(p.getIdBank() == null){
                        log.error("Missing Bank Id");
                        throw new RuntimeException("Missing Bank Id");
                    }
                    if (p.getTpeCrrency().isEmpty()) {
                        log.error("Missing Currency");
                        throw new RuntimeException("Missing Currency");
                    }
                    if(p.getDate() == null){
                        p.setDate(new Date());
                    }
                    if(p.getIdClient() == null) {
                        log.error("Missing Client id");
                        throw new RuntimeException("Missing Client id");
                    }

                    return service.saveProduct(p);

                })
                .flatMap( p -> ServerResponse
                        .created(URI.create("/api/product/".concat(p.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .syncBody(p)

                );
    }
    public Mono<ServerResponse> update(ServerRequest request) {

        Mono<Product> p = request.bodyToMono(Product.class);
        String id = request.pathVariable("id");

        return p
                .flatMap( pr -> ServerResponse
                        .created(URI.create("/api/product/".concat(id)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.update(pr,  id), Product.class)
                );
    }
    public Mono<ServerResponse> delete(ServerRequest request) {

        String id = request.pathVariable("id");

        return service.delete(id)
                .then(ServerResponse.noContent().build());
    }
}
