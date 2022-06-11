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
![Architecture](https://user-images.githubusercontent.com/34090998/173180093-2cac0853-676d-4767-bc8d-b5e70f1f0db3.png)


## Goals

todo

## Key Issues

todo

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

todo

## License

[MIT LICENSE](https://github.com/Coffee-Street/strada/blob/master/LICENSE)

## Security

### Secret Security

[![GitGuardian scan](https://github.com/Coffee-Street/strada/actions/workflows/gg-shield-action.yml/badge.svg)](https://github.com/Coffee-Street/strada/actions/workflows/gg-shield-action.yml)

### Open Source License

[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fwnsgml972%2Fstrada.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fwnsgml972%2Fstrada?ref=badge_large)
