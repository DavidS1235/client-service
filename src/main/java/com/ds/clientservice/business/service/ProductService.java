package com.ds.clientservice.business.service;

import com.ds.clientservice.document.Product;
import reactor.core.publisher.Mono;

public interface ProductService {

    public Mono<Product> saveProduct(Product p);

}
