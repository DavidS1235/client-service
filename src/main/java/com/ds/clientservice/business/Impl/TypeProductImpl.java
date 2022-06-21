package com.ds.clientservice.business.Impl;

import com.ds.clientservice.business.service.TypeProductService;
import com.ds.clientservice.document.TypeProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class TypeProductImpl implements TypeProductService {

  @Autowired
  @Qualifier("TypeProduct")
  private WebClient webClient;

  @Override
  public Flux<TypeProduct> findAll() {
    return webClient.get()
            .accept(MediaType.APPLICATION_JSON)
            // se envía el request
            .exchange()
            // se recibe el Flux<Producto>
            .flatMapMany(response -> response.bodyToFlux(TypeProduct.class))
            ;
  }
}
