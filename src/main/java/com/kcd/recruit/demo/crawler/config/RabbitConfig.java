package com.kcd.recruit.demo.crawler.config;

import com.kcd.recruit.demo.crawler.component.Receiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {
  private final Receiver bizStsReceiver;
  private final Receiver dummyReceiver;

  @RabbitListener(id="receiver1", queues="queue1")
  public void process1(Message message) {
    _do(message, bizStsReceiver);
  }

  @RabbitListener(id="receiver2", queues="queue2") 
  public void process2(Message message){
    _do(message, dummyReceiver);
  }

  @RabbitListener(id="receiver3", queues="queue3")
  public void process3(Message message) {
    _do(message, dummyReceiver);
  }

  private void _do(Message message, Receiver receiver) {
    receiver.receive(message);
  }
}