package com.ds.clientservice.business.service;

import com.ds.clientservice.document.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

  public Mono<Client> save(Client c);

  public Flux<Client> findAll();

  public Mono<Client> find(String id);

  public Mono<Void> Delete(Client c);
}
