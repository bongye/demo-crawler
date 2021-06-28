package com.kcd.recruit.demo.crawler.component;

import org.springframework.amqp.core.Message;

public interface Receiver {
  public void receive(Message message);
}