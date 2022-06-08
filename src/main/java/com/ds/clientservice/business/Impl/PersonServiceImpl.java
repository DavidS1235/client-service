package com.ds.clientservice.business.Impl;

import com.ds.clientservice.business.service.PersonService;
import com.ds.clientservice.document.Person;
import com.ds.clientservice.repository.PersonRespository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRespository repository;

    @Override
    public Mono<Person> save(Person p) {
        return repository.save(p);
    }

    @Override
    public Flux<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Person> find(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Void> Delete(Person p) {
        return repository.delete(p);
    }
}
