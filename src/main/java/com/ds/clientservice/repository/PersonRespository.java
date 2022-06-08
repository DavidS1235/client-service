package com.ds.clientservice.repository;

import com.ds.clientservice.document.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonRespository extends ReactiveMongoRepository<Person, String> {
}
