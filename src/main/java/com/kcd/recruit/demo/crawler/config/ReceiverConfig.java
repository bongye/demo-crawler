package com.kcd.recruit.demo.crawler.config;

import com.kcd.recruit.demo.crawler.component.Receiver;
import com.kcd.recruit.demo.crawler.component.impl.BizNameReceiver;
import com.kcd.recruit.demo.crawler.component.impl.BizStsReceiver;
import com.kcd.recruit.demo.crawler.component.impl.DummyReceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceiverConfig {
  @Bean
  public Receiver bizStsReceiver() {
    return new BizStsReceiver();
  }

  @Bean
  public Receiver bizNameReceiver() {
    return new BizNameReceiver();
  }

  @Bean
  public Receiver dummyReceiver() {
    return new DummyReceiver();
  }
}
