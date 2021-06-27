package com.kcd.recruit.demo.crawler.rabbit;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import lombok.extern.slf4j.Slf4j;

@RabbitListener(queues = "${spring.rabbitmq.task-queue}")
@Slf4j
public class Receiver {
  @RabbitHandler
  public void process(byte[] bytes) {
    try {
      String message = new String(bytes, "UTF-8");
      log.info("message received : {}", message);
    } catch (UnsupportedEncodingException e) {
      log.error("message received[on bytes] : {}", bytes);
      log.error("messsage encoding not supported on UTF-8", e);
    }
  }
}