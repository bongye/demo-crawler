package com.kcd.recruit.demo.crawler.service;

import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrawlerService {
  private final RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;

  public void startRabbitListener() {
    if (this.rabbitListenerEndpointRegistry.isRunning()) {
      log.info("rabbit endpoint registry already running.");
      return;
    }
    this.rabbitListenerEndpointRegistry.start();
  }

  public void stopRabbitListener() {
    if (!this.rabbitListenerEndpointRegistry.isRunning()) {
      log.info("rabbit endpoint registry already stopped.");
      return;
    }
    this.rabbitListenerEndpointRegistry.stop();
  }
}
