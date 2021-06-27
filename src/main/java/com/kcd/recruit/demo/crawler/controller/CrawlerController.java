package com.kcd.recruit.demo.crawler.controller;

import com.kcd.recruit.demo.crawler.response.RabbitResponse;
import com.kcd.recruit.demo.crawler.service.CrawlerService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CrawlerController {
  private final CrawlerService crawlerService;

  @RequestMapping(value="/rabbit-listener-start", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RabbitResponse> start() {
    crawlerService.startRabbitListener();
    return ResponseEntity.ok(RabbitResponse.builder().message("Start RabbitMQ Listener").build());
  }

  @RequestMapping(value="/rabbit-listener-stop", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RabbitResponse> stop() {
    crawlerService.stopRabbitListener();
    return ResponseEntity.ok(RabbitResponse.builder().message("Stop RabbitMQ Listner").build());
  }
}