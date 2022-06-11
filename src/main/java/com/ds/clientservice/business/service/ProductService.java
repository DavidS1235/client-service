package com.ds.clientservice.business.service;

import com.ds.clientservice.document.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    public Flux<Product> findAll();

    public Mono<Product> findById(String id);
    public Mono<Product> saveProduct(Product p);
    public Mono<Product> update(Product p, String id);
    public Mono<Void> delete(String id);
}
