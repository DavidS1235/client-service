package com.ds.clientservice.controller;


import com.ds.clientservice.business.service.ClientService;
import com.ds.clientservice.document.Client;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping("")
    public Mono<ResponseEntity<Flux<Client>>> findAll(){
        return Mono.just(ResponseEntity.ok()
                .body(service.findAll()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Client>> findById(@PathVariable String id) {
        return service.find(id)
                .map(c -> ResponseEntity.ok()
                        .body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public Mono<ResponseEntity<Client>> create(@RequestBody Client c) {
        return service.save(c)
                .map(cl -> ResponseEntity.created(URI.create("/api/client/".concat(cl.getId())))
                        .body(cl)
                );
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Client>> update(@RequestBody Client c, @PathVariable String id){
        return service.find(id)
                .flatMap(cl -> {
                    cl.setCode(c.getCode());
                    cl.setIdPerson(c.getIdPerson());
                    cl.setName(c.getName());
                    cl.setPerson(c.getPerson());
                    cl.setTypeClient(c.getTypeClient());
                    cl.setCtaAhorro(c.getCtaAhorro());
                    cl.setPlzFijo(c.getPlzFijo());
                    cl.setCredPersonal(c.getCredPersonal());
                    cl.setCtaCorriente(c.getCtaCorriente());
                    cl.setCredEmpresarial(c.getCredEmpresarial());
                    cl.setTcPersonal(c.getTcPersonal());
                    cl.setTcEmpresarial(c.getTcEmpresarial());
                    cl.setProduct(c.getProduct());
                    return service.save(cl);
                })
                .map(cl -> ResponseEntity.created(URI.create("/api/client/".concat(cl.getId())))
                        .body(cl)
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
        return service.find(id)
                .flatMap(c -> {
                    return service.Delete(c)
                            .then(Mono.just(ResponseEntity.noContent().build()));
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
