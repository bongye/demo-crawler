package com.kcd.recruit.demo.crawler.component.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import com.kcd.recruit.demo.crawler.component.Receiver;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.amqp.core.Message;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BizNameReceiver implements Receiver {
  private final String url = "https://teht.hometax.go.kr/wqAction.do?actionId=ATERDAAA001R02&screenId=UTERDAAA03&popupYn=false&realScreenId=";

  public void receive(Message message) {
    try {
      String bizno = new String(message.getBody(), "UTF-8");
      log.info("Received message : {}", bizno);

      getBizName(bizno);
    } catch (IOException e) {
      log.error("Unexpected exception occurs", e);
    }
  }

  public void getBizName(String bizno) throws IOException {
    if (bizno == null || bizno.length() != 10) {
      log.warn("bizno is not valid : {}", bizno);
    }

    String inputTemplate = StreamUtils.copyToString(
        new ClassPathResource("biz-name-in.xml").getInputStream(), StandardCharsets.UTF_8);
    String body = String.format(inputTemplate, bizno, bizno.substring(3, 5));
    log.debug("request body : {}", body);

    Document document = Jsoup.connect(url).header("Content-Type", "application/xml; charset=UTF-8")
        .requestBody(body).post();
    log.debug("fetched document : {}", document.html());
    String bizName = document.select("txprNm").text();
    log.info("biz name of {} : {}", bizno, bizName);
  }
}
