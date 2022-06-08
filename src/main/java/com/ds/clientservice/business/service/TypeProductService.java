package com.ds.clientservice.business.service;

import com.ds.clientservice.document.TypeProduct;
import reactor.core.publisher.Flux;

public interface TypeProductService {

    public Flux<TypeProduct> findAll();

}
