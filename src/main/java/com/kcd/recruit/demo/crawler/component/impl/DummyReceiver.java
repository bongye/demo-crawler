package com.kcd.recruit.demo.crawler.component.impl;

import java.io.UnsupportedEncodingException;

import com.kcd.recruit.demo.crawler.component.Receiver;

import org.springframework.amqp.core.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DummyReceiver implements Receiver {
  public void receive(Message message) {
    try {
      String m = new String(message.getBody(), "UTF-8");
      log.info("Received message : {}", m);
    } catch (UnsupportedEncodingException e) {
      log.error("UnsupportedEncodingException", e);
    }
  } 
}
