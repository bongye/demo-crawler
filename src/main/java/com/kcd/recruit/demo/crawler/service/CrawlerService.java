package com.kcd.recruit.demo.crawler.service;

import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrawlerService {
  private final RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;

  public void startRabbitListener () {
    this.rabbitListenerEndpointRegistry.start();
  }

  public void stopRabbitListener() {
    this.rabbitListenerEndpointRegistry.stop();
  }
}