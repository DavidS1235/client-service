package com.ds.clientservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {


  @Bean(name = "product")
  public WebClient registerWebClient() {
    return WebClient.create("http://localhost:8005/api/product");
  }

  @Bean(name = "TypeProduct")
  public WebClient registerWebClientTp() {
    return WebClient.create("http://localhost:8005/api/TypeProduct");
  }

}
