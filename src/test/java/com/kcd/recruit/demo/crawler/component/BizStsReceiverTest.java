package com.kcd.recruit.demo.crawler.component;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BizStsReceiverTest {
  @Autowired
  private Receiver bizStsReceiver;

  @Test
  public void testReceive() {
    String bizno = "1168115020";
    Message message = MessageBuilder.withBody(bizno.getBytes()).build();
    bizStsReceiver.receive(message);
  }  
}
