package com.kcd.recruit.demo.crawler.controller;

import com.kcd.recruit.demo.crawler.response.RabbitResponse;
import com.kcd.recruit.demo.crawler.service.CrawlerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CrawlerController {
  private final CrawlerService crawlerService;

  @GetMapping(value = "/rabbit-listener-start")
  public ResponseEntity<RabbitResponse> start() {
    crawlerService.startRabbitListener();
    return ResponseEntity.ok(RabbitResponse.builder().message("Start RabbitMQ Listener").build());
  }

  @GetMapping(value = "/rabbit-listener-stop")
  public ResponseEntity<RabbitResponse> stop() {
    crawlerService.stopRabbitListener();
    return ResponseEntity.ok(RabbitResponse.builder().message("Stop RabbitMQ Listner").build());
  }
}