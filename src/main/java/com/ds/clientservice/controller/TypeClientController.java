package com.ds.clientservice.controller;

import com.ds.clientservice.business.service.TypeClientService;
import com.ds.clientservice.document.TypeClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/typeclient")
public class TypeClientController {
  @Autowired
  private TypeClientService service;

  @CircuitBreaker(name = "client")
  @GetMapping("")
  public Mono<ResponseEntity<Flux<TypeClient>>> findAll() {
    return Mono.just(ResponseEntity.ok()
            .body(service.findAll()));
  }

  @CircuitBreaker(name = "client")
  @GetMapping("/{id}")
  public Mono<ResponseEntity<TypeClient>> findById(@PathVariable String id) {
    return service.find(id)
            .map(p -> ResponseEntity.ok()
                    .body(p))
            .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @CircuitBreaker(name = "client")
  @PostMapping("")
  public Mono<ResponseEntity<TypeClient>> create(@RequestBody TypeClient tc) {
    return service.save(tc)
            .map(t -> ResponseEntity.created(URI.create("/api/typeclient/".concat(t.getId())))
                    .body(t)
            );
  }

  @CircuitBreaker(name = "client")
  @PutMapping("/{id}")
  public Mono<ResponseEntity<TypeClient>> update(@RequestBody TypeClient tc, @PathVariable String id) {
    return service.find(id)
            .flatMap(t -> {
              t.setCode(tc.getCode());
              t.setName(tc.getName());
              return service.save(t);
            })
            .map(t -> ResponseEntity.created(URI.create("/api/typeclient/".concat(t.getId())))
                    .body(t)
            )
            .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @CircuitBreaker(name = "client")
  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
    return service.find(id)
            .flatMap(t -> {
              return service.Delete(t)
                      .then(Mono.just(ResponseEntity.noContent().build()));
            })
            .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
