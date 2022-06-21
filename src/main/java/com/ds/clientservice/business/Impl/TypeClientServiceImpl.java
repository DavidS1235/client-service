package com.ds.clientservice.business.Impl;

import com.ds.clientservice.business.service.TypeClientService;
import com.ds.clientservice.document.TypeClient;
import com.ds.clientservice.repository.TypeClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TypeClientServiceImpl implements TypeClientService {
  @Autowired
  private TypeClientRepository repository;

  @Override
  public Mono<TypeClient> save(TypeClient tp) {
    return repository.save(tp);
  }

  @Override
  public Flux<TypeClient> findAll() {
    return repository.findAll();
  }

  @Override
  public Mono<TypeClient> find(String id) {
    return repository.findById(id);
  }

  @Override
  public Mono<Void> Delete(TypeClient tp) {
    return repository.delete(tp);
  }
}
