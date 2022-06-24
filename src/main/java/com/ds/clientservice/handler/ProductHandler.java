package com.ds.clientservice.handler;

import com.ds.clientservice.business.service.ProductService;
import com.ds.clientservice.document.Product;
import java.net.URI;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

  private final Logger log = LoggerFactory.getLogger("ClientServiceApplication");
  private String tp1 = "personal";
  private String tp2 = "empresarial";
  @Autowired
  private Validator validator;
  @Autowired
  private ProductService service;


  public Mono<ServerResponse> list(ServerRequest request) {
    return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(service.findAll(), Product.class);
  }

  public Mono<ServerResponse> find(ServerRequest request) {
    String id = request.pathVariable("id");
    return service.findById(id)
            .flatMap(p -> ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .syncBody(p)
            )
            .switchIfEmpty(ServerResponse.notFound().build())
            ;
  }

  public Mono<ServerResponse> createCtaAhorro(ServerRequest request) {
    Mono<Product> product = request.bodyToMono(Product.class);
    return product
            .flatMap(p -> {
              Errors errors = new BeanPropertyBindingResult(p, Product.class.getName());
              validator.validate(p, errors);

              if (errors.hasErrors()) {
                return Flux.fromIterable(errors.getFieldErrors())
                        .map(fieldError -> "Field " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                        .collectList()
                        .flatMap(list -> ServerResponse.badRequest().body(BodyInserters.fromObject(list)));

              } else {
                if (p.getDate() == null) {
                  p.setDate(new Date());
                }
                if (p.getClient().getDeudor() == true) {
                  log.error("Not allowed!");
                  try {
                    throw new Exception("Not allowed!");
                  } catch (Exception e) {
                    throw new RuntimeException(e);
                  }
                }
                if (p.getClient().getTypeClient().getName().equals(tp2)) {
                  log.error("Invalid client type");
                  try {
                    throw new Exception("Invalid client type");
                  } catch (Exception e) {
                    throw new RuntimeException(e);
                  }
                }
                if (p.getClient().getCtaAhorro()) {
                  log.error("Max account number reached!");
                  try {
                    throw new Exception("Max account number reached!");
                  } catch (Exception e) {
                    throw new RuntimeException(e);
                  }

                } else {
                  p.getClient().setCtaAhorro(true);
                }
                return service.saveProduct(p)
                        .flatMap(pr -> ServerResponse
                                .created(URI.create("/api/product/".concat(pr.getId())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .syncBody(pr)

                        );
              }

            });
  }

  public Mono<ServerResponse> createCtaCorriente(ServerRequest request) {
    Mono<Product> product = request.bodyToMono(Product.class);
    return product
            .flatMap(p -> {
              Errors errors = new BeanPropertyBindingResult(p, Product.class.getName());
              validator.validate(p, errors);

              if (errors.hasErrors()) {
                return Flux.fromIterable(errors.getFieldErrors())
                        .map(fieldError -> "Field " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                        .collectList()
                        .flatMap(list -> ServerResponse.badRequest().body(BodyInserters.fromObject(list)));

              } else {
                if (p.getDate() == null) {
                  p.setDate(new Date());
                }
                if (p.getClient().getDeudor() == true) {
                  log.error("Not allowed!");
                  try {
                    throw new Exception("Not allowed!");
                  } catch (Exception e) {
                    throw new RuntimeException(e);
                  }
                }
                if (p.getClient().getTypeClient().getName().equals(tp1)) {
                  if (p.getClient().getCtaCorriente() == 1) {
                    log.error("Max account number reached!");
                    try {
                      throw new Exception("Max account number reached!");
                    } catch (Exception e) {
                      throw new RuntimeException(e);
                    }
                  }
                }
                p.getClient().setCtaCorriente(p.getClient().getCtaCorriente() + 1);

              }
              return service.saveProduct(p)
                      .flatMap(pr -> ServerResponse
                              .created(URI.create("/api/product/".concat(pr.getId())))
                              .contentType(MediaType.APPLICATION_JSON)
                              .syncBody(pr)

                      );


            });
  }

  public Mono<ServerResponse> createPzoFijo(ServerRequest request) {
    Mono<Product> product = request.bodyToMono(Product.class);
    return product
            .flatMap(p -> {
              Errors errors = new BeanPropertyBindingResult(p, Product.class.getName());
              validator.validate(p, errors);

              if (errors.hasErrors()) {
                return Flux.fromIterable(errors.getFieldErrors())
                        .map(fieldError -> "Field " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                        .collectList()
                        .flatMap(list -> ServerResponse.badRequest().body(BodyInserters.fromObject(list)));

              } else {
                if (p.getDate() == null) {
                  p.setDate(new Date());
                }
                if (p.getClient().getDeudor() == true) {
                  log.error("Not allowed!");
                  try {
                    throw new Exception("Not allowed!");
                  } catch (Exception e) {
                    throw new RuntimeException(e);
                  }
                }
                if (p.getClient().getTypeClient().getName().equals(tp2)) {
                  log.error("Invalid client type");
                  try {
                    throw new Exception("Invalid client type");
                  } catch (Exception e) {
                    throw new RuntimeException(e);
                  }
                }
                if (p.getClient().getPlzFijo()) {
                  log.error("Max account number reached!");
                  try {
                    throw new Exception("Max account number reached!");
                  } catch (Exception e) {
                    throw new RuntimeException(e);
                  }

                } else {
                  p.getClient().setCtaAhorro(true);
                }
                return service.saveProduct(p)
                        .flatMap(pr -> ServerResponse
                                .created(URI.create("/api/product/".concat(pr.getId())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .syncBody(pr)

                        );
              }

            });
  }

  public Mono<ServerResponse> createCredPersonal(ServerRequest request) {
    Mono<Product> product = request.bodyToMono(Product.class);


    return product
            .flatMap(p -> {
              Errors errors = new BeanPropertyBindingResult(p, Product.class.getName());
              validator.validate(p, errors);

              if (errors.hasErrors()) {
                return Flux.fromIterable(errors.getFieldErrors())
                        .map(fieldError -> "Field " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                        .collectList()
                        .flatMap(list -> ServerResponse.badRequest().body(BodyInserters.fromObject(list)));

              } else {
                if (p.getDate() == null) {
                  p.setDate(new Date());
                }
                if (p.getClient().getDeudor() == true) {
                  log.error("Not allowed!");
                  try {
                    throw new Exception("Not allowed!");
                  } catch (Exception e) {
                    throw new RuntimeException(e);
                  }
                }
                if (p.getClient().getTypeClient().getName().equals(tp2)) {
                  log.error("Invalid client type");
                  try {
                    throw new Exception("Invalid client type");
                  } catch (Exception e) {
                    throw new RuntimeException(e);
                  }
                }
                if (p.getClient().getCredPersonal()) {
                  log.error("Max account number reached!");
                  try {
                    throw new Exception("Max account number reached!");
                  } catch (Exception e) {
                    throw new RuntimeException(e);
                  }

                } else {
                  p.getClient().setCredPersonal(true);
                }
                return service.saveProduct(p)
                        .flatMap(pr -> ServerResponse
                                .created(URI.create("/api/product/".concat(pr.getId())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .syncBody(pr)

                        );
              }

            });
  }


  public Mono<ServerResponse> createCredEmpresarial(ServerRequest request) {
    Mono<Product> product = request.bodyToMono(Product.class);

    return product
            .flatMap(p -> {
              Errors errors = new BeanPropertyBindingResult(p, Product.class.getName());
              validator.validate(p, errors);

              if (errors.hasErrors()) {
                return Flux.fromIterable(errors.getFieldErrors())
                        .map(fieldError -> "Field " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                        .collectList()
                        .flatMap(list -> ServerResponse.badRequest().body(BodyInserters.fromObject(list)));

              } else {
                if (p.getDate() == null) {
                  p.setDate(new Date());
                }
                if (p.getClient().getDeudor() == true) {
                  log.error("Not allowed!");
                  try {
                    throw new Exception("Not allowed!");
                  } catch (Exception e) {
                    throw new RuntimeException(e);
                  }
                }
                if (p.getClient().getTypeClient().getName().equals(tp1)) {
                  log.error("Invalid client type");
                  try {
                    throw new Exception("Invalid client type");
                  } catch (Exception e) {
                    throw new RuntimeException(e);
                  }
                }
                if (p.getClient().getCredEmpresarial() != null) {
                  p.getClient().setCredEmpresarial(p.getClient().getCredEmpresarial() + 1);
                }
                return service.saveProduct(p)
                        .flatMap(pr -> ServerResponse
                                .created(URI.create("/api/product/".concat(pr.getId())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .syncBody(pr)

                        );
              }

            });

  }

  public Mono<ServerResponse> createTC(ServerRequest request) {
    Mono<Product> product = request.bodyToMono(Product.class);

    return product
            .flatMap(p -> {
              Errors errors = new BeanPropertyBindingResult(p, Product.class.getName());
              validator.validate(p, errors);

              if (errors.hasErrors()) {
                return Flux.fromIterable(errors.getFieldErrors())
                        .map(fieldError -> "Field " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                        .collectList()
                        .flatMap(list -> ServerResponse.badRequest().body(BodyInserters.fromObject(list)));

              } else {
                if (p.getDate() == null) {
                  p.setDate(new Date());
                }
                if (p.getClient().getDeudor() == true) {
                  log.error("Not allowed!");
                  try {
                    throw new Exception("Not allowed!");
                  } catch (Exception e) {
                    throw new RuntimeException(e);
                  }
                }

                if (p.getClient().getTcPersonal() || p.getClient().getTcEmpresarial()) {
                  try {
                    throw new Exception("Max TC number reached!");
                  } catch (Exception e) {
                    throw new RuntimeException(e);
                  }

                }
                if (p.getClient().getTypeClient().getName().equals(tp1)) {
                  p.getClient().setTcPersonal(true);
                } else
                  p.getClient().setTcEmpresarial(true);
                return service.saveProduct(p)
                        .flatMap(pr -> ServerResponse
                                .created(URI.create("/api/product/".concat(pr.getId())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .syncBody(pr)

                        );
              }

            });
  }

  public Mono<ServerResponse> update(ServerRequest request) {

    Mono<Product> p = request.bodyToMono(Product.class);
    String id = request.pathVariable("id");

    return p
            .flatMap(pr -> ServerResponse
                    .created(URI.create("/api/product/".concat(id)))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(service.update(pr, id), Product.class)
            );
  }

  public Mono<ServerResponse> delete(ServerRequest request) {

    String id = request.pathVariable("id");

    return service.delete(id)
            .then(ServerResponse.noContent().build());
  }
}
