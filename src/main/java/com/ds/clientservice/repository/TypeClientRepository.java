package com.ds.clientservice.repository;


import com.ds.clientservice.document.TypeClient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TypeClientRepository extends ReactiveMongoRepository<TypeClient, String> {
}
