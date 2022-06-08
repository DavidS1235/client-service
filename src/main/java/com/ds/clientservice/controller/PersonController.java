package com.ds.clientservice.controller;

import com.ds.clientservice.business.service.PersonService;
import com.ds.clientservice.document.Person;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/listperson")
    public Mono<ResponseEntity<Flux<Person>>> findAll(){
        return Mono.just(ResponseEntity.ok()
                .body(service.findAll()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Person>> findById(@PathVariable String id) {
        return service.find(id)
                .map(p -> ResponseEntity.ok()
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<Person>> create(@RequestBody Person p) {
        return service.save(p)
                .map(ps -> ResponseEntity.created(URI.create("/api/person/".concat(ps.getId())))
                        .body(ps)
                );
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Person>> update(@RequestBody Person p, @PathVariable String id){
        return service.find(id)
                .flatMap(ps -> {
                    ps.setIdTypeDocument(p.getIdTypeDocument());
                    ps.setDocument(p.getDocument());
                    ps.setName(p.getName());
                    ps.setPaternal(p.getPaternal());
                    ps.setMaternal(p.getMaternal());
                    ps.setIdTpePerson(p.getIdTpePerson());
                    return service.save(ps);
                })
                .map(cl -> ResponseEntity.created(URI.create("/api/person/".concat(cl.getId())))
                        .body(cl)
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
        return service.find(id)
                .flatMap(p -> {
                    return service.Delete(p)
                            .then(Mono.just(ResponseEntity.noContent().build()));
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
