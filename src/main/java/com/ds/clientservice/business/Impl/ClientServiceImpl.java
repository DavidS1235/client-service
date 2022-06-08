package com.ds.clientservice.business.Impl;

import com.ds.clientservice.business.service.ClientService;
import com.ds.clientservice.document.Client;
import com.ds.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository repository;

    @Override
    public Mono<Client> save(Client c) {
        return repository.save(c);
    }

    @Override
    public Flux<Client> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Client> find(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Void> Delete(Client c) {
        return repository.delete(c);
    }

}
