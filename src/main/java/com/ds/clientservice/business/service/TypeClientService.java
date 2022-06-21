package com.ds.clientservice.business.service;

import com.ds.clientservice.document.TypeClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TypeClientService {

  public Mono<TypeClient> save(TypeClient tc);

  public Flux<TypeClient> findAll();

  public Mono<TypeClient> find(String id);

  public Mono<Void> Delete(TypeClient tc);
}
