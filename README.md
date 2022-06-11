# Strada api server

[![Build Test Pipeline](https://github.com/Coffee-Street/strada/workflows/build-test-pipeline/badge.svg)](https://github.com/Coffee-Street/strada/actions)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fwnsgml972%2Fstrada.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fwnsgml972%2Fstrada?ref=badge_shield)

## Quick Start

```shell script
# quick start strada coffee api server
git clone https://github.com/wnsgml972/strada.git
docker-compose up
```

## Server Structure

![Architecture](https://user-images.githubusercontent.com/34090998/173180518-cb23fa97-5e5d-42fa-9094-96970f864f11.png)

## Client

- [strada-ios](https://github.com/Coffee-Street/strada-ios)
- [strada-flutter](https://github.com/Coffee-Street/strada-flutter)

## Goals

* 카페에서 사용할 수 있는 모바일 어플리케이션과 API 서버를 개발하여 상품 조회, 주문, 결제 등의 서비스를 제공한다.
* JPA를 깊게 이해하고 사용하여 비효율적인 IO를 발생시키지 않으며, 효율적인 쿼리가 동작하도록 구현한다.
* Container 가상화, DB 이중화를 통해 고가용성을 구현한다.
* 단위테스트, Tech Spec 문서화, Code Review를 통해 높은 퀄리티의 소프트웨어를 구현한다.

## Key Issues

* [Kakao 결제 API를 호출한 뒤 Deeplink를 통해 앱의 결제 화면으로 돌아오는 과정](https://github.com/Coffee-Street/Document/blob/master/tech-spec/KakaoPaymentTechSpec.md)

## Done

- 버전관리
- 문서화
- Unit Test, Integration Test 작성
- Github Action 활용한 CI 구축
  - 빌드/테스트
  - GitGuardian 을 활용한 자동화된 Security Check
  - FOSSA 활용한 자동화된 Open Source License Check
  - PR Template, 오래된 PR Check
  - AI를 활용한 자동 코드 리뷰
- Github Secrets 활용한 Secret 구현
- Docker 이용한 CD 구축
- MySQL Replication Primary / Secondary 로 데이터베이스 이중화
- RDB Transcation이 보장된 상품, 메뉴, 주문, 유저 관리, 배너 API 구현
- JWT 활용한 인증/인가 구현
- 스레드 풀 관리, `@Async`, Spring Application Event 활용하여 특정 도메인의 역할과 책임을 명확히 분리한 Event Driven 구현
- kakaopay, webclient 활용한 일관성 보장된 상품 구매 구현
- Observability 확보를 위한 Metrics 방출
  1. spring actuator exporter(web endpoint) →
  2. promethous server scrap →
  3. grafana web ui


## Purchase Product Flow (feat. kakaopay)

<img src = "https://github.com/Coffee-Street/Document/blob/master/assets/purchase_product_flow.png" width="80%" height="80%">

## DB ERD

![ERD](https://github.com/Coffee-Street/Document/blob/master/assets/2022-0611-strada_erd.png)

## License

[MIT LICENSE](https://github.com/Coffee-Street/strada/blob/master/LICENSE)

## Security

### Secret Security

[![GitGuardian scan](https://github.com/Coffee-Street/strada/actions/workflows/gg-shield-action.yml/badge.svg)](https://github.com/Coffee-Street/strada/actions/workflows/gg-shield-action.yml)

### Open Source License

[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fwnsgml972%2Fstrada.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fwnsgml972%2Fstrada?ref=badge_large)
