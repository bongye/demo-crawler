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
public class BizStsReceiver implements Receiver {
  private final String url =
      "https://teht.hometax.go.kr/wqAction.do?actionId=ATTABZAA001R08&screenId=UTEABAAA13&popupYn=false&realScreenId=";

  public void receive(Message message) {
    try {
      String bizno = new String(message.getBody(), "UTF-8");
      log.info("Received message : {}", bizno);

      getBizSts(bizno);
    } catch (IOException e) {
      log.error("Unexpected exception occurs", e);
    }
  }

  /**
   * 사업자번호로 휴폐업상태 스크래핑
   * 
   * @param bizno
   * @throws IOException
   */
  public void getBizSts(String bizno) throws IOException {
    if (bizno == null || bizno.length() != 10) {
      log.warn("bizno is not valid : {}", bizno);
    }

    String inputTemplate = StreamUtils.copyToString(
        new ClassPathResource("biz-sts-in.xml").getInputStream(), StandardCharsets.UTF_8);
    String body = String.format(inputTemplate, bizno, bizno.substring(3, 5));
    log.debug("request body : {}", body);

    Document document = Jsoup.connect(url).header("Content-Type", "application/xml; charset=UTF-8")
        .requestBody(body).post();
    log.debug("fetched document : {}", document.html());
    String bizSts = document.select("trtCntn").text();
    log.info("biz status of {} : {}", bizno, bizSts);
  }
}
