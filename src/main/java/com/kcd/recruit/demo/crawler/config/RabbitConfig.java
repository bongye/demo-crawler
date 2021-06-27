package com.kcd.recruit.demo.crawler.config;

import com.kcd.recruit.demo.crawler.rabbit.Receiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  @Bean
  public Receiver receiver() {
    return new Receiver();
  }
}