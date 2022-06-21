package com.ds.clientservice.business.Impl;

import com.ds.clientservice.business.service.ProductService;
import com.ds.clientservice.document.Product;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  @Qualifier("product")
  private WebClient webClient;

  @Override
  public Flux<Product> findAll() {
    return webClient.get()
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .flatMapMany(response -> response.bodyToFlux(Product.class));
  }

  @Override
  public Mono<Product> findById(String id) {
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    return webClient.get()
            .uri("/{id}", params)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .flatMap(response -> response.bodyToMono(Product.class))
            ;
  }

  @Override
  public Mono<Product> saveProduct(Product product) {
    return webClient.post()
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .syncBody(product)
            .retrieve()
            .bodyToMono(Product.class);
  }

  @Override
  public Mono<Product> update(Product p, String id) {
    return webClient.put()
            .uri("/{id}", Collections.singletonMap("id", id))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(p))
            .retrieve()
            .bodyToMono(Product.class)
            ;
  }

  @Override
  public Mono<Void> delete(String id) {
    return webClient.delete()
            .uri("/{id}", Collections.singletonMap("id", id))
            .exchange()
            .then()
            ;
  }
}
