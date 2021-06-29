# KCD 채용 2차 프로젝트

## 빌드 및 실행

openjdk8, docker는 설치되었다고 가정


**Build** 
```
$ ./mvnw install -f pom.xml
```
**Run on docker**
```
$ docker compose build
$ docker compose up
```

## 동작 확인
아래의 HTTP 요청들은 test.http 파일에서 모두 수행해볼 수 있습니다. ([vscode rest client plugin](https://marketplace.visualstudio.com/items?itemName=humao.rest-client) 설치 필요)

**Activate/Deactivate rabbitmq listener**
- rabbitmq listener autostartup 설정을 꺼두어서 activate 시켜야 listener들이 동작합니다.
```
# Activate
GET http://localhost:8080/rabbit-listener-start
# Deactivate
GET http://localhost:8080/rabbit-listener-stop
```

**Send Message to rabbitmq**
- 총 3개의 큐를 만들어 두었습니다. 아래 요청으로 메시지를 보내보면 spring boot log 상에 스크래핑 결과들이 출력됩니다.
  - queue1 : 휴폐업조회
  - queue2 : 상호조회
  - queue3 : 더미 (메시지만 출력)
```
# 휴폐업조회 큐로 메시지 전송
POST http://localhost:15672/api/exchanges/crawler.vhost/amq.default/publish
Authorization: Basic guest guest
Content-Type: application/json

{
  "properties": {},
  "routing_key": "queue1",
  "payload": "1168115020",
  "payload_encoding":"string"
}

# 상호조회 큐로 메시지 전송
POST http://localhost:15672/api/exchanges/crawler.vhost/amq.default/publish
Authorization: Basic guest guest
Content-Type: application/json

{
  "properties": {},
  "routing_key": "queue1",
  "payload": "1168115020",
  "payload_encoding":"string"
}

# 더미 큐로 메시지 전송
POST http://localhost:15672/api/exchanges/crawler.vhost/amq.default/publish
Authorization: Basic guest guest
Content-Type: application/json

{
  "properties": {},
  "routing_key": "queue3",
  "payload": "dummy message",
  "payload_encoding":"string"
}
```