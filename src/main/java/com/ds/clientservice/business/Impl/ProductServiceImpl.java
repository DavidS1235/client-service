package com.ds.clientservice.business.Impl;

import com.ds.clientservice.business.service.ProductService;
import com.ds.clientservice.document.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private WebClient webClient;
    @Override
    public Mono<Product> saveProduct(Product product) {
        return webClient.post()
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(product)
                .retrieve()
                .bodyToMono(Product.class);
    }
}
