# 📖 Spring MVC 학습 테스트

- Spring MVC 기능을 학습하기 위한 테스트 프로젝트

## 테스트 목록
- mapping: 요청을 메서드에 연결하는 부분 학습 테스트
- handler: 요청을 처리하는 부분 학습 테스트
- exception: 요청에 대한 예외처리 부분 학습 테스트
  
## 진행 가이드
- 클론 받기 `git clone https://github.com/next-step/spring-learning-test.git`
- 체크아웃 브랜치 `git checkout mvc`
- 뼈대 코드로 제공된 프로젝트에서 테스트를 성공 시키기 위해 테스트 코드의 주석을 참고하여 프로덕션 코드를 구현하기
- 테스트는 [mvc](https://github.com/next-step/spring-learning-test/tree/mvc/src/test/java/nextstep/helloworld/mvc) 디렉토리 하위에 위치
- [학습 테스트 활용 방법](https://github.com/next-step/spring-learning-test/blob/mvc/README.md#%ED%95%99%EC%8A%B5-%ED%85%8C%EC%8A%A4%ED%8A%B8-%ED%99%9C%EC%9A%A9-%EB%B0%A9%EB%B2%95)를 참고하여 학습 및 기능 구현
- 완성 브랜치 [mvc-sample](https://github.com/next-step/spring-learning-test/tree/mvc-sample)를 참고해서 진행해도 좋음 `git checkout mvc-sample`

## 학습 테스트 활용 방법
---
### 1. 테스트 수행

- 별도의 코드 작업 없이 우선 테스트를 수행 시키기

<p align="center">
  <img src="https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/e3be5f9a8e274e11b06b93052ff81bcd" width="70%">
</p>

---
### 2. 터미널 로그 확인

- 테스트 수행 결과를 터미널에서 확인
- 로그를 바탕으로 다음 작업을 진행

<p align="center">
  <img src="https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/e14abe8cdbcd42898f214908b355125a" width="70%">
</p>

---
### 3. 레퍼런스 문서 확인

- 학습이 필요하면 메서드 주석에 안내된 링크를 통해 레퍼런스 문서를 확인
- 링크가 없거나 키워드가 있는 경우 구글링을 통해 키워드에 대한 사전 지식을 쌓고 문서에서 해당 키워드 검색으로 문서 찾기

<p align="center">
  <img src="https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/977aea6534124294947635df34d243bf" width="70%">
</p>

---
### 4. 코드 구현

- 문서에서 안내한 방법을 코드로 직접 구현
- 이해가 되지 않아도 우선 수행시켜 테스트를 성공 시켜보기
- 한번에 이해하기 쉽지 않기 때문에 우선 동작하는 코드를 구현한 뒤 원리를 이해하는 순서로 학습하는 것을 추천

<p align="center">
  <img src="https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/346e1141fb6b492eb54bfff1da713eff" width="70%">
</p>

---
## 참고 레퍼런스 모음

### mapping
- [Request Mapping](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-requestmapping)
- [URI patterns](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-requestmapping-uri-templates)
- [Media Types - produces](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-requestmapping-produces)
- [Media Types - consumes](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-requestmapping-consumes)
- [Parameters, headers](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-requestmapping-params-and-headers)

### handler
- [Method Arguments](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments)
- [Return Values](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-return-types)

### exception
- [Exceptions](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-exceptionhandler)
