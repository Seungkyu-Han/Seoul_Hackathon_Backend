# ![CoBo](https://team-cobo.site/public/image/favicon/favicon-32x32.png) 서울 AIOT 해커톤

- [목적: 한강공원 내 생활안전·환경보호 등 시민의 불편 해소 및 다양한 편의 제공을 위해 사물인터넷(IoT) 기술을 사용하여 한강공원에 적용 가능한 아두이노 활용 시제품 개발](http://seoulhackathon.kr/)

- 개발 기간: 23.11.02 ~ 23.11.03

- [홈페이지 링크 - 호스팅 중지](https://seoul-aiot-jiozx.run.goorm.site/Reverse.html)

- [프론트엔드 깃허브](https://github.com/JongHyeok-Park/Seoul_Hackathon_Frontend)

- [백엔드 깃허브](https://github.com/Seungkyu-Han/Seoul_Hackathon_Backend)

## 목차

- [1. 프로젝트 계획](#프로젝트-계획)

- [2. 구성원 및 업무 분담](#구성원-및-업무-분담)

- [3. 페이지 설계](#페이지-설계)

- [4. DB 모델링](#DB-모델링)

- [5. 핵심 기능](#핵심-기능)

- [6. 사용한 기술](#사용한-기술)

- [7. 느낀 점](#느낀-점)

## 프로젝트 계획

1. 모니터링
   - 과속, 역주행 발생시 로그를 띄워 실시간으로 클라이언트가 확인 가능
   - 시간대, 위치, 요일 통계를 확인할 수 있음
2. 메일 알림
   - 메일 알림을 켜놓으면 로그인 가입자 메일로 통계 데이터를 받을 수 있음

## 구성원 및 업무 분담

김병찬
- PM
- 아두이노 설계 및 제작

박종혁
- 프론트엔드
- 퍼블리싱 보조

차유진
- UI 디자인
- 퍼블리싱

한승규
- 백엔드
- DB 설계
- 배포 환경 구축


## 페이지 설계

- [페이지 설계.pdf](https://www.figma.com/file/b3gMVx0938FFGuOPPAywch/2023_%EC%84%9C%EC%9A%B8AIoT%ED%95%B4%EC%BB%A4%ED%86%A4?type=design&node-id=144%3A790&mode=design&t=y7i8PGZWnFBG82AT-1)

## DB 모델링
<img src = "https://cobo-blog.s3.ap-northeast-2.amazonaws.com/img/Seoul-AIOT-db.png" alt="db-model" width="900">

## 핵심 기능

### 카카오 로그인

> 카카오 로그인을 통해 사용자의 정보를 얻고 회원이라면 토큰들을 쿠키로 저장

```
- kakao Developer에서 제공하는 로그인을 통해 코드를 받아 옴

- 프론트에서 해당 코드를 파라미터에 넣어 다시 서버의 API를 호출함

- 해당 사용자의 id가 있다면, 로그인에 성공하고 없다면 로그인에 실패

- 사용자의 AccessToken과 RefreshToken을 JWT를 이용하여 생성한 후 발급

- 프론트에서는 받은 AccessToken과 RefreshToken을 쿠키를 이용하여 지정된 시간만큼 저장함

- 인증이 필요한 API에는 AccessToken을 가져와 Authorization에 넣고, AccessToken이 만료되었다면 RefreshToken을 이용하여 재발급

- Authorization 헤더를 확인한 후 유효한 토큰이면, 해당 토큰에서 사용자의 정보를 추출하고 유효하지 않다면 403 에러를 반환
```

### 실시간 데이터 모니터링

> 아두이노에서 전송한 데이터를 소켓을 활용하여 실시간으로 모니터링
```
- 아두이노에서 센서가 감지될 때마다 서버의 Rest API를 사용하여 서버로 데이터를 전송

- 서버에서 해당 데이터를 받으면, 데이터베이스에 저장하며 이벤트리스너를 사용해서 소켓에 구현한 메서드를 호출

- 해당 메서드를 사용하여 소켓의 리스트에 추가된 모든 세션에 소켓으로 데이터를 전송

- 웹 소켓에 연결하여 웹 소켓 변수(객체)에 할당한 뒤 onmessage 메소드를 통해 메시지가 올 때마다 데이터를 처리함

- 전달 받은 데이터를 html 형식의 문자열에 저장한 뒤 해당 테이블 템플릿을 테이블 위치 하단에 추가하며 Scroll위치를 해당 Table의 height. 즉, Table의 가장 아래로 스크롤 되도록 설정함
```

### 메일 알림

> SMTP와 스케줄러를 사용하여 정해진 시간마다 메일을 발송
```
- 구글 SMTP 서비스를 이용하여, 해당 메일을 이용해 메일을 전송

- 스프링 스케줄러를 이용하여, 정해진 시간마다 데이터베이스에 저장된 메일들로 메일을 발송

- 작성이 되어 있는 HTML 코드에 동적으로 내용을 추가하여, 보고서를 사용자들에게 발송
```
발송된 메일
<img src = "https://cobo-blog.s3.ap-northeast-2.amazonaws.com/img/Seoul-AIOT-mail.png" alt="db-model" width="800" height="650">


## 사용한 기술
| 범위 |                                                                                                                                                                                                                     스택                                                                                                                                                                                                                                                                                                                                                                                   |
| :-------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| Front End | <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1566995514/noticon/jufppyr8htislboas4ve.png" alt="" height="50"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1678672480/noticon/qblxu9uo0uuitucuzhjy.png" alt="" height="50"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1567008394/noticon/ohybolu4ensol1gzqas1.png" alt="" height="50"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1567128552/noticon/mksvojnxnqtvdwrhttce.png" alt="" height="50"/> |
| Back End  |                            <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1567008187/noticon/m4oad4rbf65fjszx0did.png" alt="" height="50"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1635384696/noticon/t2v5vtq6gp7d0bgspmyh.png" alt="" height="50"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1603423163/noticon/az0cvs28lm7gxoowlsva.png" alt="" height="50"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1687307488/noticon/o9lxyva5z8zbwyeaxers.png" alt="" height="50"/>                            |
| Design  |                            <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1608448196/noticon/a0fgk99dgqtyrwwmqsbt.png" alt="" height="50"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                   |
|  DevOps   |                                                                                                  <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1581824154/noticon/a5wsihnorfumuzu7ewdd.png" alt="" height="50"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <img src="https://media.licdn.com/dms/image/D560BAQH0TLan23PvIQ/company-logo_200_200/0/1691549098754?e=2147483647&v=beta&t=OvBN0FX58BBE1MmuztyHW58HnFL6lfBWD9THjT-2_RQ" alt="" width="50"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; |


## 느낀 점

**박종혁**
- 해커톤 특성상 짧은 시간에 기능 개발을 해야해서 생각한 것만큼 많은 기능들을 넣지 못했지만, 이전에 기술블로그 개발 때 해본 카카오 로그인은 한번 해본 경험이 있어 쉽고 빠르게 개발할 수 있었다. 그래서 프로젝트들을 해보면서 다양한 기능들을 개발해봐야 실제 작접 시간을 많이 줄일 수 있다는 것을 깨달았다.
- 웹소켓을 처음 다뤄본 프로젝트였는데 소켓 처음 연결시 전달되는 데이터 처리에서 조금 난관을 겪었고, 백엔드 담당자와 Response에 대해 확실하게 소통하여 설계해야 함을 느꼈다.
- 디자이너가 디자인 한 디자인 시안을 바탕으로 레이아웃 퍼블리싱을 보조했는데, 디자이너가 제작한 디자인 시안이다 보니 레이아웃을 잡는게 생각보다 어려웠다. 또 시간이 부족해 반응형 페이지까지 제작하지 못하였다.

**차유진**
- UI를 정식으로 디자인한 경험이 처음이어서 부족한 부분이 많이 보이는 것 같다. 레이아웃의 여백 설정, 폰트 사이즈, 행간, 자간의 기준을 몰라서 디자인을 연습할 때 했던 기억을 참고했다. 다음 디자인을 할 때에는 레이아웃과 타이포그래피의 기준(여백, 행간, 자간, 폰트사이즈, 폰트강조)을 공부해서 반영해야겠다.
- UI에서 항목을 배치할 때 어떤 항목을 강조하고, 어떤 구성으로 배치해야하는 것에도 아쉬운 점이 있었다. 이번에 만든 서비스에서 제공할 내용이 많지 않은 것이 사실이었지만, 개인적인 시각으로 보았을 때 UI가 빈 것 같다는 느낌을 받았다. 이를 어떻게 해야할지 많이 고민했는데, 항목 배치를 통해 어느 정도 해결할 수 있었다.
- 실제로 브라우저에 디자인이 보여질 때 고려해야할 부분을 알 수 있었다. 피그마로 디자인할 때는 화면에서 원래 보여지는 부분을 생각하지 못했는데 이번 기회를 통해 고려해야 할 부분을 알았다.
- 퍼블리싱같은 경우에는 정말 처음이어서 레이아웃 구성이 어려웠다. 박종혁 팀원이 많은 도움을 줘서 시작과 마무리를 잘 할 수 있었던 것 같다.
- 이번에는 부족한 점이 많이 보이지만, 다음번 디자인에는 이들을 보완한 결과물을 내도록 해야겠다. 그래도 처음으로 하나의 UI를 완성했다는 것에 의미가 있었던 경험이었다.

**한승규**
- 해커톤을 처음 경험해보며, 진행과정과 중점으로 생각해야 하는 부분을 알 수 있게 되었습니다.
- 기존에 작업하던 자바가 아닌 코틀린을 사용해보며, 왜 최근에 스프링부트를 자바가 아닌 코틀린으로 개발하는 지 느낄 수 있었습니다.
- Rest API를 사용하여 이벤트 리스너를 거친 후 소켓으로 데이터를 전송하는 방법을 터득하게 되었습니다.
- SMTP 서비스와 스프링 스케줄러를 활용하여 주기적으로 알림 메일을 발송하는 방법을 터득하게 되었습니다.