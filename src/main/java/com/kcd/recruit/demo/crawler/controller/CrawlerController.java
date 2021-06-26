package com.kcd.recruit.demo.crawler.controller;

import com.kcd.recruit.demo.crawler.response.RabbitResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlerController {
  @RequestMapping(value="/rabbit-listener-start", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RabbitResponse> start() {
    return ResponseEntity.ok(RabbitResponse.builder().message("Start RabbitMQ Listener").build());
  }

  @RequestMapping(value="/rabbit-listener-stop", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RabbitResponse> stop() {
    return ResponseEntity.ok(RabbitResponse.builder().message("Stop RabbitMQ Listner").build());
  }
}