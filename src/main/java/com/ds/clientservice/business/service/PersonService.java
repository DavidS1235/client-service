package com.ds.clientservice.business.service;

import com.ds.clientservice.document.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {

  public Mono<Person> save(Person p);

  public Flux<Person> findAll();

  public Mono<Person> find(String id);

  public Mono<Void> Delete(Person p);
}
