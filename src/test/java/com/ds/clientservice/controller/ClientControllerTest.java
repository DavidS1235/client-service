package com.ds.clientservice.controller;

import com.ds.clientservice.business.service.ClientService;
import com.ds.clientservice.document.Client;
import com.ds.clientservice.document.Person;
import com.ds.clientservice.document.TypeClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@Slf4j
@WebFluxTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {

//  @InjectMocks
//  private ClientController clientController;

  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  ClientService clientService;

  Client client;

//  @Mock
//  private ClientService clientService;
//
//  private List<Client> clients;
//
//  @BeforeAll
//  static void setupBeforeAll(){
//    log.info("ClientControllerTest BeforeEach");
//  }
//
//  @BeforeEach
//  void setup(){
//    log.info("ClientControllerTest BeforeEach");
//    MockitoAnnotations.openMocks(this);
//
//    clients = new ArrayList<>();
//
//    clients.add(new Client("ID0001","C0001", "P0001", "Julio", new Person(), new TypeClient(), false, false, false, 0 , 0 , false, false));
//    clients.add(new Client("ID0002","C0002", "P0002", "Maria", new Person(), new TypeClient(), false, false, false, 0 , 0 , false, false));
//
//  }

  @BeforeEach
  void before(){
    client = new Client("C0001","CC0001", "P0001", "Juan Solis", new Person(), new TypeClient(), false, false, false, 0, 0, false, false);
  }

  @Test
  public void testFindAllClients() {
//    when(clientService.findAll()).thenReturn((Flux<Client>) Stream
//            .of(new Client("ID0001","C0001", "P0001", "Julio", new Person(), new TypeClient(), false, false, false, 0 , 0 , false, false))
//            .collect(Collectors.toList()));
    Flux<Client> responseBody = webTestClient.get()
          .uri("api/client")
          .exchange()
          .returnResult(Client.class)
          .getResponseBody();

    StepVerifier.create(responseBody)
            .expectSubscription()
            .expectNext(new Client("C0001","CC0001", "P0001", "Juan Solis", new Person(), new TypeClient(), false, false, false, 0, 0, false, false))
            .verifyComplete();
  }

}
