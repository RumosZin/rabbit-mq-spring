# Spring boot with RabbitMQ

## Docker RabbitMQ 서버 

```shell
docker run -d --name rabbitmq -p
5672:5672 -p 15672:15672 --restart=unless-stopped rabbitmq:management
```

- port 5672 : RabbitMQ Broker
- port 15672 : RabbitMQ 웹 관리 콘솔 (application.yml의 username, password 참고)

</br>

## RabbitMQ 웹 콘솔
- ```localhost:15672```에 접속해서 관련 설정을 확인하고 조작할 수 있다.
- Direct Exchange를 확인하기 위해, application.yml에 설정된 (queue, key)와는 다른 (queue, key)를 만든다.

<img width="1224" alt="image" src="https://github.com/user-attachments/assets/8046dfa3-c083-405d-bb79-8d29d98c5c73">

</br>

## Spring boot 프로젝트 실행

- Application을 실행하고 ```http://localhost:8080/send/message```로 요청을 보낸다.
- RabbitTemplate이 제공하는 ```convertAndSend``` 메서드를 이용한 ```sendMessage``` 서비스 메서드의 동작을 확인한다. **Direct Exchange 전략을 사용하므로, exchange는 application.yml에 등록된 routing key와 일치하는 queue로 바인딩한다.**

<img width="980" alt="image" src="https://github.com/user-attachments/assets/2737f74a-266e-4175-ad18-8ea9ebc22ecb">

</br>
</br>

```sample-queue``` : routing key와 일치하는 queue, 메시지를 받아서 처리했음을 알 수 있다.

<img width="506" alt="image" src="https://github.com/user-attachments/assets/7ed8ae65-2fb0-4049-934d-07d49f7d91c9">

</br>

```test-queue``` : routing key와 일치하지 않는 queue, routing key가 달라 test-queue에 바인딩되지 않으므로, 처리한 메시지가 없다.

<img width="714" alt="image" src="https://github.com/user-attachments/assets/3b58665d-1a9c-4fbf-a933-dd1caccfb3a7">

