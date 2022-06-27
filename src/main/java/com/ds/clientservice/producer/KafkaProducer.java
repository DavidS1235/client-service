package com.ds.clientservice.producer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProducer {

  private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

  private final KafkaTemplate<String,String> kafkaTemplate;


  public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String message){
    log.info("Producing message from client", message);
    this.kafkaTemplate.send("Bootcoin request", message);
  }

}
